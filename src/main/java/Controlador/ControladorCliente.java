/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Cliente;
import Vista.VentanaCliente;
import excepciones.ExcepcionesCliente;
import Repositorio.RepositorioGenerico;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Home
 */
public class ControladorCliente {
   
  private VentanaCliente vista;

    private ArrayList<Cliente> lista = new ArrayList<>();
    private RepositorioGenerico<Cliente> repositorio =
        new RepositorioGenerico<>();

    public ControladorCliente(VentanaCliente vista) {

        this.vista = vista;
        this.repositorio = repositorio;
        this.vista.getBtnRegistrar().addActionListener(e -> registrar());
        this.vista.getBtnConsultar().addActionListener(e -> consultar());
        this.vista.getBtnModificar().addActionListener(e -> modificar());
        this.vista.getBtnEliminar().addActionListener(e -> eliminar());
        
        this.vista.getBtnActualizar().addActionListener(e -> guardarModificacion());
    }

    private void registrar() {
       
      try {   
    String codigo = vista.getTxtCodigo().getText().trim();
    String nombre = vista.getTxtNombre().getText().trim();
    String identidad = vista.getTxtIdentidad().getText().trim();
    String telefono = vista.getTxtTelefono().getText().trim();
    String direccion = vista.getTxtDireccion().getText().trim();


        if (codigo.isEmpty() || nombre.isEmpty()
                || identidad.isEmpty() || telefono.isEmpty()
                || direccion.isEmpty()) {

            throw new ExcepcionesCliente("Complete todos los campos.");
        }

        if (repositorio.buscar(codigo) != null) {
            throw new ExcepcionesCliente("El código ya existe.");
        }

        Cliente cliente = new Cliente(
                codigo, nombre, identidad, telefono, direccion);

        repositorio.agregar(codigo, cliente);

        JOptionPane.showMessageDialog(vista,
                "Cliente registrado correctamente.");
          limpiar();

    } catch (ExcepcionesCliente e) {

        JOptionPane.showMessageDialog(
                vista,
                e.getMessage(),
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
 
    }
   private void consultar() {
       String codigo = JOptionPane.showInputDialog(
            vista,
            "Ingrese el código del cliente que desea buscar:",
            "Consultar cliente",
            JOptionPane.QUESTION_MESSAGE
    );
       if (codigo == null) {
        return;
    }
       codigo = codigo.trim();
       
       if (codigo.isEmpty()) {
        JOptionPane.showMessageDialog(vista,
                "Ingrese el código del cliente.");
        return;
    }

    Cliente c = repositorio.buscar(codigo);

    if (c == null) {
        JOptionPane.showMessageDialog(vista,
                "Cliente no encontrado.");
        return;
    }

    JOptionPane.showMessageDialog(vista,
            "Código: " + c.getCodigo() +
            "\nNombre: " + c.getNombreCompleto() +
            "\nIdentidad: " + c.getIdentidad() +
            "\nTeléfono: " + c.getTelefono() +
            "\nDirección: " + c.getDireccion(),
            "DATOS DEL CLIENTE ENCONTRADO",
            JOptionPane.INFORMATION_MESSAGE
    );

    vista.getTxtCodigo().setText(c.getCodigo());
    vista.getTxtNombre().setText(c.getNombreCompleto());
    vista.getTxtIdentidad().setText(c.getIdentidad());
    vista.getTxtTelefono().setText(c.getTelefono());
    vista.getTxtDireccion().setText(c.getDireccion());
    }
    
    private void modificar() { 
        try { String codigo = JOptionPane.showInputDialog(
                vista,
                "Ingrese el código del cliente:",
                "Modificar cliente",
                JOptionPane.QUESTION_MESSAGE
        );
        if (codigo == null) {
            return;
        }
        codigo = codigo.trim();
        if (codigo.isEmpty()) {
            throw new ExcepcionesCliente("Ingrese el código.");
        }Cliente c = repositorio.buscar(codigo);
        if (c == null) {throw new ExcepcionesCliente("Cliente no encontrado.");
        }

        vista.getTxtCodigo().setText(c.getCodigo());
        vista.getTxtNombre().setText(c.getNombreCompleto());
        vista.getTxtIdentidad().setText(c.getIdentidad());
        vista.getTxtTelefono().setText(c.getTelefono());
        vista.getTxtDireccion().setText(c.getDireccion());

        vista.getTxtCodigo().setEditable(false);
        vista.getTxtNombre().setEditable(true);
        vista.getTxtIdentidad().setEditable(true);
        vista.getTxtTelefono().setEditable(true);
        vista.getTxtDireccion().setEditable(true);
        
        JOptionPane.showMessageDialog(vista,
                "Modifique los datos en pantalla y presione Actualizar para guardar.");

    } catch (ExcepcionesCliente e) {

        JOptionPane.showMessageDialog(vista, e.getMessage());
    }
}
    private void eliminar() {

      try {
        String codigo = JOptionPane.showInputDialog(
                vista,
                "Ingrese el código del cliente que desea eliminar:",
                "Eliminar cliente",
                JOptionPane.QUESTION_MESSAGE
        );
        if (codigo == null) {
            return;
        }

        codigo = codigo.trim();

        if (codigo.isEmpty()) {
            throw new ExcepcionesCliente(
                    "Ingrese el código del cliente.");
        }

        Cliente cliente = repositorio.buscar(codigo);

        if (cliente == null) {
            throw new ExcepcionesCliente(
                    "Cliente no encontrado.");
        }
        int respuesta = JOptionPane.showConfirmDialog(
                vista,
                "¿Desea eliminar este cliente?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {

            repositorio.eliminar(codigo);

            JOptionPane.showMessageDialog(vista,
                    "Cliente eliminado correctamente.");

            limpiar();
        }

    } catch (ExcepcionesCliente e) {

        JOptionPane.showMessageDialog(vista,
                e.getMessage());
    }
}
    
    private void guardarModificacion() {

    try {

        String codigo = vista.getTxtCodigo().getText().trim();

        Cliente c = repositorio.buscar(codigo);

        if (c == null) {
            throw new ExcepcionesCliente("Cliente no encontrado.");
        }

        String nombre = vista.getTxtNombre().getText().trim();
        String identidad = vista.getTxtIdentidad().getText().trim();
        String telefono = vista.getTxtTelefono().getText().trim();
        String direccion = vista.getTxtDireccion().getText().trim();

        if (nombre.isEmpty() || identidad.isEmpty()
                || telefono.isEmpty() || direccion.isEmpty()) {

            JOptionPane.showMessageDialog(vista,
                    "Complete todos los campos.");
            return;
        }

        c.setNombreCompleto(nombre);
        c.setIdentidad(identidad);
        c.setTelefono(telefono);
        c.setDireccion(direccion);

       repositorio.modificar(c.getCodigo(), c);

        JOptionPane.showMessageDialog(vista,
                "Cliente modificado correctamente.");
       limpiar();
       
       vista.getTxtCodigo().setEditable(true);
       
    } catch (ExcepcionesCliente e) {

        JOptionPane.showMessageDialog(vista, e.getMessage());
    }
}
    
    public Cliente buscarCliente(String codigo) {

    if (codigo == null || codigo.trim().isEmpty()) {
        return null;
    }

    return repositorio.buscar(codigo.trim());
}    
   
    private void limpiar() {

    vista.getTxtCodigo().setText("");
    vista.getTxtNombre().setText("");
    vista.getTxtIdentidad().setText("");
    vista.getTxtTelefono().setText("");
    vista.getTxtDireccion().setText("");
}
}