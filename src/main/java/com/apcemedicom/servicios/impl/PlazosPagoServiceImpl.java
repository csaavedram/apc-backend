package com.apcemedicom.servicios.impl;

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
}
