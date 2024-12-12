package com.albahaca.ecommerce.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    public ArrayList<PedidoModel> listarPedidos() {

        return this.pedidoService.listarPedidos();
    }

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

    public ArrayList<DetallePedidoModel> obtenerDetallesPedidoPorPedidosFiltrados(List<PedidoModel> pedidosFiltrados) {
        ArrayList<DetallePedidoModel> detallesPedidos = new ArrayList<>();

        // Recorremos cada pedido filtrado
        for (PedidoModel pedido : pedidosFiltrados) {
            ArrayList<DetallePedidoModel> detalles = detallepedidoservice.listaDetallePedidoPorPedido(pedido.getId());
            if (detalles != null) {
                detallesPedidos.addAll(detalles);
            }
        }

        return detallesPedidos;
    }

    // ver si sirve porque no la use
    @GetMapping("/productosPrueba")
    public ArrayList<ProductoModel> obtenerProductosPorDetallePedido() {

        // Lista para almacenar todos los productos
        ArrayList<ProductoModel> productos = new ArrayList<>();

        // Obtenemos los detalles de los pedidos
        ArrayList<DetallePedidoModel> detallesPedidos = detallepedidoservice.listaDetallePedido();

        // Recorremos cada detalle del pedido
        for (DetallePedidoModel detalle : detallesPedidos) {
            // Obtenemos el producto_id del detalle del pedido
            Long productoId = detallepedidoservice.obtenerProductoIdPorDetalle(detalle.getId()); // Obtener el ID del
                                                                                                 // producto

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
    public ResponseEntity<List<Map<String, Object>>> obtenerProductosMasVendidos(
                @RequestParam String fechaInicio,
                @RequestParam String fechaFin) {

            // Convertir las fechas de entrada (String) en LocalDate
            LocalDate inicio = LocalDate.parse(fechaInicio);
            LocalDate fin = LocalDate.parse(fechaFin);

            // Obtener todos los pedidos desde el servicio
            ArrayList<PedidoModel> pedidos = this.pedidoService.listarPedidos();

            // Filtrar los pedidos por el rango de fechas
            List<PedidoModel> pedidosFiltrados = pedidos.stream()
                    .filter(pedido -> {
                        LocalDate fechaPedido = pedido.getFechaHora().toLocalDate();
                        return (fechaPedido.isEqual(inicio) || fechaPedido.isEqual(fin)
                                || (fechaPedido.isAfter(inicio) && fechaPedido.isBefore(fin)));
                    })
                    .toList();

        // Obtener los detalles solo de los pedidos filtrados
        ArrayList<DetallePedidoModel> detallesPedidos = this.obtenerDetallesPedidoPorPedidosFiltrados(pedidosFiltrados);

        // Mapa para contar la cantidad de veces que se vendió cada producto
        Map<String, Integer> conteoProductos = new HashMap<>();

        // Recorremos la lista de detalles de pedidos
        for (DetallePedidoModel detalle : detallesPedidos) {
            String nombreProducto = detalle.getProducto().getNombre();
            int cantidad = detalle.getCantidad();
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
            cantidadPorCategoria.put(categoria,
                    cantidadPorCategoria.getOrDefault(categoria, 0) + detalle.getCantidad());
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
            cantidadComprasPorCliente.put(cliente,
                    cantidadComprasPorCliente.getOrDefault(cliente, 0) + detalle.getCantidad());
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

    @GetMapping("/pedidosPorFecha")
    public ResponseEntity<List<Map<String, Object>>> pedidosPorFecha(
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {


        // Convertir las fechas de entrada
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);

        // Obtener todos los pedidos
        ArrayList<PedidoModel> pedidos = this.pedidoService.listarPedidos();

        // Agrupar los pedidos por fecha
        Map<LocalDate, Long> pedidosPorFecha = pedidos.stream()
                .filter(pedido -> {
                    LocalDate fechaPedido = pedido.getFechaHora().toLocalDate();
                    return !fechaPedido.isBefore(inicio) && !fechaPedido.isAfter(fin);
                })
                .collect(Collectors.groupingBy(
                        pedido -> pedido.getFechaHora().toLocalDate(),
                        Collectors.counting()
                ));

        // Convertir el mapa en una lista de mapas para JSON y ordenarlo por fecha
        List<Map<String, Object>> respuesta = pedidosPorFecha.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Ordenar por fecha
                .map(entry -> {
                    Map<String, Object> mapa = new HashMap<>();
                    mapa.put("fecha", entry.getKey().toString()); // Convertir LocalDate a String
                    mapa.put("cantidad", entry.getValue());      // Mantener el valor como Long
                    return mapa;
                })
                .toList();

        return ResponseEntity.ok(respuesta);
    }


    @GetMapping("/totalRecaudadoPorFecha")
    public ResponseEntity<List<Map<String, Object>>> totalRecaudadoPorFecha(
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {

        // Convertir las fechas de entrada
        LocalDate inicio = LocalDate.parse(fechaInicio);
        LocalDate fin = LocalDate.parse(fechaFin);

        // Obtener todos los pedidos
        ArrayList<PedidoModel> pedidos = this.pedidoService.listarPedidos();

        // Agrupar los pedidos por fecha y calcular el total recaudado
        Map<LocalDate, Double> totalPorFecha = pedidos.stream()
                .filter(pedido -> {
                    LocalDate fechaPedido = pedido.getFechaHora().toLocalDate();
                    return !fechaPedido.isBefore(inicio) && !fechaPedido.isAfter(fin);
                })
                .collect(Collectors.groupingBy(
                        pedido -> pedido.getFechaHora().toLocalDate(),
                        Collectors.summingDouble(pedido -> (double) pedido.getTotal())
                ));

        // Ordenar el mapa por fecha (clave) antes de crear la respuesta
        List<Map<String, Object>> respuesta = totalPorFecha.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Ordenar por fecha
                .map(entry -> {
                    Map<String, Object> mapa = new HashMap<>();
                    mapa.put("fecha", entry.getKey().toString()); // Convertir LocalDate a String
                    mapa.put("total", entry.getValue());         // Mantener el valor como Double
                    return mapa;
                })
                .toList();

        return ResponseEntity.ok(respuesta);
    }







}