/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import Vista.VentanaPrincipal;
import Controlador.Controladores;
/**
 *
 * @author Home
 */
public class Main {
     public static void main(String[]args){
            java.awt.EventQueue.invokeLater(() -> {
                
            VentanaPrincipal menu = new VentanaPrincipal();
            
            new Controladores(menu);
            
            menu.setVisible(true);
        });
    }
}
