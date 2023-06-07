/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas.dinamicas;

import lineales.dinamicas.Lista;

/**
 *
 * @author Benjamín Morales <benjamin.morales at est.fi.uncoma.edu.ar>
 */
public class ArbolGen {

    // atributos
    private NodoGen raiz;

    // constructor vacío
    public ArbolGen() {
        this.raiz = null;
    }

    /* 
     * inserta elemNuevo como hijo de la primer aparición de elemPadre. 
     * Para que la operación termine con éxito debe existir un nodo en el árbol 
     * con elemento = elemPadre. Devuelve verdadero cuando se pudo agregar 
     * elemNuevo a la estructura y falso en caso contrario.
     */
    public boolean insertar(Object elemNuevo, Object elemPadre) {
        boolean exito = true;
        // Agregar código
        return exito;
    }

    // pertenece(elemento): boolean
    /*
     * Devuelve verdadero si el elemento pasado por parámetro está en el árbol.
     * Falso en caso contrario.
     */
    public boolean pertenece(Object elemento) {
        boolean exito = false;
        // Agregar código
        return exito;
    }

    // ancestros (elemento): lista de elementos
    /*
     * Si el elemento se encuentra en el árbol, devuelve una lista con los ancestros 
     * del elemento. Si el elemento no está en el árbol, devuelve la lista vacía. 
     */
    public Lista ancestros(Object elem) {
        Lista lis = new Lista();
        // Agregar código
        return lis;
    }

    // Devuelve true si el árbol es vacío, falso en caso contrario.
    public boolean esVacio() {
        return this.raiz == null;
    }

    // altura():int
    /*
     * Devuelve la altura del árbol, es decir la longitud del camino más largo 
     * desde la raíz hasta una hoja.
     * Nota: un árbol vacío tiene altura -1 y una hoja tiene altura 0.
     */
    public int altura() {
        int altura = -1;
        // Agregar código
        return altura;
    }

    //Devuelve el nivel de un elemento en el árbol. Si este no existe, devuelve -1.
    public int nivel(Object elemento) {
        int nivel = -1;
        // Agregar código
        return nivel;
    }

    /* 
     * Dado un elemento, devuelve el valor almacenado en su nodo padre (busca la 
     * primera aparición de elemento).
     */
    public Object padre(Object elemento) {
        Object elemPadre = null;
        // Agregar código
        return elemPadre;
    }

    /*
     * Devuelve una lista con los elementos del árbol en el recorrido en preorden.
     * Si el árbol es vacío, devuelve una lista vacía.
     * Algoritmo preorden(nodo)
     * (1) visitar la raíz del subárbol (nodo)
     * (2) Para cada hijo i de nodo
        (2.1) recorrer hijo i en Preorden
     * Fin algoritmo
     */
    public Lista listarPreorden() {
        Lista lis = new Lista();
        // Agregar código.
        return lis;
    }

    /*
     * Devuelve una lista con los elementos del árbol en el recorrido en posorden.
     * Si el árbol es vacío, devuelve una lista vacía.
     * Algoritmo posorden(nodo)
     * (1) Para cada hijo i de nodo
        (1.1) recorrer hijo i en Preorden
     * (2) visitar la raíz del subárbol (nodo)
     * Fin algoritmo    
     */
    public Lista listarPosorden() {
        Lista lis = new Lista();
        // Agregar código.
        return lis;
    }

    /*
     * Devuelve una lista con los elementos del árbol en el recorrido en inorden.
     * Si el árbol es vacío, devuelve una lista vacía.
     * Algoritmo inorden(nodo)
     * (1) recorrer en inorden el primer hijo de nodo
     * (2) visitar la raíz del subárbol (nodo)
     * (3) Para cada hijo i de nodo (donde i >= 2)
     *   (3.1) recorrer hijo i en inorden
     * Fin algoritmo    
     */
    public Lista listarInorden() {
        Lista lis = new Lista();
        // Agregar código.
        return lis;
    }

    /*
     * Devuelve una lista con los elementos del árbol en el recorrido por niveles.
     * Si el árbol es vacío, devuelve una lista vacía.
     * Algoritmo PorNivel()
     * (1) Cola Q = nueva cola
     * (2) poner en Q el nodo raíz
     * (3) mientras not cola.vacía hacer
     *      nodo = obtener el frente de Q
     *      sacar el frente de Q
     *      visitar(nodo)
     *      Para cada hijo i de nodo
     *         (3.1) poner el hijo i de nodo en la cola Q
     *    fin mientras
     * Fin algoritmo
     */
    public Lista listarPorNiveles() {
        Lista lis = new Lista();
        // Agregar código.
        return lis;
    }

    //clone (): ArbolGenerico
    /*
     * Genera y devuelve un árbol genérico que es equivalente (igual estructura 
     * y contenido de los nodos) que el árbol original.
     */
    public ArbolGen clone() {
        ArbolGen clon = new ArbolGen();
        // Agregar código
        return clon;
    }

    // Quita todos los elementos de la estructura.
    public void vaciar() {
        this.raiz = null;
    }

    /*
     * Genera y devuelve una cadena de caracteres que indica cuál es la raíz del
     * árbol y quienes son los hijos de cada nodo.
     */
    public String toString() {
        return toStringAux(this.raiz);
    }
    
    private String toStringAux(NodoGen n) {
        // Método privado
        String s = "";
        if (n != null) {
            // visita del nodo n
            s += n.getElem().toString() + " -> ";
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += hijo.getElem().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }
            
            // comienza recorrido de los hijos de n llamando recursivamente para
            // que cada hijo agregue su subcadena a la general
            hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return s;
    }
}
