
package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.PedidoModel;
import com.albahaca.ecommerce.services.PedidoService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    
    @Autowired
    PedidoService pedidoService;
    
    @GetMapping("/listarPedidos")
    public ArrayList<PedidoModel> listarPedidos(){
        return this.pedidoService.listarPedidos();
    }
    
    @PostMapping("/guardarPedido")
    public PedidoModel guardarPedido(@RequestBody PedidoModel pedidoModel){
        return this.pedidoService.guardarPedido(pedidoModel);
    }
    
    @DeleteMapping("/eliminarPedido/{id}")
    public boolean eliminarPedido(@PathVariable("id") Long id){
        return this.pedidoService.eliminarPedido(id);
    }
    
}
