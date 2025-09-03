package com.proyecto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
	@GetMapping("/auth/login")
	public String login() {
		return "auth/login"; // nombre de la plantilla
	}

}
