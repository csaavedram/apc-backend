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
}