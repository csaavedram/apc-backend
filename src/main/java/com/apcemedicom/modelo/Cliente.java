package com.apcemedicom.modelo;
import javax.persistence.*;
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;
    private String razonSocial;
    private String ruc;
    @Column(name = "datecreated")
    private java.util.Date dateCreated;
    private Integer status;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    public java.util.Date getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(java.util.Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    public Integer getStatus(){return status;}
    public void setStatus(Integer status){this.status = status;}

    public Cliente(){

    }
}

