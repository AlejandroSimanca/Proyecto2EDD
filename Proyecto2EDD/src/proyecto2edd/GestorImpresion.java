/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

/**
 * clase controladora que administra el flujo del sistema de impresion.
 * conecta a los usuarios, el reloj y la cola del monticulo binario.
 * @author alejandrosimanca
 */
public class GestorImpresion {
    private TablaHash usuarios;
    private MonticuloBinario colaimpresion;
    private Reloj reloj;

    /**
     * constructor del gestor de impresion.
     * @param usuarios la tabla de dispersion con los usuarios cargados.
     */
    public GestorImpresion(TablaHash usuarios) {
        this.usuarios = usuarios;
        this.colaimpresion = new MonticuloBinario(100);
        this.reloj = new Reloj();
    }

    /**
     * envia un nuevo documento a la cola de impresion.
     * @param nombreusuario el identificador del usuario que envia el documento.
     * @param nombredocumento el nombre del archivo a imprimir.
     * @param tamano la cantidad de paginas del documento.
     */
    public void enviaraimprimir(String nombreusuario, String nombredocumento, int tamano) {
        Usuario usuario = usuarios.buscar(nombreusuario);
        
        if (usuario != null) {
            int etiqueta = reloj.crearEtiqueta(usuario.getTipoprioridad());
            Documento nuevodocumento = new Documento(nombredocumento, tamano, usuario, etiqueta);
            colaimpresion.insertar(nuevodocumento);
            reloj.tick();
            System.out.println("documento enviado a la cola con exito.");
        } else {
            System.out.println("error: el usuario no existe en el sistema.");
        }
    }

    /**
     * extrae el documento con mayor prioridad de la cola para simular su impresion.
     * @return el documento que fue impreso o null si la cola esta vacia.
     */
    public Documento imprimirsiguiente() {
        Documento doc = colaimpresion.extraerMinimo();
        reloj.tick();
        return doc;
    }

    /**
     * obtiene la cola de impresion actual para poder dibujarla en la interfaz.
     * @return el monticulo binario del sistema.
     */
    public MonticuloBinario getColaimpresion() {
        return colaimpresion;
    }
}