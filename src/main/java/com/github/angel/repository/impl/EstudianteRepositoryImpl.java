package com.github.angel.repository.impl;

import com.github.angel.entity.Estudiante;
import com.github.angel.exception.DatabaseConnectionException;
import com.github.angel.repository.EstudianteRepository;
import com.github.angel.utils.ConnectionDB;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteRepositoryImpl implements EstudianteRepository {
    private static final Connection connection = ConnectionDB.getInstance().getConnection();
    private static final String SQL_FIND_ALL = "SELECT * FROM estudiante";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM estudiante WHERE id = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM estudiante WHERE id = ?";
    private static final String SQL_UPDATE_BY_ID = "UPDATE estudiante SET nombre = ?, apellido = ?, genero = ?, email = ?, telefono = ?, direccion = ?, fecha_nacimiento = ?, ciudad = ? WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO estudiante (nombre, apellido, genero, email, telefono, direccion, fecha_nacimiento, ciudad) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private @NotNull Estudiante resultSetToEstudiante(@NotNull ResultSet resultSet) throws SQLException {
        Estudiante estudiante = new Estudiante();
        estudiante.setId(resultSet.getLong("id"));
        estudiante.setNombre(resultSet.getString("nombre"));
        estudiante.setApellido(resultSet.getString("apellido"));
        estudiante.setGenero(resultSet.getString("genero"));
        estudiante.setEmail(resultSet.getString("email"));
        estudiante.setTelefono(resultSet.getString("telefono"));
        estudiante.setDireccion(resultSet.getString("direccion"));
        estudiante.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
        estudiante.setCiudad(resultSet.getString("ciudad"));
        return estudiante;
    }

    @Override
    public List<Estudiante> findAll() {
        List<Estudiante> estudiantes = new ArrayList<>();
        try {
            assert connection != null;
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);
                while (resultSet.next()) {
                    Estudiante estudiante = resultSetToEstudiante(resultSet);
                    estudiantes.add(estudiante);
                }
                resultSet.close();
                return estudiantes;
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error al conectar a la base de datos", e);
        }
    }

    @Override
    public Estudiante findById(@Max(1) Long id) {
        try {
            assert connection != null;
            try(PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSetToEstudiante(resultSet);
                }
                resultSet.close();
            }

        }catch (SQLException e) {
            throw new DatabaseConnectionException("Error al conectar a la base de datos", e);
        }



        return null;
    }

    @Override
    public void save(@Valid @NotNull Estudiante entity) {
        try {
            assert connection != null;
            try(PreparedStatement statement = connection.prepareStatement(SQL_INSERT)){
                statement.setString(1, entity.getNombre());
                statement.setString(2, entity.getApellido());
                statement.setString(3, entity.getGenero());
                statement.setString(4, entity.getEmail());
                statement.setString(5, entity.getTelefono());
                statement.setString(6, entity.getDireccion());
                statement.setDate(7, new Date(entity.getFechaNacimiento().getTime()));
                statement.setString(8, entity.getCiudad());
                statement.executeUpdate();
            }
        }catch (SQLException e){
            throw new DatabaseConnectionException("Error al conectar a la base de datos", e);
        }
    }

    @Override
    public void delete(Estudiante entity) {

    }

    @Override
    public void update(Estudiante entity, Long id) {

    }

    @Override
    public boolean deleteById(Long id) {
    return true;
    }

    @Override
    public Estudiante findEstudianteByTelefono(String telefono) {
        return null;
    }

    @Override
    public Estudiante findEstudianteByNombre(String nombre) {
        return null;
    }

    @Override
    public Estudiante findEstudianteByNombreAndApellido(String nombre, String apellido) {
        return null;
    }

    @Override
    public Estudiante findEstudianteByEmail(String email) {
        return null;
    }
}
