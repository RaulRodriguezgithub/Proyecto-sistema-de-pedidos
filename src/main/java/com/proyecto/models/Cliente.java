package com.proyecto.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Cliente {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;


@NotBlank
private String nombre;

@Email
@NotBlank
private String correo;
@NotBlank
private String direccion;

@NotBlank
private String telefono;

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getCorreo() {
	return correo;
}

public void setCorreo(String correo) {
	this.correo = correo;
}

public String getDireccion() {
	return direccion;
}

public void setDireccion(String direccion) {
	this.direccion = direccion;
}

public String getTelefono() {
	return telefono;
}

public void setTelefono(String telefono) {
	this.telefono = telefono;
}


}
