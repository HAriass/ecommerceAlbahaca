package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.ProductoModel;
import com.albahaca.ecommerce.services.ProductoService;
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

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/listarProductos")
    public ArrayList<ProductoModel> listarProductos() {
        return this.productoService.listarProducto();
    }

    @PostMapping("/guardarProducto")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProductoModel> guardarProducto(@RequestBody ProductoModel producto) {
        try {
            ProductoModel productoGuardado = this.productoService.guardarProducto(producto);
            return ResponseEntity.ok(productoGuardado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Aquí se devuelve un 400 Bad Request
        }
    }

    @DeleteMapping("/eliminarProducto/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean eliminaProducto(@PathVariable("id") Long id) {
        return this.productoService.eliminarProducto(id);
    }

    @GetMapping("/obtenerProductoPorId/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Optional<ProductoModel> obtenerProductoPorId(@PathVariable("id") Long id) {
        return this.productoService.obtenerProductoPorId(id);
    }

    @GetMapping("/obtenerProductoPorNombre/{nombreFiltro}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<ProductoModel> obtenerProductoPorNombre(@PathVariable("nombreFiltro") String nombreFiltro) {
        // utilizo le metodo listarProductos para obtener todos los productos y luego
        // filtrar por nombre

        ArrayList<ProductoModel> productos = this.productoService.listarProducto();

        // Filtrar por nombre sin tener en cuenta mayúsculas y minúsculas
        return productos.stream()
                .filter(producto -> producto.getNombre().toLowerCase().contains(nombreFiltro.toLowerCase()))
                .collect(Collectors.toList());
    }

    @GetMapping("/obtenerProductoPorMarcaCategoria/{marcaCategoriaFiltro}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<ProductoModel> obtenerProductoPorMarcaCategoria(@PathVariable("marcaCategoriaFiltro") String marcaCategoriaFiltro) {

        // Obtengo la lista de todos los productos
        ArrayList<ProductoModel> productos = this.productoService.listarProducto();

        // Filtrar productos por marca o categoría (sin tener en cuenta
        // mayúsculas/minúsculas)
        return productos.stream()
                .filter(producto -> producto.getMarca().getNombre().toLowerCase()
                        .contains(marcaCategoriaFiltro.toLowerCase()) ||
                        producto.getCategoria().getNombre().toLowerCase().contains(marcaCategoriaFiltro.toLowerCase()))
                .collect(Collectors.toList());
    }

}
