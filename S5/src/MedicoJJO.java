public class MedicoJJO extends PersonaJJO {
    private String especialidad;
    private String registro;

    public MedicoJJO(String nombres, String apellidos, String sexo, String direccion, String especialidad, String registro) {
        super(nombres, apellidos, sexo, direccion);
        this.especialidad = especialidad;
        this.registro = registro;
    }

    @Override
    public String tipoIdentificacion() {
        return "Registro médico: " + registro;
    }

    public String getRegistro() {
        return registro;
    }
}
