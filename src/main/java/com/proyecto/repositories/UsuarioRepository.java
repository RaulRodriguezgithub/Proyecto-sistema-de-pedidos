package com.proyecto.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer > {

	List<Usuario> findByUsernameContainingIgnoreCase(String keyword);
	Optional<Usuario> findByUsername(String username);

}
