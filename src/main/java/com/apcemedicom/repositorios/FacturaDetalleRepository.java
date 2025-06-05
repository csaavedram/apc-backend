package com.apcemedicom.repositorios;

import com.apcemedicom.modelo.FacturaDetalle;
import com.apcemedicom.modelo.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacturaDetalleRepository extends JpaRepository<FacturaDetalle, Long> {
  List<FacturaDetalle> findFacturaDetalleByFactura(Factura factura);
}
