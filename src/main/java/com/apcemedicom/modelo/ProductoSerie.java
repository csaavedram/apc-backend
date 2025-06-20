package com.apcemedicom.modelo;

import javax.persistence.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "producto_serie")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductoSerie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoSerieId;
    
    @Column(nullable = false, unique = true)
    private String numeroSerie;
    
    @Column(name = "fechaVencimiento")
    private Date fechaVencimiento; // Opcional para productos perecibles    @Column(nullable = false)
    private String estado; // DISPONIBLE, VENDIDO, VENCIDO, DEFECTUOSO
    
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad = 1; // Cantidad de productos en este lote/serie
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productoId", nullable = false)
    @JsonIgnore
    private Producto producto;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facturaDetalleId")
    @JsonIgnore
    private FacturaDetalle facturaDetalle; // Se asigna al facturar
    
    @Column(name = "fechaCreacion")
    private Date fechaCreacion;
    
    @Column(name = "fechaVenta")
    private Date fechaVenta; // Se establece cuando se vende
    
    private String observaciones;

    // Constructor vacío
    public ProductoSerie() {
        this.fechaCreacion = new Date();
        this.estado = "DISPONIBLE";
    }

    // Constructor con parámetros básicos
    public ProductoSerie(String numeroSerie, Producto producto) {
        this();
        this.numeroSerie = numeroSerie;
        this.producto = producto;
    }

    // Getters y Setters
    public Long getProductoSerieId() {
        return productoSerieId;
    }

    public void setProductoSerieId(Long productoSerieId) {
        this.productoSerieId = productoSerieId;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getEstado() {
        return estado;
    }    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public FacturaDetalle getFacturaDetalle() {
        return facturaDetalle;
    }

    public void setFacturaDetalle(FacturaDetalle facturaDetalle) {
        this.facturaDetalle = facturaDetalle;
        if (facturaDetalle != null && "DISPONIBLE".equals(this.estado)) {
            this.estado = "VENDIDO";
            this.fechaVenta = new Date();
        }
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    // Método para verificar si está vencido
    public boolean isVencido() {
        if (fechaVencimiento == null) return false;
        return new Date().after(fechaVencimiento);
    }

    // Método para verificar si está disponible para venta
    public boolean isDisponible() {
        return "DISPONIBLE".equals(estado) && !isVencido();
    }
}
