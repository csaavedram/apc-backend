package com.apcemedicom.repositorios;

import com.apcemedicom.modelo.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CotizacionRepository extends JpaRepository<Cotizacion, Long> {
  List<Cotizacion> findByUserId(Long userId);
  Optional<Cotizacion> findByCodigo(String codigo);
}