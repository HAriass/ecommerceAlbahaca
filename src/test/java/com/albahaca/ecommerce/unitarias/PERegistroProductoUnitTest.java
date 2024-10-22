package com.albahaca.ecommerce.unitarias;

import com.albahaca.ecommerce.models.ProductoModel;
import com.albahaca.ecommerce.repositories.ProductoRepository;
import com.albahaca.ecommerce.services.ProductoService;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PERegistroProductoUnitTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarProductoNombreNulo() {
        // Asegurarse de que se lance IllegalArgumentException cuando el nombre es nulo
        ProductoModel producto = new ProductoModel();
        producto.setNombre(null);
        producto.setDescripcion("Descripción");
        producto.setCategoria(null);
        producto.setPrecio(100.0f);
        producto.setMarca(null);
        producto.setImagen("http://imagen.com");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productoService.guardarProducto(producto);
        });

        assertEquals("El nombre no puede ser nulo.", exception.getMessage());
    }
    
    @Test
    public void testGuardarProductoPrecioNegativo() {
        // Asegurarse de que se lance IllegalArgumentException cuando el precio es negativo
        ProductoModel producto = new ProductoModel();
        producto.setNombre("Producto");
        producto.setDescripcion("Descripción");
        producto.setCategoria(null);
        producto.setPrecio(-50.0f); // Precio negativo
        producto.setMarca(null);
        producto.setImagen("http://imagen.com");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productoService.guardarProducto(producto);
        });

        assertEquals("El precio no puede ser negativo.", exception.getMessage());
    }
    
    @ParameterizedTest
    @CsvSource({
        "'creatina', 'suplemento', 80.75,'http://imagen2.com'",
        "'prote', 'suplemento', 1000,'http://imagen2.com'",
    })
    public void testGuardarProductoValidos(String nombre, String descripcion, float precio, String imagen) {
        // Creamos el producto con los valores parametrizados
        ProductoModel producto = new ProductoModel();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setCategoria(null);
        producto.setPrecio(precio);
        producto.setMarca(null);
        producto.setImagen(imagen);

        // Simulamos el comportamiento del repositorio
        when(productoRepository.save(any(ProductoModel.class))).thenReturn(producto);

        // Llamamos al servicio para guardar el producto
        ProductoModel productoGuardado = productoService.guardarProducto(producto);

        // Verificamos que el producto se ha guardado correctamente
        assertNotNull(productoGuardado);
        assertEquals(nombre, productoGuardado.getNombre());

        // Verificamos que se llamó al repositorio con el producto correcto
        verify(productoRepository, times(1)).save(producto);
    }
}