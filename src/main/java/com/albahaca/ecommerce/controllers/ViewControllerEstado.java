
package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.EstadoModel;
import com.albahaca.ecommerce.services.EstadoService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewControllerEstado {
    @Autowired
    EstadoService estadoService;
            
            
    @GetMapping("/estados")
    public String estados(){
        return "estado";
    }
    
    @GetMapping("/registrarEstado")
    public String registrarEstado() {
        return "registrar-estado"; 
    }
    
    @GetMapping("/modificarEstado/{id}")
    public String modificarEstado(@PathVariable("id") Long id, Model model) {
        // Aquí puedes buscar los datos del usuario por su ID y añadirlos al modelo
        Optional<EstadoModel> estado = estadoService.obtenerEstadoPorId(id);
        if (estado.isPresent()) {
            model.addAttribute("estado", estado.get());
        }
        return "modificar-estado"; 
    }
    
}
