package com.apcemedicom.servicios;

import com.apcemedicom.modelo.PlazosPago;

import java.util.List;

public interface PlazosPagoService {
    PlazosPago agregarPlazoPago(PlazosPago paymentTerm);
    List<PlazosPago> obtenerPlazosPagoPorFactura(Long facturaId);
}
