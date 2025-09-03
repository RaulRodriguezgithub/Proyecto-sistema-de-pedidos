package com.proyecto.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.proyecto.models.Usuario;
import com.proyecto.repositories.UsuarioRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initAdminUser(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        return args -> {
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123")); // clave encriptada
               

                usuarioRepository.save(admin);
                System.out.println("Usuario admin creado con rol fijo: Admin");
            } else {
                System.out.println("Usuario admin ya existe");
            }
        };
    }
}
