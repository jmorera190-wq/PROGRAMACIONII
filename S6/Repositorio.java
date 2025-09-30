import java.util.ArrayList;
import java.util.List;

public class Repositorio {
    private List<Vehiculo> vehiculos;

    public Repositorio() {
        vehiculos = new ArrayList<>();
    }

    public void agregarVehiculo(Vehiculo v) {
        vehiculos.add(v);
    }

    public Vehiculo buscar(String marca, String linea, int modelo) {
        for (Vehiculo v : vehiculos) {
            if (v.getMarca().equalsIgnoreCase(marca) &&
                v.getLinea().equalsIgnoreCase(linea) &&
                v.getModelo() == modelo) {
                return v;
            }
        }
        return null;
    }
}
