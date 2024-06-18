package com.github.angel.entity;

import jakarta.validation.constraints.NotEmpty;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = -2913635183619938712L;
    private Long id;
    @NotEmpty(message = "El nombre no puede ser vacio")
    private String nombre;
    @NotEmpty(message = "La descripción no puede ser vacia")
    private String descripcion;
    @NotEmpty(message = "El precio no puede ser vacio")
    private Double precio;
    private Date fecha;
    private  Categorie categorie;

    public Product() {}

    public Product(String nombre, String descripcion, Double precio, Date fecha, Categorie categorie) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fecha = fecha;
        this.categorie = categorie;
    }

    public Long id() {
        return id;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public String nombre() {
        return nombre;
    }

    public Product setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String descripcion() {
        return descripcion;
    }

    public Product setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public Double precio() {
        return precio;
    }

    public Product setPrecio(Double precio) {
        this.precio = precio;
        return this;
    }

    public Date date() {
        return fecha;
    }

    public Product setFecha(Date fecha) {
        this.fecha = fecha;
        return this;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Product{");
        sb.append("id=").append(id);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", descripcion='").append(descripcion).append('\'');
        sb.append(", precio=").append(precio);
        sb.append(", fecha=").append(fecha);
        sb.append(", categorie=").append(categorie);
        sb.append('}');
        return sb.toString();
    }
}
