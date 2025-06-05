package com.apcemedicom.servicios.impl;

import com.apcemedicom.modelo.Factura;
import com.apcemedicom.modelo.FacturaDetalle;
import com.apcemedicom.repositorios.FacturaDetalleRepository;
import com.apcemedicom.servicios.FacturaDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaDetalleServiceImpl implements FacturaDetalleService {
  @Autowired
  private FacturaDetalleRepository facturaDetailsRepository;

  @Override
  public FacturaDetalle agregarDetalleFactura(FacturaDetalle facturaDetails) { return facturaDetailsRepository.save(facturaDetails); }
  @Override
  public FacturaDetalle obtenerDetalleFactura(Long facturaDetailsId) { return facturaDetailsRepository.findById(facturaDetailsId).get(); }
  @Override
  public List<FacturaDetalle> obtenerDetallesFacturaPorFactura(Factura facturaId) {
    return facturaDetailsRepository.findFacturaDetalleByFactura(facturaId);
  }
}
