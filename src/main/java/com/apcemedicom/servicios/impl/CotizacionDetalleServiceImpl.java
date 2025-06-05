package com.apcemedicom.servicios.impl;

import com.apcemedicom.modelo.Cotizacion;
import com.apcemedicom.modelo.CotizacionDetalle;
import com.apcemedicom.repositorios.CotizacionDetalleRepository;
import com.apcemedicom.servicios.CotizacionDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class CotizacionDetalleServiceImpl implements CotizacionDetalleService {
  @Autowired
  private CotizacionDetalleRepository quotationDetailsRepository;
  
  @Override
  public CotizacionDetalle agregarDetalleCotizacion(CotizacionDetalle quotationDetails) { return quotationDetailsRepository.save(quotationDetails); }
  @Override
  public CotizacionDetalle actualizarDetalleCotizacion(CotizacionDetalle quotationDetails) { return quotationDetailsRepository.save(quotationDetails); }
  @Override
  public Set<CotizacionDetalle> obtenerDetallesCotizaciones() { return new LinkedHashSet<>(quotationDetailsRepository.findAll()); }
  @Override
  public CotizacionDetalle obtenerDetalleCotizacion(Long quotationDetailsId) { return quotationDetailsRepository.findById(quotationDetailsId).get(); }
  @Override
  public void eliminarDetalleCotizacion(Long quotationDetailsId) {
    CotizacionDetalle quotationDetails = new CotizacionDetalle();
    quotationDetails.setCotizacionDetalleId(quotationDetailsId);
    quotationDetailsRepository.delete(quotationDetails);
  }
  @Override
  public List<CotizacionDetalle> obtenerDetallesCotizacionPorCotizacion(Cotizacion quotationId) {
    return quotationDetailsRepository.findCotizacionDetallesByCotizacion(quotationId);
  }
}