package com.apcemedicom.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "facturaDetails")
public class FacturaDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long facturaDetailsId;
  private Integer cantidad;
  @Column(columnDefinition = "decimal(10,2)")
  private Double totalPrice;
  @Column(columnDefinition = "decimal(10,2)")
  private Double unitPrice;
  @ManyToOne
  @JoinColumn(name = "productoId")
  private Producto product;
  @ManyToOne
  @JoinColumn(name = "facturaId")
  private Factura factura;
  @Column(name = "createdAt")
  private java.util.Date createdAt;

  private String serviceType;

  public FacturaDetails() { }
  public Long getFacturaDetailsId() { return facturaDetailsId; }
  public void setFacturaDetailsId(Long facturaDetailsId) { this.facturaDetailsId = facturaDetailsId; }
  public int getCantidad() { return cantidad; }
  public void setCantidad(int cantidad) { this.cantidad = cantidad; }
  public Double getTotalPrice() { return totalPrice; }
  public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
  public Double getUnitPrice() { return unitPrice; }
  public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }
  public Producto getProduct() {
    return product;
  }
  public void setProduct(Producto product) {
    this.product = product;
  }
  public Factura getFactura() {
    return factura;
  }
  public void setFactura(Factura factura) {
    this.factura = factura;
  }
  public Date getCreatedAt() { return createdAt; }
  public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
  public String getServiceType() {
    return serviceType;
  }
  public void setServiceType(String serviceType) {
    this.serviceType = serviceType;
  }
}
