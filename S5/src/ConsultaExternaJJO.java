public class ConsultaExternaJJO extends ServicioAtencionJJO {
    private double tarifa;

    public ConsultaExternaJJO(PacienteJJo paciente, MedicoJJO medico, String motivo, double tarifa) {
        super(paciente, medico, motivo);
        this.tarifa = tarifa;
    }

    @Override
    public double calcularCosto() {
        return tarifa;
    }

    @Override
    public String descripcion() {
        return "Consulta Externa";
    }
}
