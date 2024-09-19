
package com.albahaca.ecommerce.services;

import com.albahaca.ecommerce.models.EstadoModel;
import com.albahaca.ecommerce.repositories.EstadoRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {
    @Autowired
    EstadoRepository estadoRepository;
    
    public ArrayList<EstadoModel> obtenerEstados(){
        return (ArrayList<EstadoModel>) estadoRepository.findAll();
    }
    
    public EstadoModel guardarEstado(EstadoModel estado){
        return estadoRepository.save(estado);
    }
    
    public boolean eliminarEstado(Long id ){
        try {
            estadoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<EstadoModel> obtenerEstadoPorId(Long id) {
        return estadoRepository.findById(id); 
    }
}
