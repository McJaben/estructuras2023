package jerarquicas.dinamicas;

/**
 *
 * @author rosi
 */
public class NodoArbol {
    
    //atributos
    private Object elem;
    private NodoArbol izquierdo;
    private NodoArbol derecho;
    
    //constructor
    public NodoArbol(Object elemento, NodoArbol izq, NodoArbol der) {
        this.elem = elemento;
        this.izquierdo = izq;
        this.derecho = der;
    }
    
    //Observadores y modificadores

    public Object getElem() {
        return elem;
    }

    public void setElem(Object elem) {
        this.elem = elem;
    }

    public NodoArbol getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbol izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbol getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbol derecho) {
        this.derecho = derecho;
    }
    
}
