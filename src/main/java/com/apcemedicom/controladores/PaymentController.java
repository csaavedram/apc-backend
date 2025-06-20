package com.apcemedicom.controladores;

import com.apcemedicom.dtos.CreatePaymentDto;
import com.apcemedicom.servicios.impl.PaymentService;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/payments")
@CrossOrigin("*")
public class PaymentController {

  @Autowired
  private PaymentService paymentService;

  @PostMapping("/")
  public ResponseEntity<?> createPayment(@RequestBody CreatePaymentDto request) {
    try {
        PaymentCreateRequest sdkRequest = PaymentCreateRequest.builder()
            .token(request.getToken())
            .paymentMethodId(request.getPaymentMethodId())
            .transactionAmount(BigDecimal.valueOf(request.getTransactionAmount()))
            .installments(request.getInstallments())
            .description(request.getDescription())
            .payer(PaymentPayerRequest.builder()
                .email(request.getPayer().getEmail())
                .identification(IdentificationRequest.builder()
                    .type(request.getPayer().getIdentification().getType())
                    .number(request.getPayer().getIdentification().getNumber())
                    .build())
                .build())
            .build();

        Payment payment = paymentService.createPayment(sdkRequest);
        return ResponseEntity.ok(payment);

    } catch (MPApiException e) {
        // Mostrar detalle del error de Mercado Pago
        System.err.println("❌ MPApiException: " + e.getMessage());
        System.err.println("📩 Detalle del error (JSON): " + e.getApiResponse().getContent());

        return ResponseEntity
            .status(e.getStatusCode())
            .body(e.getApiResponse().getContent()); // Devuelve el JSON como String
    } catch (MPException e) {
        System.err.println("❌ MPException: " + e.getMessage());
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Error MP: " + e.getMessage());
    } catch (Exception e) {
        System.err.println("❌ Error inesperado: " + e.getMessage());
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error inesperado: " + e.getMessage());
    }
  }
}
