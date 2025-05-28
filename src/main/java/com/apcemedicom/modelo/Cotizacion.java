package com.apcemedicom.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cotizacion")
public class Cotizacion {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cotizacionId;
  
  public enum Divisa { Soles, Dolares }
  
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Divisa divisa;
  
  public enum TipoPago { Contado, Credito }
  
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TipoPago tipoPago;
  
  @Column(name = "plazoEntrega")
  private Date plazoEntrega;
  
  @Column(name = "validezOferta")
  private Date validezOferta;
  
  @Column(name = "total")
  private Double total;
  
  @ManyToOne
  @JoinColumn(name = "id")
  private Usuario user;
  
  @Column(nullable = false)
  private String estado;

  @Column(name = "createdAt")
  private java.util.Date createdAt;

  @Column(name = "codigo", unique = true)
  private String codigo;

  @PrePersist
  private void generarCodigo() {
    if (this.codigo == null) {
      int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
      this.codigo = "TEMP-" + year; 
    }
  }

  @PostPersist
  private void actualizarCodigo() {
    if (this.codigo.startsWith("TEMP-")) {
      int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
      this.codigo = this.cotizacionId + "-" + year;
    }
  }
  
  public Cotizacion() { }
  
  public Long getCotizacionId() { return cotizacionId; }
  public void setCotizacionId(Long cotizacionId) { this.cotizacionId = cotizacionId; }
  public Divisa getDivisa() { return divisa; }
  public void setDivisa(Divisa divisa) { this.divisa = divisa; }
  public TipoPago getTipoPago() { return tipoPago; }
  public void setTipoPago(TipoPago tipoPago) { this.tipoPago = tipoPago; }
  public Date getPlazoEntrega() { return plazoEntrega; }
  public void setPlazoEntrega(Date plazoEntrega) { this.plazoEntrega = plazoEntrega; }
  public Date getValidezOferta() { return validezOferta; }
  public void setValidezOferta(Date validezOferta) { this.validezOferta = validezOferta; }
  public Double getTotal() { return total; }
  public void setTotal(Double total) { this.total = total; }
  public Usuario getUser() { return user; }
  public void setUser(Usuario user) { this.user = user; }
  public String getEstado() { return estado; }
  public void setEstado(String estado) { this.estado = estado; }
  public Date getCreatedAt() { return createdAt; }
  public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
  public String getCodigo() { return codigo; }
  public void setCodigo(String codigo) { this.codigo = codigo; }
}
