package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.ProductoModel;
import com.albahaca.ecommerce.services.ProductoService;
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
@RequestMapping("/producto")
public class ProductoController {
    
    @Autowired
    ProductoService productoService;
    
    @GetMapping("/listarProductos")
    public ArrayList<ProductoModel> listarProductos(){
        return this.productoService.listarProducto();
    }
    
    @PostMapping("/guardarProducto")
    public ProductoModel guardarProducto(@RequestBody ProductoModel producto){
        return this.productoService.guardarProducto(producto);
    }
    
    @DeleteMapping("/eliminarProducto/{id}")
    public boolean eliminaProducto(@PathVariable("id") Long id){
        return this.productoService.eliminarProducto(id);
    }
    
    @GetMapping("/obtenerProductoPorId/{id}")
    public Optional<ProductoModel> obtenerProductoPorId(@PathVariable("id") Long id){
        return this.productoService.obtenerProductoPorId(id);
    }
    
}
