package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.CategoriaModel;
import com.albahaca.ecommerce.models.MarcaModel;
import com.albahaca.ecommerce.services.MarcaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    MarcaService marcaService;

    // Acceso permitido a todos
    @GetMapping("/listarMarcas")
    public ArrayList<MarcaModel> listarMarcas() {
        return marcaService.listarMarcas();
    }

    // Solo los usuarios con la autoridad "ADMIN" pueden guardar una marca
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/guardarMarca")
    public MarcaModel guardarMarca(@RequestBody MarcaModel marca) {
        return this.marcaService.guardarMarca(marca);
    }

    // Solo los usuarios con la autoridad "ADMIN" pueden eliminar una marca
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/eliminarMarca/{id}")
    public boolean eliminarMarca(@PathVariable Long id) {
        return this.marcaService.eliminarMarca(id);
    }

    // Solo los usuarios con la autoridad "ADMIN" pueden obtener una marca por ID
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/obtenerMarcaPorId/{id}")
    public Optional<MarcaModel> obtenerMarcaPorId(@PathVariable("id") Long id) {
        return this.marcaService.obtenerMarcaPorId(id);
    }

    @GetMapping("/obtenerMarcaPorNombre/{filtroName}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<MarcaModel> obtenerCategoriaPorId(@PathVariable("filtroName") String filtroName) {
        ArrayList<MarcaModel> marcas = this.marcaService.listarMarcas();

        // Filtrar por nombre sin tener en cuenta mayúsculas y minúsculas
        return marcas.stream()
                .filter(marca -> marca.getNombre().toLowerCase().contains(filtroName.toLowerCase()))
                .collect(Collectors.toList());
    }
}
