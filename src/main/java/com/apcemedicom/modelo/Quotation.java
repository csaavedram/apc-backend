package com.apcemedicom.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "quotation")
public class Quotation {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long quotationId;
  
  public enum Divisa { SOLES, DOLAR }
  
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Divisa divisa;
  
  public enum TipoPago { CONTADO, CREDITO }
  
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
  @JoinColumn(name = "userId")
  private Usuario user;
  
  public Quotation() { }
  
  public Long getQuotationId() { return quotationId; }
  public void setQuotationId(Long quotationId) { this.quotationId = quotationId; }
  
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
}
