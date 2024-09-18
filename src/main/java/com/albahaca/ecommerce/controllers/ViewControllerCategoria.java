
package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.CategoriaModel;
import com.albahaca.ecommerce.services.CategoriaService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewControllerCategoria {
    
    @Autowired
    CategoriaService categoriaService;
    
    @GetMapping("/categorias")
    public String categorias(){
        return "categoria";
    }
    
    @GetMapping("/registrarCategoria")
    public String registrarCategoria() {
        return "registrar-categoria"; 
    }
    
    @GetMapping("/modificarCategoria/{id}")
    public String modificarCategoria(@PathVariable("id") Long id, Model model) {
        // Aquí puedes buscar los datos del usuario por su ID y añadirlos al modelo
        Optional<CategoriaModel> categoria = categoriaService.obtenerCategoriaPorId(id);
        if (categoria.isPresent()) {
            model.addAttribute("categoria", categoria.get());
        }
        return "modificar-categoria"; 
    }
}
