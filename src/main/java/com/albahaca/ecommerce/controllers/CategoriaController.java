package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.CategoriaModel;
import com.albahaca.ecommerce.services.CategoriaService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    
    @Autowired
    CategoriaService categoriaService;
    
    @GetMapping("/listarCategorias")
    public ArrayList<CategoriaModel> listarCategorias(){
        return categoriaService.listarCategorias();
    }
    
    @PostMapping("/guardarCategoria")
    public CategoriaModel guardarCategoria(@RequestBody CategoriaModel categoria){
        return this.categoriaService.guardarCategoria(categoria);
    }
    
    @DeleteMapping("/eliminarCategoria/{id}")
    public boolean eliminarCategoria(@PathVariable("id") Long id){
        return this.categoriaService.eliminarCategoria(id);
    }
    
    @GetMapping("/obtenerCategoriaPorId/{id}")
    public Optional<CategoriaModel> obtenerCategoriaPorId(@PathVariable("id") Long id){
        return this.categoriaService.obtenerCategoriaPorId(id);
    }
    
}