package com.apcemedicom.repositorios;

import com.apcemedicom.modelo.DetalleServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetalleServicioRepository extends JpaRepository<DetalleServicio, Long> {
    
    @Query("SELECT ds FROM DetalleServicio ds WHERE ds.servicio.servicioId = :servicioId")
    List<DetalleServicio> findByServicioId(@Param("servicioId") Long servicioId);
    
    @Query("SELECT ds FROM DetalleServicio ds WHERE ds.sku = :sku")
    List<DetalleServicio> findBySku(@Param("sku") String sku);
    
    @Query("SELECT ds FROM DetalleServicio ds WHERE ds.numeroSerie = :numeroSerie")
    List<DetalleServicio> findByNumeroSerie(@Param("numeroSerie") String numeroSerie);
}
