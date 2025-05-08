package com.apcemedicom.controladores;

import com.apcemedicom.modelo.Factura;
import com.apcemedicom.servicios.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factura")
@CrossOrigin("*")
public class FacturaController {
  @Autowired
  private FacturaService facturaService;

  @PostMapping("/")
  public ResponseEntity<Factura> agregarFactura(@RequestBody Factura factura) {
    return ResponseEntity.ok(facturaService.agregarFactura(factura));
  }
  @GetMapping("/")
  public ResponseEntity<?> listarFacturas() {
    return ResponseEntity.ok(facturaService.obtenerFacturas());
  }
  @GetMapping("/{facturaId}")
  public Factura listarFactura(@PathVariable("facturaId") Long facturaId) {
    return facturaService.obtenerFactura(facturaId);
  }
  @GetMapping("/user/{id}")
  public ResponseEntity<List<Factura>> listarFacturasPorUsuario(@PathVariable("id") Long id) {
    List<Factura> facturas = facturaService.obtenerFacturasPorUsuario(id);
    return ResponseEntity.ok(facturas);
  }
}
