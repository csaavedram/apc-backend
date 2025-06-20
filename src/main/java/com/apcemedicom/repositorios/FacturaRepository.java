package com.apcemedicom.repositorios;

import com.apcemedicom.modelo.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
  List<Factura> findByUserId(Long userId);
  Optional<Factura> findByCodigo(String codigo);
  List<Factura> findByCotizacion_Codigo(String codigoCotizacion);
  List<Factura> findByCotizacion_CotizacionId(Long cotizacionId);
}
