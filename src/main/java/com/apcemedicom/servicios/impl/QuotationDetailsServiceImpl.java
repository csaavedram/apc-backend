package com.apcemedicom.servicios.impl;

import com.apcemedicom.modelo.Quotation;
import com.apcemedicom.modelo.QuotationDetails;
import com.apcemedicom.repositorios.QuotationDetailsRepository;
import com.apcemedicom.servicios.QuotationDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuotationDetailsServiceImpl implements QuotationDetailsService {
  @Autowired
  private QuotationDetailsRepository quotationDetailsRepository;
  
  @Override
  public QuotationDetails agregarDetalleCotizacion(QuotationDetails quotationDetails) { return quotationDetailsRepository.save(quotationDetails); }
  @Override
  public QuotationDetails actualizarDetalleCotizacion(QuotationDetails quotationDetails) { return quotationDetailsRepository.save(quotationDetails); }
  @Override
  public Set<QuotationDetails> obtenerDetallesCotizaciones() { return new LinkedHashSet<>(quotationDetailsRepository.findAll()); }
  @Override
  public QuotationDetails obtenerDetalleCotizacion(Long quotationDetailsId) { return quotationDetailsRepository.findById(quotationDetailsId).get(); }
  @Override
  public void eliminarDetalleCotizacion(Long quotationDetailsId) {
    QuotationDetails quotationDetails = new QuotationDetails();
    quotationDetails.setQuotationdetailsId(quotationDetailsId);
    quotationDetailsRepository.delete(quotationDetails);
  }
  @Override
  public List<QuotationDetails> obtenerDetallesCotizacionPorCotizacion(Quotation quotationId) {
    return quotationDetailsRepository.findQuotationDetailsByQuotation(quotationId);
  }
}