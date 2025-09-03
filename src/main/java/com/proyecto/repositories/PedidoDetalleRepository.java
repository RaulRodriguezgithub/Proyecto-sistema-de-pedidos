package com.proyecto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.models.PedidoDetalle;

public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Integer> {
	@Query(value="SELECT pd.pedido_id, pd.producto_id, pd.cantidad, p.nombre_producto, p.valor, p.id " +
		    "FROM pedido_detalle pd INNER JOIN producto p ON pd.producto_id = p.id " +
		    "WHERE pd.pedido_id = :idpedido", nativeQuery = true)
	List<PedidoDetalle> consultarproductosdetalle(@Param("idpedido") int idpedido);
	List<PedidoDetalle> findByPedidoId(int pedidoId);

	
}
