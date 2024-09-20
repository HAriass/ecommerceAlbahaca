
package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.TipoCuentaModel;
import com.albahaca.ecommerce.services.TipoCuentaService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewControllerTipoCuenta {
    
    @Autowired
    TipoCuentaService tipoCuentaService;
    
    @GetMapping("/tipoCuentas")
    public String tipoCuentas(){
        return "tipoCuenta";
    }
    
    @GetMapping("/registrarTipoCuenta")
    public String registrarTipoCuenta() {
        return "registrar-tipoCuenta"; 
    }
    
    @GetMapping("/modificarTipoCuenta/{id}")
    public String modificarTipoCuenta(@PathVariable("id") Long id, Model model) {
        // Aquí puedes buscar los datos del usuario por su ID y añadirlos al modelo
        Optional<TipoCuentaModel> tipoCuenta = tipoCuentaService.obtenerTipoCuentaPorId(id);
        if (tipoCuenta.isPresent()) {
            model.addAttribute("tipoCuenta", tipoCuenta.get());
        }
        return "modificar-tipoCuenta"; 
    }
}
