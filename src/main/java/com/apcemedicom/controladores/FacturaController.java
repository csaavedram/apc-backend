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

  @PatchMapping("/anular/{facturaId}")
  public void anularFactura(@PathVariable("facturaId") Long facturaId) {
    facturaService.anularFactura(facturaId);
  }
  
  @GetMapping("/codigo/{codigo}")
  public ResponseEntity<Factura> obtenerFacturaPorCodigo(@PathVariable("codigo") String codigo) {
    Factura factura = facturaService.obtenerFacturaPorCodigo(codigo);
    return ResponseEntity.ok(factura);
  }

  @GetMapping("/cotizacion/{codigoCotizacion}")
  public ResponseEntity<List<Factura>> listarFacturasPorCodigoCotizacion(@PathVariable("codigoCotizacion") String codigoCotizacion) {
    List<Factura> facturas = facturaService.obtenerFacturasPorCodigoCotizacion(codigoCotizacion);
    return ResponseEntity.ok(facturas);
  }

  @GetMapping("/cotizacion/id/{cotizacionId}")
  public ResponseEntity<List<Factura>> listarFacturasPorCotizacionId(@PathVariable("cotizacionId") Long cotizacionId) {
    List<Factura> facturas = facturaService.obtenerFacturasPorCotizacionId(cotizacionId);
    return ResponseEntity.ok(facturas);
  }
}
