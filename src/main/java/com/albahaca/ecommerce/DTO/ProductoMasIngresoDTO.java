
package com.albahaca.ecommerce.DTO;

public class ProductoMasIngresoDTO {
    private String nombreProducto;
    private Double ingreso;

    public ProductoMasIngresoDTO(String nombreProducto, Double ingreso) {
        this.nombreProducto = nombreProducto;
        this.ingreso = ingreso;
    }

    // Getters y setters
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Double getIngreso() {
        return ingreso;
    }

    public void setIngreso(Double ingreso) {
        this.ingreso = ingreso;
    }
}
