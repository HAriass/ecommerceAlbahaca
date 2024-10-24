
package com.albahaca.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewControllerPedido {
    
    @GetMapping("/pedidosCliente")
    public String pedidosCliente(){
        return "pedidosCliente";
    }
    
    @GetMapping("/pedidos")
    public String pedidos(){
        return "pedidosAdministracion";
    }
}
