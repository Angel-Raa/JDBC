package com.github.angel.service;

import com.github.angel.entity.Estudiante;

import java.sql.Date;
import java.util.List;

public interface EstudianteService {
    List<Estudiante> getAllEstudiantes();
    void addEstudiante(Estudiante estudiante);
    Estudiante getEstudianteById(Long id);
    void updateEstudiante(Estudiante estudiante, Long id);
    void deleteEstudianteById(Long id);

    Estudiante getEstudianteByEmail(String email);
    List<Estudiante> getEstudiantesByGenero(String genero);
    Estudiante getEstudianteByTelefono(String telefono);
    Estudiante getOrderFechaNacimiento(Date date);
    Estudiante getEstudianteByNombreAndApellido(String nombre, String apellido);

}
