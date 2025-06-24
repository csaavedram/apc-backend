package com.apcemedicom.controladores;

import com.apcemedicom.modelo.ProductoSerie;
import com.apcemedicom.modelo.Producto;
import com.apcemedicom.servicios.ProductoSerieService;
import com.apcemedicom.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/producto-serie")
@CrossOrigin("*")
public class ProductoSerieController {
    
    @Autowired
    private ProductoSerieService productoSerieService;
      @Autowired    private ProductoService productoService;
      // CRUD básico
    @PostMapping("/")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> agregarProductoSerie(@RequestBody Map<String, Object> requestData) {
        try {
            // Extraer datos del request
            String numeroSerie = (String) requestData.get("numeroSerie");
            String observaciones = (String) requestData.get("observaciones");
            Integer cantidad = requestData.get("cantidad") != null ? (Integer) requestData.get("cantidad") : 1;
            String fechaVencimientoStr = (String) requestData.get("fechaVencimiento");
            
            // Obtener el ID del producto
            Map<String, Object> productoMap = (Map<String, Object>) requestData.get("producto");
            Long productoId = null;
            if (productoMap != null) {
                productoId = Long.valueOf(productoMap.get("productoId").toString());
            }
            
            if (productoId == null) {
                return ResponseEntity.badRequest().body("Error: ID del producto es requerido");
            }
            
            // Obtener el producto
            Producto producto = productoService.obtenerProducto(productoId);
            if (producto == null) {
                return ResponseEntity.badRequest().body("Error: Producto no encontrado");
            }
            
            // Crear el ProductoSerie
            ProductoSerie productoSerie = new ProductoSerie();
            productoSerie.setNumeroSerie(numeroSerie);
            productoSerie.setObservaciones(observaciones);
            productoSerie.setCantidad(cantidad);
            productoSerie.setProducto(producto);
            
            // Manejar fecha de vencimiento si viene
            if (fechaVencimientoStr != null && !fechaVencimientoStr.isEmpty()) {
                // Aquí puedes agregar parsing de fecha si es necesario
            }
            
            ProductoSerie nuevaSerie = productoSerieService.agregarProductoSerie(productoSerie);
            return ResponseEntity.ok(nuevaSerie);
        } catch (Exception e) {
            e.printStackTrace(); // Para ver el error en los logs
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @GetMapping("/")
    public ResponseEntity<List<ProductoSerie>> listarProductosSerie() {
        return ResponseEntity.ok(productoSerieService.obtenerTodosProductosSerie());
    }
    
    @GetMapping("/{productoSerieId}")
    public ResponseEntity<ProductoSerie> obtenerProductoSerie(@PathVariable Long productoSerieId) {
        try {
            ProductoSerie serie = productoSerieService.obtenerProductoSerie(productoSerieId);
            return ResponseEntity.ok(serie);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }    @PutMapping("/{productoSerieId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> actualizarProductoSerie(
            @PathVariable Long productoSerieId, 
            @RequestBody Map<String, Object> requestData) {
        try {
            System.out.println("🔄 INICIANDO actualización de ProductoSerie ID: " + productoSerieId);
            System.out.println("📋 Datos recibidos: " + requestData);
            
            // Extraer datos del request
            String numeroSerie = (String) requestData.get("numeroSerie");
            String observaciones = (String) requestData.get("observaciones");
            Integer cantidad = requestData.get("cantidad") != null ? (Integer) requestData.get("cantidad") : 1;
            String fechaVencimientoStr = (String) requestData.get("fechaVencimiento");
            
            // Obtener el ID del producto
            Map<String, Object> productoMap = (Map<String, Object>) requestData.get("producto");
            Long productoId = null;
            if (productoMap != null) {
                productoId = Long.valueOf(productoMap.get("productoId").toString());
            }
            
            // Obtener el ProductoSerie existente
            ProductoSerie productoSerie = productoSerieService.obtenerProductoSerie(productoSerieId);
            if (productoSerie == null) {
                System.out.println("❌ ProductoSerie no encontrado con ID: " + productoSerieId);
                return ResponseEntity.notFound().build();
            }
            
            System.out.println("📦 ProductoSerie antes de actualizar:");
            System.out.println("   - Cantidad: " + productoSerie.getCantidad());
            System.out.println("   - CantidadOriginal: " + productoSerie.getCantidadOriginal());
            System.out.println("   - CantidadVendida: " + productoSerie.getCantidadVendida());
            System.out.println("   - Estado: " + productoSerie.getEstado());
              // Actualizar campos
            if (numeroSerie != null) {
                productoSerie.setNumeroSerie(numeroSerie);
            }
            if (observaciones != null) {
                productoSerie.setObservaciones(observaciones);
            }
            if (cantidad != null) {
                productoSerie.setCantidad(cantidad);
            }
            
            // Manejar cantidadOriginal y cantidadVendida para tracking de ventas
            if (requestData.containsKey("cantidadOriginal")) {
                Integer cantidadOriginal = (Integer) requestData.get("cantidadOriginal");
                if (cantidadOriginal != null) {
                    productoSerie.setCantidadOriginal(cantidadOriginal);
                    System.out.println("🔄 Actualizando cantidadOriginal: " + cantidadOriginal);
                }
            }
            
            if (requestData.containsKey("cantidadVendida")) {
                Integer cantidadVendida = (Integer) requestData.get("cantidadVendida");
                if (cantidadVendida != null) {
                    productoSerie.setCantidadVendida(cantidadVendida);
                    System.out.println("🛒 Actualizando cantidadVendida: " + cantidadVendida);
                }
            }
            
            // Actualizar estado si viene
            if (requestData.containsKey("estado")) {
                String estado = (String) requestData.get("estado");
                if (estado != null) {
                    productoSerie.setEstado(estado);
                    System.out.println("🏷️ Actualizando estado: " + estado);
                }
            }
            
            // Actualizar fechaVenta si viene
            if (requestData.containsKey("fechaVenta") && requestData.get("fechaVenta") != null) {
                String fechaVentaStr = requestData.get("fechaVenta").toString();
                try {
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    sdf.setTimeZone(java.util.TimeZone.getTimeZone("UTC"));
                    Date fechaVenta = sdf.parse(fechaVentaStr);
                    productoSerie.setFechaVenta(fechaVenta);
                    System.out.println("📅 Actualizando fechaVenta: " + fechaVenta);
                } catch (Exception ex) {
                    System.err.println("Error parseando fechaVenta: " + ex.getMessage());
                }
            }
            // Actualizar fecha de vencimiento si viene
            if (fechaVencimientoStr != null && !fechaVencimientoStr.isEmpty()) {
                try {
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                    sdf.setLenient(false);
                    Date fechaVencimiento = sdf.parse(fechaVencimientoStr);
                    productoSerie.setFechaVencimiento(fechaVencimiento);
                } catch (Exception ex) {
                    // Si hay error de parseo, no actualiza la fecha
                    ex.printStackTrace();
                }
            } else {
                productoSerie.setFechaVencimiento(null);
            }
            
            // Actualizar producto si viene un nuevo ID
            if (productoId != null) {
                Producto producto = productoService.obtenerProducto(productoId);
                if (producto != null) {
                    productoSerie.setProducto(producto);
                }
            }            
            ProductoSerie serieActualizada = productoSerieService.actualizarProductoSerie(productoSerie);
            
            System.out.println("✅ ProductoSerie actualizado exitosamente:");
            System.out.println("   - Cantidad: " + serieActualizada.getCantidad());
            System.out.println("   - CantidadOriginal: " + serieActualizada.getCantidadOriginal());
            System.out.println("   - CantidadVendida: " + serieActualizada.getCantidadVendida());
            System.out.println("   - Estado: " + serieActualizada.getEstado());
            System.out.println("   - FechaVenta: " + serieActualizada.getFechaVenta());
            
            return ResponseEntity.ok(serieActualizada);
        } catch (Exception e) {
            System.err.println("❌ Error actualizando ProductoSerie: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{productoSerieId}")
    public ResponseEntity<Void> eliminarProductoSerie(@PathVariable Long productoSerieId) {
        try {
            productoSerieService.eliminarProductoSerie(productoSerieId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // Consultas específicas
    @GetMapping("/numero-serie/{numeroSerie}")
    public ResponseEntity<ProductoSerie> buscarPorNumeroSerie(@PathVariable String numeroSerie) {
        ProductoSerie serie = productoSerieService.buscarPorNumeroSerie(numeroSerie);
        if (serie != null) {
            return ResponseEntity.ok(serie);
        }
        return ResponseEntity.notFound().build();
    }    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<Map<String, Object>>> obtenerSeriesPorProducto(@PathVariable Long productoId) {
        try {
            Producto producto = productoService.obtenerProducto(productoId);
            List<ProductoSerie> series = productoSerieService.obtenerSeriesPorProducto(producto);
            
            // Convertir a Map para evitar referencias circulares
            List<Map<String, Object>> seriesResponse = series.stream()
                    .map(serie -> convertirSerieAMap(serie, productoId))
                    .collect(Collectors.toList());
            
            return ResponseEntity.ok(seriesResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }    @GetMapping("/producto/{productoId}/disponibles")
    public ResponseEntity<List<Map<String, Object>>> obtenerSeriesDisponibles(@PathVariable Long productoId) {
        try {
            Producto producto = productoService.obtenerProducto(productoId);
            List<ProductoSerie> series = productoSerieService.obtenerSeriesDisponiblesPorProducto(producto);
            
            // Convertir a Map para evitar referencias circulares
            List<Map<String, Object>> seriesResponse = series.stream()
                    .map(serie -> convertirSerieAMap(serie, productoId))
                    .collect(Collectors.toList());
            
            return ResponseEntity.ok(seriesResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/producto/{productoId}/contar-disponibles")
    public ResponseEntity<Long> contarSeriesDisponibles(@PathVariable Long productoId) {
        try {
            Producto producto = productoService.obtenerProducto(productoId);
            Long cantidad = productoSerieService.contarSeriesDisponibles(producto);
            return ResponseEntity.ok(cantidad);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // Validaciones
    @GetMapping("/validar-numero-serie/{numeroSerie}")
    public ResponseEntity<Boolean> validarNumeroSerie(@PathVariable String numeroSerie) {
        boolean existe = productoSerieService.existeNumeroSerie(numeroSerie);
        return ResponseEntity.ok(existe);
    }
    
    @PostMapping("/producto/{productoId}/validar-disponibilidad")
    public ResponseEntity<Boolean> validarDisponibilidad(
            @PathVariable Long productoId, 
            @RequestBody Map<String, Integer> request) {
        try {
            Producto producto = productoService.obtenerProducto(productoId);
            int cantidad = request.get("cantidad");
            boolean disponible = productoSerieService.validarDisponibilidadSeries(producto, cantidad);
            return ResponseEntity.ok(disponible);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // Operaciones masivas
    @PostMapping("/producto/{productoId}/crear-lote")
    public ResponseEntity<List<ProductoSerie>> crearSeriesEnLote(
            @PathVariable Long productoId,
            @RequestBody Map<String, Object> request) {
        try {
            Producto producto = productoService.obtenerProducto(productoId);
            @SuppressWarnings("unchecked")
            List<String> numerosSerie = (List<String>) request.get("numerosSerie");
            
            Date fechaVencimiento = null;
            if (request.containsKey("fechaVencimiento") && request.get("fechaVencimiento") != null) {
                // Asumiendo que la fecha viene como timestamp
                Long timestamp = Long.parseLong(request.get("fechaVencimiento").toString());
                fechaVencimiento = new Date(timestamp);
            }
            
            List<ProductoSerie> seriesCreadas = productoSerieService.crearSeriesEnLote(
                producto, numerosSerie, fechaVencimiento);
            return ResponseEntity.ok(seriesCreadas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // Gestión de vencimientos
    @GetMapping("/proximas-a-vencer")
    public ResponseEntity<List<ProductoSerie>> obtenerSeriesProximasAVencer() {
        List<ProductoSerie> series = productoSerieService.obtenerSeriesProximasAVencer();
        return ResponseEntity.ok(series);
    }
      @PostMapping("/marcar-vencidas")
    public ResponseEntity<Void> marcarSeriesVencidas() {
        productoSerieService.marcarSeriesVencidas();
        return ResponseEntity.ok().build();
    }
    
    // Métodos para procesar ventas
    @PostMapping("/producto/{productoId}/procesar-venta")
    public ResponseEntity<?> procesarVenta(
            @PathVariable Long productoId,
            @RequestBody Map<String, Object> request) {
        try {
            Integer cantidadSolicitada = (Integer) request.get("cantidad");
            if (cantidadSolicitada == null || cantidadSolicitada <= 0) {
                return ResponseEntity.badRequest().body("Cantidad inválida");
            }
            
            Producto producto = productoService.obtenerProducto(productoId);
            if (producto == null) {
                return ResponseEntity.badRequest().body("Producto no encontrado");
            }
            
            // Obtener series disponibles ordenadas por fecha de vencimiento (FIFO)
            List<ProductoSerie> seriesDisponibles = productoSerieService.obtenerSeriesDisponiblesOrdenadas(producto);
            
            List<Map<String, Object>> seriesProcesadas = productoSerieService.procesarVentaSeries(
                seriesDisponibles, cantidadSolicitada);
            
            return ResponseEntity.ok(seriesProcesadas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar venta: " + e.getMessage());
        }
    }
    
    @PostMapping("/procesar-venta-multiple")
    public ResponseEntity<?> procesarVentaMultiple(@RequestBody List<Map<String, Object>> productos) {
        try {
            Map<String, Object> resultado = new HashMap<>();
            List<Map<String, Object>> productosConSeries = new java.util.ArrayList<>();
            
            for (Map<String, Object> productoData : productos) {
                Long productoId = Long.valueOf(productoData.get("productoId").toString());
                Integer cantidad = (Integer) productoData.get("cantidad");
                
                Producto producto = productoService.obtenerProducto(productoId);
                List<ProductoSerie> seriesDisponibles = productoSerieService.obtenerSeriesDisponiblesOrdenadas(producto);
                
                List<Map<String, Object>> seriesProcesadas = productoSerieService.procesarVentaSeries(
                    seriesDisponibles, cantidad);
                
                Map<String, Object> productoConSeries = new HashMap<>();
                productoConSeries.put("productoId", productoId);
                productoConSeries.put("cantidad", cantidad);
                productoConSeries.put("series", seriesProcesadas);
                productosConSeries.add(productoConSeries);
            }
            
            resultado.put("productos", productosConSeries);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar venta múltiple: " + e.getMessage());
        }
    }    // Método auxiliar para convertir ProductoSerie a Map
    private Map<String, Object> convertirSerieAMap(ProductoSerie serie, Long productoId) {
        Map<String, Object> serieMap = new HashMap<>();
        serieMap.put("productoSerieId", serie.getProductoSerieId());
        serieMap.put("numeroSerie", serie.getNumeroSerie());
        serieMap.put("fechaVencimiento", serie.getFechaVencimiento());
        serieMap.put("estado", serie.getEstado());
        serieMap.put("cantidad", serie.getCantidad());
        serieMap.put("cantidadOriginal", serie.getCantidadOriginal());
        serieMap.put("cantidadVendida", serie.getCantidadVendida());
        serieMap.put("fechaCreacion", serie.getFechaCreacion());
        serieMap.put("fechaVenta", serie.getFechaVenta());
        serieMap.put("observaciones", serie.getObservaciones());
        serieMap.put("productoId", productoId);
        
        // Información adicional para mostrar en el frontend
        if (serie.getCantidadOriginal() != null && serie.getCantidadVendida() != null) {
            serieMap.put("ventaParcial", serie.getCantidadVendida() > 0 && serie.getCantidad() > 0);
            serieMap.put("porcentajeVendido", 
                serie.getCantidadOriginal() > 0 ? 
                    (serie.getCantidadVendida() * 100.0 / serie.getCantidadOriginal()) : 0);
        }
        
        return serieMap;
    }
}
