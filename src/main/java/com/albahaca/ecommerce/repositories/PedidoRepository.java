
package com.albahaca.ecommerce.repositories;

import com.albahaca.ecommerce.models.PedidoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository<PedidoModel, Long>{
    
}
