package com.apcemedicom.repositorios;

import com.apcemedicom.modelo.PlazosPago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlazosPagoRepository extends JpaRepository<PlazosPago, Long> {
    List<PlazosPago> findByFactura_FacturaId(Long facturaId);
    List<PlazosPago> findByCotizacion_CotizacionId(Long cotizacionId);
    List<PlazosPago> findByNotaCredito_NotaCreditoId(Long notaCreditoId);
}
