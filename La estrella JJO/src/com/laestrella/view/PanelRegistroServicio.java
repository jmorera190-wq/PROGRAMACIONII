package com.laestrella.view;

import com.laestrella.controller.ControladorProduccion;
import com.laestrella.datos.Repositorio;
import com.laestrella.model.Servicio;
import com.laestrella.model.Funcionario;
import com.laestrella.model.TipoVehiculo;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class PanelRegistroServicio extends JPanel {
    private JComboBox<Servicio> cbServicio;
    private JComboBox<Funcionario> cbFuncionario;
    private JComboBox<TipoVehiculo> cbTipoVehiculo;
    private JTextField tfFecha;
    private JButton btnRegistrar;
    private JLabel lblPrecio;
    private ControladorProduccion controlador;

    public PanelRegistroServicio(Repositorio repo) {
        this.controlador = new ControladorProduccion(repo);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.fill = GridBagConstraints.HORIZONTAL;

        cbServicio = new JComboBox<>(repo.getServicios().toArray(new Servicio[0]));
        cbFuncionario = new JComboBox<>(repo.getFuncionarios().toArray(new Funcionario[0]));
        cbTipoVehiculo = new JComboBox<>(repo.getTiposVehiculo().toArray(new TipoVehiculo[0]));
        tfFecha = new JTextField(LocalDate.now().toString());
        btnRegistrar = new JButton("Registrar Servicio");
        lblPrecio = new JLabel("Precio: $0.0");

        c.gridx = 0; c.gridy = 0; add(new JLabel("Servicio:"), c);
        c.gridx = 1; add(cbServicio, c);
        c.gridx = 0; c.gridy = 1; add(new JLabel("Funcionario:"), c);
        c.gridx = 1; add(cbFuncionario, c);
        c.gridx = 0; c.gridy = 2; add(new JLabel("Tipo Vehículo:"), c);
        c.gridx = 1; add(cbTipoVehiculo, c);
        c.gridx = 0; c.gridy = 3; add(new JLabel("Fecha (YYYY-MM-DD):"), c);
        c.gridx = 1; add(tfFecha, c);
        c.gridx = 1; c.gridy = 4; add(lblPrecio, c);
        c.gridx = 1; c.gridy = 5; add(btnRegistrar, c);

        cbServicio.addActionListener(e -> actualizarPrecio(repo));
        cbTipoVehiculo.addActionListener(e -> actualizarPrecio(repo));
        btnRegistrar.addActionListener(e -> registrar());

        actualizarPrecio(repo);
    }

    private void actualizarPrecio(Repositorio repo) {
        Servicio s = (Servicio) cbServicio.getSelectedItem();
        TipoVehiculo t = (TipoVehiculo) cbTipoVehiculo.getSelectedItem();
        if (s!=null && t!=null) lblPrecio.setText("Precio: $" + s.getTarifa(t.getId()));
    }

    private void registrar() {
        try {
            Servicio s = (Servicio) cbServicio.getSelectedItem();
            Funcionario f = (Funcionario) cbFuncionario.getSelectedItem();
            TipoVehiculo t = (TipoVehiculo) cbTipoVehiculo.getSelectedItem();
            String fecha = tfFecha.getText().trim();
            if (s==null || f==null || t==null) { JOptionPane.showMessageDialog(this, "Seleccione todos los campos"); return; }
            controlador.registrar(s.getId(), f.getId(), t.getId(), fecha);
            JOptionPane.showMessageDialog(this, "Servicio registrado: " + s.getNombre());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al registrar: " + ex.getMessage());
        }
    }
}
