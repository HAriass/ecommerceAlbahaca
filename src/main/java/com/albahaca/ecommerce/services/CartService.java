package com.albahaca.ecommerce.services;
import com.albahaca.ecommerce.models.CartItem;
import com.albahaca.ecommerce.models.ProductoModel;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private List<CartItem> items = new ArrayList<>();

    public void addToCart(ProductoModel producto, int cantidad) {
        // Verificar si el producto ya está en el carrito
        for (CartItem item : items) {
            if (item.getProducto().getId().equals(producto.getId())) {
                item.setCantidad(item.getCantidad() + cantidad);
                return;
            }
        }
        // Si no está, agregar un nuevo CartItem
        items.add(new CartItem(producto, cantidad));
    }

    public List<CartItem> getCartItems() {
        return items;
    }
    
    public void delItemToCart(Long idProducto) {
        // Buscar el producto en la lista de items
        items.removeIf(item -> item.getProducto().getId().equals(idProducto));
    }


    public void clearCart() {
        items.clear();
    }
}
