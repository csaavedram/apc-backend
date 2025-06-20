package com.apcemedicom.controladores;

import com.apcemedicom.servicios.ProductoService;
import com.apcemedicom.servicios.ProductoSerieService;
import com.apcemedicom.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/productos")
@CrossOrigin("*")
public class ProductoController {    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private ProductoSerieService productoSerieService;
    @PostMapping("/")
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto){
        return ResponseEntity.ok(productoService.agregarProducto(producto));
    }
    @PutMapping("/")
    public ResponseEntity<Producto> activarProducto(@RequestBody Producto producto){
        return ResponseEntity.ok(productoService.activarProducto(producto));
    }
    @GetMapping("/")
    public ResponseEntity<?> listarProductos(){
        return ResponseEntity.ok(productoService.obtenerProductos());
    }
    @GetMapping("/{productoId}")
    public Producto listarProducto(@PathVariable("productoId")Long productoId){
        return productoService.obtenerProducto(productoId);
    }    @DeleteMapping("/{productoId}")
    public void eliminarProducto(@PathVariable("productoId") Long productoId){
        productoService.eliminarProducto(productoId);
    }
    
    // Endpoint para aumentar stock
    @PutMapping("/{productoId}/aumentar-stock")
    public ResponseEntity<Producto> aumentarStock(
            @PathVariable("productoId") Long productoId,
            @RequestBody Map<String, Integer> request) {
        try {
            Integer cantidad = request.get("cantidad");
            if (cantidad == null || cantidad <= 0) {
                return ResponseEntity.badRequest().build();
            }
            
            Producto producto = productoService.aumentarStock(productoId, cantidad);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // Endpoint para restar stock
    @PutMapping("/{productoId}/restar-stock")
    public ResponseEntity<Producto> restarStock(
            @PathVariable("productoId") Long productoId,
            @RequestBody Map<String, Integer> request) {
        try {
            Integer cantidad = request.get("cantidad");
            if (cantidad == null || cantidad <= 0) {
                return ResponseEntity.badRequest().build();
            }
            
            Producto producto = productoService.restarStock(productoId, cantidad);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {            return ResponseEntity.badRequest().build();
        }
    }
    
    // Endpoint para recalcular stock basado en series disponibles
    @PutMapping("/{productoId}/recalcular-stock")
    public ResponseEntity<Producto> recalcularStock(@PathVariable("productoId") Long productoId) {
        try {
            Producto producto = productoService.obtenerProducto(productoId);
            productoSerieService.recalcularStockProducto(producto);
              // Obtener el producto actualizado
            Producto productoActualizado = productoService.obtenerProducto(productoId);
            return ResponseEntity.ok(productoActualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
