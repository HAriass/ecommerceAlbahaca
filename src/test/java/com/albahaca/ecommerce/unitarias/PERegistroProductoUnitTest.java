package com.albahaca.ecommerce.unitarias;

import com.albahaca.ecommerce.models.ProductoModel;
import com.albahaca.ecommerce.repositories.ProductoRepository;
import com.albahaca.ecommerce.services.ProductoService;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
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

    @ParameterizedTest
    @CsvSource({
        ",'Proteina', '1', 100.50, 'proteic', 'http://imagen1.com'",
        "'creatina', 'suplemento', '2', 80.75, 'proteic', 'http://imagen2.com'",
        "'prote', 'suplemento', '3', 1000, 'proteicus', 'http://imagen2.com'",
        "'creatina', 'suplemento', '2', -2, 'proteicus', 'http://imagen2.com'",
        

    })
    public void testGuardarProducto(String nombre, String descripcion, String categoria, float precio, String marca, String imagen) {
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