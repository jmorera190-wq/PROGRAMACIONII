public class Main {
    public static void main(String[] args) {

        Repositorio repo = new Repositorio();
        repo.agregarVehiculo(new Vehiculo("Ford", "Taurus", 2018, 50000000, false));
        repo.agregarVehiculo(new Vehiculo("Chevrolet", "Spark", 2020, 30000000, true));

        Vehiculo v = repo.buscar("Ford", "Taurus", 2018);

        if (v != null) {

            ImpuestoVehicular impuesto = new ImpuestoVehicular(v, 10, 15, 200000);


            boolean aplicaProntoPago = true;
            boolean aplicaTraslado = true;

            System.out.println(impuesto.getReporte(aplicaProntoPago, aplicaTraslado));
        } else {
            System.out.println("Vehículo no encontrado.");
        }


        javax.swing.SwingUtilities.invokeLater(() -> {
            InterfazImpuestos ventana = new InterfazImpuestos();
            ventana.setVisible(true);
        });
    }
}
