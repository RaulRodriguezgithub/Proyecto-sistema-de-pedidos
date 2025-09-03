package com.proyecto.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.proyecto.models.Usuario;
import com.proyecto.services.UsuarioService;

@ControllerAdvice
@Controller
@RequestMapping("admin/usuarios")
public class UsuarioController {
@Autowired
private UsuarioService usuarioService;


@ModelAttribute("usuarioLogueado")
public String agregarUsuarioLogueado() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    return (auth != null && auth.isAuthenticated()) ? auth.getName() : "Invitado";
}

@GetMapping("listar")
public String listar(@RequestParam(value = "keyword", required = false)String keyword, Model model) {
    List<Usuario> usuario;
   
    if (keyword != null && !keyword.isEmpty()) {
        usuario = usuarioService.buscar(keyword);  // <- método en el service
    } else {
        usuario = usuarioService.listar();
    } 
   
   
    model.addAttribute("usuario", usuario);
    return "admin/usuarios/listar";
}

@GetMapping("insertar")
public String usuarioInsertar(Model model) {
	model.addAttribute("usuario", new Usuario());
	return "admin/usuarios/insertar";
}

@PostMapping("guardar")
public String guardar(@Validated Usuario u, Model model) {
	usuarioService.guardar(u);
	return "redirect:/admin/usuarios/listar";
}

@GetMapping("eliminar/{id}")
public String borrar(@PathVariable int id, Model model) {
	usuarioService.eliminar(id);
	return "redirect:/admin/usuarios/listar";
		
}


}
