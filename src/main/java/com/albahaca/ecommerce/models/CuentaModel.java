
package com.albahaca.ecommerce.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cuenta")
public class CuentaModel {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    
    private String nombre;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "tipo_cuenta_id")
    private TipoCuentaModel tipoCuentaModel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoCuentaModel getTipoCuentaModel() {
        return tipoCuentaModel;
    }

    public void setTipoCuentaModel(TipoCuentaModel tipoCuentaModel) {
        this.tipoCuentaModel = tipoCuentaModel;
    }
    
    

}
