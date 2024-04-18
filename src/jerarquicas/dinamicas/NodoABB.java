package jerarquicas.dinamicas;

/**
 *
 * @author Benjamín Morales <benjamin.morales at est.fi.uncoma.edu.ar> 
 */
public class NodoABB implements Comparable {
    
    //atributos
    private Comparable elem;
    private NodoABB izquierdo;
    private NodoABB derecho;
    
    //constructor
    public NodoABB(Comparable elemento, NodoABB izq, NodoABB der) {
        this.elem = elemento;
        this.izquierdo = izq;
        this.derecho = der;
    }
    
    //Observadores y modificadores

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

    @Override
    public int compareTo(Object o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
    
}
