package com.apcemedicom.controladores;

import com.apcemedicom.modelo.Factura;
import com.apcemedicom.modelo.FacturaDetalle;
import com.apcemedicom.servicios.FacturaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
