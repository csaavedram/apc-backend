package com.apcemedicom.controladores;

import com.apcemedicom.modelo.DetalleServicio;
import com.apcemedicom.modelo.Servicio;
import com.apcemedicom.servicios.DetalleServicioService;
import com.apcemedicom.servicios.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle-servicios")
@CrossOrigin("*")
public class DetalleServicioController {

    @Autowired
    private DetalleServicioService detalleServicioService;

    @Autowired
    private ServicioService servicioService;    @PostMapping("/")
    public ResponseEntity<DetalleServicio> guardarDetalleServicio(@RequestBody DetalleServicio detalleServicio) {
        try {
            // Verificar que el servicio existe y asignarlo correctamente
            if (detalleServicio.getServicio() != null && detalleServicio.getServicio().getServicioId() != null) {
                Servicio servicio = servicioService.obtenerServicio(detalleServicio.getServicio().getServicioId());
                if (servicio == null) {
                    return ResponseEntity.badRequest().build();
                }
                detalleServicio.setServicio(servicio);
            } else {
                return ResponseEntity.badRequest().build();
            }
            
            DetalleServicio detalleServicioGuardado = detalleServicioService.guardarDetalleServicio(detalleServicio);
            return ResponseEntity.ok(detalleServicioGuardado);
        } catch (Exception e) {
            e.printStackTrace(); // Para ver el error en la consola
            return ResponseEntity.internalServerError().build();
        }
    }    @PostMapping("/servicio/{servicioId}")
    public ResponseEntity<DetalleServicio> guardarDetalleServicioSimple(
            @PathVariable("servicioId") Long servicioId,
            @RequestBody DetalleServicio detalleServicio) {
        try {
            System.out.println("=== CREANDO DETALLE SERVICIO ===");
            System.out.println("servicioId: " + servicioId);
            System.out.println("Datos recibidos: " + detalleServicio);
            
            Servicio servicio = servicioService.obtenerServicio(servicioId);
            if (servicio == null) {
                System.out.println("ERROR: Servicio no encontrado con ID: " + servicioId);
                return ResponseEntity.badRequest().build();
            }
            
            detalleServicio.setServicio(servicio);
            System.out.println("Guardando detalle...");
            DetalleServicio detalleServicioGuardado = detalleServicioService.guardarDetalleServicio(detalleServicio);
            System.out.println("Detalle guardado exitosamente: " + detalleServicioGuardado.getDetalleServicioId());
            return ResponseEntity.ok(detalleServicioGuardado);
        } catch (Exception e) {
            System.out.println("ERROR EN CREACIÓN: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listarDetalleServicios() {
        return ResponseEntity.ok(detalleServicioService.obtenerDetalleServicios());
    }

    @GetMapping("/{detalleServicioId}")
    public DetalleServicio obtenerDetalleServicio(@PathVariable("detalleServicioId") Long detalleServicioId) {
        return detalleServicioService.obtenerDetalleServicio(detalleServicioId);
    }

    @GetMapping("/servicio/{servicioId}")
    public ResponseEntity<List<DetalleServicio>> obtenerDetalleServiciosPorServicioId(@PathVariable("servicioId") Long servicioId) {
        List<DetalleServicio> detalleServicios = detalleServicioService.obtenerDetalleServiciosPorServicioId(servicioId);
        return ResponseEntity.ok(detalleServicios);
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<List<DetalleServicio>> obtenerDetalleServiciosPorSku(@PathVariable("sku") String sku) {
        List<DetalleServicio> detalleServicios = detalleServicioService.obtenerDetalleServiciosPorSku(sku);
        return ResponseEntity.ok(detalleServicios);
    }

    @GetMapping("/numero-serie/{numeroSerie}")
    public ResponseEntity<List<DetalleServicio>> obtenerDetalleServiciosPorNumeroSerie(@PathVariable("numeroSerie") String numeroSerie) {
        List<DetalleServicio> detalleServicios = detalleServicioService.obtenerDetalleServiciosPorNumeroSerie(numeroSerie);
        return ResponseEntity.ok(detalleServicios);
    }    @PutMapping("/{detalleServicioId}")
    public ResponseEntity<DetalleServicio> actualizarDetalleServicio(@PathVariable("detalleServicioId") Long detalleServicioId, @RequestBody DetalleServicio detalleServicio) {
        try {
            detalleServicio.setDetalleServicioId(detalleServicioId);
            
            // Verificar que el servicio existe y asignarlo correctamente
            if (detalleServicio.getServicio() != null && detalleServicio.getServicio().getServicioId() != null) {
                Servicio servicio = servicioService.obtenerServicio(detalleServicio.getServicio().getServicioId());
                if (servicio == null) {
                    return ResponseEntity.badRequest().build();
                }
                detalleServicio.setServicio(servicio);
            } else {
                return ResponseEntity.badRequest().build();
            }
            
            DetalleServicio detalleServicioActualizado = detalleServicioService.actualizarDetalleServicio(detalleServicio);
            return ResponseEntity.ok(detalleServicioActualizado);
        } catch (Exception e) {
            e.printStackTrace(); // Para debugging
            return ResponseEntity.internalServerError().build();
        }
    }    @PutMapping("/{detalleServicioId}/servicio/{servicioId}")
    public ResponseEntity<DetalleServicio> actualizarDetalleServicioSimple(
            @PathVariable("detalleServicioId") Long detalleServicioId,
            @PathVariable("servicioId") Long servicioId,
            @RequestBody DetalleServicio detalleServicio) {
        try {
            System.out.println("=== ACTUALIZANDO DETALLE SERVICIO ===");
            System.out.println("detalleServicioId: " + detalleServicioId);
            System.out.println("servicioId: " + servicioId);
            System.out.println("Datos recibidos: " + detalleServicio);
            
            // Obtener el detalle existente
            DetalleServicio detalleExistente = detalleServicioService.obtenerDetalleServicio(detalleServicioId);
            if (detalleExistente == null) {
                System.out.println("ERROR: Detalle no encontrado con ID: " + detalleServicioId);
                return ResponseEntity.notFound().build();
            }

            // Obtener el servicio
            Servicio servicio = servicioService.obtenerServicio(servicioId);
            if (servicio == null) {
                System.out.println("ERROR: Servicio no encontrado con ID: " + servicioId);
                return ResponseEntity.badRequest().build();
            }            // Actualizar los campos
            System.out.println("Actualizando campos...");
            System.out.println("Modelo anterior: " + detalleExistente.getModeloEquipo() + " -> Nuevo: " + detalleServicio.getModeloEquipo());
            System.out.println("SKU anterior: " + detalleExistente.getSku() + " -> Nuevo: " + detalleServicio.getSku());
            
            detalleExistente.setModeloEquipo(detalleServicio.getModeloEquipo());
            detalleExistente.setSku(detalleServicio.getSku());
            detalleExistente.setNumeroSerie(detalleServicio.getNumeroSerie());
            detalleExistente.setFechaGarantia(detalleServicio.getFechaGarantia());
            detalleExistente.setObservacion(detalleServicio.getObservacion());
            detalleExistente.setServicio(servicio);

            System.out.println("Guardando detalle actualizado...");
            DetalleServicio detalleActualizado = detalleServicioService.actualizarDetalleServicio(detalleExistente);
            System.out.println("Detalle actualizado exitosamente: " + detalleActualizado.getDetalleServicioId());
            return ResponseEntity.ok(detalleActualizado);
        } catch (Exception e) {
            System.out.println("ERROR EN ACTUALIZACIÓN: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{detalleServicioId}")
    public void eliminarDetalleServicio(@PathVariable("detalleServicioId") Long detalleServicioId) {
        detalleServicioService.eliminarDetalleServicio(detalleServicioId);
    }
}
