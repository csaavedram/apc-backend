package com.apcemedicom.servicios.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcemedicom.modelo.Factura;
import com.apcemedicom.modelo.FacturaDetalle;
import com.apcemedicom.repositorios.FacturaDetalleRepository;
import com.apcemedicom.servicios.FacturaDetalleService;

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
  @Override
  public Set<FacturaDetalle> obtenerDetallesFacturas() {
    return new LinkedHashSet<>(facturaDetailsRepository.findAll());}
}
