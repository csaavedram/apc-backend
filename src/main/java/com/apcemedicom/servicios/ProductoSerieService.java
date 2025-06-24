package com.apcemedicom.servicios;

import com.apcemedicom.modelo.ProductoSerie;
import com.apcemedicom.modelo.Producto;
import com.apcemedicom.modelo.FacturaDetalle;

import java.util.List;
import java.util.Date;

public interface ProductoSerieService {
    
    // CRUD básico
    ProductoSerie agregarProductoSerie(ProductoSerie productoSerie);
    ProductoSerie actualizarProductoSerie(ProductoSerie productoSerie);
    ProductoSerie obtenerProductoSerie(Long productoSerieId);
    List<ProductoSerie> obtenerTodosProductosSerie();
    void eliminarProductoSerie(Long productoSerieId);
    
    // Operaciones específicas
    ProductoSerie buscarPorNumeroSerie(String numeroSerie);
    List<ProductoSerie> obtenerSeriesPorProducto(Producto producto);
    List<ProductoSerie> obtenerSeriesDisponiblesPorProducto(Producto producto);
    List<ProductoSerie> obtenerSeriesPorFacturaDetalle(FacturaDetalle facturaDetalle);
    
    // Operaciones de negocio
    List<ProductoSerie> asignarSeriesAFactura(FacturaDetalle facturaDetalle, int cantidad);
    void liberarSeriesDeFactura(FacturaDetalle facturaDetalle);
    boolean validarDisponibilidadSeries(Producto producto, int cantidad);
    
    // Gestión de stock automático
    void recalcularStockProducto(Producto producto);
    Integer calcularStockTotalProducto(Producto producto);
    List<ProductoSerie> obtenerSeriesProximasAVencer();
    void marcarSeriesVencidas();
    
    // Validaciones
    boolean existeNumeroSerie(String numeroSerie);
    Long contarSeriesDisponibles(Producto producto);
    
    // Operaciones masivas
    List<ProductoSerie> crearSeriesEnLote(Producto producto, List<String> numerosSerie, Date fechaVencimiento);
    
    // Métodos para procesar ventas
    List<ProductoSerie> obtenerSeriesDisponiblesOrdenadas(Producto producto);
    List<java.util.Map<String, Object>> procesarVentaSeries(List<ProductoSerie> seriesDisponibles, int cantidadSolicitada);
}
