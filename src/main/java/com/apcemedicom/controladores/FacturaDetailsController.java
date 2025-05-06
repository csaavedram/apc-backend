package com.apcemedicom.controladores;

import com.apcemedicom.modelo.Factura;
import com.apcemedicom.modelo.FacturaDetails;
import com.apcemedicom.servicios.FacturaDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facturadetails")
@CrossOrigin("*")
public class FacturaDetailsController {
  @Autowired
  private FacturaDetailsService facturaDetailsService;

  @PostMapping("/")
  public ResponseEntity<FacturaDetails> agregarFacturaDetail(@RequestBody FacturaDetails facturaDetail) {
    return ResponseEntity.ok(facturaDetailsService.agregarDetalleFactura(facturaDetail));
  }
  @GetMapping("/{facturaDetailsId}")
  public FacturaDetails listarFacturaDetail(@PathVariable("facturaDetailsId") Long facturaDetailsId) {
    return facturaDetailsService.obtenerDetalleFactura(facturaDetailsId);
  }
  @GetMapping("/factura/{facturaId}")
  public ResponseEntity<List<FacturaDetails>> listarFacturaDetailsPorFactura(@PathVariable("facturaId") Factura facturaId) {
    List<FacturaDetails> facturaDetails = facturaDetailsService.obtenerDetallesFacturaPorFactura(facturaId);
    return ResponseEntity.ok(facturaDetails);
  }
}
