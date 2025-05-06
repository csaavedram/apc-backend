package com.apcemedicom.servicios;

import com.apcemedicom.modelo.Factura;
import com.apcemedicom.modelo.FacturaDetails;

import java.util.List;

public interface FacturaDetailsService {
    FacturaDetails agregarDetalleFactura(FacturaDetails facturaDetails);
    FacturaDetails obtenerDetalleFactura(Long facturaDetailsId);
    List<FacturaDetails> obtenerDetallesFacturaPorFactura(Factura facturaId);
}
