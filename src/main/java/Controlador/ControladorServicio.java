/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Servicio;
import Vista.ServicioMecanicos;
import excepciones.ExcepcioneServicio;
import Repositorio.RepositorioGenerico;
import Vista.VentanaVehiculo;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Home
 */
public class ControladorServicio {
    
    private ServicioMecanicos vista;
    private ArrayList<Servicio> lista = new ArrayList<>();
    private RepositorioGenerico<Servicio> repositorio =
            new RepositorioGenerico<>();
    
      public ControladorServicio(ServicioMecanicos vista) {
          
        this.vista = vista;
          
        vista.getBtnRegistrar().addActionListener(e -> registrar());
        this.vista.getBtnConsultar().addActionListener(e -> consultar());
      }
      
     public void registrar() {
         String codigo = vista.getTxtCodigo().getText().trim();
         String descripcion = vista.getTxtDescripcion().getText().trim();
         String precioTexto = vista.getTxtPrecioBase().getText().trim();
         String tiempoTexto = vista.getTxtTiempoEstimado().getText().trim();
         String cambioAceite = vista.getTxtCambioAceite().getText().trim();
         String revisionFrenos = vista.getTxtRevisionFrenos().getText().trim();
         String diagnosticoElectronico = vista.getTxtDiagnosticoElectrico().getText().trim();
         String alineamiento = vista.getTxtAlineamiento().getText().trim();
         String mantenimientoGeneral = vista.getTxtMantenimiento().getText().trim();

try {

    if (codigo.isEmpty() || descripcion.isEmpty()
            || precioTexto.isEmpty() || tiempoTexto.isEmpty()) {

        throw new ExcepcioneServicio(
                "Complete todos los campos.");
    }

    if (repositorio.buscar(codigo) != null) {
        throw new ExcepcioneServicio(
                "El código del servicio ya existe.");
    }

    double precio = Double.parseDouble(precioTexto);
    int tiempo = Integer.parseInt(tiempoTexto);


    if (precio <= 0) {
        throw new ExcepcioneServicio(
                "El precio debe ser mayor que cero.");
    }

    Servicio servicio = new Servicio(
            codigo,
            descripcion,
            precio,
            tiempo,
            cambioAceite,
            revisionFrenos,
            diagnosticoElectronico,
            alineamiento,
            mantenimientoGeneral
    );

    repositorio.agregar(codigo, servicio);

    JOptionPane.showMessageDialog(vista,
            "Servicio registrado correctamente.");

    limpiar();

} catch (ExcepcioneServicio e) {

    JOptionPane.showMessageDialog(vista,
            e.getMessage());

} catch (NumberFormatException e) {

    JOptionPane.showMessageDialog(vista,
            "El precio y el tiempo deben ser números.");
}
    }

    public void consultar() {
        String codigo = JOptionPane.showInputDialog(
            vista,
            "Ingrese el código del servicio que desea buscar:",
            "Consultar servicio",
            JOptionPane.QUESTION_MESSAGE
    );
        if (codigo == null) {
        return;
    }
        codigo = codigo.trim();
        
        Servicio servicio = repositorio.buscar(codigo);
        
         if (servicio == null) {
        JOptionPane.showMessageDialog(vista,
                "Servicio no encontrado.");
        return;
    }
         JOptionPane.showMessageDialog(vista,
                 "Código: " + servicio.getCodigo() +
            "\nDescripción: " + servicio.getDescripcion() +
            "\nPrecio Base: " + servicio.getPrecioBase() +
            "\nTiempo Estimado: " + servicio.getTiempoEstimado() +
            "\nCambio de Aceite: " + servicio.getCambioAceite() +
            "\nRevison de Frenos: " + servicio.getRevisionFrenos() +
            "\nDiagnóstico Electrónico: " + servicio.getDiagnosticoElectronico( )+
            "\nAlineamiento: " + servicio.getAlineamiento() +
            "\nMantenimiento General: " + servicio.getMantenimientoGeneral() ,
            "DATOS DEL SERVICIO ENCONTRADO",
            JOptionPane.INFORMATION_MESSAGE
    );

    vista.getTxtCodigo().setText(servicio.getCodigo());
    vista.getTxtDescripcion().setText(servicio.getDescripcion());
    vista.getTxtPrecioBase().setText(String.valueOf(servicio.getPrecioBase()));
    vista.getTxtTiempoEstimado().setText(String.valueOf(servicio.getTiempoEstimado()));
    vista.getTxtCambioAceite().setText(servicio.getCambioAceite());
    vista.getTxtRevisionFrenos().setText(servicio.getRevisionFrenos());
    vista.getTxtDiagnosticoElectrico().setText(servicio.getDiagnosticoElectronico());
    vista.getTxtAlineamiento().setText(servicio.getAlineamiento());
    vista.getTxtMantenimiento().setText(servicio.getMantenimientoGeneral());
    }
    
    public Servicio buscarServicio(String codigo) {

    if (codigo == null || codigo.trim().isEmpty()) {
        return null;
    }

    return repositorio.buscar(codigo.trim());
}
    private void limpiar() {

        vista.getTxtCodigo().setText("");
        vista.getTxtDescripcion().setText("");
        vista.getTxtPrecioBase().setText("");
        vista.getTxtTiempoEstimado().setText("");
        vista.getTxtCambioAceite().setText("");
        vista.getTxtRevisionFrenos().setText("");
        vista.getTxtDiagnosticoElectrico().setText("");
        vista.getTxtAlineamiento().setText("");
        vista.getTxtMantenimiento().setText("");
    }
}