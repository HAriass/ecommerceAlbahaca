
package com.albahaca.ecommerce.integracion;

import com.albahaca.ecommerce.models.EstadoModel;
import com.albahaca.ecommerce.models.PedidoModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
public class TDRegistroPedidoIntegracionTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testGuardarPedido_Exitoso() throws Exception {
        EstadoModel estado = new EstadoModel();
        estado.setNombre("Confirmado");
        estado.setDescripcion("Pedido confirmado");
        
        // Crea un pedido válido
        PedidoModel pedido = new PedidoModel();
        pedido.setTotal(100.0f);
        pedido.setEstado(estado);
        pedido.setFechaHora(LocalDateTime.now());

        // Realiza la solicitud POST al controlador
        mockMvc.perform(post("/pedido/guardarPedido")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue())) // Verifica que se genere un ID
                .andExpect(jsonPath("$.total", is(100.0)))
                .andExpect(jsonPath("$.estado.nombre", is("Confirmado")));
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testGuardarPedido_FalloPrecioNegativo() throws Exception {
        // Configura un estado válido
        EstadoModel estado = new EstadoModel();
        estado.setId(1L);
        estado.setNombre("Confirmado");

        // Crea un pedido con total negativo
        PedidoModel pedido = new PedidoModel();
        pedido.setTotal(-5.0f);
        pedido.setEstado(estado);

        // Realiza la solicitud POST al controlador esperando una excepción
        mockMvc.perform(post("/pedido/guardarPedido")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void testGuardarPedido_FalloEstadoNulo() throws Exception {
        // Crea un pedido con estado nulo
        PedidoModel pedido = new PedidoModel();
        pedido.setTotal(100.0f);
        pedido.setEstado(null);

        // Realiza la solicitud POST al controlador esperando una excepción
        mockMvc.perform(post("/pedido/guardarPedido")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isBadRequest());
    }
}
