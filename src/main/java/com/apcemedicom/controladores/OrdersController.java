package com.apcemedicom.controladores;

import com.apcemedicom.servicios.OrdersService;
import com.apcemedicom.modelo.Orders;
import com.apcemedicom.dtos.AtenderOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @PostMapping("/")
    public ResponseEntity<Orders> agregarOrder(@RequestBody Orders order){
        return ResponseEntity.ok(ordersService.agregarOrder(order));
    }
    @PutMapping("/")
    public ResponseEntity<Orders> activarOrder(@RequestBody Orders order){
        return ResponseEntity.ok(ordersService.activarOrder(order));
    }
    @GetMapping("/")
    public ResponseEntity<?> listarOrders(){
        return ResponseEntity.ok(ordersService.obtenerOrders());
    }
    @GetMapping("/{orderId}")
    public Orders listarOrder(@PathVariable("orderId")Long orderId){
        return ordersService.obtenerOrder(orderId);
    }
    @DeleteMapping("/{orderId}")
    public void eliminarOrder(@PathVariable("orderId") Long orderId){
        ordersService.eliminarOrder(orderId);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Orders>> listarOrdersPorUsuario(@PathVariable("id") Long id) {
        List<Orders> orders = ordersService.obtenerOrdersPorUsuario(id);
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/cambiar-estado/{orderId}")
    public void cambiarEstadoOrder(@PathVariable("orderId") Long orderId) {
        ordersService.cambiarEstadoOrder(orderId);
    }

    @PatchMapping("/atender/{orderId}")
    public void atenderOrder(@PathVariable("orderId") Long orderId, @RequestBody AtenderOrderDTO atenderOrderDTO) {
        ordersService.atenderOrder(orderId, atenderOrderDTO.getPreciocli());
    }

    @PatchMapping("/rechazar/{orderId}")
    public void rechazarOrder(@PathVariable("orderId") Long orderId) {
        ordersService.rechazarOrder(orderId);
    }

    @PatchMapping("/aceptar/{orderId}")
    public void aceptarOrder(@PathVariable("orderId") Long orderId) {
        ordersService.aceptarOrder(orderId);
    }
}
