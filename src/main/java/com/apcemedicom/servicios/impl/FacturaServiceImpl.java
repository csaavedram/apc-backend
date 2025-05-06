package com.apcemedicom.servicios.impl;

import com.apcemedicom.modelo.Factura;
import com.apcemedicom.repositorios.FacturaRepository;
import com.apcemedicom.servicios.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class FacturaServiceImpl implements FacturaService {
  @Autowired
  private FacturaRepository facturaRepository;

  @Override
  public Factura agregarFactura(Factura factura) { return facturaRepository.save(factura); }
  @Override
  public Set<Factura> obtenerFacturas() { return new LinkedHashSet<>(facturaRepository.findAll()); }
  @Override
  public Factura obtenerFactura(Long facturaId) { return facturaRepository.findById(facturaId).get(); }
  @Override
  public List<Factura> obtenerFacturasPorUsuario(Long userId) {
    return facturaRepository.findByUserId(userId);
  }
}
