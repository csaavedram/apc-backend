package com.apcemedicom.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcemedicom.modelo.NotaCredito;
import com.apcemedicom.modelo.NotaCreditoDetalle;
import com.apcemedicom.repositorios.NotaCreditoDetalleRepository;
import com.apcemedicom.servicios.NotaCreditoDetalleService;

@Service
public class NotaCreditoDetalleServiceImpl implements NotaCreditoDetalleService {
  @Autowired
  private NotaCreditoDetalleRepository notaCreditoDetalleRepository;

  @Override
  public NotaCreditoDetalle agregarDetalleNotaCredito(NotaCreditoDetalle notaCreditoDetalle) { return notaCreditoDetalleRepository.save(notaCreditoDetalle); }
  @Override
  public NotaCreditoDetalle obtenerDetalleNotaCredito(Long notaCreditoDetalleId) { return notaCreditoDetalleRepository.findById(notaCreditoDetalleId).get(); }
  @Override
  public List<NotaCreditoDetalle> obtenerDetallesNotaCreditoPorNotaCredito(NotaCredito notaCreditoId) {
    return notaCreditoDetalleRepository.findNotaCreditoDetalleByNotaCredito(notaCreditoId);
  }
}
