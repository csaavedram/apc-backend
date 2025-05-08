package com.apcemedicom.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "factura")
public class Factura {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long facturaId;

  public enum Divisa { Soles, Dolares }

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Divisa divisa;

  public enum TipoPago { Contado, Credito }

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TipoPago tipoPago;

  @Column(name = "total")
  private Double total;

  @ManyToOne
  @JoinColumn(name = "id")
  private Usuario user;
  
  @Column(name = "fechaEmision")
  private java.util.Date fechaEmision;

  public Factura() { }

  public Long getFacturaId() { return facturaId; }
  public void setFacturaId(Long facturaId) { this.facturaId = facturaId; }

  public Divisa getDivisa() { return divisa; }
  public void setDivisa(Divisa divisa) { this.divisa = divisa; }

  public TipoPago getTipoPago() { return tipoPago; }
  public void setTipoPago(TipoPago tipoPago) { this.tipoPago = tipoPago; }

  public Double getTotal() { return total; }
  public void setTotal(Double total) { this.total = total; }

  public Usuario getUser() { return user; }
  public void setUser(Usuario user) { this.user = user; }

  public Date getFechaEmision() { return fechaEmision; }
  public void setFechaEmision(Date fechaEmision) { this.fechaEmision = fechaEmision; }
}
