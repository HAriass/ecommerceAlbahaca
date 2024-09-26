
package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.EstadoModel;
import com.albahaca.ecommerce.services.EstadoService;
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
@RequestMapping("/estado")
public class EstadoController {
    @Autowired
    EstadoService estadoService;

    @GetMapping("/listarEstados")
    public ArrayList<EstadoModel> obtenerEstado() {
        return estadoService.obtenerEstados();
    }

    @PostMapping("/guardarEstado")
    @PreAuthorize("hasAuthority('ADMIN')")
    public EstadoModel guardarEstado(@RequestBody EstadoModel estado) {
        return this.estadoService.guardarEstado(estado);
    }

    @DeleteMapping("/eliminarEstado/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean eliminarEstado(@PathVariable("id") Long id) {
        return this.estadoService.eliminarEstado(id);
    }

    @GetMapping("/obtenerEstadoPorId/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Optional<EstadoModel> obtenerEstadoPorId(@PathVariable("id") Long id) {
        return this.estadoService.obtenerEstadoPorId(id);
    }

    @GetMapping("/obtenerEstadoPorNombre/{filtroName}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<EstadoModel> obtenerEstadoPorId(@PathVariable("filtroName") String filtroName) {
        ArrayList<EstadoModel> estados = this.estadoService.obtenerEstados();

        // Filtrar por nombre sin tener en cuenta mayúsculas y minúsculas
        return estados.stream()
                .filter(estado -> estado.getNombre().toLowerCase().contains(filtroName.toLowerCase()))
                .collect(Collectors.toList());
    }
}
