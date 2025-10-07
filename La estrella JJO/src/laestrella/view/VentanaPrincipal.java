package com.laestrella.view;

import com.laestrella.datos.Repositorio;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private Repositorio repo;

    public VentanaPrincipal(Repositorio repo) {
        this.repo = repo;
        setTitle("La Estrella - Gestión de Producción (In-Memory)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Registro", new PanelRegistroServicio(repo));
        tabs.addTab("Consultas", new PanelConsultas(repo));
        add(tabs, BorderLayout.CENTER);
    }
}
