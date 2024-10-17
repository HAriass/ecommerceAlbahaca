package com.albahaca.ecommerce.unitarias;

import com.albahaca.ecommerce.models.ProductoModel;
import com.albahaca.ecommerce.repositories.ProductoRepository;
import com.albahaca.ecommerce.services.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class VLRegistroProductoUnitTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @ParameterizedTest
    @CsvSource({
        "1, 'Prueba Unitaria 1', 'Precio Negativo', -100.0",
        "2, 'Prueba Unitaria 3', 'Precio 0', 0.0",
        "3, 'Prueba Unitaria 4', 'Precio Positivo', 1000.01",
        "4, 'Prueba Unitaria 5', 'Precio positivo', 999999.99"
    })
    public void testGuardarProducto(Long id, String nombre, String descripcion, float precio) {
        // Crear un producto de ejemplo
        ProductoModel producto = new ProductoModel();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);

        // Simular que el repositorio guarda el producto correctamente
        when(productoRepository.save(any(ProductoModel.class))).thenReturn(producto);

        // Llamar al método guardarProducto y verificar el resultado
        ProductoModel resultado = productoService.guardarProducto(producto);

        // Verificar que el resultado sea el esperado
        assertEquals(producto.getId(), resultado.getId());
        assertEquals(producto.getNombre(), resultado.getNombre());
        assertEquals(producto.getDescripcion(), resultado.getDescripcion());
        assertEquals(producto.getPrecio(), resultado.getPrecio());
    }
}