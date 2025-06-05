package com.apcemedicom.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apcemedicom.modelo.NotaCredito;
import com.apcemedicom.modelo.NotaCreditoDetalle;
import com.apcemedicom.servicios.NotaCreditoDetalleService;

@RestController
@RequestMapping("/notacreditodetails")
@CrossOrigin("*")
public class NotaCreditoDetalleController {
  @Autowired
  private NotaCreditoDetalleService notaCreditoDetalleService;

  @PostMapping("/")
  public ResponseEntity<NotaCreditoDetalle> agregarNotaCreditoDetalle(@RequestBody NotaCreditoDetalle notaCreditoDetalle) {
    return ResponseEntity.ok(notaCreditoDetalleService.agregarDetalleNotaCredito(notaCreditoDetalle));
  }
  @GetMapping("/{notaCreditoDetalleId}")
  public NotaCreditoDetalle listarNotaCreditoDetalle(@PathVariable("notaCreditoDetalleId") Long notaCreditoDetalleId) {
    return notaCreditoDetalleService.obtenerDetalleNotaCredito(notaCreditoDetalleId);
  }
  @GetMapping("/notacredito/{notaCreditoId}")
  public ResponseEntity<List<NotaCreditoDetalle>> listarDetallesPorNotaCredito(@PathVariable("notaCreditoId") NotaCredito notaCreditoId) {
    List<NotaCreditoDetalle> detalles = notaCreditoDetalleService.obtenerDetallesNotaCreditoPorNotaCredito(notaCreditoId);
    return ResponseEntity.ok(detalles);
  }
}
