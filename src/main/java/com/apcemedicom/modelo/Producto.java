package com.apcemedicom.modelo;
import javax.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "productos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoId;
    private String nombreProducto;
    private String SKU;
    @Column(columnDefinition = "decimal(10,2)")
    private Double precio;
    private Integer stock;
    private String descripcion;
    private String imagen;
    @Column(name = "datecreated")
    private java.util.Date dateCreated;    private Integer status;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoriaId")
    private Categoria categoria;
    
    // Relación con números de serie
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ProductoSerie> numerosSerie;
    
    // Campo para indicar si el producto requiere número de serie
    @Column(name = "requiereNumeroSerie")
    private Boolean requiereNumeroSerie = false;
      // Campo para indicar si el producto es perecible
    @Column(name = "esPerecible")
    private Boolean esPerecible = false;
    
    public Long getProductoId() {
        return productoId;
    }
    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public String getSKU() {
        return SKU;
    }
    public void setSKU(String SKU) {
        this.SKU = SKU;
    }
    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public Integer getStock(){ return stock; }
    public void setStock(Integer stock){this.stock = stock;}
    public java.util.Date getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(java.util.Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    public Integer getStatus(){return status;}
    public void setStatus(Integer status){this.status = status;}
    public Categoria getCategoria() {        return categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public List<ProductoSerie> getNumerosSerie() {
        return numerosSerie;
    }
    
    public void setNumerosSerie(List<ProductoSerie> numerosSerie) {
        this.numerosSerie = numerosSerie;
    }
    
    public Boolean getRequiereNumeroSerie() {
        return requiereNumeroSerie;
    }
    
    public void setRequiereNumeroSerie(Boolean requiereNumeroSerie) {
        this.requiereNumeroSerie = requiereNumeroSerie;
    }
    
    public Boolean getEsPerecible() {
        return esPerecible;
    }
    
    public void setEsPerecible(Boolean esPerecible) {
        this.esPerecible = esPerecible;
    }
    
    public Producto(){

    }
}

