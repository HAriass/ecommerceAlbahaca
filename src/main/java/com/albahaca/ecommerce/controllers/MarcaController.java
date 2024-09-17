package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.MarcaModel;
import com.albahaca.ecommerce.services.MarcaService;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @PostMapping("/guardarMarca")
    public MarcaModel guardarMarca(@RequestBody MarcaModel marca){
        return this.marcaService.guardarMarca(marca); // this hace referencia al autowired MarcaService marcaService;
    }
    
    @DeleteMapping("/eliminarMarca/{id}")
    public boolean eliminarMarca(@PathVariable Long id){
        return this.marcaService.eliminarMarca(id);
    }
}
