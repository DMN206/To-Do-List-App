package com.dmn206.todoapp.util;

import java.sql.*;

public class ConexionBD {

    // Atributo de tipo ConexionBD porque guarda la instancia de la base de datos en el atributo
    // es static porque pertenece a la clase, no a un Objeto

    // Atributo tipo Connection: representa la sesión de comunicación activa con la base de datos

    private static ConexionBD instancia; // Instancia Singletone
    private Connection conexion;
    private static final String URL = "jdbc:sqlite:todoapp.db";

    private ConexionBD() {
            try {
                conexion = DriverManager.getConnection(URL);
                inicializarBD();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    // Statement en Java es una interfaz de la API que envía comandos SQL estáticos a la base de datos y devuelve los resultados

    public void inicializarBD(){

        //Declaramos dentro del paréntesis, ya que Java lo cierra automáticamente al terminar el bloque

        try (Statement statement = conexion.createStatement()){
            String sql = "CREATE TABLE IF NOT EXISTS tareas(\n" +
                    "    id              INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    titulo          TEXT NOT NULL,\n" +
                    "    contenido       TEXT NULL,\n" +
                    "    completada      INTEGER DEFAULT 0,\n" +
                    "    fecha_creacion  TEXT NOT NULL,\n" +
                    "    prioridad       TEXT NOT NULL\n" +
                    "\n" +
                    "\n" +
                    ");";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static ConexionBD getInstance(){

        if (instancia == null){
            instancia = new ConexionBD();

            }
            return instancia;
        }

    public Connection getConexion(){
        return conexion;
    }


}
