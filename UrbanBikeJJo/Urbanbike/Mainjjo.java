package Urbanbike;

import java.util.List;
import java.util.ArrayList;

public class Mainjjo {
  public static void main(String[] args) {
    List<Bicicletajjo> bicicletas = new ArrayList<>();

    B_electrica biciE = new B_electrica("Yamaha", "E-Rider", 25, "Aluminio", 27,
      "E12345", 1, 500, 80, 250, true, 45, true, true);

    B_mecanica biciM = new B_mecanica("Giant", "MTB", 12, "Carbono", 29,
      "M98765", 2, 18, "Disco", true);

    bicicletas.add(biciE);
    bicicletas.add(biciM);


    for (Bicicletajjo b : bicicletas) {
      b.mostrarEstado();
    }


    System.out.println("Autonomía restante bici eléctrica: " +
      biciE.calcularAutonomiaRestanteKm() + " km");


    biciM.descripcionTecnica();


    biciE.registrarMantenimiento();
    biciE.mostrarEstado();
  }
}
