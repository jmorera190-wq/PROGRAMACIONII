package com.laestrella.view;

import com.laestrella.datos.Repositorio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class PanelConsultas extends JPanel {
    private Repositorio repo;
    private JTable table;
    private DefaultTableModel model;

    public PanelConsultas(Repositorio repo) {
        this.repo = repo;
        setLayout(new BorderLayout());
        JPanel top = new JPanel();
        JButton btnPorServicio = new JButton("Producción por Servicio");
        JButton btnPorFuncionario = new JButton("Producción por Funcionario");
        JButton btnPorRango = new JButton("Producción por Rango (últimos 7 días)");

        top.add(btnPorServicio);
        top.add(btnPorFuncionario);
        top.add(btnPorRango);

        add(top, BorderLayout.NORTH);

        model = new DefaultTableModel();
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnPorServicio.addActionListener(e -> mostrarPorServicio());
        btnPorFuncionario.addActionListener(e -> mostrarPorFuncionario());
        btnPorRango.addActionListener(e -> mostrarPorRango());
    }

    private void mostrarPorServicio() {
        List<Object[]> datos = repo.produccionPorServicio();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Servicio","Cantidad","Total"});
        for (Object[] r : datos) model.addRow(r);
    }

    private void mostrarPorFuncionario() {
        List<Object[]> datos = repo.produccionPorFuncionario();
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Funcionario","Cantidad","Total"});
        for (Object[] r : datos) model.addRow(r);
    }

    private void mostrarPorRango() {
        LocalDate hasta = LocalDate.now();
        LocalDate desde = hasta.minusDays(7);
        List<Object[]> datos = repo.produccionPorRango(desde.toString(), hasta.toString());
        model.setRowCount(0);
        model.setColumnIdentifiers(new String[]{"Fecha","Cantidad","Total"});
        for (Object[] r : datos) model.addRow(r);
    }
}
