
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
}
