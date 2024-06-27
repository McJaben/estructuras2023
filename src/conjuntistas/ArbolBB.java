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
     * Recibe el elemento que desea eliminar y lo remueve del árbol.
     * Devuelve true si la eliminación tuvo éxito, y falso si el elemento
     * no se encuentra en el árbol, y por tanto no se pudo eliminar.
     */
    public boolean eliminar(Comparable elem) {
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
    private boolean eliminarAux(NodoABB nodo, NodoABB padre, Comparable buscado) {
        boolean exito = false;

        if (nodo != null) {
            if (buscado.compareTo(nodo.getElem()) == 0) {
                // Encontró el nodo buscado, chequea a qué caso de eliminación pertenece
                if (padre != null) {
                    // Verifico si el nodo buscado es HI o HD de su padre. true = HI, false = HD
                    boolean esHijoIzquierdo = buscado.compareTo(padre.getIzquierdo().getElem()) == 0;

                    // Cuenta cantidad de hijos para distinguir caso 1, 2 y 3
                    String hijos = "";
                    if (nodo.getIzquierdo() != null) {
                        hijos += "I";
                    }
                    if (nodo.getDerecho() != null) {
                        hijos += "D";
                    }

                    // Elimino según cada caso
                    if (hijos.equals("")) { // caso 1 - nodo es hoja
                        if (esHijoIzquierdo) {
                            padre.setIzquierdo(null);
                        } else {
                            padre.setDerecho(null);
                        }
                    } else if (hijos.length() == 1) { // caso 2 - Nodo tiene 1 hijo
                        // Diferencio por hijos.equals("I") || hijos.equals("D")
                        if (hijos.equals("I")) { // Nodo sólo tiene HI
                            if (esHijoIzquierdo) {
                                padre.setIzquierdo(nodo.getIzquierdo());
                            } else {
                                padre.setDerecho(nodo.getIzquierdo());
                            }
                        } else { // Nodo sólo tiene HD
                            if (esHijoIzquierdo) {
                                padre.setIzquierdo(nodo.getDerecho());
                            } else {
                                padre.setDerecho(nodo.getDerecho());
                            }
                        }
                    } else if (hijos.length() == 2) { // caso 3 - Nodo tiene 3 hijos
                        NodoABB padreCandidato = obtenerPadreCandidato(nodo);
                        NodoABB candidato = null;
                        if (padreCandidato.getIzquierdo() != null) {
                            // El candidato es HI del padre
                            candidato = padreCandidato.getIzquierdo();
                        } else {
                            // El padre es una hoja y es, por tanto, el propio candidato
                            candidato = padreCandidato;
                            // El padre del nuevo candidato es el propio nodo a eliminar

                        }
                        // Reemplazo el valor del nodo a eliminar por el del candidato
                        nodo.setElem(candidato.getElem());
                        // Enlazo al HD del candidato con el padre del candidato
                        padreCandidato.setIzquierdo(candidato.getDerecho());
                    }
                }
            } else {
                this.raiz = null; // El nodo a eliminar es la raíz
            }
        } else if (buscado.compareTo(nodo.getElem()) < 0) {
            // Bajar por rama izquierda
        } else {
            // Bajar por rama derecha
        }

        return exito;
    }

    /*
     * Método privado. Devuelve el padre del menor elemento
     * del subárbol derecho del nodo pasado por parámetro
     */
    private NodoABB obtenerPadreCandidato(NodoABB n) {
        NodoABB padre = n;

        NodoABB aux = n.getDerecho(); // Comienza recorrido por subarbol derecho
        NodoABB candidato = aux.getIzquierdo();
        while (candidato.getIzquierdo() != null) {
            aux = candidato;
            candidato.getIzquierdo();
        }
        if (aux != null) {
            padre = aux;
        }
        // aux representa al menor elemento del subárbol derecho de n
        // Retorno el padre del menor elemento del subárbol derecho de n
        // Si el subárbol derecho de n es una hoja, padre representa al menor
        // elemento del subárbol derecho de n
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
