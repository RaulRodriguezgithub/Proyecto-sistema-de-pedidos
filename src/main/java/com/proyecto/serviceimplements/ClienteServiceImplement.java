package com.proyecto.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.models.Cliente;
import com.proyecto.repositories.ClienteRepository;
import com.proyecto.services.ClienteService;

@Service
public class ClienteServiceImplement implements ClienteService{
@Autowired
private ClienteRepository repository;
	@Override
	public List<Cliente> listar() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Optional<Cliente> buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public Cliente guardar(Cliente cliente) {
		// TODO Auto-generated method stub
		return repository.save(cliente);
	}

}
