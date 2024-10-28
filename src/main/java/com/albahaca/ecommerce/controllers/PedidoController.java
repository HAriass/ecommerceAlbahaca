
package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.PedidoModel;
import com.albahaca.ecommerce.services.PedidoService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> guardarPedido(@RequestBody PedidoModel pedidoModel) {
        try {
            // Intenta guardar el pedido
            PedidoModel savedPedido = pedidoService.guardarPedido(pedidoModel);
            return ResponseEntity.ok(savedPedido); // Retorna el pedido guardado con HTTP 200 OK
        } catch (IllegalArgumentException e) {
            // Maneja las excepciones de validación
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // Maneja otras excepciones no esperadas
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el pedido.");
        }
    }
    
    @DeleteMapping("/eliminarPedido/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean eliminarPedido(@PathVariable("id") Long id){
        return this.pedidoService.eliminarPedido(id);
    }
    
}
