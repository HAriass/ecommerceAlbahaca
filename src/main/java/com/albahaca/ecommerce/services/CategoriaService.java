
package com.albahaca.ecommerce.services;

import com.albahaca.ecommerce.models.CategoriaModel;
import com.albahaca.ecommerce.repositories.CategoriaRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    
    @Autowired
    CategoriaRepository categoriaRepository;
    
    public ArrayList<CategoriaModel> listarCategorias(){
        return (ArrayList<CategoriaModel>) categoriaRepository.findAll();
    }
    
    public CategoriaModel guardarCategoria(CategoriaModel categoria){
        validarCategoria(categoria);
        return categoriaRepository.save(categoria);
    }

    public boolean eliminarCategoria(Long id) {
        try {
            categoriaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public Optional<CategoriaModel> obtenerCategoriaPorId(Long id){
        return categoriaRepository.findById(id);
    }
    
    private void validarCategoria(CategoriaModel categoria) {
       if (categoria.getNombre() == null || categoria.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }

        if (categoria.getDescripcion() == null || categoria.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
    }
}
