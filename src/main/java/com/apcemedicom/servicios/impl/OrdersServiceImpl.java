package com.apcemedicom.servicios.impl;

import com.apcemedicom.modelo.Orders;
import com.apcemedicom.repositorios.OrdersRepository;
import com.apcemedicom.servicios.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public Orders agregarOrder(Orders order){ return ordersRepository.save(order);}
    @Override
    public Orders activarOrder(Orders order){return ordersRepository.save(order);}
    @Override
    public Set<Orders> obtenerOrders(){ return new LinkedHashSet<>(ordersRepository.findAll());}
    @Override
    public Orders obtenerOrder(Long orderId) { return ordersRepository.findById(orderId).get();}
    @Override
    public void eliminarOrder(Long orderId) {
        Orders order = new Orders();
        order.setOrderId(orderId);
        ordersRepository.delete(order);
    }
    @Override
    public List<Orders> obtenerOrdersPorUsuario(Long id) {
        return ordersRepository.findByUserId(id);
    }

    @Override
    public void atenderOrder(Long orderId, Double preciocli) {
        ordersRepository.findById(orderId).ifPresent(order -> {
            order.setPreciocli(preciocli);
            order.setStatus("Atendido");
            ordersRepository.save(order);
        });
    }

    @Override
    public void rechazarOrder(Long ordersId) {
        ordersRepository.findById(ordersId).ifPresent(order -> {
            order.setStatus("Rechazado");
            ordersRepository.save(order);
        });
    }

    @Override
    public void aceptarOrder(Long ordersId) {
        ordersRepository.findById(ordersId).ifPresent(order -> {
            order.setStatus("Aceptado");
            ordersRepository.save(order);
        });
    }

    @Override
    public void pagarOrden(Long ordersId) {
        ordersRepository.findById(ordersId).ifPresent(order -> {
            order.setStatus("Pagado");
            ordersRepository.save(order);
        });
    }

    @Override
    public void pagarParcialmenteOrden(Long ordersId) {
        ordersRepository.findById(ordersId).ifPresent(order -> {
            order.setStatus("Pagado parcialmente");
            ordersRepository.save(order);
        });
    }
}
