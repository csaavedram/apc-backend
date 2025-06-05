package com.apcemedicom.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcemedicom.modelo.OrdenCotizacion;
import com.apcemedicom.repositorios.OrdenCotizacionRepository;
import com.apcemedicom.servicios.OrdenCotizacionService;

@Service
public class OrdenCotizacionServiceImpl implements OrdenCotizacionService {
  @Autowired
  private OrdenCotizacionRepository ordenCotizacionRepository;

  @Override
  public OrdenCotizacion agregarOrdenCotizacion(OrdenCotizacion ordenCotizacion) {
    return ordenCotizacionRepository.save(ordenCotizacion);
  }

  @Override
  public List<OrdenCotizacion> obtenerOrdenesPorOrderId(Long orderId) {
    return ordenCotizacionRepository.findByOrderOrderId(orderId);
  }

  @Override
  public List<OrdenCotizacion> obtenerOrdenesPorCotizacionId(Long cotizacionId) {
    return ordenCotizacionRepository.findByCotizacionCotizacionId(cotizacionId);
  }
}
