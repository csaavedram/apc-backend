package com.apcemedicom.controladores;

import com.apcemedicom.modelo.Quotation;
import com.apcemedicom.modelo.QuotationDetails;
import com.apcemedicom.servicios.QuotationDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotationdetails")
@CrossOrigin("*")
public class QuotationDetailsController {
  @Autowired
  private QuotationDetailsService quotationDetailsService;
  @PostMapping("/")
  public ResponseEntity<QuotationDetails> agregarQuotationDetail(@RequestBody QuotationDetails quotationDetail){
    return ResponseEntity.ok(quotationDetailsService.agregarDetalleCotizacion(quotationDetail));
  }
  @PutMapping("/")
  public ResponseEntity<QuotationDetails> actualizarQuotationDetail(@RequestBody QuotationDetails quotationDetail){
    return ResponseEntity.ok(quotationDetailsService.actualizarDetalleCotizacion(quotationDetail));
  }
  @GetMapping("/")
  public ResponseEntity<?> listarQuotationDetails() {
    return ResponseEntity.ok(quotationDetailsService.obtenerDetallesCotizaciones());
  }
  @GetMapping("/{quotationDetailsId}")
  public QuotationDetails listarQuotationDetail(@PathVariable("quotationDetailsId") Long quotationDetailsId) {
    return quotationDetailsService.obtenerDetalleCotizacion(quotationDetailsId);
  }
  @DeleteMapping("/{quotationDetailsId}")
  public void eliminarQuotationDetail(@PathVariable("quotationDetailsId") Long quotationDetailsId) {
    quotationDetailsService.eliminarDetalleCotizacion(quotationDetailsId);
  }
  @GetMapping("/quotation/{quotationId}")
  public ResponseEntity<List<QuotationDetails>> listarQuotationDetailsPorQuotation(@PathVariable("quotationId") Quotation quotationId) {
    List<QuotationDetails> quotationDetails = quotationDetailsService.obtenerDetallesCotizacionPorCotizacion(quotationId);
    return ResponseEntity.ok(quotationDetails);
  }
}