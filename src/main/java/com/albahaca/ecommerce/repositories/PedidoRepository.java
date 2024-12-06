
package com.albahaca.ecommerce.repositories;

import com.albahaca.ecommerce.DTO.ClienteMasComprasDTO;
import com.albahaca.ecommerce.models.PedidoModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository<PedidoModel, Long>{
    ArrayList<PedidoModel> findByCuentaId(Long cuentaId);
    
    @Query("SELECT new com.albahaca.ecommerce.DTO.ClienteMasComprasDTO(c.nombre, COUNT(p.cuenta.id)) " +
           "FROM PedidoModel p " +
           "INNER JOIN p.cuenta c " +
           "GROUP BY c.id, c.nombre " +
           "ORDER BY COUNT(p.cuenta.id) DESC")
    List<ClienteMasComprasDTO> obtenerClienteConMasCompras();
}
