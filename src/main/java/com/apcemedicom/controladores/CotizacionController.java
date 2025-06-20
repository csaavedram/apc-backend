package com.apcemedicom.controladores;

import com.apcemedicom.modelo.Cotizacion;
import com.apcemedicom.servicios.CotizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotation")
@CrossOrigin("*")
public class CotizacionController {
  @Autowired
  private CotizacionService cotizacionService;
  @PostMapping("/")
  public ResponseEntity<Cotizacion> agregarCotizacion(@RequestBody Cotizacion quotation){
    return ResponseEntity.ok(cotizacionService.agregarCotizacion(quotation));
  }
  @PutMapping("/")
  public ResponseEntity<Cotizacion> actualizarCotizacion(@RequestBody Cotizacion quotation){
    return ResponseEntity.ok(cotizacionService.actualizarCotizacion(quotation));
  }
  @GetMapping("/")
  public ResponseEntity<?> listarCotizaciones() {
    return ResponseEntity.ok(cotizacionService.obtenerCotizaciones());
  }
  @GetMapping("/{quotationId}")
  public Cotizacion listarCotizacion(@PathVariable("quotationId") Long quotationId) {
    return cotizacionService.obtenerCotizacion(quotationId);
  }
  @DeleteMapping("/{quotationId}")
  public void eliminarCotizacion(@PathVariable("quotationId") Long quotationId) {
    cotizacionService.eliminarCotizacion(quotationId);
  }
  @GetMapping("/user/{id}")
  public ResponseEntity<List<Cotizacion>> listarCotizacionesPorUsuario(@PathVariable("id") Long id) {
    List<Cotizacion> quotations = cotizacionService.obtenerCotizacionesPorUsuario(id);
    return ResponseEntity.ok(quotations);
  }
  @PatchMapping("/anular/{quotationId}")
  public ResponseEntity<Cotizacion> anularCotizacion(@PathVariable("quotationId") Long quotationId) {
    cotizacionService.anularCotizacion(quotationId);
    Cotizacion quotation = cotizacionService.obtenerCotizacion(quotationId);
    return ResponseEntity.ok(quotation);
  }
  @GetMapping("/codigo/{codigo}")
  public ResponseEntity<Cotizacion> obtenerCotizacionPorCodigo(@PathVariable("codigo") String codigo) {
    Cotizacion quotation = cotizacionService.obtenerCotizacionPorCodigo(codigo);
    return ResponseEntity.ok(quotation);
  }
  @PatchMapping("/aceptar/{quotationId}")
  public ResponseEntity<Cotizacion> aceptarCotizacion(@PathVariable("quotationId") Long quotationId) {
    cotizacionService.aceptarCotizacion(quotationId);
    Cotizacion quotation = cotizacionService.obtenerCotizacion(quotationId);
    return ResponseEntity.ok(quotation);
  }
  @PatchMapping("/pagar/{quotationId}")
  public ResponseEntity<Cotizacion> pagarCotizacion(@PathVariable("quotationId") Long quotationId) {
      cotizacionService.pagarCotizacion(quotationId);
      Cotizacion quotation = cotizacionService.obtenerCotizacion(quotationId);
      return ResponseEntity.ok(quotation);
  }

  @PatchMapping("/pagar-parcialmente/{quotationId}")
  public ResponseEntity<Cotizacion> pagarParcialmenteCotizacion(@PathVariable("quotationId") Long quotationId) {
      cotizacionService.pagarParcialmenteCotizacion(quotationId);
      Cotizacion quotation = cotizacionService.obtenerCotizacion(quotationId);
      return ResponseEntity.ok(quotation);
  }
}
