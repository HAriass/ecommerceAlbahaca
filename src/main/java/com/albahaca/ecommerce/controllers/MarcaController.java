
package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.MarcaModel;
import com.albahaca.ecommerce.services.MarcaService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marca")
public class MarcaController {
    
    @Autowired
    MarcaService marcaService;
    
    @GetMapping("/listarMarcas")
    public ArrayList<MarcaModel> listarMarcas(){
        return marcaService.listarMarcas();
    }
}
