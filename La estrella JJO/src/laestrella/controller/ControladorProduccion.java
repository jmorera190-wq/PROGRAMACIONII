package com.laestrella.controller;

import com.laestrella.datos.Repositorio;

public class ControladorProduccion {
    private Repositorio repo;

    public ControladorProduccion(Repositorio repo){ this.repo = repo; }

    public void registrar(int servicioId, int funcionarioId, int tipoVehiculoId, String fecha) {
        double precio = repo.getServicios().stream().filter(s->s.getId()==servicioId).findFirst().map(s->s.getTarifa(tipoVehiculoId)).orElse(0.0);
        repo.registrarServicio(servicioId, funcionarioId, tipoVehiculoId, fecha, precio, "");
    }
}
