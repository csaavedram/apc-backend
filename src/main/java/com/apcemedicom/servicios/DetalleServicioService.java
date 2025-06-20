package com.apcemedicom.servicios;

import com.apcemedicom.modelo.DetalleServicio;

import java.util.List;
import java.util.Set;

public interface DetalleServicioService {

    DetalleServicio guardarDetalleServicio(DetalleServicio detalleServicio);

    DetalleServicio actualizarDetalleServicio(DetalleServicio detalleServicio);

    Set<DetalleServicio> obtenerDetalleServicios();

    DetalleServicio obtenerDetalleServicio(Long detalleServicioId);

    void eliminarDetalleServicio(Long detalleServicioId);

    List<DetalleServicio> obtenerDetalleServiciosPorServicioId(Long servicioId);

    List<DetalleServicio> obtenerDetalleServiciosPorSku(String sku);

    List<DetalleServicio> obtenerDetalleServiciosPorNumeroSerie(String numeroSerie);
}
