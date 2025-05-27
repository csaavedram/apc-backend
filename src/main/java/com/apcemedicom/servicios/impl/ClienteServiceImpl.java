package com.apcemedicom.servicios.impl;

import com.apcemedicom.repositorios.ClienteRepository;
import com.apcemedicom.modelo.Cliente;
import com.apcemedicom.servicios.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente agregarCliente(Cliente cliente){ return clienteRepository.save(cliente);}
    @Override
    public Cliente activarCliente(Cliente cliente){return clienteRepository.save(cliente);}
    @Override
    public Set<Cliente> obtenerClientes(){ return new LinkedHashSet<>(clienteRepository.findAll());}
    @Override
    public Cliente obtenerCliente(Long clienteId) { return clienteRepository.findById(clienteId).get();}
    @Override
    public void eliminarCliente(Long clienteId) {
        Cliente cliente = new Cliente();
        cliente.setClienteId(clienteId);
        clienteRepository.delete(cliente);
    }
}
