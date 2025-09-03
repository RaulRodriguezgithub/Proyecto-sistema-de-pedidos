package com.proyecto.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.proyecto.models.Producto;
import com.proyecto.services.ProductoService;

@Controller
@RequestMapping("/admin/productos")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	
	@GetMapping("listar")
	public String listar(@RequestParam(value = "keyword", required = false)String keyword, Model model) {
	    List<Producto> productos;
	   
	    if (keyword != null && !keyword.isEmpty()) {
	        productos = productoService.buscar(keyword);  // <- método en el service
	    } else {
	        productos = productoService.listar();
	    }
	    
	    
	    
	    model.addAttribute("producto", productos);
	    return "admin/productos/listar";
	}

	
	@GetMapping("insertar")
	public String productoinsertar(Model model) {
		model.addAttribute("producto", new Producto());
		return "admin/productos/insertar";
	}
	
	@PostMapping("guardar")
	public String guardar(@Validated Producto producto, @RequestParam("imagen") MultipartFile file, Model model) {
	    if (!file.isEmpty()) {
	        try {
	            String directorio = "src/main/resources/static/imagenes/productos";
	            Path ruta = Paths.get(directorio, file.getOriginalFilename());
	            Files.write(ruta, file.getBytes());

	            // Ruta pública que el navegador puede acceder
	            producto.setImagenUrl("/imagenes/productos/" + file.getOriginalFilename());

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    // Guardar el producto en la base de datos
	    productoService.guardar(producto);
	    return "redirect:/admin/productos/listar";
	}
	
	@GetMapping("actualizar/{id}")
	public String actualizar(@PathVariable int id, Model model) {
		Optional<Producto> producto = productoService.buscarPorId(id);
		model.addAttribute("producto", producto);
		return "admin/productos/actualizar";
	}
	
	@GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        productoService.eliminar(id);
        return "redirect:/admin/productos/listar";
    }
	
}
