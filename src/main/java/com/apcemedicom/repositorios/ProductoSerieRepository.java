package com.apcemedicom.repositorios;

import com.apcemedicom.modelo.ProductoSerie;
import com.apcemedicom.modelo.Producto;
import com.apcemedicom.modelo.FacturaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductoSerieRepository extends JpaRepository<ProductoSerie, Long> {
    
    // Buscar por número de serie
    Optional<ProductoSerie> findByNumeroSerie(String numeroSerie);
    
    // Buscar por producto
    List<ProductoSerie> findByProducto(Producto producto);
    
    // Buscar por producto y estado
    List<ProductoSerie> findByProductoAndEstado(Producto producto, String estado);
    
    // Buscar series disponibles para un producto
    @Query("SELECT ps FROM ProductoSerie ps WHERE ps.producto = :producto AND ps.estado = 'DISPONIBLE' AND (ps.fechaVencimiento IS NULL OR ps.fechaVencimiento > CURRENT_DATE)")
    List<ProductoSerie> findSeriesDisponibles(@Param("producto") Producto producto);
    
    // Buscar series por factura detalle
    List<ProductoSerie> findByFacturaDetalle(FacturaDetalle facturaDetalle);
    
    // Buscar series vendidas
    List<ProductoSerie> findByEstado(String estado);
    
    // Buscar series próximas a vencer
    @Query("SELECT ps FROM ProductoSerie ps WHERE ps.fechaVencimiento IS NOT NULL AND ps.fechaVencimiento BETWEEN CURRENT_DATE AND DATE_ADD(CURRENT_DATE, 30, 'DAY') AND ps.estado = 'DISPONIBLE'")
    List<ProductoSerie> findSeriesProximasAVencer();
    
    // Contar series disponibles por producto
    @Query("SELECT COUNT(ps) FROM ProductoSerie ps WHERE ps.producto = :producto AND ps.estado = 'DISPONIBLE' AND (ps.fechaVencimiento IS NULL OR ps.fechaVencimiento > CURRENT_DATE)")
    Long countSeriesDisponibles(@Param("producto") Producto producto);
    
    // Verificar si existe número de serie
    boolean existsByNumeroSerie(String numeroSerie);
    
    // Buscar series disponibles ordenadas por fecha de vencimiento (FIFO)
    @Query("SELECT ps FROM ProductoSerie ps WHERE ps.producto = :producto AND ps.estado = 'DISPONIBLE' AND (ps.fechaVencimiento IS NULL OR ps.fechaVencimiento > CURRENT_DATE) ORDER BY ps.fechaVencimiento ASC NULLS LAST, ps.fechaCreacion ASC")
    List<ProductoSerie> findSeriesDisponiblesOrdenadas(@Param("producto") Producto producto);
}
