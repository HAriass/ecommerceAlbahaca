
package com.albahaca.ecommerce.services;

import com.albahaca.ecommerce.models.MarcaModel;
import com.albahaca.ecommerce.repositories.MarcaRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarcaService {
    
    @Autowired
    MarcaRepository marcaRepository;
    
    public ArrayList<MarcaModel> listarMarcas(){
        return (ArrayList<MarcaModel>) marcaRepository.findAll();
    }
    
    public MarcaModel guardarMarca(MarcaModel marcaModel){
        return marcaRepository.save(marcaModel);
    }
    
    public boolean eliminarMarca(Long id){
        try {
            marcaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public Optional<MarcaModel> obtenerMarcaPorId(Long id){
        return marcaRepository.findById(id);
    }   
}
