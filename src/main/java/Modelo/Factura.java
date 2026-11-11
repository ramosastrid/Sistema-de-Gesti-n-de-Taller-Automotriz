/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Modelo.OrdenTrabajo;

/**
 *
 * @author Home
 */
public class Factura {
    private OrdenTrabajo orden;
    private double subtotalServicio;
    private double subtotalRespuesto;
    private double impuesto;
    private double total;
    
    public Factura(OrdenTrabajo orden,double subtotalServicio,double subtotalRespuesto,double impuesto,double total){
        this.orden = orden;
        this.subtotalServicio = subtotalServicio;
        this.subtotalRespuesto = subtotalRespuesto;
        this.impuesto = impuesto;
        this.total = total;
    }
    public OrdenTrabajo getOrden(){return orden;}
    public double getSubtotalServicio(){return subtotalServicio;}
    public double getSubtotalRespuesto(){return subtotalRespuesto;}
    public double gstImpuesto(){return impuesto;}
    public double getTotal(){return total;}
}
