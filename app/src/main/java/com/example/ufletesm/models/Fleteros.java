package com.example.ufletesm.models;

public class Fleteros {

    private String nombre;
    private String apellidom;
    private String apellidop;
    private String telefono;
    private String pathFoto_v;
    private String correo;
    private String password;

    public Fleteros(){}

    public Fleteros(String nombre, String apellidom, String apellidop, String telefono, String pathFoto_v, String correo, String password) {
        this.nombre = nombre;
        this.apellidom = apellidom;
        this.apellidop = apellidop;
        this.telefono = telefono;
        this.pathFoto_v = pathFoto_v;
        this.correo = correo;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidom() {
        return apellidom;
    }

    public void setApellidom(String apellidom) {
        this.apellidom = apellidom;
    }

    public String getApellidop() {
        return apellidop;
    }

    public void setApellidop(String apellidop) {
        this.apellidop = apellidop;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPathFoto_v() {
        return pathFoto_v;
    }

    public void setPathFoto_v(String pathFoto_v) {
        this.pathFoto_v = pathFoto_v;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
