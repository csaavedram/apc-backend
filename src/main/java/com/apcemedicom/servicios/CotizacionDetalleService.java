package com.apcemedicom.servicios;

import com.apcemedicom.modelo.Cotizacion;
import com.apcemedicom.modelo.CotizacionDetalle;

import java.util.List;
import java.util.Set;

public interface CotizacionDetalleService {
    CotizacionDetalle agregarDetalleCotizacion(CotizacionDetalle quotationDetails);
    CotizacionDetalle actualizarDetalleCotizacion(CotizacionDetalle quotationDetails);
    Set<CotizacionDetalle> obtenerDetallesCotizaciones();
    CotizacionDetalle obtenerDetalleCotizacion(Long quotationDetailsId);
    void eliminarDetalleCotizacion(Long quotationDetailsId);
    List<CotizacionDetalle> obtenerDetallesCotizacionPorCotizacion(Cotizacion quotationId);
}
