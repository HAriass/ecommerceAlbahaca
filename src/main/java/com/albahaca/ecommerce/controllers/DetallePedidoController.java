
package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.DetallePedidoModel;
import com.albahaca.ecommerce.services.DetallePedidoService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detallePedido")
public class DetallePedidoController {
    
    @Autowired
    DetallePedidoService detallePedidoService;
    
    @GetMapping("/listarDetallesPedidoPorPedido/{pedidoId}")
    public ArrayList<DetallePedidoModel> listarDetallesPedidoPorPedido(@PathVariable("pedidoId") long pedidoId){
        return this.detallePedidoService.listaDetallePedidoPorPedido(pedidoId);
    }
}
