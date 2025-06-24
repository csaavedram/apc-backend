package com.apcemedicom.servicios;

import java.util.List;
import java.util.Set;
import java.util.Map;

import com.apcemedicom.modelo.Factura;
import com.apcemedicom.modelo.FacturaDetalle;
import com.apcemedicom.modelo.ProductoSerie;
import com.apcemedicom.dto.FacturaDetalleDashboardDTO;
import com.apcemedicom.dto.GarantiaDTO;

public interface FacturaDetalleService {
    FacturaDetalle agregarDetalleFactura(FacturaDetalle facturaDetails);
    FacturaDetalle agregarDetalleFacturaConSeries(FacturaDetalle facturaDetails, List<String> numerosSerie);
    FacturaDetalle obtenerDetalleFactura(Long facturaDetailsId);
    FacturaDetalle obtenerDetalleFacturaConSeries(Long facturaDetailsId);
    List<FacturaDetalle> obtenerDetallesFacturaPorFactura(Factura facturaId);
    Set<FacturaDetalle> obtenerDetallesFacturas();    List<FacturaDetalleDashboardDTO> obtenerDetallesFacturasParaDashboard(); // Usando DTO
    // TODO: Revisar firma del método
    // List<FacturaDetalle> obtenerDetallesFacturaPorFacturaConSeries(Factura facturaId);
    void liberarSeriesDeFacturaDetalle(Long facturaDetalleId);
    List<FacturaDetalle> buscarDetallesPorNumeroSerie(String numeroSerie);
    List<GarantiaDTO> buscarGarantiaPorNumeroSerie(String numeroSerie);
    Map<String, Object> buscarGarantiaCompleta(String numeroSerie);
}
