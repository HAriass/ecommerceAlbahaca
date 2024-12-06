
package com.albahaca.ecommerce.repositories;

import com.albahaca.ecommerce.DTO.ProductoMasIngresoDTO;
import com.albahaca.ecommerce.DTO.ProductoMasVendidoDTO;
import org.springframework.data.repository.CrudRepository;
import com.albahaca.ecommerce.models.DetallePedidoModel;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface DetallePedidoRepository extends CrudRepository<DetallePedidoModel, Long> {
    ArrayList<DetallePedidoModel> findByPedidoId(Long pedidoId);
    int countByProductoId(long id);
    
    //Consulta para productos mas vendido
    @Query("SELECT new com.albahaca.ecommerce.DTO.ProductoMasVendidoDTO(p.nombre, SUM(dp.cantidad)) " +
       "FROM DetallePedidoModel dp " +
       "INNER JOIN dp.producto p " +
       "WHERE dp.pedido.fechaHora BETWEEN :fechaInicio AND :fechaFin " +
       "GROUP BY p.id, p.nombre " +
       "ORDER BY SUM(dp.cantidad) DESC")
    List<ProductoMasVendidoDTO> obtenerProductosMasVendidos(java.time.LocalDateTime fechaInicio, java.time.LocalDateTime fechaFin);


    @Query("SELECT new com.albahaca.ecommerce.DTO.ProductoMasIngresoDTO(p.nombre, SUM(dp.subtotal)) " +
           "FROM DetallePedidoModel dp " +
           "INNER JOIN dp.producto p " +
           "WHERE dp.pedido.fechaHora BETWEEN :fechaInicio AND :fechaFin " +
           "GROUP BY p.id, p.nombre " +
           "ORDER BY SUM(dp.subtotal) DESC")
    List<ProductoMasIngresoDTO> obtenerProductoConMasIngresos(java.time.LocalDateTime fechaInicio, java.time.LocalDateTime fechaFin);


}

