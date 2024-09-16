
package com.albahaca.ecommerce.services;

import com.albahaca.ecommerce.models.MarcaModel;
import com.albahaca.ecommerce.repositories.MarcaRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarcaService {
    
    @Autowired
    MarcaRepository marcaRepository;
    
    public ArrayList<MarcaModel> listarMarcas(){
        return (ArrayList<MarcaModel>) marcaRepository.findAll();
    }
}
