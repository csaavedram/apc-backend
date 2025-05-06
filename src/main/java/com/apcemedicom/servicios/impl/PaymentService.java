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
    PaymentCreateRequest paymentRequest = PaymentCreateRequest.builder()
      .token(request.getToken())
      .transactionAmount(request.getTransactionAmount())
      .description(request.getDescription())
      .paymentMethodId(request.getPaymentMethodId())
      .payer(request.getPayer())
      .installments(request.getInstallments())
      .build();
    
    return paymentClient.create(paymentRequest);
  }
}
