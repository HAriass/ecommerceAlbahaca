package com.albahaca.ecommerce.integracion;

import com.albahaca.ecommerce.models.CategoriaModel;
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
public class PERegistroCategoriaIntegracionTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(authorities  = "ADMIN")
    public void testGuardarCategoria_NombreValido() throws Exception {
        // Crea una categoría válida
        CategoriaModel categoria = new CategoriaModel();
        categoria.setNombre("nombre valido");
        categoria.setDescripcion("Categoría nombre valida");
        categoria.setImagen("imagen_nombreValido.jpg");

        // Realiza la solicitud POST al controlador
        mockMvc.perform(post("/categoria/guardarCategoria")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.nombre", is("nombre valido")))
                .andExpect(jsonPath("$.descripcion", is("Categoría nombre valida")))
                .andExpect(jsonPath("$.imagen", is("imagen_nombreValido.jpg")));
    }

    @Test
    @WithMockUser(authorities  = "ADMIN")
    public void testGuardarCategoria_NombreInvalido() throws Exception {
        // Crea una categoría con nombre inválido
        CategoriaModel categoria = new CategoriaModel();
        categoria.setNombre(""); // Nombre inválido
        categoria.setDescripcion("Categoría sin nombre");
        categoria.setImagen("imagen_nombreInvalido.jpg");

        // Realiza la solicitud POST al controlador
        mockMvc.perform(post("/categoria/guardarCategoria")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoria)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities  = "ADMIN")
    public void testGuardarCategoria_DescripcionInvalida() throws Exception {
        // Crea una categoría con descripción inválida
        CategoriaModel categoria = new CategoriaModel();
        categoria.setNombre("Electrónica");
        categoria.setDescripcion(""); // Descripción inválida
        categoria.setImagen("imagen_electronica.jpg");

        // Realiza la solicitud POST al controlador
        mockMvc.perform(post("/categoria/guardarCategoria")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoria)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities  = "ADMIN")
    public void testGuardarCategoria_DescripcionValida() throws Exception {
        // Crea una categoría válida
        CategoriaModel categoria = new CategoriaModel();
        categoria.setNombre("Creatina");
        categoria.setDescripcion("Descripción válida");
        categoria.setImagen("imagen_creatina.jpg");

        // Realiza la solicitud POST al controlador
        mockMvc.perform(post("/categoria/guardarCategoria")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.nombre", is("Creatina")))
                .andExpect(jsonPath("$.descripcion", is("Descripción válida")))
                .andExpect(jsonPath("$.imagen", is("imagen_creatina.jpg")));
    }
}
