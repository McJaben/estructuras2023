package conjuntistas;

import lineales.dinamicas.Lista;

/**
 * @author Benjamín Morales <benjamin.morales at est.fi.uncoma.edu.ar>
 *         Clase Árbol Binario de Búsqueda.
 */

public class ArbolBB {
    // Atributos
    private NodoABB raiz;

    // Constructor vacío
    public ArbolBB() {
        this.raiz = null;
    }

    /*
     * Devuelve verdadero si el elemento recibido por parámetro
     * está en el árbol y falso en caso contrario.
     */
    public boolean pertenece(Comparable elem) {
        return perteneceAux(this.raiz, elem);
    }

    /*
     * Método auxiliar y privado, que recorre la estructura de forma recursiva.
     */
    private boolean perteneceAux(NodoABB n, Comparable elemento) {
        boolean exito = false;
        if (n != null) {
            if ((elemento.compareTo(n.getElem()) == 0)) {
                // Elemento encontrado
                exito = true;
            } else if (elemento.compareTo(n.getElem()) < 0) {
                // elemento es menor que n.getElem()
                // busca a la izquierda de n
                exito = perteneceAux(n.getIzquierdo(), elemento);
            } else {
                // elemento es mayor que n.getElem()
                // busca a la derecha de n
                exito = perteneceAux(n.getDerecho(), elemento);
            }
        }
        return exito;
    }

    /*
     * Implementación iterativa del método pertenece().
     * Devuelve verdadero si el elemento recibido por parámetro 
     * está en el árbol, falso en caso contrario.
     */
    public boolean perteneceIterativo(Comparable elemento) {
        NodoABB actual = this.raiz;
        boolean exito = false;
    
        while (actual != null && !exito) {
            int comparacion = elemento.compareTo(actual.getElem());
    
            if (comparacion == 0) {
                // Elemento encontrado
                exito = true;
            } else if (comparacion < 0) {
                // Busca en el subárbol izquierdo
                actual = actual.getIzquierdo();
            } else {
                // Busca en el subárbol derecho
                actual = actual.getDerecho();
            }
        }

        return exito;
    }
    

    /*
     * Recibe un elemento y lo agrega en el árbol de manera ordenada.
     * Si el elemento ya se encuentra en el árbol no realiza la inserción.
     * Devuelve verdadero si el elemento se agrega a la estructura y
     * falso en caso contrario (si está repetido).
     */
    public boolean insertar(Comparable elem) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoABB(elem);
        } else {
            exito = insertarAux(this.raiz, elem);
        }
        return exito;
    }

    private boolean insertarAux(NodoABB n, Comparable elemento) {
        // precondicion: n no es nulo
        boolean exito = true;

        if ((elemento.compareTo(n.getElem()) == 0)) {
            // Reportar error: Elemento repetido
            exito = false;
        } else if (elemento.compareTo(n.getElem()) < 0) {
            // el elemento es menor que n.getElem()
            // si tiene HI baja a la izquierda, sino agrega elemento
            if (n.getIzquierdo() != null) {
                exito = insertarAux(n.getIzquierdo(), elemento);
            } else {
                n.setIzquierdo(new NodoABB(elemento));
            }
        } else {
            // el elemento es mayor que n.getElem()
            // si tiene HD baja a la derecha, sino agrega elemento
            if (n.getDerecho() != null) {
                exito = insertarAux(n.getDerecho(), elemento);
            } else {
                n.setDerecho(new NodoABB(elemento));
            }
        }

        return exito;
    }

    /*
     * 
     */
    public boolean eliminar(Comparable elem) {
        boolean exito = false;
        // Implementar
        return exito;
    }

    /*
     * 
     */
    public Lista listar() {
        Lista lis = new Lista();
        // Implementar
        return lis;
    }

    /*
     */
    public Lista listarRango(Comparable minElem, Comparable maxElem) {
        Lista lis = new Lista();
        // Implementar
        return lis;
    }

    /*
     * 
     */
    public Comparable minimoElem() {
        Comparable elem = null;
        // Implementar
        return elem;
    }

    /*
     * 
     */
    public Comparable maximoElem() {
        Comparable elem = null;
        // Implementar
        return elem;
    }

    /*
     * Devuelve falso si hay al menos un elemento en el árbol. Verdadero en caso
     * contrario.
     */
    public boolean esVacio() {
        return this.raiz == null;
    }

}
