package com.apcemedicom.repositorios;

import com.apcemedicom.modelo.PaymentTerm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentTermRepository extends JpaRepository<PaymentTerm, Long> {
    List<PaymentTerm> findByFactura_FacturaId(Long facturaId);
}
