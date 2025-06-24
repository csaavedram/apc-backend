package com.apcemedicom.dto;

public class FacturaDetalleDashboardDTO {
    private Long facturaDetalleId;
    private Integer cantidad;
    private Double precioTotal;
    private Double precioUnitario;
    private String nombreProducto;
    private String tipoServicio;
    
    // Constructores
    public FacturaDetalleDashboardDTO() {}
    
    public FacturaDetalleDashboardDTO(Long facturaDetalleId, Integer cantidad, Double precioTotal, 
                                    Double precioUnitario, String nombreProducto, String tipoServicio) {
        this.facturaDetalleId = facturaDetalleId;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
        this.precioUnitario = precioUnitario;
        this.nombreProducto = nombreProducto;
        this.tipoServicio = tipoServicio;
    }
    
    // Getters y Setters
    public Long getFacturaDetalleId() {
        return facturaDetalleId;
    }
    
    public void setFacturaDetalleId(Long facturaDetalleId) {
        this.facturaDetalleId = facturaDetalleId;
    }
    
    public Integer getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    public Double getPrecioTotal() {
        return precioTotal;
    }
    
    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }
    
    public Double getPrecioUnitario() {
        return precioUnitario;
    }
    
    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    
    public String getNombreProducto() {
        return nombreProducto;
    }
    
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
    public String getTipoServicio() {
        return tipoServicio;
    }
    
    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }
}
