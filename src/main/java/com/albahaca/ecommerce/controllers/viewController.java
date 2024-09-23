
package com.albahaca.ecommerce.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class viewController {
    
    @GetMapping("/menu")
    public String menu(){
        return "menu";
    }
    
}
