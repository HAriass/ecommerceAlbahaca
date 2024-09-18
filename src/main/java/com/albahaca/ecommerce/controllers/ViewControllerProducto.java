
package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.ProductoModel;
import com.albahaca.ecommerce.services.ProductoService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewControllerProducto {
    
    @Autowired
    ProductoService productoService;
    
    @GetMapping("/productos")
    public String productos(){
        return "producto";
    }
    
    @GetMapping("/registrarProducto")
    public String registrarProducto() {
        return "registrar-producto"; 
    }
    
    @GetMapping("/modificarProducto/{id}")
    public String modificarProducto(@PathVariable("id") Long id, Model model) {
        // Aquí puedes buscar los datos del usuario por su ID y añadirlos al modelo
        Optional<ProductoModel> producto = productoService.obtenerProductoPorId(id);
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get());
        }
        return "modificar-producto"; 
    }
}
