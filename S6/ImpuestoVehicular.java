import java.text.NumberFormat;
import java.util.Locale;

public class ImpuestoVehicular {
    private Vehiculo vehiculo;
    private double descProntoPago;
    private double descTraslado;
    private double descServicioPublico;

    public ImpuestoVehicular(Vehiculo vehiculo, double descProntoPago, double descTraslado, double descServicioPublico) {
        this.vehiculo = vehiculo;
        this.descProntoPago = descProntoPago;
        this.descTraslado = descTraslado;
        this.descServicioPublico = descServicioPublico;
    }

    public double calcularPago(boolean aplicaProntoPago, boolean aplicaTraslado) {
        double valor = vehiculo.getValorComercial();


        if (aplicaProntoPago) {
            valor -= valor * (descProntoPago / 100);
        }

        if (vehiculo.isServicioPublico()) {
            valor -= descServicioPublico;
        }

        if (aplicaTraslado) {
            valor -= valor * (descTraslado / 100);
        }

        return valor;
    }

    public String getReporte(boolean aplicaProntoPago, boolean aplicaTraslado) {
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

        return "Impuesto base: " + formatoMoneda.format(vehiculo.getValorComercial()) + "\n" +
"Pago final: " + formatoMoneda.format(calcularPago(aplicaProntoPago, aplicaTraslado));
    }
}
