package com.laestrella;

import com.laestrella.datos.Repositorio;
import com.laestrella.view.VentanaPrincipal;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        Repositorio repo = Repositorio.getInstance();
        repo.cargarDatosIniciales();


        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal(repo).setVisible(true);
        });
    }
}
