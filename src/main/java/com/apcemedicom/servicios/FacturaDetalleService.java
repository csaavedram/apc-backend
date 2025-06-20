package com.apcemedicom.servicios;

import java.util.List;
import java.util.Set;

import com.apcemedicom.modelo.Factura;
import com.apcemedicom.modelo.FacturaDetalle;

public interface FacturaDetalleService {
    FacturaDetalle agregarDetalleFactura(FacturaDetalle facturaDetails);
    FacturaDetalle obtenerDetalleFactura(Long facturaDetailsId);
    List<FacturaDetalle> obtenerDetallesFacturaPorFactura(Factura facturaId);
    Set<FacturaDetalle> obtenerDetallesFacturas();
}
