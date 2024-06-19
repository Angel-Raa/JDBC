package com.github.angel.entity;

import java.io.Serial;
import java.io.Serializable;

public class Categorie implements Serializable {
    @Serial
    private static final long serialVersionUID = -2913615183619938712L;
    private Long id;
    private String name;

    public Categorie() {
    }

    public Categorie(String name) {
        this.name = name;
    }

    public Categorie(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Categorie{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
