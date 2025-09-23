public abstract class PersonaJJO {
        protected String nombres;
        protected String apellidos;
        protected String sexo;
        protected String direccion;

        public PersonaJJO(String nombres, String apellidos, String sexo, String direccion) {
            this.nombres = nombres;
            this.apellidos = apellidos;
            this.sexo = sexo;
            this.direccion = direccion;
        }

        public String nombreCompleto() {
            return apellidos + " " + nombres;
        }

        // método abstracto (cada clase hija debe implementarlo)
        public abstract String tipoIdentificacion();
    }


