package com.apcemedicom.controladores;

import com.apcemedicom.dtos.ActualizarFacturaDTO;
import com.apcemedicom.modelo.PlazosPago;
import com.apcemedicom.servicios.PlazosPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plazospago")
@CrossOrigin("*")
public class PlazosPagoController {

  @Autowired
  private PlazosPagoService plazosPagoService;

  @PostMapping("/")
  public ResponseEntity<PlazosPago> agregarPlazoPago(@RequestBody PlazosPago paymentTerm) {
    return ResponseEntity.ok(plazosPagoService.agregarPlazoPago(paymentTerm));
  }

  @GetMapping("/factura/{facturaId}")
  public ResponseEntity<List<PlazosPago>> obtenerPlazosPagoPorFactura(@PathVariable("facturaId") Long facturaId) {
    return ResponseEntity.ok(plazosPagoService.obtenerPlazosPagoPorFactura(facturaId));
  }

  @GetMapping("/cotizacion/{cotizacionId}")
  public ResponseEntity<List<PlazosPago>> obtenerPlazosPagoPorCotizacion(@PathVariable("cotizacionId") Long cotizacionId) {
    return ResponseEntity.ok(plazosPagoService.obtenerPlazosPagoPorCotizacion(cotizacionId));
  }

  @PatchMapping("/{plazoPagoId}")
  public ResponseEntity<PlazosPago> actualizarFacturaEnPlazoPago(@PathVariable("plazoPagoId") Long plazoPagoId, @RequestBody ActualizarFacturaDTO actualizarFacturaDTO) {
    PlazosPago plazoPagoActualizado = plazosPagoService.actualizarFacturaEnPlazoPago(plazoPagoId, actualizarFacturaDTO.getFacturaId());
    return ResponseEntity.ok(plazoPagoActualizado);
  }
}
