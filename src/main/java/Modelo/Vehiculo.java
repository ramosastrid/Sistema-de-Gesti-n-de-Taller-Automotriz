/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Modelo.Cliente;

/**
 *
 * @author Home
 */
public class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int año;
    private String color;
    private Cliente propietario;
    
    @Override
public String toString() {
    return placa + " - " + marca + " " + modelo;
}
    
    public Vehiculo(String placa, String marca, String modelo, int año, String color,Cliente propietario){
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.año = año;
        this.color =  color;
        this.propietario = propietario;
    }
    public String getPlaca(){return placa;}
    public String getMarca(){return marca;}
    public String getModelo(){return modelo;}
    public int getAño(){return año;}
    public String getColor(){return color;}
    public Cliente getPropietario(){return propietario;}
    
    public void setPlaca(String placa){this.placa = placa;}
    public void setMarca(String marca){this.marca = marca;}
    public void setModelo(String modelo){this.modelo = modelo;}
    public void setAño(int año){this.año = año;}
    public void setPropietario(Cliente propietario){this.propietario = propietario;}
}
