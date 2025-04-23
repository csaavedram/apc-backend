package com.apcemedicom.servicios;

import com.apcemedicom.modelo.Quotation;
import com.apcemedicom.modelo.QuotationDetails;

import java.util.List;
import java.util.Set;

public interface QuotationDetailsService {
    QuotationDetails agregarDetalleCotizacion(QuotationDetails quotationDetails);
    QuotationDetails actualizarDetalleCotizacion(QuotationDetails quotationDetails);
    Set<QuotationDetails> obtenerDetallesCotizaciones();
    QuotationDetails obtenerDetalleCotizacion(Long quotationDetailsId);
    void eliminarDetalleCotizacion(Long quotationDetailsId);
    List<QuotationDetails> obtenerDetallesCotizacionPorCotizacion(Quotation quotationId);
}
