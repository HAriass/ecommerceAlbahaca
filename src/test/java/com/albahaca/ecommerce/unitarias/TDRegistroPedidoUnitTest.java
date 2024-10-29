/*package com.albahaca.ecommerce.unitarias;

import com.albahaca.ecommerce.models.PedidoModel;
import com.albahaca.ecommerce.models.EstadoModel; // Asegúrate de importar EstadoModel
import com.albahaca.ecommerce.repositories.PedidoRepository;
import com.albahaca.ecommerce.services.PedidoService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

public class TDRegistroPedidoUnitTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    private PedidoModel pedido;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pedido = new PedidoModel();
    }

    @Test
    public void testGuardarPedido() {
        // Tabla de decisiones
        Object[][] decisionTable = {
            // Total, Estado, Excepción esperada
            {10.0f, "Realizado", null},      // Éxito
            {-2.3f, "Realizado", IllegalArgumentException.class}, // Fallo: total < 0
            {10.0f, null, IllegalArgumentException.class},       // Fallo: estado nulo
        };

        for (Object[] row : decisionTable) {
            float total = (float) row[0];
            String estado = (String) row[1];
            Class<? extends Exception> expectedException = (Class<? extends Exception>) row[2];

            // Configura el pedido con el total y el estado
            pedido.setTotal(total);
            EstadoModel estadoModel = new EstadoModel();
            estadoModel.setNombre(estado);
            pedido.setEstado(estadoModel);

            if (expectedException != null) {
                // Se espera que se lance una excepción
                Exception exception = assertThrows(expectedException, () -> {
                    pedidoService.guardarPedido(pedido);
                });

                // Verifica que el mensaje de excepción sea el esperado
                if (total < 0) {
                    assertEquals("El total del pedido debe ser mayor a cero.", exception.getMessage());
                } else {
                    assertEquals("El estado del pedido no puede ser nulo.", exception.getMessage());
                }
            } else {
                // Se espera que el pedido se guarde correctamente
                pedidoService.guardarPedido(pedido);
                // Verificar que el pedido fue guardado
                verify(pedidoRepository).save(pedido);
            }
        }
    }
}
*/