package jerarquicas.dinamicas;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;

/**
 *
 * @author Benjamín Morales <benjamin.morales at est.fi.uncoma.edu.ar>
 */

public class ArbolBin {

    // attributes
    private NodoArbol raiz;

    // empty constructor
    public ArbolBin() {
        this.raiz = null;
    }

    /**
     * Inserta elemNuevo como hijo del primer nodo encontrado en preorden igual
     * a elemPadre, como hijo izquierdo (I) o derecho (D), segun lo indique el
     * parametro 'posicion'.
     */
    public boolean insertar(Object elemNuevo, Object elemPadre, char posicion) {
        boolean exito = true;

        if (this.raiz == null) {
            // si el árbol está vacío, pone elemNuevo en la raíz
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            // si el árbol no está vacío, busca al padre
            NodoArbol nPadre = obtenerNodo(this.raiz, elemPadre);

            // si padre existe y posicion no está ocupado lo pone, sino da error
            if (nPadre != null) {
                if (posicion == 'I' && nPadre.getIzquierdo() == null) {
                    nPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if (posicion == 'D' && nPadre.getDerecho() == null) {
                    nPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                } else {
                    exito = false;
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        NodoArbol resultado = null;
        if (n != null) {
            if (n.getElem().equals(buscado)) {
                // si el buscado es n, lo devuelve
                resultado = n;
            } else {
                // no es el buscado: busca primero en el HI
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                // si no lo encontró en el HI, busca en HD
                if (resultado == null) {
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    // Devuelve falso si hay al menos un elem en el árbol. Caso contrario, false.
    public boolean esVacio() {
        return this.raiz == null;
    }

    /**
     * Devuelve la altura del árbol, es decir, la longitud del camino más largo
     * desde la raíz hasta una hoja. Si el árbol está vacío, devuelve -1.
     */
    public int altura() {
        int altura = -1;

        if (this.raiz != null) {
            int elementosEnNivel = 1; // nro de elementos en nivel actual
            Cola q = new Cola();
            q.poner(this.raiz);

            while (!q.esVacia()) {
                int elementosSigNivel = 0; // número de elementos del siguiente nivel
                // Recorro la totalidad de los nodos en el nivel actual individualmente
                for (int i = 0; i < elementosEnNivel; i++) {
                    // Obtengo el nodo del frente de la colsa (el actual)
                    NodoArbol actual = (NodoArbol) q.obtenerFrente();

                    // Quito el nodo actual del frente de la cola
                    q.sacar();

                    // Si HI no vacío, lo agrego a la cola para ser visitado luego
                    if (actual.getIzquierdo() != null) {
                        q.poner(actual.getIzquierdo());
                        elementosSigNivel++;
                    }
                    // Si HD no vacío, lo agrego a la cola para ser visitado luego
                    if (actual.getDerecho() != null) {
                        q.poner(actual.getDerecho());
                        elementosSigNivel++;
                    }
                }
                altura++; // Al pasar de nivel, la altura aumenta en una unidad
                elementosEnNivel = elementosSigNivel; // Actualizo números al avanzar de nivel
            }
        }

        return altura;
    }

    /**
     * Devuelve el nivel de un elemento en el árbol. Si el elemento no existe,
     * devuelve -1.
     */
    public int nivel(Object elemento) {
        return nivelAux(this.raiz, elemento, 0);
    }

    /**
     * Recibe un nodo, el elemento buscado y el nivel donde se encuentra buscando.
     * Método PRIVADO, porque recibe NodoArbol como parámetro.
     */
    private int nivelAux(NodoArbol nodo, Object buscado, int nivelActual) {
        int nivel = -1; // si el árbol es vacío, devuelve -1

        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                // si el nodo contiene a buscado, devuelve el nivel actual
                nivel = nivelActual;
            } else {
                // no es el buscado: busca primero en el HI
                nivel = nivelAux(nodo.getIzquierdo(), buscado, nivelActual + 1);
                // si no lo encontró en el HI, busca en HD
                if (nivel == -1) {
                    nivel = nivelAux(nodo.getDerecho(), buscado, nivelActual + 1);
                }
            }
        }
        return nivel;
    }

    /**
     * Dado un 'elemento', devuelve el valor almacenado en su nodo padre (busca
     * la primera aparición de 'elemento'). Devuelve null en caso de que no exista
     */
    public Object padre(Object elemento) {
        Object elemPadre = null;
        // Si el elemento es la raíz, entonces no tiene padre
        boolean esLaRaiz = (this.raiz != null) && (this.raiz.getElem().equals(elemento));

        if (!esLaRaiz) {
            NodoArbol nPadre = obtenerPadre(this.raiz, elemento); // Busca el nodo padre
            if (nPadre != null) {
                elemPadre = nPadre.getElem();
            }
        }

        return elemPadre;
    }

    private NodoArbol obtenerPadre(NodoArbol nodo, Object buscado) {
        /**
         * Método PRIVADO. Retorna el nodo padre del nodo que contiene al
         * elemento buscado. Si no lo encuentra, devuelve null.
         */
        NodoArbol nodoPadre = null;

        if (nodo != null) {
            // Verifico si 'buscado' está en alguno de los hijos del nodo actual
            boolean exitoIzq = nodo.getIzquierdo() != null && nodo.getIzquierdo().getElem().equals(buscado);
            boolean exitoDer = nodo.getDerecho() != null && nodo.getDerecho().getElem().equals(buscado);
            if (exitoIzq || exitoDer) {
                // si el buscado está en el HI o HD, devuelvo el nodo actual (padre)
                nodoPadre = nodo;
            } else {
                // no es el buscado: busca primero en el HI
                nodoPadre = obtenerPadre(nodo.getIzquierdo(), buscado);
                // si no lo encontró en el HI, busca en HD
                if (nodoPadre == null) {
                    nodoPadre = obtenerPadre(nodo.getDerecho(), buscado);
                }
            }
        }
        return nodoPadre;
    }

    /**
     * Devuelve una lista con los elementos del árbol en el recorrido en PREORDEN
     */
    public Lista listarPreorden() {
        Lista lis = new Lista();
        listarPreordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPreordenAux(NodoArbol nodo, Lista lis) {
        // método recursivo PRIVADO porque su parámetro es de tipo NodoArbol

        if (nodo != null) {
            // visita al elemento en el nodo
            lis.insertar(nodo.getElem(), lis.longitud() + 1); // (1)

            // recorre a sus hijos en preorden
            listarPreordenAux(nodo.getIzquierdo(), lis); // (2)
            listarPreordenAux(nodo.getDerecho(), lis); // (3)
        }
    }

    /**
     * Devuelve una lista con los elementos del árbol en el recorrido en POSORDEN
     */
    public Lista listarPosorden() {
        Lista lis = new Lista();
        listarPosordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPosordenAux(NodoArbol nodo, Lista lis) {
        // método recursivo PRIVADO porque su parámetro es de tipo NodoArbol

        if (nodo != null) {
            // Recorre primero los subárboles izquierdo y derecho en posorden
            listarPosordenAux(nodo.getIzquierdo(), lis); // (1)
            listarPosordenAux(nodo.getDerecho(), lis); // (2)
            // Visita la raíz
            lis.insertar(nodo.getElem(), lis.longitud() + 1); // (3)
        }
    }

    /**
     * Devuelve una lista con los elementos del árbol en el recorrido en INORDEN
     */
    public Lista listarInorden() {
        Lista lis = new Lista();
        listarInordenAux(this.raiz, lis);
        return lis;
    }

    private void listarInordenAux(NodoArbol nodo, Lista lis) {
        // método recursivo PRIVADO porque su parámetro es de tipo NodoArbol

        if (nodo != null) {
            // Recorre primero el subárbol izquierdo
            listarInordenAux(nodo.getIzquierdo(), lis); // (1)

            // Visita la raíz
            lis.insertar(nodo.getElem(), lis.longitud() + 1); // (2)
            
            // Recorre subárbol derecho
            listarInordenAux(nodo.getDerecho(), lis); // (3)
        }
    }

}
