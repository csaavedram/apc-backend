package com.apcemedicom.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apcemedicom.modelo.Factura;
import com.apcemedicom.modelo.FacturaDetalle;
import com.apcemedicom.servicios.FacturaDetalleService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/facturadetails")
@CrossOrigin("*")
public class FacturaDetalleController {
  @Autowired
  private FacturaDetalleService facturaDetalleService;

  @PostMapping("/")
  public ResponseEntity<FacturaDetalle> agregarFacturaDetail(@RequestBody FacturaDetalle facturaDetail) {
    return ResponseEntity.ok(facturaDetalleService.agregarDetalleFactura(facturaDetail));
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

  
  @GetMapping("/factura/{facturaId}/con-series")
  public ResponseEntity<List<FacturaDetalle>> listarFacturaDetailsPorFacturaConSeries(@PathVariable("facturaId") Factura facturaId) {
    try {
      List<FacturaDetalle> facturaDetails = facturaDetalleService.obtenerDetallesFacturaPorFacturaConSeries(facturaId);
      return ResponseEntity.ok(facturaDetails);
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
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
}
