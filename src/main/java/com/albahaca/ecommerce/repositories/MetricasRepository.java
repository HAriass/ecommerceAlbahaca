
package com.albahaca.ecommerce.repositories;

import com.albahaca.ecommerce.models.MetricasModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricasRepository extends CrudRepository<MetricasModel, Long> {
}