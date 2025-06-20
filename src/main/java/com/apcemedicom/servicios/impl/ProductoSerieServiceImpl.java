package com.apcemedicom.servicios.impl;

import com.apcemedicom.modelo.ProductoSerie;
import com.apcemedicom.modelo.Producto;
import com.apcemedicom.modelo.FacturaDetalle;
import com.apcemedicom.repositorios.ProductoSerieRepository;
import com.apcemedicom.repositorios.ProductoRepository;
import com.apcemedicom.servicios.ProductoSerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductoSerieServiceImpl implements ProductoSerieService {
      @Autowired
    private ProductoSerieRepository productoSerieRepository;
    
    @Autowired
    private ProductoRepository productoRepository;
      @Override
    public ProductoSerie agregarProductoSerie(ProductoSerie productoSerie) {
        if (existeNumeroSerie(productoSerie.getNumeroSerie())) {
            throw new RuntimeException("El número de serie ya existe: " + productoSerie.getNumeroSerie());
        }
        productoSerie.setFechaCreacion(new Date());
        if (productoSerie.getEstado() == null) {
            productoSerie.setEstado("DISPONIBLE");
        }
        if (productoSerie.getCantidad() == null) {
            productoSerie.setCantidad(1);
        }
        
        ProductoSerie serieGuardada = productoSerieRepository.save(productoSerie);
        
        // Recalcular stock del producto automáticamente
        if (serieGuardada.getProducto() != null) {
            recalcularStockProducto(serieGuardada.getProducto());
        }
        
        return serieGuardada;
    }
    
    @Override
    public ProductoSerie actualizarProductoSerie(ProductoSerie productoSerie) {
        return productoSerieRepository.save(productoSerie);
    }
    
    @Override
    public ProductoSerie obtenerProductoSerie(Long productoSerieId) {
        return productoSerieRepository.findById(productoSerieId)
                .orElseThrow(() -> new RuntimeException("Producto serie no encontrado con ID: " + productoSerieId));
    }
    
    @Override
    public List<ProductoSerie> obtenerTodosProductosSerie() {
        return productoSerieRepository.findAll();
    }    @Override
    public void eliminarProductoSerie(Long productoSerieId) {
        ProductoSerie productoSerie = obtenerProductoSerie(productoSerieId);
        if ("VENDIDO".equals(productoSerie.getEstado())) {
            throw new RuntimeException("No se puede eliminar un número de serie que ya ha sido vendido");
        }
        
        Producto producto = productoSerie.getProducto();
        
        // Eliminar la serie
        productoSerieRepository.deleteById(productoSerieId);
        
        // Recalcular stock del producto automáticamente
        if (producto != null) {
            recalcularStockProducto(producto);
        }
    }
    
    @Override
    public ProductoSerie buscarPorNumeroSerie(String numeroSerie) {
        return productoSerieRepository.findByNumeroSerie(numeroSerie).orElse(null);
    }
    
    @Override
    public List<ProductoSerie> obtenerSeriesPorProducto(Producto producto) {
        return productoSerieRepository.findByProducto(producto);
    }
    
    @Override
    public List<ProductoSerie> obtenerSeriesDisponiblesPorProducto(Producto producto) {
        return productoSerieRepository.findSeriesDisponibles(producto);
    }
    
    @Override
    public List<ProductoSerie> obtenerSeriesPorFacturaDetalle(FacturaDetalle facturaDetalle) {
        return productoSerieRepository.findByFacturaDetalle(facturaDetalle);
    }
    
    @Override
    public List<ProductoSerie> asignarSeriesAFactura(FacturaDetalle facturaDetalle, int cantidad) {
        List<ProductoSerie> seriesDisponibles = obtenerSeriesDisponiblesPorProducto(facturaDetalle.getProducto());
        
        if (seriesDisponibles.size() < cantidad) {
            throw new RuntimeException("No hay suficientes números de serie disponibles. Requeridos: " + 
                                     cantidad + ", Disponibles: " + seriesDisponibles.size());
        }
        
        List<ProductoSerie> seriesAsignadas = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            ProductoSerie serie = seriesDisponibles.get(i);
            serie.setFacturaDetalle(facturaDetalle);
            serie.setEstado("VENDIDO");
            serie.setFechaVenta(new Date());
            seriesAsignadas.add(productoSerieRepository.save(serie));
        }
        
        return seriesAsignadas;
    }
    
    @Override
    public void liberarSeriesDeFactura(FacturaDetalle facturaDetalle) {
        List<ProductoSerie> series = obtenerSeriesPorFacturaDetalle(facturaDetalle);
        for (ProductoSerie serie : series) {
            serie.setFacturaDetalle(null);
            serie.setEstado("DISPONIBLE");
            serie.setFechaVenta(null);
            productoSerieRepository.save(serie);
        }
    }
    
    @Override
    public boolean validarDisponibilidadSeries(Producto producto, int cantidad) {
        Long seriesDisponibles = contarSeriesDisponibles(producto);
        return seriesDisponibles >= cantidad;
    }
    
    @Override
    public List<ProductoSerie> obtenerSeriesProximasAVencer() {
        return productoSerieRepository.findSeriesProximasAVencer();
    }
    
    @Override
    public void marcarSeriesVencidas() {
        List<ProductoSerie> todasLasSeries = productoSerieRepository.findByEstado("DISPONIBLE");
        for (ProductoSerie serie : todasLasSeries) {
            if (serie.isVencido()) {
                serie.setEstado("VENCIDO");
                productoSerieRepository.save(serie);
            }
        }
    }
    
    @Override
    public boolean existeNumeroSerie(String numeroSerie) {
        return productoSerieRepository.existsByNumeroSerie(numeroSerie);
    }
    
    @Override
    public Long contarSeriesDisponibles(Producto producto) {
        return productoSerieRepository.countSeriesDisponibles(producto);
    }
    
    @Override
    public List<ProductoSerie> crearSeriesEnLote(Producto producto, List<String> numerosSerie, Date fechaVencimiento) {
        List<ProductoSerie> seriesCreadas = new ArrayList<>();
        
        for (String numeroSerie : numerosSerie) {
            if (existeNumeroSerie(numeroSerie)) {
                throw new RuntimeException("El número de serie ya existe: " + numeroSerie);
            }
            
            ProductoSerie serie = new ProductoSerie(numeroSerie, producto);
            serie.setFechaVencimiento(fechaVencimiento);
            seriesCreadas.add(agregarProductoSerie(serie));
        }
          return seriesCreadas;
    }
      @Override
    public void recalcularStockProducto(Producto producto) {
        Integer stockTotal = calcularStockTotalProducto(producto);
        producto.setStock(stockTotal);
        productoRepository.save(producto);
    }
    
    @Override
    public Integer calcularStockTotalProducto(Producto producto) {
        List<ProductoSerie> seriesDisponibles = obtenerSeriesDisponiblesPorProducto(producto);
        return seriesDisponibles.stream()
                .mapToInt(serie -> serie.getCantidad() != null ? serie.getCantidad() : 1)
                .sum();
    }
}
