package com.albahaca.ecommerce.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EstadisticasViewController {

    @GetMapping("/estadisticas")
    public String estadisticas(){
        return "estadisticas";
    }
    
}
