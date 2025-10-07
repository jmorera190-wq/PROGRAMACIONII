package com.laestrella.model;

public class TipoVehiculo {
    private int id;
    private String nombre;
    public TipoVehiculo(int id, String nombre){ this.id = id; this.nombre = nombre; }
    public int getId(){ return id; }
    public String getNombre(){ return nombre; }
    @Override public String toString(){ return nombre; }
}
