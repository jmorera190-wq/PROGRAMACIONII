public class VacunacionJJO extends ServicioAtencionJJO {
    private double precioVacuna;

    public VacunacionJJO(PacienteJJo paciente, MedicoJJO medico, String motivo, double precioVacuna) {
        super(paciente, medico, motivo);
        this.precioVacuna = precioVacuna;
    }

    @Override
    public double calcularCosto() {
        return precioVacuna;
    }

    @Override
    public String descripcion() {
        return "Vacunación";
    }
}