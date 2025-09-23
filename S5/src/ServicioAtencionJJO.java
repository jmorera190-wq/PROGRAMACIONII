import java.time.LocalDateTime;

public abstract class ServicioAtencionJJO {
    protected PacienteJJo paciente;
    protected MedicoJJO medico;
    protected String motivo;
    protected LocalDateTime fecha;

    public ServicioAtencionJJO(PacienteJJo paciente, MedicoJJO medico, String motivo) {
        this.paciente = paciente;
        this.medico = medico;
        this.motivo = motivo;
        this.fecha = LocalDateTime.now();
    }


    public abstract double calcularCosto();

    public abstract String descripcion();
}
