package com.apcemedicom.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cotizacionDetalle")
public class CotizacionDetalle {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cotizacionDetalleId;
  private Integer cantidad;
  @Column(columnDefinition = "decimal(10,2)")
  private Double precioTotal;
  @Column(columnDefinition = "decimal(10,2)")
  private Double precioUnitario;
  @Column(columnDefinition = "decimal(10,2)")
  private Double precioNuevo;
  @ManyToOne
  @JoinColumn(name = "productoId")
  private Producto producto;
  @ManyToOne
  @JoinColumn(name = "cotizacionId")
  private Cotizacion cotizacion;
  @Column(name = "createdAt")
  private java.util.Date createdAt;
  
  private String tipoServicio; 
  
  public CotizacionDetalle() { }
  public Long getCotizacionDetalleId() { return cotizacionDetalleId; }
  public void setCotizacionDetalleId(Long cotizacionDetalleId) { this.cotizacionDetalleId = cotizacionDetalleId; }
  public int getCantidad() { return cantidad; }
  public void setCantidad(int cantidad) { this.cantidad = cantidad; }
  public Double getPrecioTotal() { return precioTotal; }
  public void setPrecioTotal(Double precioTotal) { this.precioTotal = precioTotal; }
  public Double getPrecioUnitario() { return precioUnitario; }
  public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
  public Double getPrecioNuevo() { return precioNuevo; }
  public void setPrecioNuevo(Double precioNuevo) { this.precioNuevo = precioNuevo; }
  public Producto getProducto() { return producto; }
  public void setProducto(Producto producto) { this.producto = producto; }
  public Cotizacion getCotizacion() { return cotizacion; }
  public void setCotizacion(Cotizacion cotizacion) { this.cotizacion = cotizacion; }
  public Date getCreatedAt() { return createdAt; }
  public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
  public String getTipoServicio() { return tipoServicio; }
  public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }
}
