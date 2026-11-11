/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.OrdenTrabajo;
import Modelo.Cliente;
import Modelo.Vehiculo;
import Modelo.Repuesto;
import Modelo.Servicio;
import Hilos.ProcesoServicioHilo;
import Controlador.ControladorCliente;
import Controlador.ControladorVehiculo;
import Vista.VentanaOrdenesTrabajo;
import Repositorio.RepositorioGenerico;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.time.LocalDate;
/**
 *
 * @author Home
 */
public class ControladorOrdenTrabajo {
    
    private VentanaOrdenesTrabajo vista;
    private ArrayList<OrdenTrabajo> lista = new ArrayList<>();
    private RepositorioGenerico<OrdenTrabajo> repositorio =
            new RepositorioGenerico<>();
    
    private Thread hiloProceso;
    private ProcesoServicioHilo procesoActual;
    private boolean procesando = false;
    private JProgressBar barraProgreso;
    private JLabel lbl_Estado;
    private JDialog dialogoProceso;
    private boolean inventarioDescontado = false;
    private ControladorCliente controladorCliente;
    private ControladorVehiculo controladorVehiculo;
    private ControladorInventario controladorInventario;
    private ControladorServicio controladorServicio;
    
    
     public ControladorOrdenTrabajo(VentanaOrdenesTrabajo vista,ControladorCliente controladorCliente,ControladorVehiculo controladorVehiculo, ControladorInventario controladorInventario,ControladorServicio controladorServicio) {
          
        this.vista = vista;
        this.controladorCliente = controladorCliente;
        this.controladorVehiculo = controladorVehiculo;
        this.controladorInventario = controladorInventario;
        this.controladorServicio = controladorServicio;
          
        
    this.vista.getBtnProcesarOrden().addActionListener(e -> procesarOrden());
    this.vista.getBtnRegistrar().addActionListener(e ->registrar());
    this.vista.getBtnListar().addActionListener(e -> listar());
    this.vista.getBtnConsultar().addActionListener(e -> consultar());
    
   
      }

    public String obtenerFecha() {
        return LocalDate.now().toString();
    }
    private void registrar() {

        try {

            String numero =
                    vista.getTxtNumeroUnico().getText().trim();

            String diagnostico =
                    vista.getTxtDiagnostico().getText().trim();

            String mecanico =
                    vista.getTxtMecanico().getText().trim();

            String servicio =
                    vista.getTxtServicio().getText().trim();

            String nombreCliente =
                    vista.getTxtCliente().getText().trim();

            String nombreVehiculo =
                    vista.getTxtVehiculo().getText().trim();

            String nombreRepuesto =
                    vista.getTxtRepuesto().getText().trim();

            if (numero.isEmpty()
                    || diagnostico.isEmpty()
                    || mecanico.isEmpty()
                    || servicio.isEmpty()
                    || nombreCliente.isEmpty()
                    || nombreVehiculo.isEmpty()) {

                JOptionPane.showMessageDialog(
                        vista,
                        "Complete todos los campos obligatorios."
                );

                return;
            }

            if (buscarOrden(numero) != null) {

                JOptionPane.showMessageDialog(
                        vista,
                        "El número de orden ya existe."
                );

                return;
            }


           Cliente cliente =
        controladorCliente.buscarCliente(nombreCliente);

if (cliente == null) {

    JOptionPane.showMessageDialog(vista,
            "No existe un cliente con ese código.");

    return;
}

Vehiculo vehiculo =
        controladorVehiculo.buscarVehiculo(nombreVehiculo);

if (vehiculo == null) {

    JOptionPane.showMessageDialog(vista,
            "No existe un vehículo con esa placa.");

    return;
}

if (vehiculo.getPropietario() == null
        || !vehiculo.getPropietario().getCodigo()
                .equalsIgnoreCase(cliente.getCodigo())) {

    JOptionPane.showMessageDialog(vista,
            "El vehículo no pertenece al cliente indicado.");

    return;
}

            Repuesto repuesto = null;

            int cantidad = 0;

            double costoRepuesto = 0;

            if (!nombreRepuesto.isEmpty()) {

         repuesto = controladorInventario.buscarRepuesto(nombreRepuesto);

    if (repuesto == null) {

        JOptionPane.showMessageDialog(vista,
                "No existe ese repuesto en el inventario.");

        return;
    }

    String textoCantidad = JOptionPane.showInputDialog(
            vista,
            "Ingrese la cantidad de repuestos que necesita:"
    );

    if (textoCantidad == null) {
        return;
    }

    cantidad = Integer.parseInt(textoCantidad.trim());

    if (cantidad <= 0) {

        JOptionPane.showMessageDialog(vista,
                "La cantidad debe ser mayor que cero.");

        return;
    }

    if (repuesto.getCantidad() < cantidad) {

        JOptionPane.showMessageDialog(vista,
                "No hay suficientes unidades disponibles.");

        return;
    }

    costoRepuesto = repuesto.getPrecioUnitario() * cantidad;
}
             Servicio servicioSeleccionado =
        controladorServicio.buscarServicio(servicio);

      double costoServicio = servicioSeleccionado.getPrecioBase();

             double Repuesto = 0;

if (repuesto != null && cantidad > 0) {
    costoRepuesto =
            repuesto.getPrecioUnitario() * cantidad;
}

double total = costoServicio + costoRepuesto;


            OrdenTrabajo nuevaOrden = new OrdenTrabajo(
                    numero,
                    obtenerFecha(),
                    cliente,
                    vehiculo,
                    diagnostico,
                    mecanico,
                    servicio,
                    repuesto,
                    cantidad,
                    total,
                    "Pendiente"
            );

            lista.add(nuevaOrden);

            JOptionPane.showMessageDialog(
                    vista,
                    "Orden registrada correctamente.\n"
                    + "Número: " + numero
                    + "\nFecha: " + obtenerFecha()
                    + "\nTotal: L " + total
                    + "\nEstado: Pendiente"
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Ingrese correctamente los costos."
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Error al registrar: " + ex.getMessage()
            );
        }
    }

    private void consultar() {

        String numero =
                vista.getTxtNumeroUnico().getText().trim();

        if (numero.isEmpty()) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Ingrese el número de orden."
            );

            return;
        }

        OrdenTrabajo orden = buscarOrden(numero);

        if (orden == null) {

            JOptionPane.showMessageDialog(
                    vista,
                    "No se encontró la orden."
            );

            return;
        }

        vista.getTxtNumeroUnico().setText(
                orden.getNumeroUnico()
        );

        vista.getTxtDiagnostico().setText(
                orden.getDiagnostico()
        );

        vista.getTxtMecanico().setText(
                orden.getMecanico()
        );

        vista.getTxtServicio().setText(
                orden.getServicio()
        );

        vista.getTxtCliente().setText(
                String.valueOf(orden.getCliente())
        );

        vista.getTxtVehiculo().setText(
                String.valueOf(orden.getVehiculo())
        );

        vista.getTxtRepuesto().setText(
                orden.getRepuesto() == null
                ? ""
                : String.valueOf(orden.getRepuesto())
        );

        vista.getTxtCostoTotal().setText(
                String.valueOf(orden.getCostoTotal())
        );

        JOptionPane.showMessageDialog(
                vista,
                "Orden encontrada.\n"
                + "Fecha: " + orden.getFecha()
                + "\nEstado: " + orden.getEstado()
                + "\nTotal: L " + orden.getCostoTotal()
        );
    }


    private void listar() {

        if (lista.isEmpty()) {

            JOptionPane.showMessageDialog(
                    vista,
                    "No hay órdenes registradas."
            );

            return;
        }

        StringBuilder datos = new StringBuilder();

        for (OrdenTrabajo orden : lista) {

            datos.append("Número: ")
                    .append(orden.getNumeroUnico())
                    .append("\nFecha: ")
                    .append(orden.getFecha())
                    .append("\nCliente: ")
                    .append(orden.getCliente())
                    .append("\nVehículo: ")
                    .append(orden.getVehiculo())
                    .append("\nServicio: ")
                    .append(orden.getServicio())
                    .append("\nEstado: ")
                    .append(orden.getEstado())
                    .append("\nTotal: L ")
                    .append(orden.getCostoTotal())
                    .append("\n----------------------\n");
        }

        JTextArea area = new JTextArea(datos.toString());

        area.setEditable(false);

        JScrollPane scroll = new JScrollPane(area);

        scroll.setPreferredSize(new Dimension(450, 350));

        JOptionPane.showMessageDialog(
                vista,
                scroll,
                "Lista de órdenes",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public OrdenTrabajo buscarOrden(String numero) {

        for (OrdenTrabajo orden : lista) {

            if (orden.getNumeroUnico().equals(numero)) {

                return orden;
            }
        }

        return null;
    }
    private void procesarOrden() {

        if (procesando) {
            return;
        }

        String numero =
                vista.getTxtNumeroUnico().getText().trim();

        OrdenTrabajo orden = buscarOrden(numero);

        if (orden == null) {

            JOptionPane.showMessageDialog(
                    vista,
                    "Primero registre o busque una orden."
            );

            return;
        }

        if (orden.getEstado().equals("Finalizada")
                || orden.getEstado().equals("Cancelada")) {

            JOptionPane.showMessageDialog(
                    vista,
                    "La orden ya está cerrada."
            );

            return;
        }

        procesando = true;

        vista.getBtnProcesarOrden().setEnabled(false);

        barraProgreso = new JProgressBar(0, 100);

        barraProgreso.setStringPainted(true);

        lbl_Estado = new JLabel("Iniciando procesamiento...");

        JPanel panel = new JPanel(new BorderLayout(10, 10));

        panel.setPreferredSize(new Dimension(350, 90));

        panel.add(lbl_Estado, BorderLayout.NORTH);

        panel.add(barraProgreso, BorderLayout.CENTER);

        dialogoProceso = new JDialog(
                (Frame) null,
                "Procesamiento de orden",
                false
        );

        dialogoProceso.setDefaultCloseOperation(
                JDialog.DO_NOTHING_ON_CLOSE
        );

        dialogoProceso.add(panel);

        dialogoProceso.pack();

        dialogoProceso.setLocationRelativeTo(vista);

        dialogoProceso.setVisible(true);

        procesoActual = new ProcesoServicioHilo(
                new ProcesoServicioHilo.ProcesoListener() {

            @Override
            public void onInicio() {

                SwingUtilities.invokeLater(() ->
                        lbl_Estado.setText(
                                "Iniciando orden..."
                        )
                );
            }

            @Override
            public void onPendiente() {

                SwingUtilities.invokeLater(() ->
                        lbl_Estado.setText(
                                "Orden pendiente..."
                        )
                );
            }

            @Override
            public void onEnProceso(int progreso) {

                SwingUtilities.invokeLater(() -> {

                    barraProgreso.setValue(progreso);

                    lbl_Estado.setText(
                            "Procesando orden... "
                            + progreso + "%"
                    );
                });
            }

            @Override
            public void onFinalizada() {

                SwingUtilities.invokeLater(() -> {

                    boolean resultado =
                            finalizarOrden(orden);

                    if (resultado) {

                        barraProgreso.setValue(100);

                        lbl_Estado.setText(
                                "Orden finalizada correctamente."
                        );

                    } else {

                        lbl_Estado.setText(
                                "No se pudo finalizar la orden."
                        );
                    }

                    terminarProceso();
                });
            }

            @Override
            public void onCancelada() {

                SwingUtilities.invokeLater(() -> {

                    lbl_Estado.setText(
                            "Procesamiento cancelado."
                    );

                    terminarProceso();
                });
            }
        });

        hiloProceso = new Thread(procesoActual);

        hiloProceso.start();
    }

    private void terminarProceso() {

        procesando = false;

        vista.getBtnProcesarOrden().setEnabled(true);

        if (dialogoProceso != null) {

            dialogoProceso.dispose();
        }
    }

    public boolean finalizarOrden(OrdenTrabajo orden) {

        if (orden == null) {
            return false;
        }

        if (orden.getEstado().equals("Finalizada")
                || orden.getEstado().equals("Cancelada")) {

            return false;
        }

        if (!orden.isInventarioDescontado()) {

            Repuesto repuesto = orden.getRepuesto();

            int cantidad = orden.getCantidadRepuesto();

            if (repuesto != null && cantidad > 0) {

                if (!repuesto.descontarCantidad(cantidad)) {

                    JOptionPane.showMessageDialog(
                            vista,
                            "No hay suficientes repuestos."
                    );

                    return false;
                }
            }

            orden.setInventarioDescontado(true);
        }

        orden.setEstado("Finalizada");

        inventarioDescontado = true;

        JOptionPane.showMessageDialog(
                vista,
                "Orden finalizada correctamente.\n"
                + "Inventario actualizado.\n"
                + "Total: L " + orden.getCostoTotal()
        );

        return true;
    }
    public ArrayList<OrdenTrabajo> getListaOrdenes() {

        return lista;
    }

    public boolean isInventarioDescontado() {

        return inventarioDescontado;
    }

    public void setInventarioDescontado(
            boolean inventarioDescontado) {

        this.inventarioDescontado = inventarioDescontado;
    }
}
