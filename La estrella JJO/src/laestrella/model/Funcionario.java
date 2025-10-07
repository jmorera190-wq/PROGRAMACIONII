package com.laestrella.model;

public class Funcionario {
    private int id;
    private String nombre;
    public Funcionario(int id, String nombre){ this.id = id; this.nombre = nombre; }
    public int getId(){ return id; }
    public String getNombre(){ return nombre; }
    @Override public String toString(){ return nombre; }
}
