package com.apcemedicom.servicios.impl;

import com.apcemedicom.modelo.ProductoSerie;
import com.apcemedicom.modelo.Producto;
import com.apcemedicom.modelo.FacturaDetalle;
import com.apcemedicom.repositorios.ProductoSerieRepository;
import com.apcemedicom.repositorios.ProductoRepository;
import com.apcemedicom.servicios.ProductoSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductoSerieServiceImpl implements ProductoSerieService {
      @Autowired
    private ProductoSerieRepository productoSerieRepository;
    
    @Autowired
    private ProductoRepository productoRepository;    @Override
    public ProductoSerie agregarProductoSerie(ProductoSerie productoSerie) {
        if (existeNumeroSerie(productoSerie.getNumeroSerie())) {
            throw new RuntimeException("El número de serie ya existe: " + productoSerie.getNumeroSerie());
        }
        productoSerie.setFechaCreacion(new Date());
        if (productoSerie.getEstado() == null) {
            productoSerie.setEstado("DISPONIBLE");
        }
        if (productoSerie.getCantidad() == null) {
            productoSerie.setCantidad(1);
        }
        
        // Establecer cantidad original igual a la cantidad inicial
        if (productoSerie.getCantidadOriginal() == null) {
            productoSerie.setCantidadOriginal(productoSerie.getCantidad());
        }
        
        // Inicializar cantidad vendida
        if (productoSerie.getCantidadVendida() == null) {
            productoSerie.setCantidadVendida(0);
        }
        
        ProductoSerie serieGuardada = productoSerieRepository.save(productoSerie);
        
        // Recalcular stock del producto automáticamente
        if (serieGuardada.getProducto() != null) {
            recalcularStockProducto(serieGuardada.getProducto());
        }
        
        return serieGuardada;
    }
      @Override
    public ProductoSerie actualizarProductoSerie(ProductoSerie productoSerie) {
        System.out.println("🔄 INICIANDO actualización de ProductoSerie en servicio");
        System.out.println("📋 Serie antes de guardar:");
        System.out.println("   - ID: " + productoSerie.getProductoSerieId());
        System.out.println("   - NumeroSerie: " + productoSerie.getNumeroSerie());
        System.out.println("   - Cantidad: " + productoSerie.getCantidad());
        System.out.println("   - CantidadOriginal: " + productoSerie.getCantidadOriginal());
        System.out.println("   - CantidadVendida: " + productoSerie.getCantidadVendida());
        System.out.println("   - Estado: " + productoSerie.getEstado());
        System.out.println("   - FechaVenta: " + productoSerie.getFechaVenta());
        
        ProductoSerie resultado = productoSerieRepository.save(productoSerie);
        
        System.out.println("✅ Serie después de guardar:");
        System.out.println("   - ID: " + resultado.getProductoSerieId());
        System.out.println("   - NumeroSerie: " + resultado.getNumeroSerie());
        System.out.println("   - Cantidad: " + resultado.getCantidad());
        System.out.println("   - CantidadOriginal: " + resultado.getCantidadOriginal());
        System.out.println("   - CantidadVendida: " + resultado.getCantidadVendida());
        System.out.println("   - Estado: " + resultado.getEstado());
        System.out.println("   - FechaVenta: " + resultado.getFechaVenta());
        
        return resultado;
    }
    
    @Override
    public ProductoSerie obtenerProductoSerie(Long productoSerieId) {
        return productoSerieRepository.findById(productoSerieId)
                .orElseThrow(() -> new RuntimeException("Producto serie no encontrado con ID: " + productoSerieId));
    }
    
    @Override
    public List<ProductoSerie> obtenerTodosProductosSerie() {
        return productoSerieRepository.findAll();
    }    @Override
    public void eliminarProductoSerie(Long productoSerieId) {
        ProductoSerie productoSerie = obtenerProductoSerie(productoSerieId);
        if ("VENDIDO".equals(productoSerie.getEstado())) {
            throw new RuntimeException("No se puede eliminar un número de serie que ya ha sido vendido");
        }
        
        Producto producto = productoSerie.getProducto();
        
        // Eliminar la serie
        productoSerieRepository.deleteById(productoSerieId);
        
        // Recalcular stock del producto automáticamente
        if (producto != null) {
            recalcularStockProducto(producto);
        }
    }
    
    @Override
    public ProductoSerie buscarPorNumeroSerie(String numeroSerie) {
        return productoSerieRepository.findByNumeroSerie(numeroSerie).orElse(null);
    }
    
    @Override
    public List<ProductoSerie> obtenerSeriesPorProducto(Producto producto) {
        return productoSerieRepository.findByProducto(producto);
    }
    
    @Override
    public List<ProductoSerie> obtenerSeriesDisponiblesPorProducto(Producto producto) {
        return productoSerieRepository.findSeriesDisponibles(producto);
    }
    
    @Override
    public List<ProductoSerie> obtenerSeriesPorFacturaDetalle(FacturaDetalle facturaDetalle) {
        return productoSerieRepository.findByFacturaDetalle(facturaDetalle);
    }
    
    @Override
    public List<ProductoSerie> asignarSeriesAFactura(FacturaDetalle facturaDetalle, int cantidad) {
        List<ProductoSerie> seriesDisponibles = obtenerSeriesDisponiblesPorProducto(facturaDetalle.getProducto());
        
        if (seriesDisponibles.size() < cantidad) {
            throw new RuntimeException("No hay suficientes números de serie disponibles. Requeridos: " + 
                                     cantidad + ", Disponibles: " + seriesDisponibles.size());
        }
        
        List<ProductoSerie> seriesAsignadas = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            ProductoSerie serie = seriesDisponibles.get(i);
            serie.setFacturaDetalle(facturaDetalle);
            serie.setEstado("VENDIDO");
            serie.setFechaVenta(new Date());
            seriesAsignadas.add(productoSerieRepository.save(serie));
        }
        
        return seriesAsignadas;
    }
    
    @Override
    public void liberarSeriesDeFactura(FacturaDetalle facturaDetalle) {
        List<ProductoSerie> series = obtenerSeriesPorFacturaDetalle(facturaDetalle);
        for (ProductoSerie serie : series) {
            serie.setFacturaDetalle(null);
            serie.setEstado("DISPONIBLE");
            serie.setFechaVenta(null);
            productoSerieRepository.save(serie);
        }
    }
    
    @Override
    public boolean validarDisponibilidadSeries(Producto producto, int cantidad) {
        Long seriesDisponibles = contarSeriesDisponibles(producto);
        return seriesDisponibles >= cantidad;
    }
    
    @Override
    public List<ProductoSerie> obtenerSeriesProximasAVencer() {
        return productoSerieRepository.findSeriesProximasAVencer();
    }
    
    @Override
    public void marcarSeriesVencidas() {
        List<ProductoSerie> todasLasSeries = productoSerieRepository.findByEstado("DISPONIBLE");
        for (ProductoSerie serie : todasLasSeries) {
            if (serie.isVencido()) {
                serie.setEstado("VENCIDO");
                productoSerieRepository.save(serie);
            }
        }
    }
    
    @Override
    public boolean existeNumeroSerie(String numeroSerie) {
        return productoSerieRepository.existsByNumeroSerie(numeroSerie);
    }
    
    @Override
    public Long contarSeriesDisponibles(Producto producto) {
        return productoSerieRepository.countSeriesDisponibles(producto);
    }
    
    @Override
    public List<ProductoSerie> crearSeriesEnLote(Producto producto, List<String> numerosSerie, Date fechaVencimiento) {
        List<ProductoSerie> seriesCreadas = new ArrayList<>();
        
        for (String numeroSerie : numerosSerie) {
            if (existeNumeroSerie(numeroSerie)) {
                throw new RuntimeException("El número de serie ya existe: " + numeroSerie);
            }
            
            ProductoSerie serie = new ProductoSerie(numeroSerie, producto);
            serie.setFechaVencimiento(fechaVencimiento);
            seriesCreadas.add(agregarProductoSerie(serie));
        }
          return seriesCreadas;
    }
      @Override
    public void recalcularStockProducto(Producto producto) {
        Integer stockTotal = calcularStockTotalProducto(producto);
        producto.setStock(stockTotal);
        productoRepository.save(producto);
    }
    
    @Override
    public Integer calcularStockTotalProducto(Producto producto) {
        List<ProductoSerie> seriesDisponibles = obtenerSeriesDisponiblesPorProducto(producto);
        return seriesDisponibles.stream()
                .mapToInt(serie -> serie.getCantidad() != null ? serie.getCantidad() : 1)
                .sum();
    }
    
    // Nuevos métodos para procesar ventas
    public List<ProductoSerie> obtenerSeriesDisponiblesOrdenadas(Producto producto) {
        return productoSerieRepository.findSeriesDisponiblesOrdenadas(producto);
    }    public List<java.util.Map<String, Object>> procesarVentaSeries(List<ProductoSerie> seriesDisponibles, int cantidadSolicitada) {
        List<java.util.Map<String, Object>> seriesProcesadas = new ArrayList<>();
        int cantidadRestante = cantidadSolicitada;
        
        for (ProductoSerie serie : seriesDisponibles) {
            if (cantidadRestante <= 0) break;
            
            java.util.Map<String, Object> serieProcesada = new java.util.HashMap<>();
            serieProcesada.put("productoSerieId", serie.getProductoSerieId());
            serieProcesada.put("numeroSerie", serie.getNumeroSerie());
            
            int cantidadDisponible = serie.getCantidad() != null ? serie.getCantidad() : 1;
            int cantidadVendidaAnterior = serie.getCantidadVendida() != null ? serie.getCantidadVendida() : 0;
            
            if (cantidadDisponible <= cantidadRestante) {
                // Vender toda la cantidad disponible de la serie
                int cantidadAVender = cantidadDisponible;
                int nuevaCantidadVendida = cantidadVendidaAnterior + cantidadAVender;
                
                // Actualizar la serie
                serie.setCantidad(0); // Ya no queda nada disponible
                serie.setCantidadVendida(nuevaCantidadVendida);
                
                // Si se vendió todo el lote original, marcar como VENDIDO
                if (nuevaCantidadVendida >= serie.getCantidadOriginal()) {
                    serie.setEstado("VENDIDO");
                    serie.setFechaVenta(new Date());
                } else {
                    // Aún queda cantidad por vender del lote original (caso raro pero posible)
                    serie.setEstado("DISPONIBLE");
                }
                
                productoSerieRepository.save(serie);
                
                serieProcesada.put("cantidadVendida", cantidadAVender);
                serieProcesada.put("cantidadVendidaTotal", nuevaCantidadVendida);
                serieProcesada.put("estado", serie.getEstado());
                serieProcesada.put("cantidadRestante", 0);
                
                cantidadRestante -= cantidadAVender;
            } else {
                // Venta parcial: reducir la cantidad disponible
                int cantidadAVender = cantidadRestante;
                int nuevaCantidadDisponible = cantidadDisponible - cantidadAVender;
                int nuevaCantidadVendida = cantidadVendidaAnterior + cantidadAVender;
                
                // Actualizar la serie
                serie.setCantidad(nuevaCantidadDisponible);
                serie.setCantidadVendida(nuevaCantidadVendida);
                
                // Mantener estado DISPONIBLE ya que aún queda stock
                productoSerieRepository.save(serie);
                
                serieProcesada.put("cantidadVendida", cantidadAVender);
                serieProcesada.put("cantidadVendidaTotal", nuevaCantidadVendida);
                serieProcesada.put("estado", "VENTA_PARCIAL");
                serieProcesada.put("cantidadRestante", nuevaCantidadDisponible);
                serieProcesada.put("cantidadOriginal", serie.getCantidadOriginal());
                
                cantidadRestante = 0;
            }
            
            seriesProcesadas.add(serieProcesada);
        }
        
        if (cantidadRestante > 0) {
            throw new RuntimeException("No hay suficiente stock disponible. Faltante: " + cantidadRestante);
        }
        
        // Recalcular stock del producto
        if (!seriesDisponibles.isEmpty()) {
            recalcularStockProducto(seriesDisponibles.get(0).getProducto());
        }
        
        return seriesProcesadas;
    }
}
