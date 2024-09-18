
package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.MarcaModel;
import com.albahaca.ecommerce.services.MarcaService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewControllerMarca {
    
    @Autowired
    MarcaService marcaService;
    
    @GetMapping("/marcas")
    public String marcas(){
        return "marca";
    }
    
    @GetMapping("/registrarMarca")
    public String registrarMarca() {
        return "registrar-marca"; 
    }
    
    @GetMapping("/modificarMarca/{id}")
    public String modificarMarca(@PathVariable("id") Long id, Model model) {
        // Aquí puedes buscar los datos del usuario por su ID y añadirlos al modelo
        Optional<MarcaModel> marca = marcaService.obtenerMarcaPorId(id);
        if (marca.isPresent()) {
            model.addAttribute("marca", marca.get());
        }
        return "modificar-marca"; 
    }

}
