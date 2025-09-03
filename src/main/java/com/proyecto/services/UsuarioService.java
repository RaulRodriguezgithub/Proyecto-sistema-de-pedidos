package com.proyecto.services;

import java.util.List;
import java.util.Optional;

import com.proyecto.models.Usuario;

public interface UsuarioService {
List<Usuario>listar();
Usuario guardar(Usuario usuario);
void eliminar(Integer id);
List<Usuario> buscar(String keyword);
Optional<Usuario> buscarPorId(int id);



}
