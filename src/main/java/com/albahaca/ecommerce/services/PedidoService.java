
package com.albahaca.ecommerce.services;

import com.albahaca.ecommerce.models.PedidoModel;
import com.albahaca.ecommerce.repositories.PedidoRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    
    @Autowired
    PedidoRepository pedidoRepository;
    
    public ArrayList<PedidoModel> listarPedidos(){
        return (ArrayList<PedidoModel>) pedidoRepository.findAll();
    }
    
    public PedidoModel guardarPedido(PedidoModel pedidoModel){
        pedidoModel.setFechaHora(LocalDateTime.now());
        validarPedido(pedidoModel);
        return pedidoRepository.save(pedidoModel);
        
    }
    
    public boolean eliminarPedido(Long id){
        try {
            pedidoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void validarPedido(PedidoModel pedidoModel){
        // Validar el total del pedido
        if (pedidoModel.getTotal() <0) {
            throw new IllegalArgumentException("El total del pedido debe ser mayor a cero.");
        }

        // Validar el estado del pedido
        if (pedidoModel.getEstado() == null) {
            throw new IllegalArgumentException("El estado del pedido no puede ser nulo.");
        }
    }
}
