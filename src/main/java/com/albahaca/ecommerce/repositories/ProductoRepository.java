
package com.albahaca.ecommerce.repositories;

import com.albahaca.ecommerce.models.ProductoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository<ProductoModel, Long>{
    
}
