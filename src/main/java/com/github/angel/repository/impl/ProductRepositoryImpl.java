package com.github.angel.repository.impl;


import com.github.angel.entity.Categorie;
import com.github.angel.entity.Product;
import com.github.angel.exception.DatabaseConnectionException;
import com.github.angel.repository.ProductRepository;
import com.github.angel.utils.ConnectionDB;
import jakarta.validation.constraints.Min;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    private final Connection connectionDB = ConnectionDB.getInstance().getConnection();
    private final String SQL_SELECT_ALL = "SELECT p.*, c.nombre as categoria FROM product as p INNER join categories as  c ON (p.categories_id = c.id) ";
    private final String SQL_INSERT = "INSERT INTO product (nombre, descripcion, precio, fecha, categories_id, codigo) VALUES (?, ?, ?, ?, ?, ?)";
    private final String SQL_UPDATE = "UPDATE product SET nombre = ?, descripcion = ?, precio = ?, fecha = ?, categories_id = ? WHERE id = ?";
    private final String SQL_FIND_BY_ID = "SELECT p.*, c.nombre as categoria FROM product as p INNER join categories as c ON (p.categories_id = c.id) WHERE p.id = ?";
    private final String SQL_DELETE_BY_PRODUCT = "DELETE FROM product as p WHERE p.id = ?";


    private static @NotNull Product resultSetToProduct(@NotNull ResultSet resultSet) throws SQLException {
        Product product = new Product();
        Categorie categorie = new Categorie();
        categorie.setId(resultSet.getLong("id"));
        categorie.setName(resultSet.getString("nombre"));
        product.setId(resultSet.getLong("id"));
        product.setNombre(resultSet.getString("nombre"));
        product.setPrecio(resultSet.getDouble("precio"));
        product.setDescripcion(resultSet.getString("descripcion"));
        product.setFecha(resultSet.getDate("fecha"));
        product.setCodigo(resultSet.getString("codigo"));
        product.setCategorie(categorie);
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            assert connectionDB != null;
            try (Statement statement = connectionDB.createStatement()) {
                ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
                while (resultSet.next()) {
                    Product product = resultSetToProduct(resultSet);
                    products.add(product);
                }
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error al conectar a la base de datos", e);
        }
        return products;
    }

    @Override
    public Product findById(@NotNull @Min(1) Long id) {
        Product product = new Product();
        try {
            assert connectionDB != null;
            try (PreparedStatement statement = connectionDB.prepareStatement(SQL_FIND_BY_ID)) {
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    product = resultSetToProduct(resultSet);
                }
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error al conectar a la base de datos", e);
        }

        return product;
    }

    @Override
    public void save(@NotNull Product entity) {
        try {
            assert connectionDB != null;
            try (PreparedStatement statement = connectionDB.prepareStatement(SQL_INSERT)) {
                statement.setString(1, entity.nombre());
                statement.setString(2, entity.descripcion());
                statement.setDouble(3, entity.precio());
                statement.setDate(4, new Date(entity.date().getTime()));
                statement.setLong(5, entity.getCategorie().getId());
                statement.setString(6, entity.getCodigo());
                statement.executeUpdate();
                close(statement);
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error al conectar a la base de datos", e);
        }

    }

    @Override
    public void delete(@NotNull Product entity) {

        try {
            assert connectionDB != null;
            try (PreparedStatement statement = connectionDB.prepareStatement(SQL_DELETE_BY_PRODUCT)) {
                statement.setLong(1, entity.id());
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("No se pudo eliminar el producto con id: " + entity.id());
                }
                close(statement);
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error al conectar a la base de datos", e);
        }

    }

    @Override
    public void update(@NotNull Product entity, @Min(1) Long id) {
        try {
            assert connectionDB != null;
            try (PreparedStatement statement = connectionDB.prepareStatement(SQL_UPDATE)) {
                statement.setLong(1, id);
                statement.setString(2, entity.nombre());
                statement.setString(3, entity.descripcion());
                statement.setDouble(4, entity.precio());
                statement.setDate(5, new Date(entity.date().getTime()));
                statement.setLong(6, entity.getCategorie().getId());
                statement.executeUpdate();

                close(statement);
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error al conectar a la base de datos", e);
        }
    }


    @Override
    public boolean deleteById(@Min(1) Long id) {
        try {
            assert connectionDB != null;
            try (PreparedStatement statement = connectionDB.prepareStatement(SQL_DELETE_BY_PRODUCT)) {
                statement.setLong(1, id);
                int affectedRows = statement.executeUpdate();
                return affectedRows > 0;
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error al conectar a la base de datos", e);
        }
    }

    private void close(@NotNull Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error al cerrar la conexión", e);
        }
    }


}
