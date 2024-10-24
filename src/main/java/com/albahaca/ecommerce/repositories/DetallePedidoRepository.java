
package com.albahaca.ecommerce.repositories;

import org.springframework.data.repository.CrudRepository;
import com.albahaca.ecommerce.models.DetallePedidoModel;
import java.util.ArrayList;

public interface DetallePedidoRepository extends CrudRepository<DetallePedidoModel, Long> {
    ArrayList<DetallePedidoModel> findByPedidoId(Long pedidoId);
}

