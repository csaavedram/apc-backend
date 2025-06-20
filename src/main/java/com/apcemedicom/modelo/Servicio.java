package com.apcemedicom.modelo;
import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long servicioId;

    private String nombre;
    private String descripcion;
    private Double costo;

    @Column(name = "fecharealizado")
    private Date fechaRealizado;

    @Column(name = "garantia")
    private Integer garantia;

    @Column(name = "fechafingarantia")
    private Date fechaFinGarantia;

    private Integer status;

    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleServicio> detalleServicios;

    // Constructor vacío
    public Servicio() {
    }

    // Getters y Setters
    public Long getServicioId() {
        return servicioId;
    }

    public void setServicioId(Long servicioId) {
        this.servicioId = servicioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Date getFechaRealizado() {
        return fechaRealizado;
    }

    public void setFechaRealizado(Date fechaRealizado) {
        this.fechaRealizado = fechaRealizado;
    }

    public Integer getGarantia() {
        return garantia;
    }

    public void setGarantia(Integer garantia) {
        this.garantia = garantia;
    }

    public Date getFechaFinGarantia() {
        return fechaFinGarantia;
    }
    public void setFechaFinGarantia(Date fechaFinGarantia) {
        this.fechaFinGarantia = fechaFinGarantia;
    }

    public Integer getStatus() {
        return status;
    }    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<DetalleServicio> getDetalleServicios() {
        return detalleServicios;
    }

    public void setDetalleServicios(List<DetalleServicio> detalleServicios) {
        this.detalleServicios = detalleServicios;
    }
}
