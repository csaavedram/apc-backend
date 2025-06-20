package com.apcemedicom.servicios;

import java.util.List;
import java.util.Set;

import com.apcemedicom.modelo.Factura;
import com.apcemedicom.modelo.FacturaDetalle;
import com.apcemedicom.modelo.ProductoSerie;

public interface FacturaDetalleService {
    FacturaDetalle agregarDetalleFactura(FacturaDetalle facturaDetails);
    FacturaDetalle agregarDetalleFacturaConSeries(FacturaDetalle facturaDetails, List<String> numerosSerie);
    FacturaDetalle obtenerDetalleFactura(Long facturaDetailsId);
    FacturaDetalle obtenerDetalleFacturaConSeries(Long facturaDetailsId);
    List<FacturaDetalle> obtenerDetallesFacturaPorFactura(Factura facturaId);
    Set<FacturaDetalle> obtenerDetallesFacturas();
    List<FacturaDetalle> obtenerDetallesFacturaPorFacturaConSeries(Factura facturaId);
    void liberarSeriesDeFacturaDetalle(Long facturaDetalleId);
}
