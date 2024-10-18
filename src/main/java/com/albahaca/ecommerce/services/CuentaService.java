
package com.albahaca.ecommerce.services;

import com.albahaca.ecommerce.models.CuentaModel;
import com.albahaca.ecommerce.repositories.CuentaRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaService {
    
    @Autowired
    CuentaRepository cuentaRepository;
    
    public ArrayList<CuentaModel> listarCuentas(){
        return (ArrayList<CuentaModel>) cuentaRepository.findAll();
    }
    
    public CuentaModel guardarCuenta(CuentaModel cuentaModel){
        return cuentaRepository.save(cuentaModel);
    }
    
    public boolean eliminarCuenta(Long id){
        try {
            cuentaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public CuentaModel findById(Long id) {
        return cuentaRepository.findById(id).orElse(null);
    }
    
}
