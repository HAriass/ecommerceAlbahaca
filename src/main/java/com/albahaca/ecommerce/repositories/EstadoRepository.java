
package com.albahaca.ecommerce.repositories;

import com.albahaca.ecommerce.models.EstadoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends CrudRepository<EstadoModel, Long>{
}
