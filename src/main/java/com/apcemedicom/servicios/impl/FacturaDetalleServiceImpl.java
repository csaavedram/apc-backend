package com.apcemedicom.servicios.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcemedicom.modelo.Factura;
import com.apcemedicom.modelo.FacturaDetalle;
import com.apcemedicom.modelo.ProductoSerie;
import com.apcemedicom.repositorios.FacturaDetalleRepository;
import com.apcemedicom.servicios.FacturaDetalleService;

import com.apcemedicom.servicios.ProductoSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FacturaDetalleServiceImpl implements FacturaDetalleService {
  @Autowired
  private FacturaDetalleRepository facturaDetailsRepository;
  
  @Autowired
  private ProductoSerieService productoSerieService;

  @Override
  public FacturaDetalle agregarDetalleFactura(FacturaDetalle facturaDetails) { 
    return facturaDetailsRepository.save(facturaDetails); 
  }
  
  @Override
  public FacturaDetalle agregarDetalleFacturaConSeries(FacturaDetalle facturaDetails, List<String> numerosSerie) {
    // Primero guardamos el detalle de factura
    FacturaDetalle detalleGuardado = facturaDetailsRepository.save(facturaDetails);
    
    // Si el producto requiere número de serie y se proporcionaron números
    if (facturaDetails.getProducto().getRequiereNumeroSerie() && numerosSerie != null && !numerosSerie.isEmpty()) {
      // Validar que la cantidad de números de serie coincida con la cantidad facturada
      if (numerosSerie.size() != facturaDetails.getCantidad()) {
        throw new RuntimeException("La cantidad de números de serie debe coincidir con la cantidad facturada");
      }
      
      // Asignar series específicas
      List<ProductoSerie> seriesAsignadas = new ArrayList<>();
      for (String numeroSerie : numerosSerie) {
        ProductoSerie serie = productoSerieService.buscarPorNumeroSerie(numeroSerie);
        if (serie == null) {
          throw new RuntimeException("Número de serie no encontrado: " + numeroSerie);
        }
        if (!serie.isDisponible()) {
          throw new RuntimeException("Número de serie no disponible: " + numeroSerie);
        }
        if (!serie.getProducto().getProductoId().equals(facturaDetails.getProducto().getProductoId())) {
          throw new RuntimeException("El número de serie no pertenece al producto especificado: " + numeroSerie);
        }
        
        serie.setFacturaDetalle(detalleGuardado);
        serie.setEstado("VENDIDO");
        serie.setFechaVenta(new java.util.Date());
        seriesAsignadas.add(productoSerieService.actualizarProductoSerie(serie));
      }
      
      detalleGuardado.setNumerosSerieAsignados(seriesAsignadas);
    } else if (facturaDetails.getProducto().getRequiereNumeroSerie()) {
      // Asignar automáticamente series disponibles
      List<ProductoSerie> seriesAsignadas = productoSerieService.asignarSeriesAFactura(
        detalleGuardado, facturaDetails.getCantidad());
      detalleGuardado.setNumerosSerieAsignados(seriesAsignadas);
    }
    
    return detalleGuardado;
  }
  
  @Override
  public FacturaDetalle obtenerDetalleFactura(Long facturaDetailsId) { 
    return facturaDetailsRepository.findById(facturaDetailsId).get(); 
  }
  
  @Override
  public FacturaDetalle obtenerDetalleFacturaConSeries(Long facturaDetailsId) {
    FacturaDetalle detalle = obtenerDetalleFactura(facturaDetailsId);
    if (detalle.getProducto().getRequiereNumeroSerie()) {
      List<ProductoSerie> series = productoSerieService.obtenerSeriesPorFacturaDetalle(detalle);
      detalle.setNumerosSerieAsignados(series);
    }
    return detalle;
  }
  
  @Override
  public List<FacturaDetalle> obtenerDetallesFacturaPorFactura(Factura facturaId) {
    return facturaDetailsRepository.findFacturaDetalleByFactura(facturaId);
  }
  @Override
  public Set<FacturaDetalle> obtenerDetallesFacturas() {
    return new LinkedHashSet<>(facturaDetailsRepository.findAll());}
  
  @Override
  public List<FacturaDetalle> obtenerDetallesFacturaPorFacturaConSeries(Factura factura) {
    List<FacturaDetalle> detalles = obtenerDetallesFacturaPorFactura(factura);
    for (FacturaDetalle detalle : detalles) {
      if (detalle.getProducto().getRequiereNumeroSerie()) {
        List<ProductoSerie> series = productoSerieService.obtenerSeriesPorFacturaDetalle(detalle);
        detalle.setNumerosSerieAsignados(series);
      }
    }
    return detalles;
  }
  
  @Override
  public void liberarSeriesDeFacturaDetalle(Long facturaDetalleId) {
    FacturaDetalle detalle = obtenerDetalleFactura(facturaDetalleId);
    productoSerieService.liberarSeriesDeFactura(detalle);
  }
}
