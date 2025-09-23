public class UrgenciasJJO extends ServicioAtencionJJO {
    private double tarifaBase;
    private int horasObservacion;

    public UrgenciasJJO(PacienteJJo paciente, MedicoJJO medico, String motivo, double tarifaBase, int horasObservacion) {
        super(paciente, medico, motivo);
        this.tarifaBase = tarifaBase;
        this.horasObservacion = horasObservacion;
    }

    @Override
    public double calcularCosto() {
        return tarifaBase + (horasObservacion * 20);
    }

    @Override
    public String descripcion() {
        return "Urgencias";
    }
}
