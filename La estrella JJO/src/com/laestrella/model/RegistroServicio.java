package com.laestrella.model;

public class RegistroServicio {
    private int id;
    private int servicioId;
    private int funcionarioId;
    private int tipoVehiculoId;
    private String fecha; // yyyy-MM-dd
    private double precioCobrado;
    private String observaciones;

    public RegistroServicio(int id, int servicioId, int funcionarioId, int tipoVehiculoId, String fecha, double precioCobrado, String observaciones) {
        this.id = id;
        this.servicioId = servicioId;
        this.funcionarioId = funcionarioId;
        this.tipoVehiculoId = tipoVehiculoId;
        this.fecha = fecha;
        this.precioCobrado = precioCobrado;
        this.observaciones = observaciones;
    }

    public int getId(){ return id; }
    public int getServicioId(){ return servicioId; }
    public int getFuncionarioId(){ return funcionarioId; }
    public int getTipoVehiculoId(){ return tipoVehiculoId; }
    public String getFecha(){ return fecha; }
    public double getPrecioCobrado(){ return precioCobrado; }
    public String getObservaciones(){ return observaciones; }
}
