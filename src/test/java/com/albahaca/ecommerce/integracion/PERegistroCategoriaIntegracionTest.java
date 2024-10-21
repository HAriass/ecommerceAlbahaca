package com.albahaca.ecommerce.integracion;

import com.albahaca.ecommerce.models.CategoriaModel;
import com.albahaca.ecommerce.services.CategoriaService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Transactional
@Rollback
public class PERegistroCategoriaIntegracionTest {

    @Autowired
    private CategoriaService categoriaService;

    @Test
    public void testGuardarCategoria_NombreValido() {
        // Crea una categoría válida
        CategoriaModel categoria = new CategoriaModel();
        categoria.setNombre("nombre valido"); // Nombre válido
        categoria.setDescripcion("Categoría nombre valida");
        categoria.setImagen("imagen_nombreValido.jpg");

        // Guarda la categoría
        CategoriaModel categoriaGuardada = categoriaService.guardarCategoria(categoria);

        // Verifica que se ha guardado correctamente
        assertNotNull(categoriaGuardada.getId());
        assertEquals("Ropa", categoriaGuardada.getNombre());
        assertEquals("Categoría de ropa", categoriaGuardada.getDescripcion());
        assertEquals("imagen_ropa.jpg", categoriaGuardada.getImagen());
    }

    @Test
    public void testGuardarCategoria_NombreInvalido() {
        // Partición de equivalencias: Nombre inválido (nulo o vacío)
        CategoriaModel categoria = new CategoriaModel();
        categoria.setNombre(""); // Nombre inválido
        categoria.setDescripcion("Categoría sin nombre");
        categoria.setImagen("imagen_nombreInvalido.jpg");

        // Intenta guardar la categoría
        assertThrows(IllegalArgumentException.class, () -> {
            categoriaService.guardarCategoria(categoria);
        });
    }
    
    @Test
    public void testGuardarCategoria_DescripcionInvalida() {
        // Partición de equivalencias: Descripción inválida (nulo o vacía)
        CategoriaModel categoria = new CategoriaModel();
        categoria.setNombre("Electrónica");
        categoria.setDescripcion(""); // Descripción inválida
        categoria.setImagen("imagen_electronica.jpg");

        // Intenta guardar la categoría
        assertThrows(IllegalArgumentException.class, () -> {
            categoriaService.guardarCategoria(categoria);
        });
    }
    
    @Test
    public void testGuardarCategoria_DescripcionValida() {
        // Crea una categoría válida
        CategoriaModel categoria = new CategoriaModel();
        categoria.setNombre("Creatina");
        categoria.setDescripcion("Descripción válida");
        categoria.setImagen("imagen_creatina.jpg");

        // Guarda la categoría
        CategoriaModel categoriaGuardada = categoriaService.guardarCategoria(categoria);

        // Verifica que se ha guardado correctamente
        assertNotNull(categoriaGuardada.getId());
        assertEquals("Creatina", categoriaGuardada.getNombre());
        assertEquals("Descripción válida", categoriaGuardada.getDescripcion());
        assertEquals("imagen_creatina.jpg", categoriaGuardada.getImagen());
    }
}
