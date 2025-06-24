package com.apcemedicom.servicios.impl;

import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
  
  private final PaymentClient paymentClient;
  
  public PaymentService() {
    this.paymentClient = new PaymentClient();
  }
    public Payment createPayment(PaymentCreateRequest request) throws MPException, MPApiException {
    // Log detallado antes de crear el request
    System.out.println("🔍 === PAYMENTSERVICE - CREANDO REQUEST PARA MERCADOPAGO ===");
    System.out.println("🎯 Token: " + request.getToken());
    System.out.println("💰 Amount: " + request.getTransactionAmount());
    System.out.println("📝 Description: " + request.getDescription());
    System.out.println("💳 Payment Method: " + request.getPaymentMethodId());
    System.out.println("📊 Installments: " + request.getInstallments());
    
    if (request.getPayer() != null) {
        System.out.println("👤 Payer Email: " + request.getPayer().getEmail());
        if (request.getPayer().getIdentification() != null) {
            System.out.println("🆔 ID Type: " + request.getPayer().getIdentification().getType());
            System.out.println("🆔 ID Number: " + request.getPayer().getIdentification().getNumber());
        }
    }
    
    PaymentCreateRequest paymentRequest = PaymentCreateRequest.builder()
      .token(request.getToken())
      .transactionAmount(request.getTransactionAmount())
      .description(request.getDescription())
      .paymentMethodId(request.getPaymentMethodId())
      .payer(request.getPayer())
      .installments(request.getInstallments())
      .build();
    
    System.out.println("🚀 Llamando a MercadoPago API...");
    
    try {
        Payment payment = paymentClient.create(paymentRequest);
        System.out.println("✅ Respuesta exitosa de MercadoPago");
        return payment;
    } catch (MPApiException e) {
        System.err.println("❌ Error MPApiException en PaymentService:");
        System.err.println("📨 Status: " + e.getStatusCode());
        System.err.println("📨 Message: " + e.getMessage());
        System.err.println("📨 Response: " + e.getApiResponse().getContent());
        throw e;
    } catch (MPException e) {
        System.err.println("❌ Error MPException en PaymentService: " + e.getMessage());
        throw e;
    }
  }
}
