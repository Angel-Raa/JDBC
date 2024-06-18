package com.github.angel.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class Estudiante implements Serializable {
    @Serial
    private static final long serialVersionUID = -1038170261810313816L;
    private Long id;
    @NotEmpty(message = "El nombre no puede ser vacio")
    private String nombre;
    @NotEmpty(message = "El apellido no puede ser vacio")
    private String apellido;
    private String genero;
    @Email(message = "Email should be valid")
    private String email;
    @NotEmpty(message = "El telefono no puede ser vacio")
    @Pattern(regexp = "\\d{10}", message = "El telefono debe tener 10 digitos")
    private String telefono;
    @NotEmpty(message = "La direccion no puede ser vacia")
    private String direccion;
    @NotEmpty(message = "La fecha de nacimiento no puede ser vacia")
    private Date fechaNacimiento;
    private String ciudad;

    public Estudiante() {
    }

    public Estudiante(String apellido, String nombre, String genero, String email, String telefono, String direccion, Date fechaNacimiento, String ciudad) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.genero = genero;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.ciudad = ciudad;
    }

    public Long getId() {
        return id;
    }

    public Estudiante setId(Long id) {
        this.id = id;
        return this;
    }

    public String getApellido() {
        return apellido;
    }

    public Estudiante setApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public String getGenero() {
        return genero;
    }

    public Estudiante setGenero(String genero) {
        this.genero = genero;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Estudiante setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Estudiante setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTelefono() {
        return telefono;
    }

    public Estudiante setTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public String getDireccion() {
        return direccion;
    }

    public Estudiante setDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Estudiante setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public String getCiudad() {
        return ciudad;
    }

    public Estudiante setCiudad(String ciudad) {
        this.ciudad = ciudad;
        return this;
    }
}
