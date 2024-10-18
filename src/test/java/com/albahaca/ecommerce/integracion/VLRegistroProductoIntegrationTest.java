package com.albahaca.ecommerce.integracion;

import com.albahaca.ecommerce.models.ProductoModel;
import com.albahaca.ecommerce.services.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
public class VLRegistroProductoIntegrationTest {

    @Autowired
    private ProductoService productoService;

    @Test
    public void testRegistrarProductoConPrecioNegativo() {
        ProductoModel producto = new ProductoModel();
        producto.setNombre("Producto con precio negativo");
        producto.setDescripcion("Prueba con precio negativo");
        producto.setPrecio(-100.0f); 

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productoService.guardarProducto(producto);
        });

        assertEquals("El precio no puede ser negativo", exception.getMessage());
    }

    @Test
    public void testRegistrarProductoConPrecioCero() {
        ProductoModel producto = new ProductoModel();
        producto.setNombre("Producto con precio 0");
        producto.setDescripcion("Prueba con precio 0");
        producto.setPrecio(0.0f); // valor límite

        ProductoModel resultado = productoService.guardarProducto(producto);
        assertNotNull(resultado);
        assertEquals(0.0f, resultado.getPrecio());
    }

    @Test
    public void testRegistrarProductoConPrecioPositivo() {
        ProductoModel producto = new ProductoModel();
        producto.setNombre("Producto con precio positivo");
        producto.setDescripcion("Prueba con precio positivo");
        producto.setPrecio(1000.01f); // valor límite

        ProductoModel resultado = productoService.guardarProducto(producto);
        assertNotNull(resultado);
        assertEquals(1000.01f, resultado.getPrecio());
    }
}