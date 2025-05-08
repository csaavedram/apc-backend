package com.apcemedicom.controladores;

import com.apcemedicom.dtos.CreatePaymentDto;
import com.apcemedicom.servicios.impl.PaymentService;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mercadopago.resources.payment.Payment;

import java.math.BigDecimal;

@RestController
@RequestMapping("/payments")
@CrossOrigin("*")
public class PaymentController {
  @Autowired
  private PaymentService paymentService;
  
  @PostMapping("/")
  public ResponseEntity<?> createPayment(@RequestBody CreatePaymentDto request) {
    PaymentCreateRequest sdkRequest = PaymentCreateRequest.builder()
      .token(request.token)
      .issuerId(request.issuer_id)
      .paymentMethodId(request.payment_method_id)
      .transactionAmount(BigDecimal.valueOf(request.transaction_amount))
      .installments(request.installments)
      .description(request.description)
      .payer(PaymentPayerRequest.builder()
        .email(request.payer.email)
        .identification(IdentificationRequest.builder()
          .type(request.payer.identification.type)
          .number(request.payer.identification.number)
          .build())
        .build())
      .build();
    
    try {
      Payment payment = paymentService.createPayment(sdkRequest);
      return ResponseEntity.ok(payment);
    } catch (MPException | MPApiException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}
