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
  private Integer dias; 

  @Column(nullable = false)
  private Double cantidad; 

  @ManyToOne
  @JoinColumn(name = "facturaId", nullable = false)
  private Factura factura;

  @Column(name = "fechaInicio", nullable = false)
  private Date fechaInicio; 

  @Column(name = "fechaFin", nullable = false)
  private Date fechaFin; 

  public PlazosPago() { }

  public Long getPlazoPagoId() { return plazoPagoId; }
  public void getPlazoPagoId(Long plazoPagoId) { this.plazoPagoId = plazoPagoId; }
  public Integer getDias() { return dias; }
  public void setDias(Integer dias) { this.dias = dias; }
  public Double getCantidad() { return cantidad; }
  public void setCantidad(Double cantidad) { this.cantidad = cantidad; }
  public Factura getFactura() { return factura; }
  public void setFactura(Factura factura) { this.factura = factura; }
  public Date getFechaInicio() { return fechaInicio; }
  public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }
  public Date getFechaFin() { return fechaFin; }
  public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }
}
