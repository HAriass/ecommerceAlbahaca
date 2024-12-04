/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.albahaca.ecommerce.services;

import com.albahaca.ecommerce.models.DetallePedidoModel;
import com.albahaca.ecommerce.repositories.DetallePedidoRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoService {
    
   @Autowired
    DetallePedidoRepository detallePedidoRepository;
    
    // Crear o actualizar un DetallePedido
    public DetallePedidoModel guardarDetallePedido(DetallePedidoModel detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }
    
    // Obtener todos los DetallesPedido
    public ArrayList<DetallePedidoModel> listaDetallePedido() {
        return (ArrayList<DetallePedidoModel>) detallePedidoRepository.findAll();
    }
    
    // Obtener todos los DetallesPedidoPorPedido
    public ArrayList<DetallePedidoModel> listaDetallePedidoPorPedido(long pedidoId) {
        return (ArrayList<DetallePedidoModel>) detallePedidoRepository.findByPedidoId(pedidoId);
    }

    // Obtener un DetallePedido por ID
    public Optional<DetallePedidoModel> obtenerDetallePorId(Long id) {
        return detallePedidoRepository.findById(id);
    }

    // Eliminar un DetallePedido por ID
    public boolean eliminarDetallePedido(Long id) {
        try {
            detallePedidoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    // Obtener el ID del producto asociado a un DetallePedido
    public Long obtenerProductoIdPorDetalle(Long detallePedidoId) {
        Optional<DetallePedidoModel> detallePedido = detallePedidoRepository.findById(detallePedidoId);
        if (detallePedido.isPresent() && detallePedido.get().getProducto() != null) {
            return detallePedido.get().getProducto().getId();
        }
        return null; // o lanzar una excepción según tus necesidades
    }
}
