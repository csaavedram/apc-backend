package com.apcemedicom.servicios;

import com.apcemedicom.modelo.Factura;
import com.apcemedicom.modelo.FacturaDetalle;
import com.apcemedicom.modelo.ProductoSerie;

import java.util.List;

public interface FacturaDetalleService {
    FacturaDetalle agregarDetalleFactura(FacturaDetalle facturaDetails);
    FacturaDetalle agregarDetalleFacturaConSeries(FacturaDetalle facturaDetails, List<String> numerosSerie);
    FacturaDetalle obtenerDetalleFactura(Long facturaDetailsId);
    FacturaDetalle obtenerDetalleFacturaConSeries(Long facturaDetailsId);
    List<FacturaDetalle> obtenerDetallesFacturaPorFactura(Factura facturaId);
    List<FacturaDetalle> obtenerDetallesFacturaPorFacturaConSeries(Factura facturaId);
    void liberarSeriesDeFacturaDetalle(Long facturaDetalleId);
}
