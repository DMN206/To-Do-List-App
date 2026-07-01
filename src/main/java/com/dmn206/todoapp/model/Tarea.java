package com.dmn206.todoapp.model;
import java.time.LocalDateTime;

public class Tarea {
    private int id;
    private String contenido;
    private String titulo;
    private boolean completado;
    private LocalDateTime fecha_creacion;
    private Prioridad prioridad;

    // CONSTRUCTOR CON ID PARA QUE CUANDO MySQL LEA LA TABLA DE TAREA EL ID EXISTA, HAGA UN SELECT ID FROM TAREA

    public Tarea(int id, String titulo, String contenido, boolean completado, LocalDateTime fecha_creacion, Prioridad prioridad){
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.completado = completado;
        this.fecha_creacion = fecha_creacion;
        this.prioridad = prioridad;
    }

    // CONSTRUCTOR SIN ID YA QUE CUANDO EL USUARIO CREE UNA TAREA NUEVA EL USUARIO NO INTRODUCIRÁ NINGÚN ID

    public Tarea(String titulo, String contenido, boolean completado, LocalDateTime fecha_creacion, Prioridad prioridad){
        this.titulo = titulo;
        this.contenido = contenido;
        this.completado = completado;
        this.fecha_creacion = fecha_creacion;
        this.prioridad = prioridad;
    }


    public int getId() {
        return id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }
}
