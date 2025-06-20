package com.apcemedicom.servicios;

import com.apcemedicom.modelo.Orders;
import java.util.List;
import java.util.Set;

public interface OrdersService {
    Orders agregarOrder(Orders order);
    Orders activarOrder(Orders order);
    Set<Orders> obtenerOrders();
    Orders obtenerOrder(Long ordersId);
    void eliminarOrder(Long ordersId);
    List<Orders> obtenerOrdersPorUsuario(Long id);
    void atenderOrder(Long orderId, Double preciocli);
    void rechazarOrder(Long ordersId);
    void aceptarOrder(Long ordersId);
    void pagarOrden(Long ordersId);
    void pagarParcialmenteOrden(Long ordersId);
}
