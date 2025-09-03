package com.proyecto.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.models.Pedido;
import com.proyecto.repositories.PedidoRepository;
import com.proyecto.services.PedidoService;

@Service
public class PedidoServiceImplement implements PedidoService {
@Autowired private PedidoRepository repository;
	@Override
	public List<Pedido> listar() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	@Override
	public List<Pedido> listarPorEstado(String estado) {
		// TODO Auto-generated method stub
		return repository.findByEstado(estado);
	}

	@Override
	public Optional<Pedido> buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}
	
	@Override
	public Pedido buscarPedidoPorId(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}
	@Override
	public List<Pedido> buscarPorCliente(String keyword) {
		// TODO Auto-generated method stub
		return repository.findByClienteNombreContainingIgnoreCase(keyword);
	}

	@Override
	public Pedido guardar(Pedido pedido) {
		// TODO Auto-generated method stub
		return repository.save(pedido);
	}

	@Override
	public Optional<Pedido> consultarunpedido(int id) {
	
	    return repository.findById(id);

	}

	@Override
	public int consultarultimo() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

	

	

	

	

	

}
