/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Home
 */
public class Repuesto {
    private String codigo;
    private String nombre;
    private int cantidad;
    private int stock;
    private int precioUnitario;
    
    @Override
public String toString() {
    return nombre + " - " + codigo;
}
    
    public Repuesto(String codigo,String nombre,int cantidad,int stock,int precioUnitario){
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.stock = stock;
        this.precioUnitario = precioUnitario;
    }
    public String getCodigo(){return codigo;}
    public String getNombre(){return nombre;}
    public int getCantidad(){return cantidad;}
    public int getStock(){return stock;}
    public int getPrecioUnitario(){return precioUnitario;}
    
    public void setCodigo(String codigo){this.codigo = codigo;}
    public void setNombre(String nombre){this.nombre = nombre;}
    public void setCantidad(int cantidad ){this.cantidad = cantidad;}
    public void setStock(int stock){this.stock = stock;}
    public void setPrecioUnitario(int precioUnitario){this.precioUnitario = precioUnitario;}
    
    public boolean descontarCantidad(int cantidad) {

    if (cantidad <= 0) {
        return false;
    }

    if (this.cantidad < cantidad) {
        return false;
    }

    this.cantidad -= cantidad;

    return true;
}
}
