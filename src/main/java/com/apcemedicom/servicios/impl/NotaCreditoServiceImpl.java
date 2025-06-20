package com.apcemedicom.servicios.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcemedicom.modelo.NotaCredito;
import com.apcemedicom.repositorios.NotaCreditoRepository;
import com.apcemedicom.servicios.NotaCreditoService;

@Service
public class NotaCreditoServiceImpl implements NotaCreditoService {
  @Autowired
  private NotaCreditoRepository notaCreditoRepository;

  @Override
  public NotaCredito agregarNotaCredito(NotaCredito notaCredito) { return notaCreditoRepository.save(notaCredito); }
  @Override
  public Set<NotaCredito> obtenerNotasCredito() { return new LinkedHashSet<>(notaCreditoRepository.findAll()); }
  @Override
  public NotaCredito obtenerNotaCredito(Long notaCreditoId) { return notaCreditoRepository.findById(notaCreditoId).get(); }
  @Override
  public List<NotaCredito> obtenerNotaCreditoPorCodigoFactura(String codigoFactura) {
    return notaCreditoRepository.findByFactura_Codigo(codigoFactura);
  }
}
