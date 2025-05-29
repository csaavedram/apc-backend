package com.apcemedicom.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apcemedicom.modelo.NotaCredito;
import com.apcemedicom.modelo.NotaCreditoDetalle;

import java.util.List;

public interface NotaCreditoDetalleRepository extends JpaRepository<NotaCreditoDetalle, Long> {
  List<NotaCreditoDetalle> findNotaCreditoDetalleByNotaCredito(NotaCredito notaCredito);
}
