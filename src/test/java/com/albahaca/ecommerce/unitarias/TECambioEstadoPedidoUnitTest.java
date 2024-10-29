package com.albahaca.ecommerce.unitarias;

import com.albahaca.ecommerce.models.EstadoModel;
import com.albahaca.ecommerce.models.PedidoModel;
import com.albahaca.ecommerce.repositories.PedidoRepository;
import com.albahaca.ecommerce.services.EstadoService;
import com.albahaca.ecommerce.services.PedidoService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class TECambioEstadoPedidoUnitTest {
    
    @InjectMocks
    private PedidoService pedidoService; // El servicio donde está el método cambiarEstadoPedidos

    @Mock
    private PedidoRepository pedidoRepository; // Si utilizas repositorios para acceder a los pedidos

    @Mock
    private EstadoService estadoService; // Simulación de EstadoService

    private PedidoModel pedido;
    private EstadoModel estadoPreparacion;
    private EstadoModel estadoEntregado;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear estado "enPreparacion"
        estadoPreparacion = new EstadoModel();
        estadoPreparacion.setId(1L);
        estadoPreparacion.setNombre("enPreparacion");
        estadoPreparacion.setDescripcion("Estado en preparación");

        // Crear estado "entregado"
        estadoEntregado = new EstadoModel();
        estadoEntregado.setId(3L);
        estadoEntregado.setNombre("entregado");
        estadoEntregado.setDescripcion("Entregado al cliente");

        // Crear un pedido en estado "enPreparacion"
        pedido = new PedidoModel();
        pedido.setId(1L);
        pedido.setFechaHora(LocalDateTime.now().minusSeconds(21)); // Fecha hace 21 segundos para simular el cambio
        pedido.setEstado(estadoPreparacion); // Asegúrate de que se asigna el estado de preparación
        pedido.setTotal(100.0f);
    }

    @Test
    public void testCambiarEstadoPedidoDeEnPreparacionAEntregado() {
        // Simular el comportamiento del repositorio devolviendo un ArrayList
        when(pedidoRepository.findAll()).thenReturn(new ArrayList<>(List.of(pedido)));

        // Simular el comportamiento de estadoService para cambiar estados
        when(estadoService.obtenerEstadoPorNombre("entregado")).thenReturn(estadoEntregado);

        // Simular el comportamiento de estadoService para el estado actual del pedido
        when(estadoService.obtenerEstadoPorNombre("enPreparacion")).thenReturn(estadoPreparacion);

        // Ejecutar el método a probar
        pedidoService.cambiarEstadoPedidos();

        // Verificar que el estado cambió a "entregado"
        assertEquals("entregado", pedido.getEstado().getNombre());
    }
}
