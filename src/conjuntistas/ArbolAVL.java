package conjuntistas;

import lineales.dinamicas.Lista;

/**
 * @author Benjamín Morales <benjamin.morales at est.fi.uncoma.edu.ar> Clase Árbol Binario AVL.
 */

public class ArbolAVL<T extends Comparable<T>> {
    // Atributos
    private NodoAVL<T> raiz;

    // Constructor vacío
    public ArbolAVL() {
        this.raiz = null;
    }
}
