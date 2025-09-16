package Urbanbike;

public class B_mecanica extends Bicicletajjo {
  private int numeroVelocidades;
  private String tipoFrenos;
  private boolean tienePortaequipaje;

  public B_mecanica(String marca, String modelo, double pesoKg, String tipoCuadro,
                           int tamañoRuedaPulgadas, String numeroSerie, int idInternoSistema,
                           int numeroVelocidades, String tipoFrenos, boolean tienePortaequipaje) {
    super(marca, modelo, pesoKg, tipoCuadro, tamañoRuedaPulgadas, numeroSerie, idInternoSistema);
    this.numeroVelocidades = numeroVelocidades;
    this.tipoFrenos = tipoFrenos;
    this.tienePortaequipaje = tienePortaequipaje;
  }

  public void descripcionTecnica() {
    System.out.println("Bicicleta Mecánica - Velocidades: " + numeroVelocidades +
      ", Frenos: " + tipoFrenos + ", Portaequipaje: " + (tienePortaequipaje ? "Sí" : "No"));
  }
}
