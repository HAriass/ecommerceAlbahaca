package com.albahaca.ecommerce.controllers;

import com.albahaca.ecommerce.DTO.ClienteMasComprasDTO;
import com.albahaca.ecommerce.models.CuentaModel;
import com.albahaca.ecommerce.services.CuentaService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
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
    
    @GetMapping("/cliente-con-mas-compras")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ClienteMasComprasDTO>> obtenerClienteConMasCompras(
        @RequestParam("inicio") String inicio,
        @RequestParam("fin") String fin) {
        
        // Convertir las fechas recibidas como String a LocalDateTime
        LocalDateTime fechaInicio = LocalDateTime.parse(inicio);
        LocalDateTime fechaFin = LocalDateTime.parse(fin);
        
        List<ClienteMasComprasDTO> clientes = cuentaService.obtenerClienteConMasCompras(fechaInicio, fechaFin);

        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build(); // Devuelve 204 si no hay datos
        }
        
        // Devuelve el primer cliente con más compras (el primero en la lista ordenada)
        return ResponseEntity.ok(clientes);
    }

}
