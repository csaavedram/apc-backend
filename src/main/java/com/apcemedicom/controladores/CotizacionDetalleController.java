package com.apcemedicom.controladores;

import com.apcemedicom.modelo.Cotizacion;
import com.apcemedicom.modelo.CotizacionDetalle;
import com.apcemedicom.servicios.CotizacionDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotationdetails")
@CrossOrigin("*")
public class CotizacionDetalleController {
  @Autowired
  private CotizacionDetalleService cotizacionDetalleService;
  @PostMapping("/")
  public ResponseEntity<CotizacionDetalle> agregarQuotationDetail(@RequestBody CotizacionDetalle quotationDetail){
    return ResponseEntity.ok(cotizacionDetalleService.agregarDetalleCotizacion(quotationDetail));
  }
  @PutMapping("/")
  public ResponseEntity<CotizacionDetalle> actualizarQuotationDetail(@RequestBody CotizacionDetalle quotationDetail){
    return ResponseEntity.ok(cotizacionDetalleService.actualizarDetalleCotizacion(quotationDetail));
  }
  @GetMapping("/")
  public ResponseEntity<?> listarQuotationDetails() {
    return ResponseEntity.ok(cotizacionDetalleService.obtenerDetallesCotizaciones());
  }
  @GetMapping("/{quotationDetailsId}")
  public CotizacionDetalle listarQuotationDetail(@PathVariable("quotationDetailsId") Long quotationDetailsId) {
    return cotizacionDetalleService.obtenerDetalleCotizacion(quotationDetailsId);
  }
  @DeleteMapping("/{quotationDetailsId}")
  public void eliminarQuotationDetail(@PathVariable("quotationDetailsId") Long quotationDetailsId) {
    cotizacionDetalleService.eliminarDetalleCotizacion(quotationDetailsId);
  }
  @GetMapping("/quotation/{quotationId}")
  public ResponseEntity<List<CotizacionDetalle>> listarQuotationDetailsPorQuotation(@PathVariable("quotationId") Cotizacion quotationId) {
    List<CotizacionDetalle> quotationDetails = cotizacionDetalleService.obtenerDetallesCotizacionPorCotizacion(quotationId);
    return ResponseEntity.ok(quotationDetails);
  }
}