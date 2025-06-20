package com.apcemedicom.servicios.impl;

import com.apcemedicom.modelo.DetalleServicio;
import com.apcemedicom.repositorios.DetalleServicioRepository;
import com.apcemedicom.servicios.DetalleServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class DetalleServicioServiceImpl implements DetalleServicioService {

    @Autowired
    private DetalleServicioRepository detalleServicioRepository;

    @Override
    public DetalleServicio guardarDetalleServicio(DetalleServicio detalleServicio) {
        return detalleServicioRepository.save(detalleServicio);
    }

    @Override
    public DetalleServicio actualizarDetalleServicio(DetalleServicio detalleServicio) {
        return detalleServicioRepository.save(detalleServicio);
    }

    @Override
    public Set<DetalleServicio> obtenerDetalleServicios() {
        return new LinkedHashSet<>(detalleServicioRepository.findAll());
    }

    @Override
    public DetalleServicio obtenerDetalleServicio(Long detalleServicioId) {
        return detalleServicioRepository.findById(detalleServicioId).get();
    }

    @Override
    public void eliminarDetalleServicio(Long detalleServicioId) {
        DetalleServicio detalleServicio = new DetalleServicio();
        detalleServicio.setDetalleServicioId(detalleServicioId);
        detalleServicioRepository.delete(detalleServicio);
    }

    @Override
    public List<DetalleServicio> obtenerDetalleServiciosPorServicioId(Long servicioId) {
        return detalleServicioRepository.findByServicioId(servicioId);
    }

    @Override
    public List<DetalleServicio> obtenerDetalleServiciosPorSku(String sku) {
        return detalleServicioRepository.findBySku(sku);
    }

    @Override
    public List<DetalleServicio> obtenerDetalleServiciosPorNumeroSerie(String numeroSerie) {
        return detalleServicioRepository.findByNumeroSerie(numeroSerie);
    }
}
