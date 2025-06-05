package com.apcemedicom.servicios;

import com.apcemedicom.modelo.Cotizacion;

import java.util.List;
import java.util.Set;

public interface CotizacionService {
  Cotizacion agregarCotizacion(Cotizacion quotation);
  Cotizacion actualizarCotizacion(Cotizacion quotation);
  Set<Cotizacion> obtenerCotizaciones();
  Cotizacion obtenerCotizacion(Long quotationId);
  void eliminarCotizacion(Long quotationId);
  List<Cotizacion> obtenerCotizacionesPorUsuario(Long userId);
  void anularCotizacion(Long quotationId);
  Cotizacion obtenerCotizacionPorCodigo(String codigo);
}