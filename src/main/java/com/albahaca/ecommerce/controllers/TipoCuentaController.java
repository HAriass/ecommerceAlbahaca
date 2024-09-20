
package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.TipoCuentaModel;
import com.albahaca.ecommerce.services.TipoCuentaService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipoCuenta")
public class TipoCuentaController {
    
    @Autowired
    TipoCuentaService tipoCuentaService;
    
    @GetMapping("/listarTipoCuentas")
    public ArrayList<TipoCuentaModel> listarTipoCuentas(){
        return this. tipoCuentaService.listarTipoCuentas();
    }
    
    @PostMapping("/guardarTipoCuenta")
    public TipoCuentaModel guardarTipoCuenta(@RequestBody TipoCuentaModel tipoCuentaModel){
        return this.tipoCuentaService.guardarTipoCuenta(tipoCuentaModel);
    }
    
    @DeleteMapping("/eliminarTipoCuenta/{id}")
    public boolean eliminarTipoCuenta(@PathVariable Long id){
        return this.tipoCuentaService.eliminarTipoCuenta(id);
    }
    
    @GetMapping("/obtenerTipoCuentaPorId/{id}")
    public Optional<TipoCuentaModel> obtenerTipoCuentaPorId(@PathVariable("id") Long id){
        return this.tipoCuentaService.obtenerTipoCuentaPorId(id);
    }
}
