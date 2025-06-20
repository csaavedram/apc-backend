package com.apcemedicom.servicios;

import com.apcemedicom.modelo.PlazosPago;

import java.util.List;

public interface PlazosPagoService {
    PlazosPago agregarPlazoPago(PlazosPago paymentTerm);
    List<PlazosPago> obtenerPlazosPagoPorFactura(Long facturaId);
    List<PlazosPago> obtenerPlazosPagoPorCotizacion(Long cotizacionId);
    PlazosPago actualizarFacturaEnPlazoPago(Long plazoPagoId, Long facturaId);
    PlazosPago actualizarPlazoPago(PlazosPago plazoPago);
    void eliminarPlazoPago(Long plazoPagoId);
    List<PlazosPago> obtenerPlazosPagoPorNotaCredito(Long notaCreditoId);
    PlazosPago actualizarNotaCreditoEnPlazoPago(Long plazoPagoId, Long notaCreditoId);
    PlazosPago cambiarEstadoAPagado(Long plazoPagoId);
}
