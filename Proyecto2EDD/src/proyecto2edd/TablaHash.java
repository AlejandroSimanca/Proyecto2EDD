/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

/**
 * implementacion propia de una tabla de dispersion.
 * no utiliza librerias externas de java.
 * @author alejandrosimanca
 */
public class TablaHash {
    private NodoHash[] tabla;
    private int tamano;

    /**
     * constructor que inicializa la tabla con un tamano fijo.
     * @param tamano capacidad inicial de la tabla.
     */
    public TablaHash(int tamano) {
        this.tamano = tamano;
        this.tabla = new NodoHash[tamano];
    }

    /**
     * funcion de dispersion para obtener el indice.
     * @param clave el nombre de usuario a procesar.
     * @return el indice dentro del arreglo.
     */
    private int funcionhash(String clave) {
        int hash = 0;
        for (int i = 0; i < clave.length(); i++) {
            hash = (31 * hash + clave.charAt(i)) % tamano;
        }
        return Math.abs(hash);
    }

    /**
     * inserta un usuario en la tabla.
     * @param usuario el objeto usuario a guardar.
     */
    public void insertar(Usuario usuario) {
        int indice = funcionhash(usuario.getNombre());
        NodoHash nuevo = new NodoHash(usuario.getNombre(), usuario);
        
        if (tabla[indice] == null) {
            tabla[indice] = nuevo;
        } else {
            NodoHash actual = tabla[indice];
            while (actual.getSiguiente() != null) {
                if (actual.getClave().equals(usuario.getNombre())) {
                    return; // el usuario ya existe
                }
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    /**
     * busca un usuario por su nombre.
     * @param nombre el identificador a buscar.
     * @return el objeto usuario o null si no existe.
     */
    public Usuario buscar(String nombre) {
        int indice = funcionhash(nombre);
        NodoHash actual = tabla[indice];
        
        while (actual != null) {
            if (actual.getClave().equals(nombre)) {
                return actual.getValor();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
}