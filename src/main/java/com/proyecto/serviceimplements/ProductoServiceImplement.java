package com.proyecto.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.proyecto.models.Producto;
import com.proyecto.repositories.ProductoRepository;
import com.proyecto.services.ProductoService;

@Service
public class ProductoServiceImplement implements ProductoService {
	@Autowired 
	private ProductoRepository repository;

	@Override
	public List<Producto> listar() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	

	
	
	@Override
	public List<Producto> listarDisponibles() {
		// TODO Auto-generated method stub
		return repository.findByStockGreaterThan(0);
	}

	@Override
	public Producto guardar(Producto producto) {
		// TODO Auto-generated method stub
		return repository.save(producto);
	}
	
	@Override
	public Producto actualizar(Producto p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Producto> buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		 return repository.findById(id);
	}
	

	@Override
	public List<Producto> buscar(String keyword) {
		// TODO Auto-generated method stub
		return repository.findByNombreProductoContainingIgnoreCase(keyword);
	}
	
	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}





	

	

	
	
	

	
	

	

	
	

}
