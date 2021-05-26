package com.emergentes.modelo;

public class Permisos {
    private int id;
    private int id_usuario;
    private int id_rol;
    private String usuario;
    private String descripcion;

    public Permisos() {
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public int getId() {
        return id;
    }
    public int getId_usuario() {
        return id_usuario;
    }
    public int getId_rol() {
        return id_rol;
    }
    public String getUsuario() {
        return usuario;
    }
    public String getDescripcion() {
        return descripcion;
    }    
}
