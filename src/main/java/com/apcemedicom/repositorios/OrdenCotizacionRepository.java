package com.apcemedicom.repositorios;

import com.apcemedicom.modelo.OrdenCotizacion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenCotizacionRepository extends JpaRepository<OrdenCotizacion, Long> {
    List<OrdenCotizacion> findByOrderOrderId(Long orderId);
    List<OrdenCotizacion> findByCotizacionCotizacionId(Long cotizacionId);
}
