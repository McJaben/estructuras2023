package conjuntistas;

import lineales.dinamicas.Lista;

/**
 * @author Benjamín Morales <benjamin.morales at est.fi.uncoma.edu.ar>
 *         Clase Árbol Binario de Búsqueda.
 */

public class ArbolBB<T extends Comparable<T>> {
    /*
     * Con esta cabecera estoy declarando que el tipo T implementa la interfaz
     * Comparable. Luego, en la firma de los métodos digo que los elementos son
     * de tipo T, que ya establecí que pueden compararse entre sí.
     * Con esto elimino los warnings de "raw type", le aseguro al compilador 
     * que los objetos son comparables, mantiene activo el chequeo de tipos y,
     * por lo tanto, evita que se generen errores en tiempo de ejecución.
     */
    
    // Atributos
    private NodoABB<T> raiz;

    // Constructor vacío
    public ArbolBB() {
        this.raiz = null;
    }

    /*
     * Recibe un elemento y lo agrega en el árbol de manera ordenada.
     * Si el elemento ya se encuentra en el árbol no realiza la inserción.
     * Devuelve verdadero si el elemento se agrega a la estructura y
     * falso en caso contrario (si está repetido).
     */
    public boolean insertar(T elem) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoABB<T>(elem);
        } else {
            exito = insertarAux(this.raiz, elem);
        }
        return exito;
    }

    private boolean insertarAux(NodoABB<T> n, T elemento) {
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
                n.setIzquierdo(new NodoABB<T>(elemento));
            }
        } else {
            // el elemento es mayor que n.getElem()
            // si tiene HD baja a la derecha, sino agrega elemento
            if (n.getDerecho() != null) {
                exito = insertarAux(n.getDerecho(), elemento);
            } else {
                n.setDerecho(new NodoABB<T>(elemento));
            }
        }
        return exito;
    }

    /*
     * Devuelve verdadero si el elemento recibido por parámetro
     * está en el árbol y falso en caso contrario.
     */
    public boolean pertenece(T elem) {
        return perteneceAux(this.raiz, elem);
    }

    /*
     * Método auxiliar y privado, que recorre la estructura de forma recursiva.
     */
    private boolean perteneceAux(NodoABB<T> n, T elemento) {
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
    public boolean perteneceIterativo(T elemento) {
        NodoABB<T> actual = this.raiz;
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
     * Recibe el elemento que desea eliminar y lo remueve del árbol.
     * Devuelve true si la eliminación tuvo éxito, y falso si el elemento
     * no se encuentra en el árbol, y por tanto no se pudo eliminar.
     */
    public boolean eliminar(T elem) {
        boolean exito = false;

        if (this.raiz != null) {
            exito = eliminarAux(this.raiz, null, elem);
        }

        return exito;
    }

    /*
     * Método PRIVADO recursivo. Recorre la estructura de manera recursiva
     * para buscar el nodo a eliminar, distinguiendo los 3 casos posibles:
     * 1. Que el nodo a eliminar sea una hoja.
     * 2. Que el nodo a eliminar tenga 1 hijo.
     * 3. Que el nodo a eliminar tenga 2 hijos.
     */
    private boolean eliminarAux(NodoABB<T> nodo, NodoABB<T> padre, T buscado) {
        boolean exito = false;

        if (nodo != null) {
            if (buscado.compareTo(nodo.getElem()) == 0) {
                // Encontró el nodo buscado, chequea a qué caso de eliminación pertenece
                if (padre == null) {
                    this.raiz = null; // El nodo a eliminar es la raíz (Caso especial del caso 1)
                } else {
                    // Verifico si nodo buscado es HI o HD de su padre.
                    //Si buscador es mayor = 'd' (HD), si es menor = 'i' (HI)
                    char pos;
                    if (buscado.compareTo(padre.getElem()) > 0) {
                        pos = 'd';
                    } else {
                        pos = 'i';
                    }
                    // Cuenta cantidad de hijos para distinguir caso 1, 2 y 3
                    int caso = 1;
                    if (nodo.getIzquierdo() != null) {
                        caso++;
                    }
                    if (nodo.getDerecho() != null) {
                        caso++;
                    }
                    // Llamo al método auxiliar que se encarga de eliminar según cada caso
                    eliminarCasos(nodo, padre, caso, pos);
                }
                exito = true;
            } else if (buscado.compareTo(nodo.getElem()) < 0) {
                // Bajar por rama izquierda
                exito = eliminarAux(nodo.getIzquierdo(), nodo, buscado);
            } else {
                // Bajar por rama derecha
                exito = eliminarAux(nodo.getDerecho(), nodo, buscado);
            }
        }
        return exito;
    }

    private void eliminarCasos(NodoABB<T> n, NodoABB<T> padre, int caso, int pos) {

        if (caso == 1) { // Caso 1 - nodo es hoja
            if (pos == 'i') {
                padre.setIzquierdo(null);
            } else {
                padre.setDerecho(null);
            }
        } else if (caso == 2) { // Caso 2 - nodo es hoja
            if (pos == 'i') { // Si nodo es HI de su padre
                if (n.getIzquierdo() != null) { 
                    padre.setIzquierdo(n.getIzquierdo()); // Si nodo n tiene HI
                } else {
                    padre.setIzquierdo(n.getDerecho()); // Si nodo n tiene HD
                }
            } else { // Si nodo es HD de su padre
                if (n.getIzquierdo() != null) { // Si nodo n tiene HI
                    padre.setDerecho(n.getIzquierdo());
                } else {
                    padre.setDerecho(n.getDerecho()); 
                }
            }
        } else {

         // caso 3 - Nodo tiene 3 hijos -- TESTEAR/MODIFICAR
            NodoABB<T> padreCandidato = obtenerPadreCandidato(n);
            NodoABB<T> candidato = null;
            if (padreCandidato.getIzquierdo() != null) {
                // El candidato es HI del padre
                candidato = padreCandidato.getIzquierdo();
            } else {
                // El padre es una hoja y es, por tanto, el propio candidato
                candidato = padreCandidato;
                // El padre del nuevo candidato es el propio nodo a eliminar

            }
            // Reemplazo el valor del nodo a eliminar por el del candidato
            n.setElem(candidato.getElem());
            // Enlazo al HD del candidato con el padre del candidato
            padreCandidato.setIzquierdo(candidato.getDerecho());
        }
    }

    /*
     * Método privado. Devuelve el padre del menor elemento
     * del subárbol derecho del nodo pasado por parámetro
     */
    private NodoABB<T> obtenerPadreCandidato(NodoABB<T> n) {
        // aux representa al menor elemento del subárbol derecho de n
        // Retorno el padre del menor elemento del subárbol derecho de n
        // Si el subárbol derecho de n es una hoja, el nodo padre es el menor
        // elemento del subárbol derecho de n
        NodoABB<T> padre = n;

        NodoABB<T> aux = n.getDerecho(); // Comienza recorrido por subarbol derecho
        NodoABB<T> candidato = aux.getIzquierdo();
        while (candidato.getIzquierdo() != null) {
            aux = candidato;
            candidato.getIzquierdo();
        }
        if (aux != null) {
            padre = aux;
        }
        return padre;
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
    public Lista listarRango(T minElem, T maxElem) {
        Lista lis = new Lista();
        // Implementar
        return lis;
    }

    /*
     * 
     */
    public T minimoElem() {
        T elem = null;
        // Implementar
        return elem;
    }

    /*
     * 
     */
    public T maximoElem() {
        T elem = null;
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

    /*
     * Vacía el árbol.
     */
    public void vaciar() {
        this.raiz = null;
    }
}
