package com.dmn206.todoapp.dao;
import com.dmn206.todoapp.model.Tarea;

import java.util.List;

public interface TareaDAO {

    void insertar(Tarea tarea);

    List<Tarea> obtenerTodas();

    Tarea obtenerTarea(int id);

    void eliminar(int id);

    void actualizar(Tarea tarea);
}
