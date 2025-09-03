package com.proyecto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	List<Pedido> findByEstado(String estado);
	List<Pedido> findByClienteNombreContainingIgnoreCase(String nombre);

	@Query(value="SELECT MAX(id) FROM pedido", nativeQuery =true)
	int consultarultimo();

}
