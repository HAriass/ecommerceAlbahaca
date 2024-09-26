package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.CategoriaModel;
import com.albahaca.ecommerce.models.ProductoModel;
import com.albahaca.ecommerce.services.CategoriaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public ArrayList<CategoriaModel> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @PostMapping("/guardarCategoria")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CategoriaModel guardarCategoria(@RequestBody CategoriaModel categoria) {
        return this.categoriaService.guardarCategoria(categoria);
    }

    @DeleteMapping("/eliminarCategoria/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean eliminarCategoria(@PathVariable("id") Long id) {
        return this.categoriaService.eliminarCategoria(id);
    }

    @GetMapping("/obtenerCategoriaPorId/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Optional<CategoriaModel> obtenerCategoriaPorId(@PathVariable("id") Long id) {
        return this.categoriaService.obtenerCategoriaPorId(id);
    }

    @GetMapping("/obtenerCategoriaPorNombre/{filtroName}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CategoriaModel> obtenerCategoriaPorId(@PathVariable("filtroName") String filtroName) {
        ArrayList<CategoriaModel> categorias = this.categoriaService.listarCategorias();

        // Filtrar por nombre sin tener en cuenta mayúsculas y minúsculas
        return categorias.stream()
                .filter(categoria -> categoria.getNombre().toLowerCase().contains(filtroName.toLowerCase()))
                .collect(Collectors.toList());
    }

}