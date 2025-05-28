package com.apcemedicom.servicios;

import com.apcemedicom.modelo.Factura;
import com.apcemedicom.modelo.FacturaDetalle;

import java.util.List;

public interface FacturaDetalleService {
    FacturaDetalle agregarDetalleFactura(FacturaDetalle facturaDetails);
    FacturaDetalle obtenerDetalleFactura(Long facturaDetailsId);
    List<FacturaDetalle> obtenerDetallesFacturaPorFactura(Factura facturaId);
}
