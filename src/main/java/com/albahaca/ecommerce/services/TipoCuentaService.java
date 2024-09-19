
package com.albahaca.ecommerce.services;

import com.albahaca.ecommerce.models.TipoCuentaModel;
import com.albahaca.ecommerce.repositories.TipoCuentaRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoCuentaService {
    
    @Autowired
    TipoCuentaRepository tipoCuentaRepository;
    
    public ArrayList<TipoCuentaModel> listarTipoCuentas(){
        return (ArrayList<TipoCuentaModel>) tipoCuentaRepository.findAll();
    }
    
    public TipoCuentaModel guardarTipoCuenta(TipoCuentaModel tipoCuentaModel){
        return tipoCuentaRepository.save(tipoCuentaModel);
    }
    
    public boolean eliminarTipoCuenta(Long id){
        try {
            tipoCuentaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public Optional<TipoCuentaModel> obtenerTipoCuentaPorId(Long id){
        return tipoCuentaRepository.findById(id);
    }  
}
