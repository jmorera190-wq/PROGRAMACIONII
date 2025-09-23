public class PacienteJJo extends PersonaJJO {
    private String codigo;

    public PacienteJJo(String nombres, String apellidos, String sexo, String direccion, String codigo) {
        super(nombres, apellidos, sexo, direccion);
        this.codigo = codigo;
    }

    @Override
    public String tipoIdentificacion() {
        return "Código paciente: " + codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
