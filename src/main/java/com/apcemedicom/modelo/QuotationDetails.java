package com.apcemedicom.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "quotationDetails")
public class QuotationDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long quotationdetailsId;
  private Integer cantidad;
  @Column(columnDefinition = "decimal(10,2)")
  private Double totalPrice;
  @Column(columnDefinition = "decimal(10,2)")
  private Double unitPrice;
  @ManyToOne
  @JoinColumn(name = "productoId")
  private Producto product;
  @ManyToOne
  @JoinColumn(name = "quotationId")
  private Quotation quotation;
  @Column(name = "createdAt")
  private java.util.Date createdAt;
  
  public QuotationDetails() { }
  public Long getQuotationdetailsId() { return quotationdetailsId; }
  public void setQuotationdetailsId(Long quotationdetailsId) { this.quotationdetailsId = quotationdetailsId; }
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
  public Quotation getQuotation() {
    return quotation;
  }
  public void setQuotation(Quotation quotation) {
    this.quotation = quotation;
  }
  public Date getCreatedAt() { return createdAt; }
  public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
