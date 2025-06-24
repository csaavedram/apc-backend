package com.apcemedicom.controladores;

import com.apcemedicom.modelo.Inventario;
import com.apcemedicom.servicios.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventario")
@CrossOrigin("*")
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;    @PostMapping("/")
    public ResponseEntity<Inventario> agregarInventario(@RequestBody Inventario inventario){
        try {
            System.out.println("📦 RECIBIENDO INVENTARIO:");
            System.out.println("- Cantidad: " + inventario.getCantidad());
            System.out.println("- Tipo: " + inventario.getTipo());
            System.out.println("- Producto ID: " + (inventario.getProducto() != null ? inventario.getProducto().getProductoId() : "null"));
            System.out.println("- Número Serie: " + inventario.getNumeroSerie());
            System.out.println("- Fecha: " + inventario.getDateCreated());
            
            Inventario resultado = inventarioService.agregarInventario(inventario);
            System.out.println("✅ INVENTARIO GUARDADO CON ID: " + resultado.getInventarioId());
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            System.err.println("❌ ERROR AL AGREGAR INVENTARIO: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
    @PutMapping("/")
    public ResponseEntity<Inventario> activarInventario(@RequestBody Inventario inventario){
        return ResponseEntity.ok(inventarioService.activarInventario(inventario));
    }
    @GetMapping("/")
    public ResponseEntity<?> listarInventarios(){
        return ResponseEntity.ok(inventarioService.obtenerInventarios());
    }
    @GetMapping("/{inventarioId}")
    public Inventario listarInventario(@PathVariable("inventarioId")Long inventarioId){
        return inventarioService.obtenerInventario(inventarioId);
    }
    @DeleteMapping("/{inventarioId}")
    public void eliminarInventario(@PathVariable("inventarioId") Long inventarioId){
        inventarioService.eliminarInventario(inventarioId);
    }
}
