package com.apcemedicom.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apcemedicom.modelo.NotaCredito;
import com.apcemedicom.servicios.NotaCreditoService;

@RestController
@RequestMapping("/notacredito")
@CrossOrigin("*")
public class NotaCreditoController {
  @Autowired
  private NotaCreditoService notaCreditoService;

  @PostMapping("/")
  public ResponseEntity<NotaCredito> agregarNotaCredito(@RequestBody NotaCredito notaCredito) {
    return ResponseEntity.ok(notaCreditoService.agregarNotaCredito(notaCredito));
  }
  @GetMapping("/")
  public ResponseEntity<?> listarNotasCredito() {
    return ResponseEntity.ok(notaCreditoService.obtenerNotasCredito());
  }
  @GetMapping("/{notaCreditoId}")
  public NotaCredito listarNotaCredito(Long notaCreditoId) {
    return notaCreditoService.obtenerNotaCredito(notaCreditoId);
  }
}
