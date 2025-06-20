package com.apcemedicom.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "detalle_servicios")
public class DetalleServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detalleServicioId;

    @Column(name = "modelo_equipo")
    private String modeloEquipo;

    @Column(name = "sku")
    private String sku;

    @Column(name = "numero_serie")
    private String numeroSerie;

    @Column(name = "fecha_garantia")
    private Date fechaGarantia;

    @Column(name = "observacion", length = 1000)
    private String observacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "servicio_id", nullable = false)
    @JsonIgnore
    private Servicio servicio;

    // Constructor vacío
    public DetalleServicio() {
    }

    // Constructor con parámetros
    public DetalleServicio(String modeloEquipo, String sku, String numeroSerie, Date fechaGarantia, String observacion, Servicio servicio) {
        this.modeloEquipo = modeloEquipo;
        this.sku = sku;
        this.numeroSerie = numeroSerie;
        this.fechaGarantia = fechaGarantia;
        this.observacion = observacion;
        this.servicio = servicio;
    }

    // Getters y Setters
    public Long getDetalleServicioId() {
        return detalleServicioId;
    }

    public void setDetalleServicioId(Long detalleServicioId) {
        this.detalleServicioId = detalleServicioId;
    }

    public String getModeloEquipo() {
        return modeloEquipo;
    }

    public void setModeloEquipo(String modeloEquipo) {
        this.modeloEquipo = modeloEquipo;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Date getFechaGarantia() {
        return fechaGarantia;
    }

    public void setFechaGarantia(Date fechaGarantia) {
        this.fechaGarantia = fechaGarantia;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
}
