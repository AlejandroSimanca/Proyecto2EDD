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
            Documento nuevodocumento = new Documento(nombredocumento, tamano, usuario);
            usuario.getDocumentos().insertar(nuevodocumento);
            
            int etiqueta = reloj.crearEtiqueta(usuario.getTipoprioridad());
            nuevodocumento.setEtiquetatiempo(etiqueta);
            nuevodocumento.setEncola(true);
            
            colaimpresion.insertar(nuevodocumento);
            reloj.tick();
            
            System.out.println("documento creado y enviado a la cola con exito.");
        } else {
            System.out.println("error: el usuario no existe en el sistema.");
        }
    }

    /**
     * cancela un documento que ya se encuentra en la cola de impresion.
     * @param nombreusuario el dueno del documento.
     * @param nombredocumento el nombre del documento a cancelar.
     * @return un mensaje indicando el resultado de la operacion.
     */
    public String cancelarImpresion(String nombreusuario, String nombredocumento) {
        Usuario usuario = usuarios.buscar(nombreusuario);
        if (usuario == null) {
            return "error: usuario no encontrado.";
        }
        
        Documento doc = usuario.getDocumentos().buscar(nombredocumento);
        if (doc == null) {
            return "error: el usuario no tiene un documento con ese nombre.";
        }
        
        if (!doc.isEncola() || doc.getIndiceCola() == -1) {
            return "error: el documento no esta actualmente en la cola.";
        }
        
        // ¡Aqui ocurre la magia! Extraemos el indice y lo cancelamos en O(log n)
        colaimpresion.cancelarDocumento(doc.getIndiceCola());
        reloj.tick();
        return "exito: el documento " + nombredocumento + " fue cancelado y retirado de la cola.";
    }

    /**
     * extrae el documento con mayor prioridad de la cola para simular su impresion.
     * @return el documento que fue impreso o null si la cola esta vacia.
     */
    public Documento imprimirsiguiente() {
        Documento doc = colaimpresion.extraerMinimo();
        if (doc != null) {
            doc.setEncola(false);
            reloj.tick();
        }
        return doc;
    }

    public MonticuloBinario getColaimpresion() {
        return colaimpresion;
    }
    
    public TablaHash getUsuarios() {
        return usuarios;
    }
}