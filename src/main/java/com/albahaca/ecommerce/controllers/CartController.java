package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.CartItem;
import com.albahaca.ecommerce.models.DetallePedidoModel;
import com.albahaca.ecommerce.models.EstadoModel;
import com.albahaca.ecommerce.models.PedidoModel;
import com.albahaca.ecommerce.models.ProductoModel;
import com.albahaca.ecommerce.services.CartService;
import com.albahaca.ecommerce.services.CuentaDetailsService;
import com.albahaca.ecommerce.services.DetallePedidoService;
import com.albahaca.ecommerce.services.EstadoService;
import com.albahaca.ecommerce.services.ProductoService;
import com.albahaca.ecommerce.services.PedidoService; // Agregar el servicio de Pedido
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private PedidoService pedidoService;
    
    @Autowired 
    private CuentaDetailsService cuentaDetailsService;
    
    @Autowired 
    private EstadoService estadoService;
    
    @Autowired
    private DetallePedidoService detallePedidoService;

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("productoId") Long productoId, 
                            @RequestParam("cantidad") int cantidad, Model model) {
        Optional<ProductoModel> productoOptional = productoService.obtenerProductoPorId(productoId);
        if (!productoOptional.isPresent()) {
            model.addAttribute("error", "El producto no se encontró");
            return "error";
        }
        ProductoModel producto = productoOptional.get();
        cartService.addToCart(producto, cantidad);
        return "redirect:/";
    }
    
    @PostMapping("/delItemToCart")
    public String delItemToCart(@RequestParam("productoId") Long productoId) {
        cartService.delItemToCart(productoId);
        return "redirect:/carrito";
    }

    @GetMapping("/carrito")
    public String viewCart(Model model) {
        List<CartItem> items = cartService.getCartItems();
        float total = 0;
        for (CartItem item : items) {
            total += item.getCantidad() * item.getProducto().getPrecio();
        }
        
        model.addAttribute("items", items);
        model.addAttribute("total", total);


        // Agregar atributo para comprobar si el carrito está vacío
        boolean carritoVacio = items.isEmpty();
        model.addAttribute("carritoVacio", carritoVacio);

        return "carrito"; 
    }

    @PostMapping("/finalizarCompra")
    public String finalizarCompra(Model model) {
        List<CartItem> items = cartService.getCartItems();
        
        // Validar si el carrito está vacío
        if (items.isEmpty()) {
            model.addAttribute("error", "El carrito está vacío. Agrega productos antes de finalizar la compra.");
            return "redirect:/carrito";
        }

        // Crear el objeto PedidoModel
        PedidoModel pedido = new PedidoModel();
        pedido.setCuenta(cuentaDetailsService.getCuentaLogueada());
        
        EstadoModel estado = estadoService.obtenerEstadoPorId(1L)
                                   .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
        pedido.setEstado(estado);

        pedidoService.guardarPedido(pedido);
        // Limpiar el carrito
        
        // Crear detalles del pedido
        this.crearDetallePedido(pedido);
        // Guardar el pedido usando PedidoService
        
        cartService.clearCart();

        return "redirect:/"; // Redirige a la vista de confirmación
    }

    private void crearDetallePedido(PedidoModel pedido) {
        List<CartItem> cartItems = cartService.getCartItems();
        float subTotal = 0;
        float total = 0;
        for (CartItem item : cartItems) {
            DetallePedidoModel detallePedido = new DetallePedidoModel();
            detallePedido.setProducto(item.getProducto());
            detallePedido.setPedido(pedido);
            detallePedido.setCantidad(item.getCantidad());
            detallePedido.setSubtotal(subTotal);
            detallePedidoService.guardarDetallePedido(detallePedido);
            total += item.getCantidad() * item.getProducto().getPrecio();
            pedido.setTotal(total);
            
            //Calculo stock
            ProductoModel producto = item.getProducto();
            int stockActual = producto.getStock();
            int stockDecrementado = stockActual - item.getCantidad();
            producto.setStock(stockDecrementado);
            productoService.guardarProducto(producto);

        }
        pedidoService.guardarPedido(pedido);
        // Limpiar el carrito
    }
}
