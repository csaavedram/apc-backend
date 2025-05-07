package com.apcemedicom.servicios;

import com.apcemedicom.modelo.PaymentTerm;

import java.util.List;

public interface PaymentTermService {
    PaymentTerm agregarPlazoPago(PaymentTerm paymentTerm);
    List<PaymentTerm> obtenerPlazosPagoPorFactura(Long facturaId);
}
