package com.albahaca.ecommerce.models;

public class CartItem {
    private ProductoModel producto; // Suponiendo que tienes una clase Producto
    private int cantidad;

    // Constructor, getters y setters
    public CartItem(ProductoModel producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public ProductoModel getProducto() {
        return producto;
    }

    public void setProducto(ProductoModel producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
