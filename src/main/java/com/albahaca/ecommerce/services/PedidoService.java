package com.albahaca.ecommerce.services;

import com.albahaca.ecommerce.models.EstadoModel;
import com.albahaca.ecommerce.models.PedidoModel;
import com.albahaca.ecommerce.repositories.PedidoRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    
    @Autowired
    PedidoRepository pedidoRepository;
    
    @Autowired 
    EstadoService estadoService;
    
    public ArrayList<PedidoModel> listarPedidos(){
        return (ArrayList<PedidoModel>) pedidoRepository.findAll();
    }
    
    public ArrayList<PedidoModel> listarPedidosCliente(Long cuentaId){
        return (ArrayList<PedidoModel>) pedidoRepository.findByCuentaId(cuentaId);
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
        if (pedidoModel.getTotal() < 0) {
            throw new IllegalArgumentException("El total del pedido debe ser mayor a cero.");
        }

        // Validar el estado del pedido
        if (pedidoModel.getEstado() == null) {
            throw new IllegalArgumentException("El estado del pedido no puede ser nulo.");
        }
    }
    
    @Scheduled(fixedRate = 10000) // Revisa cada 10 segundos
    public void cambiarEstadoPedidos() {
    for (PedidoModel pedido : this.listarPedidos()) {
        String estadoActual = pedido.getEstado().getNombre();
        LocalDateTime ahora = LocalDateTime.now();
        
        // Verifica si el pedido está en "enPreparacion" por más de 10 segundos
        if (estadoActual.equals("enPreparacion")) {
            if (pedido.getFechaHora().plusSeconds(20).isBefore(ahora)) {
                this.cambiarEstado("enEnvio", pedido);
            }
        } 
        // Cambia a "entregado" sin retraso adicional
        else if (estadoActual.equals("enEnvio")) {
            this.cambiarEstado("entregado", pedido);
        }
    }
}

    
    public void cambiarEstado(String nombreEstado, PedidoModel pedido){
        EstadoModel estadoNuevo = estadoService.obtenerEstadoPorNombre(nombreEstado);
        pedido.setEstado(estadoNuevo);
        pedidoRepository.save(pedido); // Guardar el pedido con el nuevo estado
    }
    
    public void cancelarPedido(Optional<PedidoModel> pedido){
        if(pedido.isPresent()) {
            if (pedido.get().getEstado().getNombre().equals("enPreparacion")){
                this.cambiarEstado("cancelado", pedido.get());
            }
        }
    }
    
    public Optional<PedidoModel> obtenerPedidoPorId(Long id){
        return  pedidoRepository.findById(id);
    }   
}
