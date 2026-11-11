/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Home
 */
public class Controladores {
  private VentanaPrincipal vistaPrincipal;
  private VentanaCliente vistaCliente;
  private VentanaVehiculo vistaVehiculo;
  private ServicioMecanicos vistaServicioMecanicos;
  private VentanaOrdenesTrabajo vistaOrdenesTrabajo;
  private VentanaInventario vistaInventario;
  
  private ControladorCliente controladorCliente;
  private ControladorVehiculo controladorVehiculo;
  private ControladorServicio controladorServicio;
  private ControladorInventario controladorInventario;
  private ControladorOrdenTrabajo controladorOrdenTrabajo;
 
    
    public Controladores(VentanaPrincipal vistaPrincipal) {
         
        this.vistaPrincipal = vistaPrincipal;
        
        this.vistaCliente = new VentanaCliente();
        this.vistaVehiculo = new VentanaVehiculo();
        this.vistaServicioMecanicos = new ServicioMecanicos();
        this.vistaInventario = new VentanaInventario();
        this.vistaOrdenesTrabajo = new VentanaOrdenesTrabajo();
                
        this.vistaCliente.setLocationRelativeTo(vistaPrincipal);
        this.vistaVehiculo.setLocationRelativeTo(vistaPrincipal); 
        this.vistaServicioMecanicos.setLocationRelativeTo(vistaPrincipal);
        this.vistaInventario.setLocationRelativeTo(vistaPrincipal);
        this.vistaOrdenesTrabajo.setLocationRelativeTo(vistaPrincipal);
        
        controladorCliente = new ControladorCliente(vistaCliente);
        controladorVehiculo = new ControladorVehiculo(vistaVehiculo);
        controladorServicio = new ControladorServicio(vistaServicioMecanicos);
        controladorInventario = new ControladorInventario(vistaInventario);
        controladorOrdenTrabajo = new ControladorOrdenTrabajo(vistaOrdenesTrabajo,controladorCliente,
                controladorVehiculo,controladorInventario,controladorServicio);
        
        conectarNavegacion();
    }
    
    private void conectarNavegacion() {
        this.vistaPrincipal.getBtnCliente().addActionListener(e -> {
            this.vistaPrincipal.setVisible(false);
            this.vistaCliente.setVisible(true);
        });
        
        this.vistaPrincipal.getBtnVehiculo().addActionListener(e -> {
            this.vistaPrincipal.setVisible(false);
            this.vistaVehiculo.setVisible(true);
        });
        
         this.vistaPrincipal.getBtnServicio().addActionListener(e -> {
            this.vistaPrincipal.setVisible(false);
            this.vistaServicioMecanicos.setVisible(true);
        });
         
          this.vistaPrincipal.getBtnRepuestos().addActionListener(e -> {
            this.vistaPrincipal.setVisible(false);
            this.vistaInventario.setVisible(true);
        });
          
           this.vistaPrincipal.getBtnRepuestos().addActionListener(e -> {
           this.vistaPrincipal.setVisible(false);
           this.vistaInventario.setVisible(true);
        });
           this.vistaPrincipal.getBtnOrdenes().addActionListener(e -> {
           this.vistaPrincipal.setVisible(false);
           this.vistaOrdenesTrabajo.setVisible(true);
        });
          
          
        this.vistaCliente.getBtnVolver().addActionListener(e -> {
            this.vistaCliente.setVisible(false);
            this.vistaPrincipal.setVisible(true);
        
    });
         this.vistaVehiculo.getBtnVolver().addActionListener(e -> {
            this.vistaVehiculo.setVisible(false);
            this.vistaPrincipal.setVisible(true);
        
    });
         
          this.vistaServicioMecanicos.getBtnVolver().addActionListener(e -> {
            this.vistaServicioMecanicos.setVisible(false);
            this.vistaPrincipal.setVisible(true);
        
    });
           this.vistaInventario.getBtnVolver().addActionListener(e -> {
            this.vistaInventario.setVisible(false);
            this.vistaPrincipal.setVisible(true);
        
    });
           
           this.vistaOrdenesTrabajo.getBtnVolver().addActionListener(e -> {
            this.vistaOrdenesTrabajo.setVisible(false);
            this.vistaPrincipal.setVisible(true);
        
    });
    }
}
    
