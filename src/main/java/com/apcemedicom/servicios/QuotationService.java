package com.apcemedicom.servicios;

import com.apcemedicom.modelo.Quotation;

import java.util.List;
import java.util.Set;

public interface QuotationService {
  Quotation agregarCotizacion(Quotation quotation);
  Quotation actualizarCotizacion(Quotation quotation);
  Set<Quotation> obtenerCotizaciones();
  Quotation obtenerCotizacion(Long quotationId);
  void eliminarCotizacion(Long quotationId);
  List<Quotation> obtenerCotizacionesPorUsuario(Long userId);
}