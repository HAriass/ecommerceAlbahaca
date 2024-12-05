package com.albahaca.ecommerce.controllers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.albahaca.ecommerce.models.DetallePedidoModel;
import com.albahaca.ecommerce.models.PedidoModel;
import com.albahaca.ecommerce.models.ProductoModel;
import com.albahaca.ecommerce.services.DetallePedidoService;
import com.albahaca.ecommerce.services.PedidoService;
import com.albahaca.ecommerce.services.ProductoService;


@RestController
@RequestMapping("/estadisticas")
public class EstadisticasController { 

    @Autowired 
    PedidoService pedidoService;
    @Autowired 
    DetallePedidoService detallepedidoservice;
    @Autowired 
    ProductoService productoservice;
    

    @GetMapping("/listarPedidos")
    public ArrayList<PedidoModel> listarPedidos() {
        return this.pedidoService.listarPedidos();
    }

    @GetMapping("/listarDetallePedidos")
    public ArrayList<DetallePedidoModel> obtenerDetallesPedidoPorPedido() {
        // Lista para almacenar todos los detalles de los pedidos
        ArrayList<DetallePedidoModel> detallesPedidos = new ArrayList<>();
        
        // Obtenemos la lista de pedidos desde el servicio
        ArrayList<PedidoModel> pedidos = this.pedidoService.listarPedidos();
        
        // Recorremos cada pedido
        for (PedidoModel pedido : pedidos) {
            // Obtenemos los detalles del pedido actual
            ArrayList<DetallePedidoModel> detalles = detallepedidoservice.listaDetallePedidoPorPedido(pedido.getId());
            // Agregamos los detalles a la lista principal
            if (detalles != null) {
                detallesPedidos.addAll(detalles);
            }
        }
        
        return detallesPedidos;
    }

    //ver si sirve porque no la use
    @GetMapping("/productosPrueba")
    public ArrayList<ProductoModel> obtenerProductosPorDetallePedido() {

        System.out.println("sanchez gay");
        // Lista para almacenar todos los productos
        ArrayList<ProductoModel> productos = new ArrayList<>();
        
        // Obtenemos los detalles de los pedidos
        ArrayList<DetallePedidoModel> detallesPedidos = detallepedidoservice.listaDetallePedido();
    
        // Recorremos cada detalle del pedido
        for (DetallePedidoModel detalle : detallesPedidos) {
            // Obtenemos el producto_id del detalle del pedido
            Long productoId = detallepedidoservice.obtenerProductoIdPorDetalle(detalle.getId()); // Obtener el ID del producto
    
            // Obtenemos el producto asociado al producto_id
            if (productoId != null) {
                ProductoModel producto = productoservice.obtenerProductoPorId(productoId)
                                        .orElse(null); // Devuelve null si no encuentra el producto
    
                // Agregamos el producto a la lista si no es nulo
                if (producto != null) {
                    productos.add(producto);
                }
            }
        }
    
        return productos;
    }
    
    @GetMapping("/masVendidos")
    public ResponseEntity<List<Map<String, Object>>> obtenerProductosMasVendidos() {
        // Obtenemos la lista de detalles de pedidos desde la otra ruta
        List<DetallePedidoModel> detallesPedidos = obtenerDetallesPedidoPorPedido(); // Lista de DetallePedidoModel

        // Mapa para contar la cantidad de veces que se vendió cada producto (por nombre)
        Map<String, Integer> conteoProductos = new HashMap<>();

        // Recorremos la lista de detalles de pedidos
        for (DetallePedidoModel detalle : detallesPedidos) {
            // Obtenemos el nombre del producto y la cantidad
            String nombreProducto = detalle.getProducto().getNombre();
            int cantidad = detalle.getCantidad();

            // Sumar la cantidad vendida de ese producto
            conteoProductos.put(nombreProducto, conteoProductos.getOrDefault(nombreProducto, 0) + cantidad);
        }

        // Convertimos el resultado a una lista de mapas para retornar como JSON
        List<Map<String, Object>> productosMasVendidos = new ArrayList<>();
        conteoProductos.forEach((nombre, cantidad) -> {
            Map<String, Object> productoMap = new HashMap<>();
            productoMap.put("nombre", nombre);
            productoMap.put("cantidad", cantidad);
            productosMasVendidos.add(productoMap);
        });

        return ResponseEntity.ok(productosMasVendidos);
    }

    @GetMapping("/cantidadPorCategoria")
    public ResponseEntity<List<Map<String, Object>>> obtenerCantidadPorCategoria() {
        // Lista para almacenar todos los detalles de los pedidos
        ArrayList<DetallePedidoModel> detallesPedidos = obtenerDetallesPedidoPorPedido();
        
        // Mapa para almacenar la cantidad por categoría
        Map<String, Integer> cantidadPorCategoria = new HashMap<>();
        
        // Iteramos por cada detalle de pedido
        for (DetallePedidoModel detalle : detallesPedidos) {
            // Obtenemos la categoría del producto
            String categoria = detalle.getProducto().getCategoria().getNombre();
            
            // Sumamos la cantidad vendida de esa categoría
            cantidadPorCategoria.put(categoria, cantidadPorCategoria.getOrDefault(categoria, 0) + detalle.getCantidad());
        }

        // Convertimos el mapa en una lista de mapas para enviarlo como respuesta
        List<Map<String, Object>> resultado = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : cantidadPorCategoria.entrySet()) {
            Map<String, Object> categoriaData = new HashMap<>();
            categoriaData.put("categoria", entry.getKey());
            categoriaData.put("cantidadVendida", entry.getValue());
            resultado.add(categoriaData);
        }

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/clientesMasCompraron")
    public ResponseEntity<List<Map<String, Object>>> obtenerComprasPorCliente() {
        // Lista para almacenar todos los detalles de los pedidos
        ArrayList<DetallePedidoModel> detallesPedidos = obtenerDetallesPedidoPorPedido();
        
        // Mapa para almacenar la cantidad de productos comprados por cada cliente
        Map<String, Integer> cantidadComprasPorCliente = new HashMap<>();
        
        // Iteramos por cada detalle de pedido
        for (DetallePedidoModel detalle : detallesPedidos) {
            // Obtenemos el nombre del cliente desde el pedido asociado
            String cliente = detalle.getPedido().getCuenta().getNombre();
            
            // Sumamos la cantidad de productos comprados por ese cliente
            cantidadComprasPorCliente.put(cliente, cantidadComprasPorCliente.getOrDefault(cliente, 0) + detalle.getCantidad());
        }
    
        // Convertimos el mapa en una lista de mapas para enviarlo como respuesta
        List<Map<String, Object>> resultado = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : cantidadComprasPorCliente.entrySet()) {
            Map<String, Object> clienteData = new HashMap<>();
            clienteData.put("cliente", entry.getKey());
            clienteData.put("cantidadComprada", entry.getValue());
            resultado.add(clienteData);
        }
    
        return ResponseEntity.ok(resultado);
    }
    

    

}