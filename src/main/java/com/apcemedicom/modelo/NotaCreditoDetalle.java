package com.apcemedicom.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notaCreditoDetalle")
public class NotaCreditoDetalle {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long notaCreditoDetalleId;
  private Integer cantidad;
  @Column(columnDefinition = "decimal(10,2)")
  private Double precioTotal;
  @Column(columnDefinition = "decimal(10,2)")
  private Double precioUnitario;
  @ManyToOne
  @JoinColumn(name = "productoId")
  private Producto producto;
  @ManyToOne
  @JoinColumn(name = "notaCreditoId")
  private NotaCredito notaCredito;
  @Column(name = "createdAt")
  private java.util.Date createdAt;
  private String tipoServicio;

  public NotaCreditoDetalle() { }
  public Long getNotaCreditoDetalleId() { return notaCreditoDetalleId; }
  public void setNotaCreditoDetalleId(Long notaCreditoDetalleId) { this.notaCreditoDetalleId = notaCreditoDetalleId; }
  public int getCantidad() { return cantidad; }
  public void setCantidad(int cantidad) { this.cantidad = cantidad; }
  public Double getPrecioTotal() { return precioTotal; }
  public void setPrecioTotal(Double precioTotal) { this.precioTotal = precioTotal; }
  public Double getPrecioUnitario() { return precioUnitario; }
  public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
  public Producto getProducto() { return producto; }
  public void setProducto(Producto producto) { this.producto = producto; }
  public NotaCredito getNotaCredito() { return notaCredito; }
  public void setNotaCredito(NotaCredito notaCredito) { this.notaCredito = notaCredito; }
  public Date getCreatedAt() { return createdAt; }
  public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
  public String getTipoServicio() { return tipoServicio; }
  public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }
}
