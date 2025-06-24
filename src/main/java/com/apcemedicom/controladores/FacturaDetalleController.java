package com.apcemedicom.controladores;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apcemedicom.modelo.Factura;
import com.apcemedicom.modelo.FacturaDetalle;
import com.apcemedicom.servicios.FacturaDetalleService;

@RestController
@RequestMapping("/facturadetails")
@CrossOrigin("*")
public class FacturaDetalleController {
  @Autowired
  private FacturaDetalleService facturaDetalleService;

  @PostMapping("/")
  public ResponseEntity<FacturaDetalle> agregarFacturaDetail(@RequestBody FacturaDetalle facturaDetail) {
    try {
      System.out.println("📦 RECIBIENDO FACTURADETALLE:");
      System.out.println("- Cantidad: " + facturaDetail.getCantidad());
      System.out.println("- Precio Unitario: " + facturaDetail.getPrecioUnitario());
      System.out.println("- Precio Total: " + facturaDetail.getPrecioTotal());
      System.out.println("- Producto ID: " + (facturaDetail.getProducto() != null ? facturaDetail.getProducto().getProductoId() : "null"));
      System.out.println("- Factura ID: " + (facturaDetail.getFactura() != null ? facturaDetail.getFactura().getFacturaId() : "null"));
      System.out.println("- Tipo Servicio: " + facturaDetail.getTipoServicio());
      System.out.println("- Números de Serie: '" + facturaDetail.getNumerosSerie() + "'");
      System.out.println("- Números de Serie (length): " + (facturaDetail.getNumerosSerie() != null ? facturaDetail.getNumerosSerie().length() : "null"));
      
      FacturaDetalle resultado = facturaDetalleService.agregarDetalleFactura(facturaDetail);
      System.out.println("✅ FACTURADETALLE GUARDADO CON ID: " + resultado.getFacturaDetalleId());
      System.out.println("✅ GUARDADO - Números de Serie: '" + resultado.getNumerosSerie() + "'");
      return ResponseEntity.ok(resultado);
    } catch (Exception e) {
      System.err.println("❌ ERROR: " + e.getMessage());
      e.printStackTrace();
      return ResponseEntity.status(500).body(null);
    }
  }
  
  @PostMapping("/con-series")
  public ResponseEntity<FacturaDetalle> agregarFacturaDetailConSeries(@RequestBody Map<String, Object> request) {
    try {
      FacturaDetalle facturaDetail = (FacturaDetalle) request.get("facturaDetalle");
      @SuppressWarnings("unchecked")
      List<String> numerosSerie = (List<String>) request.get("numerosSerie");
      
      FacturaDetalle resultado = facturaDetalleService.agregarDetalleFacturaConSeries(facturaDetail, numerosSerie);
      return ResponseEntity.ok(resultado);
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }
  
  @GetMapping("/{facturaDetailsId}")
  public FacturaDetalle listarFacturaDetail(@PathVariable("facturaDetailsId") Long facturaDetailsId) {
    return facturaDetalleService.obtenerDetalleFactura(facturaDetailsId);
  }
  
  @GetMapping("/{facturaDetailsId}/con-series")
  public ResponseEntity<FacturaDetalle> listarFacturaDetailConSeries(@PathVariable("facturaDetailsId") Long facturaDetailsId) {
    try {
      FacturaDetalle detalle = facturaDetalleService.obtenerDetalleFacturaConSeries(facturaDetailsId);
      return ResponseEntity.ok(detalle);
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }
  
  @GetMapping("/factura/{facturaId}")
  public ResponseEntity<List<FacturaDetalle>> listarFacturaDetailsPorFactura(@PathVariable("facturaId") Factura facturaId) {
    List<FacturaDetalle> facturaDetails = facturaDetalleService.obtenerDetallesFacturaPorFactura(facturaId);
    return ResponseEntity.ok(facturaDetails);
  }
  
  @GetMapping("/")
  public ResponseEntity<?> listarFacturas() {
    return ResponseEntity.ok(facturaDetalleService.obtenerDetallesFacturas());
  }
  
  // Nuevo endpoint específico para el dashboard que evita referencias circulares
  @GetMapping("/dashboard")
  public ResponseEntity<?> listarFacturasParaDashboard() {
    try {
      return ResponseEntity.ok(facturaDetalleService.obtenerDetallesFacturasParaDashboard());
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Error al obtener datos para dashboard");
    }
  }
  
  @DeleteMapping("/{facturaDetailsId}/liberar-series")
  public ResponseEntity<Void> liberarSeriesDeFacturaDetalle(@PathVariable("facturaDetailsId") Long facturaDetailsId) {
    try {
      facturaDetalleService.liberarSeriesDeFacturaDetalle(facturaDetailsId);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }
  
  // Nuevo endpoint para buscar facturas por número de serie/lote
  @GetMapping("/buscar-por-serie/{numeroSerie}")
  public ResponseEntity<List<FacturaDetalle>> buscarFacturasPorNumeroSerie(@PathVariable("numeroSerie") String numeroSerie) {
    try {
      List<FacturaDetalle> detalles = facturaDetalleService.buscarDetallesPorNumeroSerie(numeroSerie);
      return ResponseEntity.ok(detalles);
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }
  
  // Endpoint específico para búsqueda de garantía
  @GetMapping("/serie/{numeroSerie}")
  public ResponseEntity<List<FacturaDetalle>> buscarPorNumeroSerie(@PathVariable("numeroSerie") String numeroSerie) {
    try {
      System.out.println("🔍 Buscando detalles de factura para número de serie: " + numeroSerie);
      List<FacturaDetalle> detalles = facturaDetalleService.buscarDetallesPorNumeroSerie(numeroSerie);
      System.out.println("✅ Detalles encontrados: " + detalles.size());
      return ResponseEntity.ok(detalles);
    } catch (Exception e) {
      System.err.println("❌ Error buscando por número de serie: " + e.getMessage());
      e.printStackTrace();
      return ResponseEntity.badRequest().build();
    }
  }
  
  // Endpoint simple para garantía - solo devuelve información básica
  @GetMapping("/garantia/{numeroSerie}")
  public ResponseEntity<Map<String, Object>> buscarGarantia(@PathVariable("numeroSerie") String numeroSerie) {
    try {
      System.out.println("🔍 Buscando garantía para número de serie: " + numeroSerie);
      
      // Delegar toda la lógica al servicio para evitar problemas de lazy loading
      Map<String, Object> resultado = facturaDetalleService.buscarGarantiaCompleta(numeroSerie);
      
      System.out.println("✅ Garantía procesada exitosamente");
      return ResponseEntity.ok(resultado);
      
    } catch (Exception e) {
      System.err.println("❌ Error buscando garantía: " + e.getMessage());
      System.err.println("❌ Tipo de error: " + e.getClass().getSimpleName());
      e.printStackTrace();
      Map<String, Object> error = new HashMap<>();
      error.put("encontrado", false);
      error.put("mensaje", "Error al buscar: " + e.getMessage());
      return ResponseEntity.ok(error);
    }
  }
}
