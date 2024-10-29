
package com.albahaca.ecommerce.integracion;

import com.albahaca.ecommerce.models.EstadoModel;
import com.albahaca.ecommerce.models.PedidoModel;
import com.albahaca.ecommerce.repositories.EstadoRepository;
import com.albahaca.ecommerce.repositories.PedidoRepository;
import com.albahaca.ecommerce.services.PedidoService;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TECambioEstadoPedidoIntegracionTest {
   
    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private EstadoRepository estadoRepository;

    private EstadoModel estadoPreparacion;
    private EstadoModel estadoEntregado;

    @BeforeEach
    public void setUp() {
        // Inicializar estados y guardarlos en el repositorio si es necesario
        estadoPreparacion = new EstadoModel();
        estadoPreparacion.setNombre("enPreparacion");
        estadoPreparacion.setDescripcion("Estado en preparación");

        estadoEntregado = new EstadoModel();
        estadoEntregado.setNombre("entregado");
        estadoEntregado.setDescripcion("Entregado al cliente");

        estadoRepository.save(estadoPreparacion);
        estadoRepository.save(estadoEntregado);
    }

    @Test
    public void testCambiarEstadoPedidoDeEnPreparacionAEntregado() {
        // Crear y guardar un pedido en estado "enPreparacion"
        PedidoModel pedido = new PedidoModel();
        pedido.setFechaHora(LocalDateTime.now().minusSeconds(21));
        pedido.setEstado(estadoPreparacion);
        pedido.setTotal(100.0f);

        pedidoRepository.save(pedido); // Guardar el pedido en la base de datos

        // Ejecutar el método a probar
        pedidoService.cambiarEstadoPedidos();

        // Obtener el pedido actualizado y verificar que el estado ha cambiado a "entregado"
        PedidoModel pedidoActualizado = pedidoRepository.findById(pedido.getId()).orElseThrow();
        assertEquals("entregado", pedidoActualizado.getEstado().getNombre());
    }
}
