/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo; 
import Modelo.Cliente;
import Modelo.Vehiculo;
import Modelo.Repuesto;

/**
 *
 * @author Home
 */
public class OrdenTrabajo {
    private String numeroUnico;
    private String fecha;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private String diagnostico;
    private String mecanico;
    private String servicio;
    private Repuesto repuesto;
    private double costoTotal;
    private String estado;
    private int cantidadRepuesto;
    
    private boolean inventarioDescontado = false;
    

    
    public OrdenTrabajo(String numeroUnico,String fecha, Cliente cliente,Vehiculo vehiculo,String diagnostico,String mecanico,String servicio,Repuesto repuesto, int cantidadRepuesto,double costoTotal, String estado){
        this.numeroUnico = numeroUnico;
        this.fecha = fecha;
        this.cliente = cliente;
        this.vehiculo = vehiculo; 
        this.diagnostico = diagnostico;
        this.mecanico = mecanico;
        this.servicio = servicio;
        this.repuesto = repuesto;
        this.cantidadRepuesto = cantidadRepuesto;
        this.costoTotal = costoTotal;
        this.estado = estado;
        
        
        
    }
    public String getNumeroUnico(){return numeroUnico;}
    public String getFecha(){return fecha;}
    public Cliente getCliente(){return cliente;}
    public Vehiculo getVehiculo(){return vehiculo;}
    public String getDiagnostico(){return diagnostico;}
    public String getMecanico(){return mecanico;}
    public String getServicio(){return servicio;}
    public Repuesto getRepuesto(){return repuesto;}
    public double getCostoTotal(){return costoTotal;}
    public String getEstado(){return estado;}
    public int getCantidadRepuesto() {
    return cantidadRepuesto;
}

public void setCantidadRepuesto(int cantidadRepuesto) {
    this.cantidadRepuesto = cantidadRepuesto;}
    
    public void setNumeroUnico(String numeroUnico){this.numeroUnico = numeroUnico;}
    public void setCliente(Cliente cliente){this.cliente = cliente;}
    public void setVehiculo(Vehiculo vehiculo){this.vehiculo= vehiculo;}
    public void setDiagnostico(String diagnostico){this.diagnostico = diagnostico;}
    public void setMecanico(String mecanico){this.mecanico = mecanico;}
    public void setServicio(String servicio){this.servicio= servicio;}
    public void setRespuesto(Repuesto repuesto){this.repuesto= repuesto;}
    public void setCostoTotal(double costoTotal){this.costoTotal = costoTotal;}
    public void setEstado(String estado){this.estado = estado;}
    
    public void setInventarioDescontado(boolean inventarioDescontado) {
        this.inventarioDescontado = inventarioDescontado;
    }
    
     public boolean isInventarioDescontado() {
    return inventarioDescontado; }
}
