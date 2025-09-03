package com.proyecto.services;

import java.util.List;
import java.util.Optional;



import com.proyecto.models.Producto;

public interface ProductoService {
	List<Producto> listar();
    List<Producto> listarDisponibles(); // solo stock > 0
    Producto guardar(Producto producto);
    public Producto actualizar(Producto p);
    Optional<Producto> buscarPorId(Integer id);
    void eliminar(Integer id);
	List<Producto> buscar(String keyword);
	
	
	
	
}
