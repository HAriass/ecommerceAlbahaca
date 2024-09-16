
package com.albahaca.ecommerce.repositories;

import com.albahaca.ecommerce.models.MarcaModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends CrudRepository<MarcaModel, Long>{
    
}
