public class Vehiculo {
    private String marca;
    private String linea;
    private int modelo;
    private double valorComercial;
    private boolean servicioPublico;

    public Vehiculo(String marca, String linea, int modelo, double valorComercial, boolean servicioPublico) {
        this.marca = marca;
        this.linea = linea;
        this.modelo = modelo;
        this.valorComercial = valorComercial;
        this.servicioPublico = servicioPublico;
    }

    public double getValorComercial() {
        return valorComercial;
    }

    public boolean isServicioPublico() {
        return servicioPublico;
    }

    public String getMarca() { return marca; }
    public String getLinea() { return linea; }
    public int getModelo() { return modelo; }
}
