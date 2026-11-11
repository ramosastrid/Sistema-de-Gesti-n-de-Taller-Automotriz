/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Cliente;
import Modelo.Vehiculo;
import Vista.VentanaVehiculo;
import excepciones.ExcepcionVehiculo;
import excepciones.ExcepcionesCliente;
import Repositorio.RepositorioGenerico;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Home
 */
public class ControladorVehiculo {
  
  
    private VentanaVehiculo vista;
    private ArrayList<Vehiculo> lista = new ArrayList<>();
    private RepositorioGenerico<Vehiculo> repositorio =
            new RepositorioGenerico<>();

    public ControladorVehiculo(VentanaVehiculo vista) {

        this.vista = vista;

        vista.getBtnRegistrar().addActionListener(e -> registrar());
        this.vista.getBtnConsultar().addActionListener(e -> consultar());
        this.vista.getBtnListar().addActionListener(e -> listar());
    }
    
    private void registrar() {

    
    try {

        String placa = vista.getTxtPlaca().getText().trim();
        String marca = vista.getTxtMarca().getText().trim();
        String modelo = vista.getTxtModelo().getText().trim();
        String yearTexto = vista.getTxtAño().getText().trim();
        String color = vista.getTxtColor().getText().trim();
        String codigo = vista.getTxtCodigoCliente().getText().trim();

        if (placa.isEmpty() || marca.isEmpty()
                || modelo.isEmpty() || yearTexto.isEmpty()
                || color.isEmpty() || codigo.isEmpty()) {

            throw new ExcepcionVehiculo("Complete todos los campos.");
        }

        if (repositorio.buscar(placa.toUpperCase()) != null) {
            throw new ExcepcionVehiculo("La placa ya existe.");
        }

       int year = Integer.parseInt(yearTexto);

        Cliente propietario = new Cliente(
                codigo, "", "", "", "");

        Vehiculo vehiculo = new Vehiculo(
                placa.toUpperCase(), marca, modelo,
                year, color, propietario);

        repositorio.agregar(placa.toUpperCase(), vehiculo);

        JOptionPane.showMessageDialog(vista,
                "Vehículo registrado correctamente.");

        limpiar();

    } catch (ExcepcionVehiculo e) {

        JOptionPane.showMessageDialog(
                vista, e.getMessage(),
                "Error de registro",
                JOptionPane.ERROR_MESSAGE);

     }
    }
    private void consultar() {
    String placa = JOptionPane.showInputDialog(
            vista,
            "Ingrese la placa del vehículo que desea buscar:",
            "Consultar vehículo",
            JOptionPane.QUESTION_MESSAGE
    );
    if (placa == null) {
        return;
    }

    placa = placa.trim();
    
    if (placa.isEmpty()) {
        JOptionPane.showMessageDialog(vista,
                "Ingrese una placa.");
        return;
    }
    Vehiculo vehiculo = repositorio.buscar(placa);

    if (vehiculo == null) {
        JOptionPane.showMessageDialog(vista,
                "Vehículo no encontrado.");
        return;
    }
    vista.getTxtPlaca().setText(vehiculo.getPlaca());
    vista.getTxtMarca().setText(vehiculo.getMarca());
    vista.getTxtModelo().setText(vehiculo.getModelo());
    vista.getTxtAño().setText(String.valueOf(vehiculo.getAño()));
    vista.getTxtColor().setText(vehiculo.getColor());

    vista.getTxtCodigoCliente().setText(
            vehiculo.getPropietario().getCodigo());
    
    JOptionPane.showMessageDialog(vista,
            "VEHÍCULO ENCONTRADO\n\n"
            + "Placa: " + vehiculo.getPlaca()
            + "\nMarca: " + vehiculo.getMarca()
            + "\nModelo: " + vehiculo.getModelo()
            + "\nAño: " + vehiculo.getAño()
            + "\nColor: " + vehiculo.getColor()
            + "\nCódigo del propietario: "
            + vehiculo.getPropietario().getCodigo(),
            "Resultado de búsqueda",
            JOptionPane.INFORMATION_MESSAGE);
    }

      private void listar() {
    if(repositorio.obtenerTodos().isEmpty()){
            JOptionPane.showMessageDialog(vista, "No hay vehículos registrado. ");
            return;
        }
        String mensaje = "";
        
      JTextArea area = new JTextArea(20,20);
      area.setEditable(false);
        
        for (Vehiculo vehiculo : repositorio.obtenerTodos()){
            mensaje +="Placa: " + vehiculo.getPlaca() + 
                    "\nMarca: " + vehiculo.getMarca() +
                    "\nModelo: " + vehiculo.getModelo() +
                    "\nAño: " + vehiculo.getAño() + 
                    "\nColor: " + vehiculo.getColor() +
                    "\nCodigo Cliente: " + vehiculo.getPropietario().getCodigo()+
                    "\n--------------------------\n";
            
           JScrollPane srcoll = new JScrollPane(area);
                   
        }
        JOptionPane.showMessageDialog(vista,
                mensaje, 
                "Vehículos Registrado",
                JOptionPane.INFORMATION_MESSAGE);
    }

      public Vehiculo buscarVehiculo(String placa) {

    if (placa == null || placa.trim().isEmpty()) {
        return null;
    }

    return repositorio.buscar(placa.trim().toUpperCase());
}
public ArrayList<Vehiculo> getListaVehiculos() {

    return new ArrayList<>(repositorio.obtenerTodos());
}

    private void limpiar() {

        vista.getTxtPlaca().setText("");
        vista.getTxtMarca().setText("");
        vista.getTxtModelo().setText("");
        vista.getTxtAño().setText("");
        vista.getTxtColor().setText("");
        vista.getTxtCodigoCliente().setText("");
    }
}
