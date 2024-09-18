
package com.albahaca.ecommerce.repositories;

import com.albahaca.ecommerce.models.TipoCuentaModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCuentaRepository extends CrudRepository<TipoCuentaModel, Long>{
    
}
