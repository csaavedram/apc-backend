package com.apcemedicom.repositorios;

import com.apcemedicom.modelo.FacturaDetails;
import com.apcemedicom.modelo.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacturaDetailsRepository extends JpaRepository<FacturaDetails, Long> {
  List<FacturaDetails> findFacturaDetailsByFactura(Factura factura);
}
