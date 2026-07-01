package com.dmn206.todoapp.dao;

import com.dmn206.todoapp.model.Tarea;
import com.dmn206.todoapp.util.ConexionBD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TareaDaoImpl implements TareaDAO{

    private Connection conexion;

    public TareaDaoImpl(){
        this.conexion = ConexionBD.getInstance().getConexion();
    }

    @Override
    public void insertar(Tarea tarea) {
        String sql = "INSERT INTO tareas (titulo, contenido, completada, fecha_creacion, prioridad) VALUES (?, ?, ?, ?, ?);";
        //Usamos PreparedStatement ya que en el SQL los valores de la tabla con la opción Insertar varían
        //Se usa Statement cuando los valores del SQL son fijos

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)){
            preparedStatement.setString(1, tarea.getTitulo());
            preparedStatement.setString(2, tarea.getContenido());
            preparedStatement.setInt(3, tarea.isCompletado() ? 1 : 0); //if tarea == completado => 1; else => 0
            preparedStatement.setString(4, tarea.getFecha_creacion().toString());
            preparedStatement.setString(5, tarea.getPrioridad().name());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Tarea> obtenerTodas() {
        return List.of();
    }

    @Override
    public Tarea obtenerTarea(int id) {
        return null;
    }

    @Override
    public void eliminar(int id) {

    }

    @Override
    public void actualizar(Tarea tarea) {

    }
}
