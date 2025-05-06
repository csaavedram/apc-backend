package com.apcemedicom.repositorios;

import com.apcemedicom.modelo.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
  List<Factura> findByUserId(Long userId);
}
