
package com.albahaca.ecommerce.models;

import java.util.Collection;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@NoArgsConstructor
public class SecurityCuenta implements UserDetails{
    
    private CuentaModel cuenta;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(cuenta.getRol()));
    }

    @Override
    public String getPassword() {
        return cuenta.getPassword();
    }

    @Override
    public String getUsername() {
        return cuenta.getNombre();
    }
    
}
