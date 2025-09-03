package com.proyecto.serviceimplements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyecto.models.Usuario;
import com.proyecto.repositories.UsuarioRepository;
import com.proyecto.services.UsuarioService;

@Service
public class UsuarioServiceImplement implements UsuarioService {
@Autowired
	private UsuarioRepository repository;
@Autowired
private PasswordEncoder passwordEncoder;
	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Usuario guardar(Usuario usuario) {
		// TODO Auto-generated method stub
		String claveEncriptada = passwordEncoder.encode(usuario.getPassword());
	    usuario.setPassword(claveEncriptada);
		return repository.save(usuario);
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public List<Usuario> buscar(String keyword) {
		// TODO Auto-generated method stub
		return repository.findByUsernameContainingIgnoreCase(keyword);
	}

	@Override
	public Optional<Usuario> buscarPorId(int id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	

	
	
}
