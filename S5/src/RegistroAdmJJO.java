import java.util.ArrayList;

public class RegistroAdmJJO {
    private ArrayList<ServicioAtencionJJO> servicios = new ArrayList<>();

    public void registrarAtencion(ServicioAtencionJJO servicio) {
        servicios.add(servicio);
        System.out.println("Se registró una atención: " + servicio.descripcion() +
                " | Costo: " + servicio.calcularCosto());
    }

    public void buscarPorPaciente(String codigo) {
        System.out.println("Atenciones del paciente " + codigo + ":");
        for (ServicioAtencionJJO s : servicios) {
            if (s.paciente.getCodigo().equals(codigo)) {
                System.out.println(" - " + s.descripcion() + " (" + s.motivo + ")");
            }
        }
    }

    public void buscarPorMedico(String registro) {
        System.out.println("Atenciones del médico " + registro + ":");
        for (ServicioAtencionJJO s : servicios) {
            if (s.medico.getRegistro().equals(registro)) {
                System.out.println(" - " + s.descripcion() + " (" + s.motivo + ")");
            }
        }
    }
}