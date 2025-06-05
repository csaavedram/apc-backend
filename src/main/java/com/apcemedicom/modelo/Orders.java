package com.apcemedicom.modelo;
import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "id")
    private Usuario user;
    @Column(columnDefinition = "decimal(10,2)")
    private Double subtotalPrice;
    @Column(columnDefinition = "decimal(10,2)")
    private Double deliveryPrice;
    @Column(columnDefinition = "decimal(10,2)")
    private Double totalPrice;
    @Column(columnDefinition = "decimal(10,2)")
    private Double preciocli;
    private String streetAddress;
    private String status;
    @Column(name = "createdAt")
    private java.util.Date createdAt;
    private java.util.Date fechaOperacion;
    private String nOperacion;
    private String tipoOperacion;
    private String tipoPago;

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Usuario getUser() {
        return user;
    }
    public void setUser(Usuario user) {
        this.user = user;
    }
    public Double getSubtotalPrice() {
        return subtotalPrice;
    }
    public void setSubtotalPrice(Double subtotalPrice) {
        this.subtotalPrice = subtotalPrice;
    }
    public Double getDeliveryPrice() {
        return deliveryPrice;
    }
    public void setDeliveryPrice(Double deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }
    public Double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Double getPreciocli() {
        return preciocli;
    }
    public void setPreciocli(Double preciocli) {
        this.preciocli = preciocli;
    }
    public String getStreetAddress() {
        return streetAddress;
    }
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    public String getStatus(){return status;}
    public void setStatus(String status){this.status = status;}
    public java.util.Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }
    public java.util.Date getFechaOperacion() {
        return fechaOperacion;
    }
    public void setFechaOperacion(java.util.Date fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }
    public String getNOperacion() {
        return nOperacion;
    }
    public void setNOperacion(String nOperacion) {
        this.nOperacion = nOperacion;
    }
    public String getTipoOperacion() {
        return tipoOperacion;
    }
    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }
    public String getTipoPago() {
        return tipoPago;
    }
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
    public Orders(){

    }
}

