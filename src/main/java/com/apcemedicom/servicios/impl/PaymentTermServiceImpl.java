package com.apcemedicom.servicios.impl;

import com.apcemedicom.modelo.PaymentTerm;
import com.apcemedicom.repositorios.PaymentTermRepository;
import com.apcemedicom.servicios.PaymentTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentTermServiceImpl implements PaymentTermService {

  @Autowired
  private PaymentTermRepository paymentTermRepository;

  @Override
  public PaymentTerm agregarPlazoPago(PaymentTerm paymentTerm) {
      return paymentTermRepository.save(paymentTerm);
  }
  @Override
  public List<PaymentTerm> obtenerPlazosPagoPorFactura(Long facturaId) {
      return paymentTermRepository.findByFactura_FacturaId(facturaId);
  }
}
