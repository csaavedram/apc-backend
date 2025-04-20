package com.apcemedicom.repositorios;

import com.apcemedicom.modelo.QuotationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuotationDetailsRepository extends JpaRepository<QuotationDetails, Long> {
  List<QuotationDetails> findQuotationDetailsByQuotation(Long quotationId);
}
