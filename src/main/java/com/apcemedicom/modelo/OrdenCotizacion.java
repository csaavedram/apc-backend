package com.apcemedicom.modelo;

import javax.persistence.*;

@Entity
@Table(name = "ordenCotizacion")
public class OrdenCotizacion {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ordenCotizacionId;

  @ManyToOne
  @JoinColumn(name = "cotizacionId")
  private Cotizacion cotizacion;

  @ManyToOne
  @JoinColumn(name = "orderId")
  private Orders order;

  public OrdenCotizacion() {}

  public Long getOrdenCotizacionId() {
      return ordenCotizacionId;
  }

  public void setOrdenCotizacionId(Long ordenCotizacionId) {
      this.ordenCotizacionId = ordenCotizacionId;
  }

  public Cotizacion getCotizacion() {
      return cotizacion;
  }

  public void setCotizacion(Cotizacion cotizacion) {
      this.cotizacion = cotizacion;
  }

  public Orders getOrder() {
      return order;
  }

  public void setOrder(Orders order) {
      this.order = order;
  }
}
