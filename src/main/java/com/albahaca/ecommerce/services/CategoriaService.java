package com.albahaca.ecommerce.services;

import com.albahaca.ecommerce.DTO.GananciaPorCategoriaDTO;
import com.albahaca.ecommerce.models.CategoriaModel;
import com.albahaca.ecommerce.models.ProductoModel;
import com.albahaca.ecommerce.repositories.CategoriaRepository;
import com.albahaca.ecommerce.repositories.ProductoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    ProductoRepository productoRepository; // Agregar el repositorio de Producto

    public ArrayList<CategoriaModel> listarCategorias() {
        return (ArrayList<CategoriaModel>) categoriaRepository.findAll();
    }

    public CategoriaModel guardarCategoria(CategoriaModel categoria) {
        validarCategoria(categoria);
        return categoriaRepository.save(categoria);
    }

    public boolean eliminarCategoria(Long id) {
        // Verificar si existen productos asociados a la categoría
        ArrayList<ProductoModel> productosAsociados = productoRepository.findByCategoriaId(id);
        if (!productosAsociados.isEmpty()) {
            // No se puede eliminar si hay productos asociados
            return false;
        }
        
        try {
            categoriaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<CategoriaModel> obtenerCategoriaPorId(Long id) {
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
    
    
    //Ganancia por categoria
    public List<GananciaPorCategoriaDTO> obtenerGananciasPorCategoria() {
        return categoriaRepository.obtenerGananciasPorCategoria();
    }
}
