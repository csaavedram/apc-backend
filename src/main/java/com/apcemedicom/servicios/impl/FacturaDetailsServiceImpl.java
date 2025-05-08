package com.apcemedicom.servicios.impl;

import com.apcemedicom.modelo.Factura;
import com.apcemedicom.modelo.FacturaDetails;
import com.apcemedicom.repositorios.FacturaDetailsRepository;
import com.apcemedicom.servicios.FacturaDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaDetailsServiceImpl implements FacturaDetailsService {
  @Autowired
  private FacturaDetailsRepository facturaDetailsRepository;

  @Override
  public FacturaDetails agregarDetalleFactura(FacturaDetails facturaDetails) { return facturaDetailsRepository.save(facturaDetails); }
  @Override
  public FacturaDetails obtenerDetalleFactura(Long facturaDetailsId) { return facturaDetailsRepository.findById(facturaDetailsId).get(); }
  @Override
  public List<FacturaDetails> obtenerDetallesFacturaPorFactura(Factura facturaId) {
    return facturaDetailsRepository.findFacturaDetailsByFactura(facturaId);
  }
}
