package com.apcemedicom.servicios;

import com.apcemedicom.modelo.Cliente;

import java.util.Set;

public interface ClienteService {
    Cliente agregarCliente(Cliente cliente);
    Cliente activarCliente(Cliente cliente);
    Set<Cliente> obtenerClientes();
    Cliente obtenerCliente(Long clienteId);
    void eliminarCliente(Long clienteId);
}
