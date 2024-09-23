
package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewControllerCuenta {
    
    @Autowired
    CuentaService cuentaService;
    
    @GetMapping("/registrarCuenta")
    public String registrarCuenta(){
        return "registrar-cuenta";
    }
}
