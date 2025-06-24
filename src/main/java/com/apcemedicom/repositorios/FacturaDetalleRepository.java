package com.apcemedicom.repositorios;

import com.apcemedicom.modelo.FacturaDetalle;
import com.apcemedicom.modelo.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacturaDetalleRepository extends JpaRepository<FacturaDetalle, Long> {
  List<FacturaDetalle> findFacturaDetalleByFactura(Factura factura);
  List<FacturaDetalle> findByNumerosSerieLike(String numeroSerie);    @Query(value = "SELECT " +
    "fd.factura_detalle_id, " +
    "fd.cantidad, " +
    "fd.precio_total, " +
    "fd.precio_unitario, " +
    "fd.numeros_serie, " +
    "fd.tipo_servicio, " +
    "fd.created_at, " +
    "p.producto_id, " +
    "p.nombre_producto, " +
    "p.descripcion as descripcion_producto, " +
    "f.factura_id, " +
    "f.codigo as codigo_factura, " +
    "f.divisa, " +
    "f.tipo_pago, " +
    "f.total as total_factura, " +
    "f.fecha_emision, " +
    "f.estado as estado_factura, " +
    "u.id as usuario_id, " +
    "u.nombre as nombre_usuario, " +
    "u.apellido as apellido_usuario, " +
    "u.razon_social, " +
    "u.ruc as ruc_usuario, " +
    "u.email as email_usuario, " +
    "u.tipo_usuario, " +
    "u.username as username_usuario " +
    "FROM factura_detalle fd " +
    "LEFT JOIN productos p ON fd.producto_id = p.producto_id " +
    "INNER JOIN factura f ON fd.factura_id = f.factura_id " +
    "INNER JOIN usuarios u ON f.id = u.id " +
    "WHERE fd.numeros_serie LIKE :numeroSerie", nativeQuery = true)
  List<Object[]> buscarGarantiaPorNumeroSerie(@Param("numeroSerie") String numeroSerie);
}
