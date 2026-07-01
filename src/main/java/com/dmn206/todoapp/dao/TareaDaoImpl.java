package com.dmn206.todoapp.dao;

import com.dmn206.todoapp.model.Prioridad;
import com.dmn206.todoapp.model.Tarea;
import com.dmn206.todoapp.util.ConexionBD;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        String sql = "SELECT * FROM tareas;";
        List<Tarea> tareas = new ArrayList<>();

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)){

            // ResultSet es un objeto que representa el conjunto de resultados de una consulta SQL en una base de datos
            ResultSet rs = preparedStatement.executeQuery();

            /*
            rs.next() -> Mueve el cursor una fila hacia abajo y devuelve:
            true → si ha llegado a una fila con datos.
            false → si ya no hay más filas.
            */

            while(rs.next()){
                int id = rs.getInt("id");
                String titulo= rs.getString("titulo");
                String contenido= rs.getString("contenido");
                boolean completada = rs.getInt("completada") == 1;
                LocalDateTime fechaCreacion = LocalDateTime.parse(rs.getString("fecha_creacion"));
                Prioridad prioridad = Prioridad.valueOf(rs.getString("prioridad"));

                Tarea tarea = new Tarea(id, titulo, contenido, completada, fechaCreacion, prioridad);
                tareas.add(tarea);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tareas;
    }

    @Override
    public Tarea obtenerTarea(int id) {
        String sql = "SELECT * FROM tareas WHERE id = ?;";

        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                int idObtenido = rs.getInt("id");
                String titulo= rs.getString("titulo");
                String contenido= rs.getString("contenido");
                boolean completada = rs.getInt("completada") == 1;
                LocalDateTime fechaCreacion = LocalDateTime.parse(rs.getString("fecha_creacion"));
                Prioridad prioridad = Prioridad.valueOf(rs.getString("prioridad"));

                Tarea tarea = new Tarea(idObtenido, titulo, contenido, completada, fechaCreacion, prioridad);
                return tarea;
            }

        } catch (SQLException e) {
            e.printStackTrace();

            }
        return null;
    }

    @Override
    public void eliminar(int id) {

    }

    @Override
    public void actualizar(Tarea tarea) {

    }
}
