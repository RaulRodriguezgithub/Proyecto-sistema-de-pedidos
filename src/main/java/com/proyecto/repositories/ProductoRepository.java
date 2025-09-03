package com.proyecto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	List<Producto> findByStockGreaterThan(int cantidad);
	List<Producto> findByNombreProductoContainingIgnoreCase(String nombreProducto);

}
