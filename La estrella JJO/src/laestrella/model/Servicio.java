package com.laestrella.model;

public class Servicio {
    private int id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private double tarifaAutomovil;
    private double tarifaCamioneta;

    public Servicio(int id, String codigo, String nombre, String descripcion, double tarifaAutomovil, double tarifaCamioneta) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tarifaAutomovil = tarifaAutomovil;
        this.tarifaCamioneta = tarifaCamioneta;
    }

    public int getId(){ return id; }
    public String getCodigo(){ return codigo; }
    public String getNombre(){ return nombre; }
    public String getDescripcion(){ return descripcion; }
    public double getTarifa(int tipoVehiculoId){
        return tipoVehiculoId==1 ? tarifaAutomovil : tarifaCamioneta;
    }
    @Override public String toString(){ return nombre; }
}
