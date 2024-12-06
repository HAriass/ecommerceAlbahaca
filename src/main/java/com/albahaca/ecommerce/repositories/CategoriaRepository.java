package com.albahaca.ecommerce.repositories;

import com.albahaca.ecommerce.DTO.GananciaPorCategoriaDTO;
import com.albahaca.ecommerce.models.CategoriaModel;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends CrudRepository<CategoriaModel, Long> {

    @Query("""
            SELECT new com.albahaca.ecommerce.DTO.GananciaPorCategoriaDTO(c.nombre, SUM(dp.subtotal))
            FROM DetallePedidoModel dp
            INNER JOIN dp.producto p
            INNER JOIN p.categoria c
            INNER JOIN dp.pedido ped
            WHERE ped.fechaHora BETWEEN :fechaInicio AND :fechaFin
            GROUP BY c.id, c.nombre
            """)
    List<GananciaPorCategoriaDTO> obtenerGananciasPorCategoriaConFechas(java.time.LocalDateTime fechaInicio, java.time.LocalDateTime fechaFin);
}

