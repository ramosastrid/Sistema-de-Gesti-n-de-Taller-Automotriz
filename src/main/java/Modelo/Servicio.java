/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Home
 */
public class Servicio {
    private String codigo;
    private String descripcion;
    private double precioBase;
    private int tiempoEstimado;
    private String cambioAceite;
    private String revisionFrenos;
    private String diagnosticoElectronico;
    private String alineamiento;
    private String mantenimientoGeneral;
    
    public Servicio(String codigo,String descripcion, double precioBase, int tiempoEstimado,String cambioAceite,String diagnosticoElectronico,String alineamiento,String mantenimientoGeneral,String revisionFrenos){
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.tiempoEstimado = tiempoEstimado;
        this.cambioAceite =cambioAceite;
        this.revisionFrenos = revisionFrenos;
        this.diagnosticoElectronico = diagnosticoElectronico;
        this.alineamiento = alineamiento;
        this.mantenimientoGeneral = mantenimientoGeneral;
    }
    public String getCodigo(){return codigo;}
    public String getDescripcion(){return descripcion;}
    public double getPrecioBase(){return precioBase;}
    public int getTiempoEstimado(){return tiempoEstimado;}
    public String getCambioAceite(){return cambioAceite;}
    public String getRevisionFrenos(){return revisionFrenos;}
    public String getDiagnosticoElectronico(){return diagnosticoElectronico;}
    public String getAlineamiento(){return alineamiento;}
    public String getMantenimientoGeneral(){return mantenimientoGeneral;}
    
    public void setCodigo(String codigo){this.codigo = codigo;}
    public void setDescripcion(String descripcion){this.descripcion = descripcion;}
    public void setPrecioBase(double precioBase){this.precioBase = precioBase;}
    public void setTiempoEstimado(int tiempoEstimado){this.tiempoEstimado = tiempoEstimado;}
    public void setCambioAceite(String cambioAceite){this.cambioAceite = cambioAceite;}
    public void setRevisionFrenos(String revisionFrenos){this.revisionFrenos = revisionFrenos;}
    public void setDiagnosticoElectronico(String diagnosticoElectronico){this.diagnosticoElectronico = diagnosticoElectronico;}
    public void setAlineamiento(String alineamiento){this.alineamiento = alineamiento;}
    public void setMantenimientoGeneral(String  mantenimientoGeneral){this.mantenimientoGeneral = mantenimientoGeneral;}
}
