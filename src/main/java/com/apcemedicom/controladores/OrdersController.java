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

    @PatchMapping("/pagar/{orderId}")
    public ResponseEntity<Orders> pagarOrden(@PathVariable("orderId") Long orderId) {
        ordersService.pagarOrden(orderId);
        Orders order = ordersService.obtenerOrder(orderId);
        return ResponseEntity.ok(order);
    }

    @PatchMapping("/pagar-parcialmente/{orderId}")
    public ResponseEntity<Orders> pagarParcialmenteOrden(@PathVariable("orderId") Long orderId) {
        ordersService.pagarParcialmenteOrden(orderId);
        Orders order = ordersService.obtenerOrder(orderId);
        return ResponseEntity.ok(order);
    }    @PatchMapping("/atender/{orderId}")
    public void atenderOrder(@PathVariable("orderId") Long orderId, @RequestBody AtenderOrderDTO atenderOrderDTO) {
        ordersService.atenderOrder(orderId, atenderOrderDTO.getPreciocli(), atenderOrderDTO.getTotalPrice());
    }

    @PatchMapping("/rechazar/{orderId}")
    public void rechazarOrder(@PathVariable("orderId") Long orderId) {
        ordersService.rechazarOrder(orderId);
    }

    @PatchMapping("/aceptar/{orderId}")
    public ResponseEntity<Orders> aceptarOrder(@PathVariable("orderId") Long orderId) {
        ordersService.aceptarOrder(orderId);
        Orders order = ordersService.obtenerOrder(orderId);
        return ResponseEntity.ok(order);
    }
}
