package com.apcemedicom.repositorios;

import com.apcemedicom.modelo.CotizacionDetalle;
import com.apcemedicom.modelo.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CotizacionDetalleRepository extends JpaRepository<CotizacionDetalle, Long> {
  List<CotizacionDetalle> findCotizacionDetallesByCotizacion(Cotizacion quotation);
}
