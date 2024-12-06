
package com.albahaca.ecommerce.DTO;

public class ProductoMasVendidoDTO {
    private String nombreProducto;
    private Long cantidadVendida;

    // Constructor
    public ProductoMasVendidoDTO(String nombreProducto, Long cantidadVendida) {
        this.nombreProducto = nombreProducto;
        this.cantidadVendida = cantidadVendida;
    }

    // Getters y Setters
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Long getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(Long cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }
}
