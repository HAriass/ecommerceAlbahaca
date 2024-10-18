package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.CuentaModel;
import com.albahaca.ecommerce.models.EstadoModel;
import com.albahaca.ecommerce.models.PedidoModel;
import com.albahaca.ecommerce.models.ProductoModel;
import com.albahaca.ecommerce.services.CartService;
import com.albahaca.ecommerce.services.CuentaDetailsService;
import com.albahaca.ecommerce.services.CuentaService;
import com.albahaca.ecommerce.services.EstadoService;
import com.albahaca.ecommerce.services.ProductoService;
import com.albahaca.ecommerce.services.PedidoService; // Agregar el servicio de Pedido
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import org.springframework.security.core.Authentication;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private PedidoService pedidoService; // Inyectar el servicio de Pedido
    
    @Autowired 
    private CuentaDetailsService cuentaDetailsService;
    
    @Autowired 
    private EstadoService estadoService;

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

    @GetMapping("/carrito")
    public String viewCart(Model model) {
        model.addAttribute("items", cartService.getCartItems());
        return "carrito"; 
    }

     @PostMapping("/finalizarCompra")
    public String finalizarCompra() {
        // Aquí deberías crear tu objeto PedidoModel
        PedidoModel pedido = new PedidoModel();
        // Establecer la cuenta en el pedido
        pedido.setCuenta(cuentaDetailsService.getCuentaLogueada());

        // Guardar el pedido usando tu PedidoService
        pedidoService.guardarPedido(pedido);

        return "redirect:/"; // Redirige a la vista de confirmación o a la página deseada
    }

    
}
