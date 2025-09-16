package Urbanbike;

import java.util.Date;

public abstract class Bicicletajjo{

  private String marca;
  private String modelo;
  private double pesoKg;
  private String tipoCuadro;
  private int tamañoRuedaPulgadas;
  private double kilometrajeTotalKm;
  private Date fechaUltimoMantenimiento;
  private boolean necesitaServicio;
  private String estadoActual; // disponible, en uso, en mantenimiento
  private String numeroSerie;
  private int idInternoSistema;
  private Date fechaRegistro;

  public Bicicletajjo(String marca, String modelo, double pesoKg, String tipoCuadro, int tamañoRuedaPulgadas, String numeroSerie, int idInternoSistema) {


  }

  public void Bicicleta(String marca, String modelo, double pesoKg, String tipoCuadro,
                        int tamañoRuedaPulgadas, String numeroSerie, int idInternoSistema) {
    this.marca = marca;
    this.modelo = modelo;
    this.pesoKg = pesoKg;
    this.tipoCuadro = tipoCuadro;
    this.tamañoRuedaPulgadas = tamañoRuedaPulgadas;
    this.numeroSerie = numeroSerie;
    this.idInternoSistema = idInternoSistema;
    this.fechaRegistro = new Date();
    this.estadoActual = "Disponible";
    this.necesitaServicio = false;
  }


  public void mostrarEstado() {
    System.out.println("Bicicleta " + numeroSerie + " - Estado actual: " + estadoActual);
  }

  public void registrarMantenimiento() {
    this.fechaUltimoMantenimiento = new Date();
    this.estadoActual = "Disponible";
    this.necesitaServicio = false;
    System.out.println("Mantenimiento registrado para bicicleta " + numeroSerie);
  }


  public String getEstadoActual() {
    return estadoActual;
  }

  public void setEstadoActual(String estadoActual) {
    this.estadoActual = estadoActual;
  }

  public String getNumeroSerie() {
    return numeroSerie;
  }
}
