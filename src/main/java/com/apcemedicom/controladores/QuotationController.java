package com.apcemedicom.controladores;

import com.apcemedicom.modelo.Quotation;
import com.apcemedicom.servicios.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotation")
@CrossOrigin("*")
public class QuotationController {
  @Autowired
  private QuotationService quotationService;
  @PostMapping("/")
  public ResponseEntity<Quotation> agregarCotizacion(@RequestBody Quotation quotation){
    return ResponseEntity.ok(quotationService.agregarCotizacion(quotation));
  }
  @PutMapping("/")
  public ResponseEntity<Quotation> actualizarCotizacion(@RequestBody Quotation quotation){
    return ResponseEntity.ok(quotationService.actualizarCotizacion(quotation));
  }
  @GetMapping("/")
  public ResponseEntity<?> listarCotizaciones() {
    return ResponseEntity.ok(quotationService.obtenerCotizaciones());
  }
  @GetMapping("/{quotationId}")
  public Quotation listarCotizacion(@PathVariable("quotationId") Long quotationId) {
    return quotationService.obtenerCotizacion(quotationId);
  }
  @DeleteMapping("/{quotationId}")
  public void eliminarCotizacion(@PathVariable("quotationId") Long quotationId) {
    quotationService.eliminarCotizacion(quotationId);
  }
  @GetMapping("/user/{id}")
  public ResponseEntity<List<Quotation>> listarCotizacionesPorUsuario(@PathVariable("id") Long id) {
    List<Quotation> quotations = quotationService.obtenerCotizacionesPorUsuario(id);
    return ResponseEntity.ok(quotations);
  }
  @PatchMapping("/cancel/{quotationId}")
  public ResponseEntity<Quotation> cancelarCotizacion(@PathVariable("quotationId") Long quotationId) {
    quotationService.cancelarCotizacion(quotationId);
    Quotation quotation = quotationService.obtenerCotizacion(quotationId);
    return ResponseEntity.ok(quotation);
  }
  @GetMapping("/codigo/{codigo}")
  public ResponseEntity<Quotation> obtenerCotizacionPorCodigo(@PathVariable("codigo") String codigo) {
    Quotation quotation = quotationService.obtenerCotizacionPorCodigo(codigo);
    return ResponseEntity.ok(quotation);
  }
}
