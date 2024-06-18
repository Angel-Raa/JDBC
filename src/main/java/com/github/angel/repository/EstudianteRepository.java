package com.github.angel.repository;

import com.github.angel.entity.Estudiante;

public interface EstudianteRepository extends GenericRepository<Estudiante, Long>{
    Estudiante findEstudianteByTelefono(String telefono);
    Estudiante findEstudianteByNombre(String nombre);
    Estudiante findEstudianteByNombreAndApellido(String nombre, String apellido);
    Estudiante findEstudianteByEmail(String email);
}
