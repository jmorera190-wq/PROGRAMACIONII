public class Main {
    public static void main(String[] args) {

        PacienteJJo p1 = new PacienteJJo("Andrés", "Gómez", "M", "Calle 1", "P001");
        PacienteJJo p2 = new PacienteJJo("Laura", "Pérez", "F", "Calle 2", "P002");

        MedicoJJO m1 = new MedicoJJO("Carlos", "Ramírez", "M", "Cra 10", "General", "REG100");
        MedicoJJO m2 = new MedicoJJO("María", "López", "F", "Cra 20", "Urgencias", "REG200");


        RegistroAdmJJO manager = new RegistroAdmJJO();


        ConsultaExternaJJO c1 = new ConsultaExternaJJO(p1, m1, "Dolor de cabeza", 30.0);
        UrgenciasJJO u1 = new UrgenciasJJO(p2, m2, "Accidente", 100.0, 3);
        VacunacionJJO v1 = new VacunacionJJO(p1, m1, "COVID", 0.0);

        manager.registrarAtencion(c1);
        manager.registrarAtencion(u1);
        manager.registrarAtencion(v1);

     
        manager.buscarPorPaciente("P001");
        manager.buscarPorMedico("REG200");
    }
}
