package com.apcemedicom.controladores;

import com.apcemedicom.modelo.PaymentTerm;
import com.apcemedicom.servicios.PaymentTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paymentterms")
@CrossOrigin("*")
public class PaymentTermController {

  @Autowired
  private PaymentTermService paymentTermService;

  @PostMapping("/")
  public ResponseEntity<PaymentTerm> agregarPlazoPago(@RequestBody PaymentTerm paymentTerm) {
    return ResponseEntity.ok(paymentTermService.agregarPlazoPago(paymentTerm));
  }

  @GetMapping("/factura/{facturaId}")
  public ResponseEntity<List<PaymentTerm>> obtenerPlazosPagoPorFactura(@PathVariable("facturaId") Long facturaId) {
    return ResponseEntity.ok(paymentTermService.obtenerPlazosPagoPorFactura(facturaId));
  }
}
