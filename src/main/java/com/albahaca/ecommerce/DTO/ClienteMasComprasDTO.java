package com.albahaca.ecommerce.DTO;

public class ClienteMasComprasDTO {
    private String nombreCliente;
    private Long cantidadDeCompras;

    public ClienteMasComprasDTO(String nombreCliente, Long cantidadDeCompras) {
        this.nombreCliente = nombreCliente;
        this.cantidadDeCompras = cantidadDeCompras;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Long getCantidadDeCompras() {
        return cantidadDeCompras;
    }

    public void setCantidadDeCompras(Long cantidadDeCompras) {
        this.cantidadDeCompras = cantidadDeCompras;
    }
}
