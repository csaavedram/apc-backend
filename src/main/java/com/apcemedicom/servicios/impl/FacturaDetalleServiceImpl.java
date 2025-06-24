package com.apcemedicom.servicios.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apcemedicom.modelo.Factura;
import com.apcemedicom.modelo.FacturaDetalle;
import com.apcemedicom.modelo.ProductoSerie;
import com.apcemedicom.repositorios.FacturaDetalleRepository;
import com.apcemedicom.servicios.FacturaDetalleService;
import com.apcemedicom.servicios.ProductoSerieService;
import com.apcemedicom.dto.FacturaDetalleDashboardDTO;
import com.apcemedicom.dto.GarantiaDTO;

@Service
public class FacturaDetalleServiceImpl implements FacturaDetalleService {
  @Autowired
  private FacturaDetalleRepository facturaDetailsRepository;
  
  @Autowired
  private ProductoSerieService productoSerieService;  @Override
  @Transactional
  public FacturaDetalle agregarDetalleFactura(FacturaDetalle facturaDetails) {
    try {
      System.out.println("� INICIANDO agregarDetalleFactura");
      System.out.println("📋 FacturaDetalle recibido:");
      System.out.println("- cantidad: " + facturaDetails.getCantidad());
      System.out.println("- precioUnitario: " + facturaDetails.getPrecioUnitario());
      System.out.println("- precioTotal: " + facturaDetails.getPrecioTotal());
      System.out.println("- numerosSerie (String): '" + facturaDetails.getNumerosSerie() + "'");
      System.out.println("- tipoServicio: " + facturaDetails.getTipoServicio());
      if (facturaDetails.getProducto() != null) {
        System.out.println("- productoId: " + facturaDetails.getProducto().getProductoId());
      }
      if (facturaDetails.getFactura() != null) {
        System.out.println("- facturaId: " + facturaDetails.getFactura().getFacturaId());
      }
      
      // Guardar el detalle de factura
      FacturaDetalle resultado = facturaDetailsRepository.save(facturaDetails);
      System.out.println("✅ FacturaDetalle guardado con ID: " + resultado.getFacturaDetalleId());
      
      // Si hay números de serie como String y hay producto, procesarlos
      if (facturaDetails.getNumerosSerie() != null && 
          !facturaDetails.getNumerosSerie().trim().isEmpty() && 
          facturaDetails.getProducto() != null) {
          
        System.out.println("🔢 Procesando números de serie desde String: " + facturaDetails.getNumerosSerie());
        
        // Dividir los números de serie por comas
        String[] numerosArray = facturaDetails.getNumerosSerie().split(",");
        List<ProductoSerie> seriesAsignadas = new ArrayList<>();
        
        for (String numeroSerieStr : numerosArray) {
          String numeroSerie = numeroSerieStr.trim();
          if (!numeroSerie.isEmpty()) {
            System.out.println("🔍 Buscando serie: " + numeroSerie);
            
            ProductoSerie serie = productoSerieService.buscarPorNumeroSerie(numeroSerie);
            if (serie != null) {
              System.out.println("✅ Serie encontrada: " + numeroSerie);
              
              // Asignar la serie a este detalle de factura
              serie.setFacturaDetalle(resultado);
              ProductoSerie serieActualizada = productoSerieService.actualizarProductoSerie(serie);
              seriesAsignadas.add(serieActualizada);
              
              System.out.println("🔗 Serie " + numeroSerie + " asignada al FacturaDetalle " + resultado.getFacturaDetalleId());
            } else {
              System.out.println("⚠️ Serie no encontrada: " + numeroSerie);
            }
          }
        }
        
        if (!seriesAsignadas.isEmpty()) {
          resultado.setNumerosSerieAsignados(seriesAsignadas);
          System.out.println("✅ Total series asignadas: " + seriesAsignadas.size());
        }
      }
      
      System.out.println("✅ Proceso agregarDetalleFactura completado");
      System.out.println("- facturaDetalleId final: " + resultado.getFacturaDetalleId());
      System.out.println("- numerosSerie final: " + resultado.getNumerosSerie());
      System.out.println("- cantidad guardada: " + resultado.getCantidad());
      System.out.println("- precioUnitario guardado: " + resultado.getPrecioUnitario());
      System.out.println("- precioTotal guardado: " + resultado.getPrecioTotal());
      
      return resultado;
    } catch (Exception e) {
      System.err.println("❌ Error en agregarDetalleFactura: " + e.getMessage());
      e.printStackTrace();
      throw e;
    }
  }
    @Override
  @Transactional
  public FacturaDetalle agregarDetalleFacturaConSeries(FacturaDetalle facturaDetails, List<String> numerosSerie) {
    // Primero guardamos el detalle de factura
    FacturaDetalle detalleGuardado = facturaDetailsRepository.save(facturaDetails);
    
    // Si el producto requiere número de serie y se proporcionaron números
    if (facturaDetails.getProducto().getRequiereNumeroSerie() && numerosSerie != null && !numerosSerie.isEmpty()) {
      // Validar que la cantidad de números de serie coincida con la cantidad facturada
      if (numerosSerie.size() != facturaDetails.getCantidad()) {
        throw new RuntimeException("La cantidad de números de serie debe coincidir con la cantidad facturada");
      }
      
      // Asignar series específicas
      List<ProductoSerie> seriesAsignadas = new ArrayList<>();
      for (String numeroSerie : numerosSerie) {
        ProductoSerie serie = productoSerieService.buscarPorNumeroSerie(numeroSerie);
        if (serie == null) {
          throw new RuntimeException("Número de serie no encontrado: " + numeroSerie);
        }
        if (!serie.isDisponible()) {
          throw new RuntimeException("Número de serie no disponible: " + numeroSerie);
        }
        if (!serie.getProducto().getProductoId().equals(facturaDetails.getProducto().getProductoId())) {
          throw new RuntimeException("El número de serie no pertenece al producto especificado: " + numeroSerie);
        }
        
        serie.setFacturaDetalle(detalleGuardado);
        serie.setEstado("VENDIDO");
        serie.setFechaVenta(new java.util.Date());
        seriesAsignadas.add(productoSerieService.actualizarProductoSerie(serie));
      }
      
      detalleGuardado.setNumerosSerieAsignados(seriesAsignadas);
    } else if (facturaDetails.getProducto().getRequiereNumeroSerie()) {
      // Asignar automáticamente series disponibles
      List<ProductoSerie> seriesAsignadas = productoSerieService.asignarSeriesAFactura(
        detalleGuardado, facturaDetails.getCantidad());
      detalleGuardado.setNumerosSerieAsignados(seriesAsignadas);
    }
    
    return detalleGuardado;
  }
    @Override
  @Transactional(readOnly = true)
  public FacturaDetalle obtenerDetalleFactura(Long facturaDetailsId) {
    return facturaDetailsRepository.findById(facturaDetailsId).get(); 
  }
    @Override
  @Transactional(readOnly = true)
  public FacturaDetalle obtenerDetalleFacturaConSeries(Long facturaDetailsId) {
    FacturaDetalle detalle = obtenerDetalleFactura(facturaDetailsId);
    if (detalle.getProducto().getRequiereNumeroSerie()) {
      List<ProductoSerie> series = productoSerieService.obtenerSeriesPorFacturaDetalle(detalle);
      detalle.setNumerosSerieAsignados(series);
    }
    return detalle;
  }
    @Override
  @Transactional(readOnly = true)
  public List<FacturaDetalle> obtenerDetallesFacturaPorFactura(Factura facturaId) {
    return facturaDetailsRepository.findFacturaDetalleByFactura(facturaId);
  }  @Override
  @Transactional(readOnly = true)
  public Set<FacturaDetalle> obtenerDetallesFacturas() {
    return new LinkedHashSet<>(facturaDetailsRepository.findAll());
  }
  @Override
  @Transactional(readOnly = true)
  public List<FacturaDetalleDashboardDTO> obtenerDetallesFacturasParaDashboard() {
    // Obtener todos los detalles de facturas
    List<FacturaDetalle> detalles = facturaDetailsRepository.findAll();
    
    // Convertir a DTO para evitar referencias circulares
    return detalles.stream().map(detalle -> {
      String nombreProducto = "";
      try {
        if (detalle.getProducto() != null) {
          nombreProducto = detalle.getProducto().getNombreProducto();
        }
      } catch (Exception e) {
        nombreProducto = "Producto no disponible";
      }
      
      return new FacturaDetalleDashboardDTO(
        detalle.getFacturaDetalleId(),
        detalle.getCantidad(),
        detalle.getPrecioTotal(),
        detalle.getPrecioUnitario(),
        nombreProducto,
        detalle.getTipoServicio()
      );
    }).collect(Collectors.toList());
  }
  /* TODO: Revisar firma del método en interface
  @Override
  public List<FacturaDetalle> obtenerDetallesFacturaPorFacturaConSeries(Factura facturaId) {
    List<FacturaDetalle> detalles = obtenerDetallesFacturaPorFactura(facturaId);
    for (FacturaDetalle detalle : detalles) {
      if (detalle.getProducto() != null && detalle.getProducto().getRequiereNumeroSerie()) {
        List<ProductoSerie> series = productoSerieService.obtenerSeriesPorFacturaDetalle(detalle);
        detalle.setNumerosSerieAsignados(series);
      }
    }
    return detalles;
  }
  */
  @Override
  @Transactional
  public void liberarSeriesDeFacturaDetalle(Long facturaDetalleId) {
    FacturaDetalle detalle = obtenerDetalleFactura(facturaDetalleId);
    productoSerieService.liberarSeriesDeFactura(detalle);  }
  
  @Override
  @Transactional(readOnly = true)
  public List<FacturaDetalle> buscarDetallesPorNumeroSerie(String numeroSerie) {
    System.out.println("🔍 Buscando FacturaDetalle con número de serie: " + numeroSerie);
    
    // Validaciones de entrada
    if (numeroSerie == null || numeroSerie.trim().isEmpty()) {
      System.out.println("❌ Número de serie vacío o null");
      return new ArrayList<>();
    }
    
    String numeroSerieLimpio = numeroSerie.trim();
    System.out.println("🔍 Número de serie limpio: " + numeroSerieLimpio);
    
    try {
      // Buscar usando LIKE para encontrar el número de serie dentro del campo numerosSerie
      // que puede contener múltiples números separados por comas
      String patronBusqueda = "%" + numeroSerieLimpio + "%";
      System.out.println("🔍 Patrón de búsqueda: " + patronBusqueda);
      
      List<FacturaDetalle> detalles = facturaDetailsRepository.findByNumerosSerieLike(patronBusqueda);
      System.out.println("📋 Detalles encontrados: " + detalles.size());
      
      // Filtrar resultados para asegurar que coincida exactamente el número de serie
      // (no solo que esté contenido como substring)
      List<FacturaDetalle> detallesFiltrados = detalles.stream()
        .filter(detalle -> {
          if (detalle.getNumerosSerie() == null || detalle.getNumerosSerie().trim().isEmpty()) {
            return false;
          }
          
          // Dividir por comas y verificar coincidencia exacta
          String[] numerosSeries = detalle.getNumerosSerie().split(",");
          for (String ns : numerosSeries) {
            if (ns.trim().equalsIgnoreCase(numeroSerieLimpio)) {
              return true;
            }
          }
          return false;
        })
        .collect(Collectors.toList());        
      System.out.println("✅ Detalles filtrados (coincidencia exacta): " + detallesFiltrados.size());
      return detallesFiltrados;
      
    } catch (Exception e) {
      System.err.println("❌ Error en buscarDetallesPorNumeroSerie: " + e.getMessage());
      e.printStackTrace();
      return new ArrayList<>();
    }
  }
    @Override
  @Transactional(readOnly = true)
  public List<GarantiaDTO> buscarGarantiaPorNumeroSerie(String numeroSerie) {
    System.out.println("🔍 Buscando garantía por número de serie: " + numeroSerie);
    
    // Usar la consulta nativa del repositorio para obtener los datos
    List<Object[]> resultados = facturaDetailsRepository.buscarGarantiaPorNumeroSerie("%" + numeroSerie + "%");
    
    // Convertir Object[] a GarantiaDTO
    List<GarantiaDTO> garantias = resultados.stream()
      .map(this::convertirAGarantiaDTO)
      .filter(dto -> dto != null)
      .filter(dto -> {
        // Filtrar para coincidencia exacta del número de serie
        if (dto.getNumerosSerie() == null || dto.getNumerosSerie().trim().isEmpty()) {
          return false;
        }
        
        String[] numerosSeries = dto.getNumerosSerie().split(",");
        for (String ns : numerosSeries) {
          if (ns.trim().equals(numeroSerie.trim())) {
            return true;
          }
        }
        return false;
      })
      .collect(Collectors.toList());
    
    System.out.println("✅ Garantías encontradas: " + garantias.size());
    
    return garantias;
  }  private GarantiaDTO convertirAGarantiaDTO(Object[] fila) {
    try {
      return new GarantiaDTO(
        (Long) fila[0],     // facturaDetalleId
        (Integer) fila[1],  // cantidad
        (Double) fila[2],   // precioTotal
        (Double) fila[3],   // precioUnitario
        (String) fila[4],   // numerosSerie
        (String) fila[5],   // tipoServicio
        (java.sql.Timestamp) fila[6], // createdAt
        (Long) fila[7],     // productoId
        (String) fila[8],   // nombreProducto
        (String) fila[9],   // descripcionProducto
        (Long) fila[10],    // facturaId
        (String) fila[11],  // codigoFactura
        (String) fila[12],  // divisa
        (String) fila[13],  // tipoPago
        (Double) fila[14],  // totalFactura
        (java.sql.Timestamp) fila[15], // fechaEmision
        (String) fila[16],  // estadoFactura
        (Long) fila[17],    // usuarioId
        (String) fila[18],  // nombreUsuario
        (String) fila[19],  // apellidoUsuario
        (String) fila[20],  // razonSocial
        (String) fila[21],  // rucUsuario
        (String) fila[22],  // emailUsuario
        (String) fila[23],  // tipoUsuario
        (String) fila[24]   // usernameUsuario
      );
    } catch (Exception e) {
      System.err.println("❌ Error convirtiendo a GarantiaDTO: " + e.getMessage());
      return null;
    }
  }
    @Override
  @Transactional(readOnly = true)
  public Map<String, Object> buscarGarantiaCompleta(String numeroSerie) {
    System.out.println("🔍 Buscando garantía completa para número de serie: " + numeroSerie);
    
    try {
      // Validaciones de entrada
      if (numeroSerie == null || numeroSerie.trim().isEmpty()) {
        System.out.println("❌ Número de serie vacío o null");
        Map<String, Object> error = new HashMap<>();
        error.put("encontrado", false);
        error.put("mensaje", "Número de serie no válido");
        return error;
      }
      
      String numeroSerieLimpio = numeroSerie.trim();
      System.out.println("🔍 Número de serie limpio: " + numeroSerieLimpio);
      
      // Buscar detalles
      List<FacturaDetalle> detalles = buscarDetallesPorNumeroSerie(numeroSerieLimpio);
      System.out.println("📋 Detalles encontrados: " + detalles.size());
      
      if (detalles.isEmpty()) {
        System.out.println("❌ No se encontraron detalles");
        Map<String, Object> error = new HashMap<>();
        error.put("encontrado", false);
        error.put("mensaje", "No se encontró el número de serie");
        return error;
      }
      
      FacturaDetalle detalle = detalles.get(0);
      System.out.println("📦 Procesando detalle ID: " + detalle.getFacturaDetalleId());
      
      // Crear respuesta dentro del contexto transaccional
      Map<String, Object> respuesta = new HashMap<>();
      respuesta.put("encontrado", true);
      
      // Verificar y procesar factura (las propiedades lazy se cargan aquí dentro de la transacción)
      if (detalle.getFactura() != null) {
        System.out.println("📄 Factura ID: " + detalle.getFactura().getFacturaId());
        respuesta.put("facturaId", detalle.getFactura().getFacturaId());
        respuesta.put("codigoFactura", detalle.getFactura().getCodigo() != null ? detalle.getFactura().getCodigo() : "N/A");
        respuesta.put("fechaEmision", detalle.getFactura().getFechaEmision());
        
        // Verificar usuario (carga lazy dentro de la transacción)
        if (detalle.getFactura().getUser() != null) {
          System.out.println("👤 Usuario ID: " + detalle.getFactura().getUser().getId());
          respuesta.put("clienteId", detalle.getFactura().getUser().getId());
          String nombre = detalle.getFactura().getUser().getNombre() + " " + detalle.getFactura().getUser().getApellido();
          respuesta.put("clienteNombre", nombre);
          respuesta.put("clienteRucDni", detalle.getFactura().getUser().getUsername());
          System.out.println("👤 Cliente: " + nombre);
          System.out.println("📄 RUC/DNI: " + detalle.getFactura().getUser().getUsername());
        } else {
          System.out.println("❌ Usuario es null");
          respuesta.put("clienteNombre", "Usuario no encontrado");
          respuesta.put("clienteRucDni", "N/A");
        }
      } else {
        System.out.println("❌ Factura es null");
        Map<String, Object> errorFactura = new HashMap<>();
        errorFactura.put("encontrado", false);
        errorFactura.put("mensaje", "Factura no encontrada");
        return errorFactura;
      }
      
      // Verificar producto (carga lazy dentro de la transacción)
      if (detalle.getProducto() != null) {
        System.out.println("🏷️ Producto: " + detalle.getProducto().getNombreProducto());
        respuesta.put("productoNombre", detalle.getProducto().getNombreProducto());
      } else {
        System.out.println("❌ Producto es null");
        respuesta.put("productoNombre", "Producto no encontrado");
      }
      
      respuesta.put("cantidad", detalle.getCantidad());
      respuesta.put("precioTotal", detalle.getPrecioTotal());
      
      System.out.println("✅ Garantía completa procesada exitosamente");
      return respuesta;
      
    } catch (Exception e) {
      System.err.println("❌ Error en buscarGarantiaCompleta: " + e.getMessage());
      e.printStackTrace();
      Map<String, Object> error = new HashMap<>();
      error.put("encontrado", false);
      error.put("mensaje", "Error interno al buscar garantía: " + e.getMessage());
      return error;
    }
  }
}
