
package com.albahaca.ecommerce.services;

import com.albahaca.ecommerce.models.CuentaModel;
import com.albahaca.ecommerce.models.SecurityCuenta;
import com.albahaca.ecommerce.repositories.CuentaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CuentaDetailsService implements UserDetailsService{
    
    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        CuentaModel cuenta = cuentaRepository.findByNombre(username);
        
        if(cuenta==null){
            throw new UsernameNotFoundException("Cuenta not found");
        }
        
        return new SecurityCuenta(cuenta); //recorda que SecurityCuenta implementa UserDetails
        
    }
    public CuentaModel getCuentaLogueada() {
        // Obtener la autenticación actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Verificar que el usuario esté autenticado
        if (authentication != null && authentication.isAuthenticated()) {
            String nombreUsuario = authentication.getName(); // Obtiene el nombre de usuario

            // Buscar la cuenta en la base de datos usando el nombre de usuario
            return cuentaRepository.findByNombre(nombreUsuario);
        }
        return null; // O manejar el caso en que no esté autenticado
    }
    
}
