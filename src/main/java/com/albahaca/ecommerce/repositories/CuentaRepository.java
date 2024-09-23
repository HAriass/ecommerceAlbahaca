
package com.albahaca.ecommerce.repositories;

import com.albahaca.ecommerce.models.CuentaModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends CrudRepository<CuentaModel, Long>{
    
    CuentaModel findByNombre(String nombre);
}
