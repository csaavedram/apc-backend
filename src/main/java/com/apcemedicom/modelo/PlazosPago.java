package com.apcemedicom.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "plazos_pago")
public class PlazosPago {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long plazoPagoId;

  @Column(nullable = false)
  private Double cantidad; 

  @ManyToOne
  @JoinColumn(name = "facturaId", nullable = true)
  private Factura factura;

  @ManyToOne
  @JoinColumn(name = "cotizacionId", nullable = true)
  private Cotizacion cotizacion;

  @ManyToOne
  @JoinColumn(name = "notaCreditoId", nullable = true)
  private NotaCredito notaCredito;

  @Column(name = "fechaInicio", nullable = false)
  private Date fechaInicio; 

  @Column(name = "fechaFin", nullable = false)
  private Date fechaFin; 
  
  @Column(nullable = false)
  private String estado;

  @Column(nullable = false)
  private Integer nroCuota;

  public PlazosPago() { }

  public Long getPlazoPagoId() { return plazoPagoId; }
  public void getPlazoPagoId(Long plazoPagoId) { this.plazoPagoId = plazoPagoId; }
  public Double getCantidad() { return cantidad; }
  public void setCantidad(Double cantidad) { this.cantidad = cantidad; }
  public Factura getFactura() { return factura; }
  public void setFactura(Factura factura) { this.factura = factura; }
  public Cotizacion getCotizacion() { return cotizacion; }
  public void setCotizacion(Cotizacion cotizacion) { this.cotizacion = cotizacion; }
  public NotaCredito getNotaCredito() { return notaCredito; }
  public void setNotaCredito(NotaCredito notaCredito) { this.notaCredito = notaCredito; }
  public Date getFechaInicio() { return fechaInicio; }
  public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }
  public Date getFechaFin() { return fechaFin; }
  public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }
  public String getEstado() { return estado; }
  public void setEstado(String estado) { this.estado = estado; }
  public Integer getNroCuota() {
    return nroCuota;
  }
  public void setNroCuota(Integer nroCuota) {
    this.nroCuota = nroCuota;
  }
}
