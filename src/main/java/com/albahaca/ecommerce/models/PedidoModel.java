
package com.albahaca.ecommerce.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedido")
public class PedidoModel {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    
    private LocalDateTime fechaHora;
    
    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private CuentaModel cuenta;
    
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private EstadoModel estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public CuentaModel getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaModel cuenta) {
        this.cuenta = cuenta;
    }

    public EstadoModel getEstado() {
        return estado;
    }

    public void setEstado(EstadoModel estado) {
        this.estado = estado;
    }
    
    
}
