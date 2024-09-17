package com.albahaca.ecommerce.repositories;

import com.albahaca.ecommerce.models.CategoriaModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends CrudRepository<CategoriaModel, Long> {
    
}
