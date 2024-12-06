
package com.albahaca.ecommerce.services;

import com.albahaca.ecommerce.DTO.ProductoMasIngresoDTO;
import com.albahaca.ecommerce.DTO.ProductoMasVendidoDTO;
import com.albahaca.ecommerce.models.ProductoModel;
import com.albahaca.ecommerce.repositories.DetallePedidoRepository;
import com.albahaca.ecommerce.repositories.ProductoRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class ProductoService {
    
    @Autowired
    ProductoRepository productoRepository;
    
    @Autowired 
    DetallePedidoRepository detallePedidoRepository;
    
    public ArrayList<ProductoModel> listarProducto(){
        return (ArrayList<ProductoModel>) productoRepository.findAll();
    }
    
    public ProductoModel guardarProducto(ProductoModel producto){
        validarProducto(producto);
        return productoRepository.save(producto);
    }
    
    public boolean eliminarProducto(Long id) {
        if (detallePedidoRepository.countByProductoId(id) > 0) {
            return false; // No elimina si hay detalle de pedido asociados
        }
        try {
            productoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
    public Optional<ProductoModel> obtenerProductoPorId(Long id){
        return productoRepository.findById(id);
    } 
    
    private void validarProducto(ProductoModel producto) {
        if (producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (producto.getNombre()== null){
            throw new IllegalArgumentException("El nombre no puede ser nulo.");
        }
    }
    
    public ArrayList<ProductoModel> obtenerProductoPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }
    
    public List<ProductoMasVendidoDTO> obtenerProductosMasVendidos(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return detallePedidoRepository.obtenerProductosMasVendidos(fechaInicio, fechaFin);
    }
    
    public List<ProductoMasIngresoDTO> obtenerProductoConMasIngresos(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return detallePedidoRepository.obtenerProductoConMasIngresos(fechaInicio, fechaFin);
    }
    
}
