package conjuntistas;

/**
 * @author Benjamín Morales <benjamin.morales at est.fi.uncoma.edu.ar>
 */

public class NodoABB {

    // Atributos
    private Comparable elem;
    private NodoABB izquierdo;
    private NodoABB derecho;

    // Constructores
    public NodoABB(Comparable nuevoElem, NodoABB izq, NodoABB der) {
        this.elem = nuevoElem;
        this.izquierdo = izq;
        this.derecho = der;
    }

    public NodoABB(Comparable nuevoElem) {
        this.elem = nuevoElem;
    }

    // Observadores y modificadores

    public Comparable getElem() {
        return elem;
    }

    public void setElem(Comparable elem) {
        this.elem = elem;
    }

    public NodoABB getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoABB izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoABB getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoABB derecho) {
        this.derecho = derecho;
    }

}