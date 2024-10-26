package com.albahaca.ecommerce.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.albahaca.ecommerce.models.MarcaModel;
import com.albahaca.ecommerce.services.MarcaService;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    MarcaService marcaService;

    @GetMapping("/listarMarcas")
    public ArrayList<MarcaModel> listarMarcas() {
        return marcaService.listarMarcas();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/guardarMarca")
    public MarcaModel guardarMarca(@RequestBody MarcaModel marca) {
        return this.marcaService.guardarMarca(marca);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/eliminarMarca/{id}")
    public ResponseEntity<String> eliminarMarca(@PathVariable Long id) {
        boolean eliminado = marcaService.eliminarMarca(id);
        if (eliminado) {
            return ResponseEntity.ok("Marca eliminada correctamente.");
        } else {
            return ResponseEntity.status(409).body("No se puede eliminar la marca, ya que está asociada a uno o más productos.");
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/obtenerMarcaPorId/{id}")
    public Optional<MarcaModel> obtenerMarcaPorId(@PathVariable("id") Long id) {
        return this.marcaService.obtenerMarcaPorId(id);
    }

    @GetMapping("/obtenerMarcaPorNombre/{filtroName}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<MarcaModel> obtenerCategoriaPorId(@PathVariable("filtroName") String filtroName) {
        ArrayList<MarcaModel> marcas = this.marcaService.listarMarcas();
        return marcas.stream()
                .filter(marca -> marca.getNombre().toLowerCase().contains(filtroName.toLowerCase()))
                .collect(Collectors.toList());
    }
}
