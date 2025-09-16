package Urbanbike;

public class B_electrica extends Bicicletajjo {
  private double capacidadBateria; // en Wh
  private double nivelCargaActualPorcentaje;
  private int potenciaMotorWatts;
  private boolean asistenciaPedaleo;
  private double velocidadMaximaKmH;
  private boolean gpsIntegrado;
  private boolean bluetoothDisponible;

  public B_electrica(String marca, String modelo, double pesoKg, String tipoCuadro,
                     int tamañoRuedaPulgadas, String numeroSerie, int idInternoSistema,
                     double capacidadBateria, double nivelCargaActualPorcentaje,
                     int potenciaMotorWatts, boolean asistenciaPedaleo,
                     double velocidadMaximaKmH, boolean gpsIntegrado,
                     boolean bluetoothDisponible) {
    super(marca, modelo, pesoKg, tipoCuadro, tamañoRuedaPulgadas, numeroSerie, idInternoSistema);
    this.capacidadBateria = capacidadBateria;
    this.nivelCargaActualPorcentaje = nivelCargaActualPorcentaje;
    this.potenciaMotorWatts = potenciaMotorWatts;
    this.asistenciaPedaleo = asistenciaPedaleo;
    this.velocidadMaximaKmH = velocidadMaximaKmH;
    this.gpsIntegrado = gpsIntegrado;
    this.bluetoothDisponible = bluetoothDisponible;
  }

  public double calcularAutonomiaRestanteKm() {

    return nivelCargaActualPorcentaje * (capacidadBateria / 100);
  }
}
