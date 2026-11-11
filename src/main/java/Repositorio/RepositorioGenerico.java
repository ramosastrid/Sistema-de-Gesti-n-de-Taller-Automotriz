/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author Home
 */
public class RepositorioGenerico<T> {
    private ArrayList<T> lista = new ArrayList<>();
    private HashMap<String, T> mapa = new HashMap<>();
    private Stack<T> historial = new Stack<>();

public void agregar(String clave, T elemento) {

    if (mapa.containsKey(clave)) {
        throw new IllegalArgumentException("Código duplicado");
    }
    lista.add(elemento);
    mapa.put(clave, elemento);
}

public T buscar(String clave) {
    return mapa.get(clave);
}

public void eliminar(String clave) {
    T elemento = mapa.remove(clave);

    if (elemento != null) {
        lista.remove(elemento);
    }
}

public ArrayList<T> obtenerTodos() {
    return lista;
}

public void modificar(String clave, T elementoNuevo) {

    if (!mapa.containsKey(clave)) {
        throw new IllegalArgumentException("Registro no encontrado");
    }

    T elementoAnterior = mapa.get(clave);

    int posicion = lista.indexOf(elementoAnterior);

    if (posicion != -1) {
        lista.set(posicion, elementoNuevo);
    }

    mapa.put(clave, elementoNuevo);
}
}
