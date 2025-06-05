package com.apcemedicom.servicios;

import java.util.Set;

import com.apcemedicom.modelo.NotaCredito;

public interface NotaCreditoService {
  NotaCredito agregarNotaCredito(NotaCredito notaCredito);
  Set<NotaCredito> obtenerNotasCredito();
  NotaCredito obtenerNotaCredito(Long notaCreditoId);
}
