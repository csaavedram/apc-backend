package com.apcemedicom.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment_terms")
public class PaymentTerm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long paymentTermId;

  @Column(nullable = false)
  private Integer days; 

  @Column(nullable = false)
  private Double amount; 

  @ManyToOne
  @JoinColumn(name = "facturaId", nullable = false)
  private Factura factura;

  @Column(name = "startDate", nullable = false)
  private Date startDate; 

  @Column(name = "dueDate", nullable = false)
  private Date dueDate; 

  public PaymentTerm() { }

  public Long getPaymentTermId() { return paymentTermId; }
  public void setPaymentTermId(Long paymentTermId) { this.paymentTermId = paymentTermId; }

  public Integer getDays() { return days; }
  public void setDays(Integer days) { this.days = days; }

  public Double getAmount() { return amount; }
  public void setAmount(Double amount) { this.amount = amount; }

  public Factura getFactura() { return factura; }
  public void setFactura(Factura factura) { this.factura = factura; }

  public Date getStartDate() { return startDate; }
  public void setStartDate(Date startDate) { this.startDate = startDate; }

  public Date getDueDate() { return dueDate; }
  public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
}
