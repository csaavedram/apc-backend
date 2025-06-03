package com.apcemedicom.servicios;

import java.util.List;

import com.apcemedicom.modelo.OrdenCotizacion;

public interface OrdenCotizacionService {
    List<OrdenCotizacion> obtenerOrdenesPorOrderId(Long orderId);
    List<OrdenCotizacion> obtenerOrdenesPorCotizacionId(Long cotizacionId);
    OrdenCotizacion agregarOrdenCotizacion(OrdenCotizacion ordenCotizacion);
}