package com.albahaca.ecommerce.integracion;

import com.albahaca.ecommerce.models.ProductoModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
public class VLRegistroProductoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testRegistrarProductoConPrecioNegativo() throws Exception {
        ProductoModel producto = new ProductoModel();
        producto.setNombre("Producto con precio negativo");
        producto.setDescripcion("Prueba con precio negativo");
        producto.setPrecio(-100.0f); // Precio negativo

        // Realiza la solicitud POST al controlador
        mockMvc.perform(post("/producto/guardarProducto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testRegistrarProductoConPrecioPositivo() throws Exception {
        ProductoModel producto = new ProductoModel();
        producto.setNombre("Producto con precio positivo");
        producto.setDescripcion("Prueba con precio positivo");
        producto.setPrecio(1000.01f); // Precio positivo

        // Realiza la solicitud POST al controlador
        mockMvc.perform(post("/producto/guardarProducto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.nombre", is("Producto con precio positivo")))
                .andExpect(jsonPath("$.descripcion", is("Prueba con precio positivo")))
                .andExpect(jsonPath("$.precio", is(1000.01)));
    }
}