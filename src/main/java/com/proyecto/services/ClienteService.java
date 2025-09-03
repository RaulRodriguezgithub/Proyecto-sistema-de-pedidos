package com.proyecto.services;

import java.util.List;
import java.util.Optional;

import com.proyecto.models.Cliente;

public interface ClienteService {
	List<Cliente> listar();
	Optional<Cliente> buscarPorId(Integer id);
    Cliente guardar(Cliente cliente);
}
