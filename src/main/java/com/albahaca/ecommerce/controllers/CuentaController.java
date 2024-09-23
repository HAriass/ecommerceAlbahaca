package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.models.CuentaModel;
import com.albahaca.ecommerce.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    CuentaService cuentaService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;  // Inyecta el encoder

    @GetMapping("/listarCuentas")
    public ArrayList<CuentaModel> listarCuentas() {
        return this.cuentaService.listarCuentas();
    }

    @PostMapping("/guardarCuenta")
    public ResponseEntity<?> guardarCuenta(@RequestBody CuentaModel cuentaModel) {
        try {
            // Encripta la contraseña antes de guardar la cuenta
            String encodedPassword = passwordEncoder.encode(cuentaModel.getPassword());
            cuentaModel.setPassword(encodedPassword);  // Establece la contraseña encriptada

            // Guarda la cuenta con la contraseña encriptada
            CuentaModel nuevaCuenta = this.cuentaService.guardarCuenta(cuentaModel);

            return ResponseEntity.ok(nuevaCuenta);  // Devuelve la nueva cuenta creada
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al guardar la cuenta: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminarCuenta/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public boolean eliminarCuenta(@PathVariable("id") Long id) {
        return this.cuentaService.eliminarCuenta(id);
    }
}
