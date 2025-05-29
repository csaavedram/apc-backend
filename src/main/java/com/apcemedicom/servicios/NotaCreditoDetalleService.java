package com.apcemedicom.servicios;

import java.util.List;

import com.apcemedicom.modelo.NotaCredito;
import com.apcemedicom.modelo.NotaCreditoDetalle;

public interface NotaCreditoDetalleService {
  NotaCreditoDetalle agregarDetalleNotaCredito(NotaCreditoDetalle notaCreditoDetalle);
  NotaCreditoDetalle obtenerDetalleNotaCredito(Long notaCreditoDetalleId);
  List<NotaCreditoDetalle> obtenerDetallesNotaCreditoPorNotaCredito(NotaCredito notaCreditoId);
}
