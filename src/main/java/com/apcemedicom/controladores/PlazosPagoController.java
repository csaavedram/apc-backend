package com.apcemedicom.controladores;

import com.apcemedicom.dtos.ActualizarFacturaDTO;
import com.apcemedicom.dtos.ActualizarNotaCreditoDTO;
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

  @GetMapping("/notaCredito/{notaCreditoId}")
  public ResponseEntity<List<PlazosPago>> obtenerPlazosPagoPorNotaCredito(@PathVariable("notaCreditoId") Long notaCreditoId) {
    return ResponseEntity.ok(plazosPagoService.obtenerPlazosPagoPorNotaCredito(notaCreditoId));
  }

  @PatchMapping("/{plazoPagoId}")
  public ResponseEntity<PlazosPago> actualizarFacturaEnPlazoPago(@PathVariable("plazoPagoId") Long plazoPagoId, @RequestBody ActualizarFacturaDTO actualizarFacturaDTO) {
    PlazosPago plazoPagoActualizado = plazosPagoService.actualizarFacturaEnPlazoPago(plazoPagoId, actualizarFacturaDTO.getFacturaId());
    return ResponseEntity.ok(plazoPagoActualizado);
  }

  @PatchMapping("/{plazoPagoId}/notaCredito")
  public ResponseEntity<PlazosPago> actualizarNotaCreditoEnPlazoPago(@PathVariable("plazoPagoId") Long plazoPagoId, @RequestBody ActualizarNotaCreditoDTO actualizarNotaCreditoDTO) {
    PlazosPago plazoPagoActualizado = plazosPagoService.actualizarNotaCreditoEnPlazoPago(plazoPagoId, actualizarNotaCreditoDTO.getNotaCreditoId());
    return ResponseEntity.ok(plazoPagoActualizado);
  }

  @PatchMapping("/{plazoPagoId}/estado/pagado")
  public ResponseEntity<PlazosPago> cambiarEstadoAPagado(@PathVariable("plazoPagoId") Long plazoPagoId) {
    PlazosPago plazoPagoActualizado = plazosPagoService.cambiarEstadoAPagado(plazoPagoId);
    return ResponseEntity.ok(plazoPagoActualizado);
  }

  @PutMapping("/")
  public ResponseEntity<PlazosPago> actualizarPlazoPago(@RequestBody PlazosPago plazoPago) {
    PlazosPago plazoPagoActualizado = plazosPagoService.actualizarPlazoPago(plazoPago);
    return ResponseEntity.ok(plazoPagoActualizado);
  }

  @DeleteMapping("/{plazoPagoId}")
  public ResponseEntity<Void> eliminarPlazoPago(@PathVariable("plazoPagoId") Long plazoPagoId) {
    plazosPagoService.eliminarPlazoPago(plazoPagoId);
    return ResponseEntity.noContent().build();
  }
}
