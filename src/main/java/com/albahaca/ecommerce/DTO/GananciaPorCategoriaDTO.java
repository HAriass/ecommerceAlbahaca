
package com.albahaca.ecommerce.DTO;

public class GananciaPorCategoriaDTO {
    private String nombreCategoria;
    private Double gananciaTotal;

    // Constructor
    public GananciaPorCategoriaDTO(String nombreCategoria, Double gananciaTotal) {
        this.nombreCategoria = nombreCategoria;
        this.gananciaTotal = gananciaTotal;
    }

    // Getters y setters
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public Double getGananciaTotal() {
        return gananciaTotal;
    }

    public void setGananciaTotal(Double gananciaTotal) {
        this.gananciaTotal = gananciaTotal;
    }
}
