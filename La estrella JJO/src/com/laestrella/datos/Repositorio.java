package com.laestrella.datos;

import com.laestrella.model.Funcionario;
import com.laestrella.model.Servicio;
import com.laestrella.model.TipoVehiculo;
import com.laestrella.model.RegistroServicio;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class Repositorio {
    private static Repositorio instance = null;

    private List<Servicio> servicios = new ArrayList<>();
    private List<Funcionario> funcionarios = new ArrayList<>();
    private List<TipoVehiculo> tiposVehiculo = new ArrayList<>();
    private List<RegistroServicio> registros = new ArrayList<>();
    private int nextRegistroId = 1;

    private Repositorio(){}

    public static Repositorio getInstance() {
        if (instance == null) instance = new Repositorio();
        return instance;
    }

    public void cargarDatosIniciales() {
        if (!servicios.isEmpty()) return; // ya cargado

        servicios.add(new Servicio(1, "LB", "Lavado Básico", "Lavado exterior, interior y aspirada", 15000, 20000));
        servicios.add(new Servicio(2, "LE", "Lavado Especial", "Lavado + polichado y ceras", 30000, 40000));
        servicios.add(new Servicio(3, "DB", "Desinfección Básica", "Ozono", 25000, 35000));
        servicios.add(new Servicio(4, "DA", "Desinfección Avanzada", "Ozono + vapor", 30000, 45000));
        servicios.add(new Servicio(5, "C1", "Combo 1", "Lavado + polichado + desengrasante", 40000, 55000));
        servicios.add(new Servicio(6, "C2", "Combo 2", "Combo1 + grafitado de chasis", 50000, 65000));
        servicios.add(new Servicio(7, "C3", "Combo 3", "Combo2 + tapicería", 65000, 80000));

        funcionarios.add(new Funcionario(1, "Juan Ochoa"));
        funcionarios.add(new Funcionario(2, "Diego Garzon"));
        funcionarios.add(new Funcionario(3, "Lucia Paez"));

        tiposVehiculo.add(new TipoVehiculo(1, "Automóvil"));
        tiposVehiculo.add(new TipoVehiculo(2, "Camioneta"));
    }

    // getters
    public List<Servicio> getServicios(){ return servicios; }
    public List<Funcionario> getFuncionarios(){ return funcionarios; }
    public List<TipoVehiculo> getTiposVehiculo(){ return tiposVehiculo; }

    public void registrarServicio(int servicioId, int funcionarioId, int tipoVehiculoId, String fecha, double precio, String obs){
        RegistroServicio r = new RegistroServicio(nextRegistroId++, servicioId, funcionarioId, tipoVehiculoId, fecha, precio, obs);
        registros.add(r);
    }

    public List<RegistroServicio> getRegistros(){ return registros; }

    // reportes
    public List<Object[]> produccionPorServicio(){
        return servicios.stream().map(s -> {
            long cantidad = registros.stream().filter(r -> r.getServicioId()==s.getId()).count();
            double total = registros.stream().filter(r -> r.getServicioId()==s.getId()).mapToDouble(r->r.getPrecioCobrado()).sum();
            return new Object[]{s.getNombre(), cantidad, total};
        }).collect(Collectors.toList());
    }

    public List<Object[]> produccionPorFuncionario(){
        return funcionarios.stream().map(f -> {
            long cantidad = registros.stream().filter(r -> r.getFuncionarioId()==f.getId()).count();
            double total = registros.stream().filter(r -> r.getFuncionarioId()==f.getId()).mapToDouble(r->r.getPrecioCobrado()).sum();
            return new Object[]{f.getNombre(), cantidad, total};
        }).collect(Collectors.toList());
    }

    public List<Object[]> produccionPorRango(String desde, String hasta){
        LocalDate d = LocalDate.parse(desde);
        LocalDate h = LocalDate.parse(hasta);
        return registros.stream()
                .filter(r -> {
                    LocalDate f = LocalDate.parse(r.getFecha());
                    return (f.isEqual(d) || f.isAfter(d)) && (f.isEqual(h) || f.isBefore(h));
                })
                .collect(java.util.stream.Collectors.groupingBy(RegistroServicio::getFecha,
                        java.util.stream.Collectors.summingDouble(RegistroServicio::getPrecioCobrado)))
                .entrySet().stream()
                .map(e -> new Object[]{e.getKey(), registros.stream().filter(r->r.getFecha().equals(e.getKey())).count(), e.getValue()})
                .collect(Collectors.toList());
    }
}
