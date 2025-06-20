package com.apcemedicom.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notaCredito")
public class NotaCredito {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long notaCreditoId;

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

  @Column(name = "codigo", unique = true)
  private String codigo;

  @Column(name = "estado", nullable = false)
  private String estado;

  @ManyToOne
  @JoinColumn(name = "facturaId", nullable = true)
  private Factura factura;

  @PrePersist
  private void generarCodigoTemporal() {
    if (this.codigo == null) {
      int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
      this.codigo = "TEMP-" + year; 
    }
  }

  @PostPersist
  private void actualizarCodigo() {
    if (this.codigo.startsWith("TEMP-")) {
      this.codigo = "N001-00" + this.notaCreditoId;
    }
  }

  public NotaCredito() { }

  public Long getNotaCreditoId() { return notaCreditoId; }
  public void setNotaCreditoId(Long notaCreditoId) { this.notaCreditoId = notaCreditoId; }
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
  public String getCodigo() { return codigo; }
  public void setCodigo(String codigo) { this.codigo = codigo; }
  public String getEstado() { return estado; }
  public void setEstado(String estado) { this.estado = estado; }
  public Factura getFactura() { return factura; }
  public void setFactura(Factura factura) { this.factura = factura; }
}
