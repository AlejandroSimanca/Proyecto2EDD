/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

public class TablaHash {

    private NodoHash[] arreglo;
    private int tamanoMax;

    public TablaHash(int tamano) {
        this.tamanoMax = tamano;
        this.arreglo = new NodoHash[tamano];
    }

    private int hash(String nombre) {
        int h = 0;
        for (int i = 0; i < nombre.length(); i++) {
            h = (31 * h + nombre.charAt(i)) % tamanoMax;
        }
        return Math.abs(h);
    }

    public void insertar(Usuario usuario) {
        int indice = hash(usuario.getNombre());
        NodoHash nuevo = new NodoHash(usuario);
        if (arreglo[indice] == null) {
            arreglo[indice] = nuevo;
        } else {
            NodoHash actual = arreglo[indice];
            while (actual.getSiguiente() != null) {
                if (actual.getUsuario().getNombre().equals(usuario.getNombre())) {
                    return;
                }
                actual = actual.getSiguiente();
            }
            if (!actual.getUsuario().getNombre().equals(usuario.getNombre())) {
                actual.setSiguiente(nuevo);
            }
        }
    }

    public Usuario buscar(String nombre) {
        int indice = hash(nombre);
        NodoHash actual = arreglo[indice];
        while (actual != null) {
            if (actual.getUsuario().getNombre().equals(nombre)) {
                return actual.getUsuario();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    public void eliminar(String nombre) {
        int indice = hash(nombre);
        if (arreglo[indice] == null) {
            return;
        }
        if (arreglo[indice].getUsuario().getNombre().equals(nombre)) {
            arreglo[indice] = arreglo[indice].getSiguiente();
            return;
        }
        NodoHash actual = arreglo[indice];
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getUsuario().getNombre().equals(nombre)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return;
            }
            actual = actual.getSiguiente();
        }
    }
}