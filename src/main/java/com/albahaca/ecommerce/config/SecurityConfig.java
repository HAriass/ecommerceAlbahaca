package com.albahaca.ecommerce.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/css/**", "/js/**", "/images/**").permitAll()  // Permitir acceso a recursos estáticos
                .requestMatchers("/", 
                                 "/marca/listarMarcas", 
                                 "/categoria/listarCategorias", 
                                 "/registrarCuenta", 
                                 "/cuenta/guardarCuenta",
                                 "/estado/listarEstados", 
                                 "/pedido/listarPedidos", 
                                 "/producto/listarProductos", 
                                 "/tipoCuenta/listarTipoCuentas").permitAll()  // Acceso público a los listados
                .requestMatchers("/menu").hasAuthority("ADMIN")  // Solo administradores pueden acceder a "/menu"
                .requestMatchers("/marca/guardarMarca", 
                                 "/marca/eliminarMarca/**", 
                                 "/marca/obtenerMarcaPorId/**").hasAuthority("ADMIN")  // ADMIN para marca
                .requestMatchers("/categoria/guardarCategoria", 
                                 "/categoria/eliminarCategoria/**", 
                                 "/categoria/obtenerCategoriaPorId/**").hasAuthority("ADMIN")  // ADMIN para categoria
                .requestMatchers( 
                                 "/cuenta/eliminarCuenta/**", 
                                 "/cuenta/obtenerCuentaPorId/**").hasAuthority("ADMIN")  // ADMIN para cuenta
                .requestMatchers("/estado/guardarEstado", 
                                 "/estado/eliminarEstado/**", 
                                 "/estado/obtenerEstadoPorId/**").hasAuthority("ADMIN")  // ADMIN para estado
                .requestMatchers("/pedido/guardarPedido", 
                                 "/pedido/eliminarPedido/**", 
                                 "/pedido/obtenerPedidoPorId/**").hasAuthority("ADMIN")  // ADMIN para pedido
                .requestMatchers("/producto/guardarProducto", 
                                 "/producto/eliminarProducto/**", 
                                 "/producto/obtenerProductoPorId/**").hasAuthority("ADMIN")  // ADMIN para producto
                .requestMatchers("/tipoCuenta/guardarTipoCuenta", 
                                 "/tipoCuenta/eliminarTipoCuenta/**", 
                                 "/tipoCuenta/obtenerTipoCuentaPorId/**").hasAuthority("ADMIN")  // ADMIN para tipo cuenta
                .anyRequest().authenticated()  // Cualquier otra solicitud requiere autenticación
            )
            .formLogin(form -> form
                .defaultSuccessUrl("/", true)  // Redirige a "/" tras login exitoso
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")  // URL para la solicitud de logout
                .logoutSuccessUrl("/")  // Redirige a "/" tras logout exitoso
                .invalidateHttpSession(true)  // Invalida la sesión
                .deleteCookies("JSESSIONID")  // Elimina las cookies de sesión
            )
            .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
