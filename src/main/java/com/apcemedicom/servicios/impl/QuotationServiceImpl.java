package com.apcemedicom.servicios.impl;

import com.apcemedicom.modelo.Quotation;
import com.apcemedicom.repositorios.QuotationRepository;
import com.apcemedicom.servicios.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuotationServiceImpl implements QuotationService {
  @Autowired
  private QuotationRepository quotationRepository;
  
  @Override
  public Quotation agregarCotizacion(Quotation quotation){ return quotationRepository.save(quotation);}
  @Override
  public Quotation actualizarCotizacion(Quotation quotation){ return quotationRepository.save(quotation); }
  @Override
  public Set<Quotation> obtenerCotizaciones(){ return new LinkedHashSet<>(quotationRepository.findAll());}
  @Override
  public Quotation obtenerCotizacion(Long quotationId) { return quotationRepository.findById(quotationId).get();}
  @Override
  public void eliminarCotizacion(Long quotationId) {
    Quotation quotation = new Quotation();
    quotation.setQuotationId(quotationId);
    quotationRepository.delete(quotation);
  }
  @Override
  public List<Quotation> obtenerCotizacionesPorUsuario(Long userId) {
    return quotationRepository.findByUserId(userId);
  }
}