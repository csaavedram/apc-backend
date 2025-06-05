package com.apcemedicom.controladores;

import com.apcemedicom.servicios.OrdenCotizacionService;
import com.apcemedicom.modelo.OrdenCotizacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordencotizacion")
@CrossOrigin("*")
public class OrdenCotizacionController {
  @Autowired
  private OrdenCotizacionService ordenCotizacionService;

  @PostMapping("/")
  public ResponseEntity<OrdenCotizacion> agregarOrdenCotizacion(@RequestBody OrdenCotizacion ordenCotizacion) {
    return ResponseEntity.ok(ordenCotizacionService.agregarOrdenCotizacion(ordenCotizacion));
  }

  @GetMapping("/order/{orderId}")
  public ResponseEntity<List<OrdenCotizacion>> obtenerOrdenesPorOrderId(@PathVariable("orderId") Long orderId) {
    return ResponseEntity.ok(ordenCotizacionService.obtenerOrdenesPorOrderId(orderId));
  }

  @GetMapping("/cotizacion/{cotizacionId}")
  public ResponseEntity<List<OrdenCotizacion>> obtenerOrdenesPorCotizacionId(@PathVariable("cotizacionId") Long cotizacionId) {
    return ResponseEntity.ok(ordenCotizacionService.obtenerOrdenesPorCotizacionId(cotizacionId));
  }
}
