package com.apcemedicom.servicios.impl;

import com.apcemedicom.modelo.Producto;
import com.apcemedicom.repositorios.ProductoRepository;
import com.apcemedicom.servicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto agregarProducto(Producto producto){ return productoRepository.save(producto);}
    @Override
    public Producto activarProducto(Producto producto){return productoRepository.save(producto);}
    @Override
    public Set<Producto> obtenerProductos(){ return new LinkedHashSet<>(productoRepository.findAll());}
    @Override
    public Producto obtenerProducto(Long productoId) { return productoRepository.findById(productoId).get();}    @Override
    public void eliminarProducto(Long productoId) {
        Producto producto = new Producto();
        producto.setProductoId(productoId);
        productoRepository.delete(producto);
    }
    
    @Override
    public Producto aumentarStock(Long productoId, Integer cantidad) {
        Producto producto = obtenerProducto(productoId);
        if (producto.getStock() == null) {
            producto.setStock(0);
        }
        producto.setStock(producto.getStock() + cantidad);
        return productoRepository.save(producto);
    }
    
    @Override
    public Producto restarStock(Long productoId, Integer cantidad) {
        Producto producto = obtenerProducto(productoId);
        if (producto.getStock() == null) {
            producto.setStock(0);
        }
        
        if (producto.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente. Stock actual: " + producto.getStock() + ", cantidad solicitada: " + cantidad);
        }
        
        producto.setStock(producto.getStock() - cantidad);
        return productoRepository.save(producto);
    }
}
