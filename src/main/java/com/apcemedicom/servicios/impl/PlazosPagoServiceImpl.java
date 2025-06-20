package com.apcemedicom.servicios.impl;

import com.apcemedicom.modelo.Factura;
import com.apcemedicom.modelo.PlazosPago;
import com.apcemedicom.repositorios.PlazosPagoRepository;
import com.apcemedicom.servicios.PlazosPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlazosPagoServiceImpl implements PlazosPagoService {

  @Autowired
  private PlazosPagoRepository plazosPagoRepository;

  @Override
  public PlazosPago agregarPlazoPago(PlazosPago plazoPago) {
      return plazosPagoRepository.save(plazoPago);
  }
  @Override
  public List<PlazosPago> obtenerPlazosPagoPorFactura(Long facturaId) {
      return plazosPagoRepository.findByFactura_FacturaId(facturaId);
  }

  @Override
  public List<PlazosPago> obtenerPlazosPagoPorCotizacion(Long cotizacionId) {
      return plazosPagoRepository.findByCotizacion_CotizacionId(cotizacionId);
  }

  @Override
  public PlazosPago actualizarFacturaEnPlazoPago(Long plazoPagoId, Long facturaId) {
    PlazosPago plazoPago = plazosPagoRepository.findById(plazoPagoId).orElseThrow(() -> new RuntimeException("Plazo de pago no encontrado"));
    Factura factura = new Factura();
    factura.setFacturaId(facturaId);
    plazoPago.setFactura(factura);
    return plazosPagoRepository.save(plazoPago);
  }

  @Override
  public PlazosPago actualizarPlazoPago(PlazosPago plazoPago) {
    return plazosPagoRepository.save(plazoPago);
  }

  @Override
  public void eliminarPlazoPago(Long plazoPagoId) {
      plazosPagoRepository.deleteById(plazoPagoId);
  }
}
