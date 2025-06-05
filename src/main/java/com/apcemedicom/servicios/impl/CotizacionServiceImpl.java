package com.apcemedicom.servicios.impl;

import com.apcemedicom.modelo.Cotizacion;
import com.apcemedicom.repositorios.CotizacionRepository;
import com.apcemedicom.servicios.CotizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class CotizacionServiceImpl implements CotizacionService {
  @Autowired
  private CotizacionRepository quotationRepository;
  
  @Override
  public Cotizacion agregarCotizacion(Cotizacion quotation){ return quotationRepository.save(quotation);}
  @Override
  public Cotizacion actualizarCotizacion(Cotizacion quotation){ return quotationRepository.save(quotation); }
  @Override
  public Set<Cotizacion> obtenerCotizaciones(){ return new LinkedHashSet<>(quotationRepository.findAll());}
  @Override
  public Cotizacion obtenerCotizacion(Long quotationId) { return quotationRepository.findById(quotationId).get();}
  @Override
  public void eliminarCotizacion(Long quotationId) {
    Cotizacion quotation = new Cotizacion();
    quotation.setCotizacionId(quotationId);
    quotationRepository.delete(quotation);
  }
  @Override
  public List<Cotizacion> obtenerCotizacionesPorUsuario(Long userId) {
    return quotationRepository.findByUserId(userId);
  }
  @Override
  public void anularCotizacion(Long quotationId) {
    quotationRepository.findById(quotationId).ifPresent(quotation -> {
        quotation.setEstado("Anulada");
        quotationRepository.save(quotation);
    });
  }
  @Override
  public Cotizacion obtenerCotizacionPorCodigo(String codigo) {
    return quotationRepository.findByCodigo(codigo)
        .orElseThrow(() -> new RuntimeException("Cotización no encontrada con el código: " + codigo));
  }
}