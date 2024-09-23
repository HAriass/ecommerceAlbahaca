
package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.CuentaModel;
import com.albahaca.ecommerce.services.CuentaService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {
    
    @Autowired
    CuentaService cuentaService;
    
    @GetMapping("/listarCuentas")
    public ArrayList<CuentaModel> listarCuentas(){
        return this.cuentaService.listarCuentas();
    }
    
    @PostMapping("/guardarCuenta")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CuentaModel guardarCuenta(@RequestBody CuentaModel cuentaModel){
        return this.cuentaService.guardarCuenta(cuentaModel);
    }
    @DeleteMapping("/eliminarCuenta/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean eliminarCuenta(@PathVariable("id") Long id){
        return this.cuentaService.eliminarCuenta(id);
    }

}
