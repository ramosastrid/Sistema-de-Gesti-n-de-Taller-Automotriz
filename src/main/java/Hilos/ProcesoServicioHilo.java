/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hilos;

import javax.swing.SwingUtilities;
/**
 *
 * @author Home
 */
public class ProcesoServicioHilo implements Runnable{
    
    public interface ProcesoListener{
        void onInicio();
        void onPendiente();
        void onEnProceso(int progreso);
        void onFinalizada();
        void onCancelada();
    }
    
    private ProcesoListener listener;

    private volatile boolean cancelado = false;

    public ProcesoServicioHilo(ProcesoListener listener) {

        this.listener = listener;
    }

    public void cancelar() {

        cancelado = true;
    }

    private void notificar(Runnable accion) {

        SwingUtilities.invokeLater(accion);
    }

    @Override
    
    public void run() {
        notificar(() -> listener.onInicio());
        try {
            notificar(() -> listener.onPendiente());
            for (int i = 1; i <= 100; i++) {

                Thread.sleep(50);

                if (cancelado) {

                    notificar(() -> listener.onCancelada());

                    return;
                }

                final int avance = i;

                notificar(() ->
                        listener.onEnProceso(avance)
                );
            }

            notificar(() -> listener.onFinalizada());

        } catch (InterruptedException ex) {

            Thread.currentThread().interrupt();

            notificar(() -> listener.onCancelada());
        }
    }
}
