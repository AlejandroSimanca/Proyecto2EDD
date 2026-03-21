/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

/**
 * lista enlazada simple para almacenar los documentos de un usuario.
 * @author alejandrosimanca
 */
public class ListaDocumentos {
    private NodoDocumento cabeza;

    public ListaDocumentos() {
        this.cabeza = null;
    }

    /**
     * inserta un nuevo documento al inicio de la lista.
     * @param doc el documento a insertar.
     */
    public void insertar(Documento doc) {
        NodoDocumento nuevo = new NodoDocumento(doc);
        nuevo.setSiguiente(cabeza);
        cabeza = nuevo;
    }

    /**
     * busca un documento por su nombre.
     * @param nombre el nombre del documento.
     * @return el documento encontrado o null.
     */
    public Documento buscar(String nombre) {
        NodoDocumento actual = cabeza;
        while (actual != null) {
            if (actual.getDocumento().getNombre().equals(nombre)) {
                return actual.getDocumento();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    /**
     * elimina un documento de la lista si no esta en la cola de impresion.
     * @param nombre el nombre del documento a eliminar.
     * @return true si se elimino, false en caso contrario.
     */
    public boolean eliminar(String nombre) {
        if (cabeza == null) return false;

        if (cabeza.getDocumento().getNombre().equals(nombre)) {
            if (!cabeza.getDocumento().isEncola()) {
                cabeza = cabeza.getSiguiente();
                return true;
            }
            return false;
        }

        NodoDocumento actual = cabeza;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDocumento().getNombre().equals(nombre)) {
                if (!actual.getSiguiente().getDocumento().isEncola()) {
                    actual.setSiguiente(actual.getSiguiente().getSiguiente());
                    return true;
                }
                return false;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public NodoDocumento getCabeza() {
        return cabeza;
    }
}