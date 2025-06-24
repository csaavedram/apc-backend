package com.apcemedicom.modelo;
import javax.persistence.*;
@Entity
@Table(name = "inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventarioId;
    @ManyToOne
    @JoinColumn(name = "productoId")
    private Producto producto;    private Integer cantidad;
    private String tipo;
    private String numeroSerie; // Nuevo campo para número de serie/lote
    @Column(name = "datecreated")
    private java.util.Date dateCreated;
    public Long getInventarioId() {
        return inventarioId;
    }
    public void setInventarioId(Long inventarioId) {
        this.inventarioId = inventarioId;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public java.util.Date getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(java.util.Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    public Producto getProducto() {
        return producto;
    }    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Inventario(){

    }
}

