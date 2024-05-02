package jerarquicas.dinamicas;

/**
 *
 * @author Benjamín Morales <benjamin.morales at est.fi.uncoma.edu.ar>
 */

public class NodoArbol {

    // attributes
    private Object elem;
    private NodoArbol izquierdo;
    private NodoArbol derecho;

    // constructor
    public NodoArbol(Object nuevoElem, NodoArbol hijoIzq, NodoArbol hijoDer) {
        this.elem = nuevoElem;
        this.izquierdo = hijoIzq;
        this.derecho = hijoDer;
    }

    // getters and setters
    public Object getElem() {
        return elem;
    }

    public void setElem(Object elemento) {
        this.elem = elemento;
    }

    public NodoArbol getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbol izq) {
        this.izquierdo = izq;
    }

    public NodoArbol getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbol der) {
        this.derecho = der;
    }
}
