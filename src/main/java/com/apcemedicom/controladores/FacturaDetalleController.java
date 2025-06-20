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
  @GetMapping("/{facturaDetailsId}")
  public FacturaDetalle listarFacturaDetail(@PathVariable("facturaDetailsId") Long facturaDetailsId) {
    return facturaDetalleService.obtenerDetalleFactura(facturaDetailsId);
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
}
