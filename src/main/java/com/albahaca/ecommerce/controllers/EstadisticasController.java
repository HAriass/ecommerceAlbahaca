package com.albahaca.ecommerce.controllers;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.albahaca.ecommerce.models.DetallePedidoModel;
import com.albahaca.ecommerce.models.PedidoModel;
import com.albahaca.ecommerce.models.ProductoModel;
import com.albahaca.ecommerce.services.PedidoService;
import com.albahaca.ecommerce.services.DetallePedidoService;
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
    
    

}