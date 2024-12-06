
package com.albahaca.ecommerce.services;

import com.albahaca.ecommerce.DTO.ClienteMasComprasDTO;
import com.albahaca.ecommerce.models.CuentaModel;
import com.albahaca.ecommerce.repositories.CuentaRepository;
import com.albahaca.ecommerce.repositories.PedidoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaService {
    
    @Autowired
    CuentaRepository cuentaRepository;
    
    @Autowired
    PedidoRepository pedidoRepository;
    
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
    
    public List<ClienteMasComprasDTO> obtenerClienteConMasCompras(){
        return pedidoRepository.obtenerClienteConMasCompras();
    }
    
}
