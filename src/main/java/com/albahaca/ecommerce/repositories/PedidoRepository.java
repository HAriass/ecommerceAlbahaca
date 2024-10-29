
package com.albahaca.ecommerce.repositories;

import com.albahaca.ecommerce.models.PedidoModel;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository<PedidoModel, Long>{
    ArrayList<PedidoModel> findByCuentaId(Long cuentaId);
}
