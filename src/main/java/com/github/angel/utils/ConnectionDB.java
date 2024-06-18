package com.github.angel.utils;

import com.github.angel.exception.DatabaseConnectionException;
import io.github.cdimascio.dotenv.Dotenv;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final Dotenv DOTENV = Dotenv.load();
    private static final String URL = "jdbc:postgresql://localhost:5432/" + DOTENV.get("POSTGRES_DB");
    private static final String USER = DOTENV.get("POSTGRES_USER");
    private static final String PASSWORD = DOTENV.get("POSTGRES_PASSWORD");
    private static ConnectionDB instance;

    private ConnectionDB() {}

    public static synchronized ConnectionDB getInstance() {
        if (instance == null) {
            instance = new ConnectionDB();
        }
        return instance;
    }

    @Contract(pure = true)
    public @Nullable Connection getConnection() throws DatabaseConnectionException {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error al conectar a la base de datos", e);

        }
        return connection;
    }

}
