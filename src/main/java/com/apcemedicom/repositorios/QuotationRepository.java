package com.apcemedicom.repositorios;

import com.apcemedicom.modelo.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuotationRepository extends JpaRepository<Quotation, Long> {
  List<Quotation> findByUserId(Long userId);
  Optional<Quotation> findByCodigo(String codigo);
}