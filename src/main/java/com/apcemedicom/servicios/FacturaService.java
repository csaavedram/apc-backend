package com.apcemedicom.servicios;

import com.apcemedicom.modelo.Factura;

import java.util.List;
import java.util.Set;

public interface FacturaService {
  Factura agregarFactura(Factura factura);
  Set<Factura> obtenerFacturas();
  Factura obtenerFactura(Long facturaId);
  List<Factura> obtenerFacturasPorUsuario(Long userId);
  void anularFactura(Long facturaId);
  Factura obtenerFacturaPorCodigo(String codigo);
  List<Factura> obtenerFacturasPorCodigoCotizacion(String codigoCotizacion);
  List<Factura> obtenerFacturasPorCotizacionId(Long cotizacionId);
}
