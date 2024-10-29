
package com.albahaca.ecommerce.repositories;

import com.albahaca.ecommerce.models.ProductoModel;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository<ProductoModel, Long>{

    ArrayList<ProductoModel> findByCategoriaId(Long categoriaId);
    int countByMarcaId(Long marcaId); // Este método cuenta los productos relacionados con la marca
}
