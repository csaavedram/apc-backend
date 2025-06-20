package com.apcemedicom.modelo;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "facturaDetalle")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FacturaDetalle {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long facturaDetalleId;
  private Integer cantidad;
  @Column(columnDefinition = "decimal(10,2)")
  private Double precioTotal;
  @Column(columnDefinition = "decimal(10,2)")
  private Double precioUnitario;  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "productoId")
  private Producto producto;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "facturaId")
  private Factura factura;
  @Column(name = "createdAt")
  private java.util.Date createdAt;
  private String tipoServicio;
  // Relación con números de serie asignados a esta factura
  @OneToMany(mappedBy = "facturaDetalle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnore
  private List<ProductoSerie> numerosSerieAsignados;

  public FacturaDetalle() { }
  public Long getFacturaDetalleId() { return facturaDetalleId; }
  public void setFacturaDetalleId(Long facturaDetalleId) { this.facturaDetalleId = facturaDetalleId; }
  public int getCantidad() { return cantidad; }
  public void setCantidad(int cantidad) { this.cantidad = cantidad; }
  public Double getPrecioTotal() { return precioTotal; }
  public void setPrecioTotal(Double precioTotal) { this.precioTotal = precioTotal; }
  public Double getPrecioUnitario() { return precioUnitario; }
  public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
  public Producto getProducto() { return producto; }
  public void setProducto(Producto producto) { this.producto = producto; }
  public Factura getFactura() { return factura; }
  public void setFactura(Factura factura) { this.factura = factura; }
  public Date getCreatedAt() { return createdAt; }
  public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }  public String getTipoServicio() { return tipoServicio; }
  public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }
  
  public List<ProductoSerie> getNumerosSerieAsignados() {
    return numerosSerieAsignados;
  }
  
  public void setNumerosSerieAsignados(List<ProductoSerie> numerosSerieAsignados) {
    this.numerosSerieAsignados = numerosSerieAsignados;
  }
}
