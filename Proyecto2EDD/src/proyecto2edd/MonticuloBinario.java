/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

/**
 * implementacion de una cola de prioridad usando un monticulo binario (min-heap).
 * almacena los documentos ordenados por su etiqueta de tiempo.
 * @author alejandrosimanca
 */
public class MonticuloBinario {
    private Documento[] monticulo;
    private int tamano;
    private int capacidad;

    /**
     * constructor del monticulo binario.
     * @param capacidad numero maximo de documentos que puede almacenar.
     */
    public MonticuloBinario(int capacidad) {
        this.capacidad = capacidad;
        this.tamano = 0;
        this.monticulo = new Documento[capacidad];
    }

    private int getPadre(int i) {
        return (i - 1) / 2;
    }

    private int getHijoIzquierdo(int i) {
        return (2 * i) + 1;
    }

    private int getHijoDerecho(int i) {
        return (2 * i) + 2;
    }

    private void intercambiar(int i, int j) {
        Documento temporal = monticulo[i];
        monticulo[i] = monticulo[j];
        monticulo[j] = temporal;
        
        // ACTUALIZACION: Cada vez que se mueven, les avisamos su nueva posicion
        if (monticulo[i] != null) monticulo[i].setIndiceCola(i);
        if (monticulo[j] != null) monticulo[j].setIndiceCola(j);
    }

    /**
     * inserta un nuevo documento en la cola y reordena el monticulo hacia arriba.
     * @param doc el documento a insertar.
     */
    public void insertar(Documento doc) {
        if (tamano == capacidad) {
            System.out.println("el monticulo esta lleno");
            return;
        }

        tamano++;
        int i = tamano - 1;
        monticulo[i] = doc;
        doc.setIndiceCola(i); // le decimos su posicion inicial

        while (i != 0 && monticulo[getPadre(i)].getEtiquetatiempo() > monticulo[i].getEtiquetatiempo()) {
            intercambiar(i, getPadre(i));
            i = getPadre(i);
        }
    }

    /**
     * metodo recursivo para mantener la propiedad de min-heap de arriba hacia abajo.
     * @param i el indice desde donde se empieza a reordenar.
     */
    private void minHeapify(int i) {
        int izq = getHijoIzquierdo(i);
        int der = getHijoDerecho(i);
        int maspequeno = i;

        if (izq < tamano && monticulo[izq].getEtiquetatiempo() < monticulo[i].getEtiquetatiempo()) {
            maspequeno = izq;
        }

        if (der < tamano && monticulo[der].getEtiquetatiempo() < monticulo[maspequeno].getEtiquetatiempo()) {
            maspequeno = der;
        }

        if (maspequeno != i) {
            intercambiar(i, maspequeno);
            minHeapify(maspequeno);
        }
    }

    /**
     * extrae e imprime el documento con la menor etiqueta de tiempo (la raiz del arbol).
     * @return el documento extraido o null si esta vacio.
     */
    public Documento extraerMinimo() {
        if (tamano <= 0) {
            return null;
        }
        if (tamano == 1) {
            tamano--;
            monticulo[0].setIndiceCola(-1);
            return monticulo[0];
        }

        Documento raiz = monticulo[0];
        raiz.setIndiceCola(-1); // le quitamos su indice porque ya salio
        
        monticulo[0] = monticulo[tamano - 1];
        if (monticulo[0] != null) monticulo[0].setIndiceCola(0);
        
        tamano--;
        minHeapify(0);

        return raiz;
    }
    
    /**
     * cancela un documento especifico en la cola usando su indice en O(log n).
     * aplica el truco de cambiar la prioridad y hacer un extraerMinimo.
     * @param indice la posicion del documento en el arreglo.
     */
    public void cancelarDocumento(int indice) {
        if (indice < 0 || indice >= tamano) return;
        
        // 1. Cambiamos su etiqueta a un valor sumamente bajo
        monticulo[indice].setEtiquetatiempo(-999999);
        
        // 2. Lo obligamos a flotar hasta la raiz (posicion 0)
        int i = indice;
        while (i != 0 && monticulo[getPadre(i)].getEtiquetatiempo() > monticulo[i].getEtiquetatiempo()) {
            intercambiar(i, getPadre(i));
            i = getPadre(i);
        }
        
        // 3. Extraemos la raiz (lo eliminamos de la cola pero no lo mandamos a imprimir)
        Documento cancelado = extraerMinimo();
        cancelado.setEncola(false); // ya no esta en cola
    }

    /**
     * obtiene el arreglo interno del monticulo para su visualizacion.
     * @return el arreglo de documentos.
     */
    public Documento[] getMonticulo() {
        return monticulo;
    }

    /**
     * obtiene la cantidad actual de elementos en el monticulo.
     * @return el tamano del monticulo.
     */
    public int getTamano() {
        return tamano;
    }
}