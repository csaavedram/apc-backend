package com.apcemedicom.repositorios;

import com.apcemedicom.modelo.NotaCredito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaCreditoRepository extends JpaRepository<NotaCredito, Long>{
    List<NotaCredito> findByUserId(Long userId);
    List<NotaCredito> findByFactura_Codigo(String codigo);
}
