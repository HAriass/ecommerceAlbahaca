
package com.albahaca.ecommerce.services;

import com.albahaca.ecommerce.models.ProductoModel;
import com.albahaca.ecommerce.repositories.ProductoRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    
    @Autowired
    ProductoRepository productoRepository;
    
    public ArrayList<ProductoModel> listarProducto(){
        return (ArrayList<ProductoModel>) productoRepository.findAll();
    }
    
    public ProductoModel guardarProducto(ProductoModel producto){
        return productoRepository.save(producto);
    }
    
    public boolean eliminarProducto(Long id){
        try {
            productoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
