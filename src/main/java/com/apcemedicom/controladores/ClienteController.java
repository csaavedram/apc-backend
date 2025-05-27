package com.apcemedicom.controladores;

import com.apcemedicom.modelo.Cliente;
import com.apcemedicom.servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@CrossOrigin("*")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @PostMapping("/")
    public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.agregarCliente(cliente));
    }
    @PutMapping("/")
    public ResponseEntity<Cliente> activarCliente(@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.activarCliente(cliente));
    }
    @GetMapping("/")
    public ResponseEntity<?> listarClientes(){
        return ResponseEntity.ok(clienteService.obtenerClientes());
    }
    @GetMapping("/{clienteId}")
    public Cliente listarCliente(@PathVariable("clienteId")Long clienteId){
        return clienteService.obtenerCliente(clienteId);
    }
    @DeleteMapping("/{clienteId}")
    public void eliminarCliente(@PathVariable("clienteId") Long clienteId){
        clienteService.eliminarCliente(clienteId);
    }
}
