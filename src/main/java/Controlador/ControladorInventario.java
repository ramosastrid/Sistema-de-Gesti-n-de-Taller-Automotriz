/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Repuesto;
import Modelo.Vehiculo;
import Vista.VentanaInventario;
import Repositorio.RepositorioGenerico;
import excepciones.ExcepcionInventario;
import excepciones.ExcepcioneServicio;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Home
 */
public class ControladorInventario {
    
    private VentanaInventario vista;
    private ArrayList<Repuesto> lista = new ArrayList<>();
    private RepositorioGenerico<Repuesto> repositorio =
            new RepositorioGenerico<>();
    
     public  ControladorInventario(VentanaInventario vista) {
          this.vista = vista;
          
        vista.getBtnRegistrar().addActionListener(e -> registrar());
        this.vista.getBtnConsular().addActionListener(e-> consultar());
        this.vista.getBtnDescuento().addActionListener(e-> descuento());
       
     }
     
      public void registrar(){
         
    try {
        String codigo = vista.getTxtCodigoRepuesto().getText().trim();
        String nombre = vista.getTxtNombre().getText().trim();
        String cantidadTexto = vista.getTxtCantidad().getText().trim();
        String stockTexto = vista.getTxtStockMinimo().getText().trim();
        String precioTexto = vista.getTxtPrecioUnitario().getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty()
                || cantidadTexto.isEmpty() || stockTexto.isEmpty()
                || precioTexto.isEmpty()) {

            throw new ExcepcionInventario("Complete todos los campos.");
        }

        if (repositorio.buscar(codigo) != null) {
            throw new ExcepcionInventario(
                    "El código del repuesto ya existe.");
        }

        int cantidad = Integer.parseInt(cantidadTexto);
        int stock = Integer.parseInt(stockTexto);
        int precioUnitario = Integer.parseInt(precioTexto);

        if (cantidad < 0) {
            throw new ExcepcionInventario(
                    "La cantidad no puede ser negativa.");
        }

        if (stock < 0) {
            throw new ExcepcionInventario(
                    "El stock mínimo no puede ser negativo.");
        }

       if (precioUnitario <= 0) {
    throw new ExcepcionInventario(
            "El precio debe ser mayor que cero.");
     }

        Repuesto repuesto = new Repuesto(
                codigo,
                nombre,
                cantidad,
                precioUnitario,
                stock
        );

        repositorio.agregar(codigo, repuesto);

        JOptionPane.showMessageDialog(vista,
                "Repuesto registrado correctamente.");

        limpiar();

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(vista,
                "Ingrese cantidades y precios válidos.");

    } catch (ExcepcionInventario e) {

        JOptionPane.showMessageDialog(vista,
                e.getMessage());
    }
      }
      
      public void consultar(){
          if (repositorio.obtenerTodos().isEmpty()) {

        JOptionPane.showMessageDialog(vista,
                "No hay Inventario registrados.");
        return;
    }

    Object[][] datos = new Object[repositorio.obtenerTodos().size()][5];

    int fila = 0;

    for (Repuesto repuesto : repositorio.obtenerTodos()) {

        datos[fila][0] = repuesto.getCodigo();
        datos[fila][1] = repuesto.getNombre();
        datos[fila][2] = repuesto.getCantidad();
        datos[fila][3] = repuesto.getStock();
        datos[fila][4] = repuesto.getPrecioUnitario();

        fila++;
    }

    String[] columnas = {
        "Codigo",
        "Nombre",
        "Cantidad",
        "Stock",
        "PrecioUnitario",
    };

    JTable tabla = new JTable(datos, columnas);

    tabla.setEnabled(false);
    tabla.setRowHeight(25);

    JScrollPane scroll = new JScrollPane(tabla);
    scroll.setPreferredSize(new Dimension(700, 250));

    JOptionPane.showMessageDialog(
            vista,
            scroll,
            "VEHÍCULOS REGISTRADOS",
            JOptionPane.INFORMATION_MESSAGE
    );
      }
      
      public void descuento(){
              String codigo = JOptionPane.showInputDialog("Ingrese codigo");
              Repuesto repuesto = repositorio.buscar(codigo);
              
              if(repuesto == null){
                  JOptionPane.showMessageDialog(vista, "Repuesto no encontrado");
                  return;
              }
              
              int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad a descontar: "));
              
              if(cantidad <=0 || cantidad > repuesto.getCantidad()){
                  JOptionPane.showMessageDialog(vista,"Cantidad invalidad");
                  return;
              }
              
              repuesto.setCantidad(repuesto.getCantidad()-cantidad);
              
              int descuento = cantidad * repuesto.getPrecioUnitario();
              
                JOptionPane.showMessageDialog(vista,
            "Descuento: L. " + descuento
            + "\nCantidad restante: " + repuesto.getCantidad());
      } 
          public Repuesto buscarRepuesto(String dato) {

    if (dato == null || dato.trim().isEmpty()) {
        return null;
    }

    String busqueda = dato.trim();

    for (Repuesto repuesto : repositorio.obtenerTodos()) {

        if (repuesto.getCodigo().equalsIgnoreCase(busqueda)
                || repuesto.getNombre().equalsIgnoreCase(busqueda)) {

            return repuesto;
        }
    }

    return null;
}
      
       private void limpiar() {

        vista.getTxtCodigoRepuesto().setText("");
        vista.getTxtNombre().setText("");
        vista.getTxtCantidad().setText("");
        vista.getTxtStockMinimo().setText("");
        vista.getTxtPrecioUnitario().setText("");
    }
}
