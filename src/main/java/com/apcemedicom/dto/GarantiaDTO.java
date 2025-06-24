package com.apcemedicom.dto;

import java.util.Date;

public class GarantiaDTO {
    private Long facturaDetalleId;
    private Integer cantidad;
    private Double precioTotal;
    private Double precioUnitario;
    private String numerosSerie;
    private String tipoServicio;
    private Date createdAt;
    
    // Datos del producto
    private Long productoId;
    private String nombreProducto;
    private String descripcionProducto;
    
    // Datos de la factura
    private Long facturaId;
    private String codigoFactura;
    private String divisa;
    private String tipoPago;
    private Double totalFactura;
    private Date fechaEmision;
    private String estadoFactura;
    
    // Datos del cliente/usuario
    private Long usuarioId;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String razonSocial;
    private String rucUsuario;
    private String emailUsuario;
    private String tipoUsuario;
    private String usernameUsuario;
    
    // Constructor vacío
    public GarantiaDTO() {}
    
    // Constructor completo
    public GarantiaDTO(Long facturaDetalleId, Integer cantidad, Double precioTotal, Double precioUnitario,
                       String numerosSerie, String tipoServicio, Date createdAt,
                       Long productoId, String nombreProducto, String descripcionProducto,
                       Long facturaId, String codigoFactura, String divisa, String tipoPago,
                       Double totalFactura, Date fechaEmision, String estadoFactura,
                       Long usuarioId, String nombreUsuario, String apellidoUsuario, String razonSocial,
                       String rucUsuario, String emailUsuario, String tipoUsuario, String usernameUsuario) {
        this.facturaDetalleId = facturaDetalleId;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
        this.precioUnitario = precioUnitario;
        this.numerosSerie = numerosSerie;
        this.tipoServicio = tipoServicio;
        this.createdAt = createdAt;
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.facturaId = facturaId;
        this.codigoFactura = codigoFactura;
        this.divisa = divisa;
        this.tipoPago = tipoPago;
        this.totalFactura = totalFactura;
        this.fechaEmision = fechaEmision;
        this.estadoFactura = estadoFactura;
        this.usuarioId = usuarioId;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.razonSocial = razonSocial;
        this.rucUsuario = rucUsuario;
        this.emailUsuario = emailUsuario;
        this.tipoUsuario = tipoUsuario;
        this.usernameUsuario = usernameUsuario;
    }
    
    // Getters y Setters
    public Long getFacturaDetalleId() { return facturaDetalleId; }
    public void setFacturaDetalleId(Long facturaDetalleId) { this.facturaDetalleId = facturaDetalleId; }
    
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    
    public Double getPrecioTotal() { return precioTotal; }
    public void setPrecioTotal(Double precioTotal) { this.precioTotal = precioTotal; }
    
    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
    
    public String getNumerosSerie() { return numerosSerie; }
    public void setNumerosSerie(String numerosSerie) { this.numerosSerie = numerosSerie; }
    
    public String getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    
    public Long getProductoId() { return productoId; }
    public void setProductoId(Long productoId) { this.productoId = productoId; }
    
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
    
    public String getDescripcionProducto() { return descripcionProducto; }
    public void setDescripcionProducto(String descripcionProducto) { this.descripcionProducto = descripcionProducto; }
    
    public Long getFacturaId() { return facturaId; }
    public void setFacturaId(Long facturaId) { this.facturaId = facturaId; }
    
    public String getCodigoFactura() { return codigoFactura; }
    public void setCodigoFactura(String codigoFactura) { this.codigoFactura = codigoFactura; }
    
    public String getDivisa() { return divisa; }
    public void setDivisa(String divisa) { this.divisa = divisa; }
    
    public String getTipoPago() { return tipoPago; }
    public void setTipoPago(String tipoPago) { this.tipoPago = tipoPago; }
    
    public Double getTotalFactura() { return totalFactura; }
    public void setTotalFactura(Double totalFactura) { this.totalFactura = totalFactura; }
    
    public Date getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(Date fechaEmision) { this.fechaEmision = fechaEmision; }
    
    public String getEstadoFactura() { return estadoFactura; }
    public void setEstadoFactura(String estadoFactura) { this.estadoFactura = estadoFactura; }
    
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    
    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    
    public String getApellidoUsuario() { return apellidoUsuario; }
    public void setApellidoUsuario(String apellidoUsuario) { this.apellidoUsuario = apellidoUsuario; }
    
    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }
    
    public String getRucUsuario() { return rucUsuario; }
    public void setRucUsuario(String rucUsuario) { this.rucUsuario = rucUsuario; }
    
    public String getEmailUsuario() { return emailUsuario; }
    public void setEmailUsuario(String emailUsuario) { this.emailUsuario = emailUsuario; }
    
    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }
    
    public String getUsernameUsuario() { return usernameUsuario; }
    public void setUsernameUsuario(String usernameUsuario) { this.usernameUsuario = usernameUsuario; }
}
