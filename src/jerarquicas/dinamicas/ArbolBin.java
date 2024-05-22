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
            Cola cola = new Cola();
            cola.poner(this.raiz);

            while (!cola.esVacia()) {
                int elementosSigNivel = 0; // número de elementos del siguiente nivel
                // Recorro la totalidad de los nodos en el nivel actual individualmente
                for (int i = 0; i < elementosEnNivel; i++) {
                    // Obtengo el nodo del frente de la cola (el actual)
                    NodoArbol actual = (NodoArbol) cola.obtenerFrente();

                    // Quito el nodo actual del frente de la cola
                    cola.sacar();

                    // Si HI no nulo, lo agrego a la cola para ser visitado luego
                    if (actual.getIzquierdo() != null) {
                        cola.poner(actual.getIzquierdo());
                        elementosSigNivel++;
                    }
                    // Si HD no nulo, lo agrego a la cola para ser visitado luego
                    if (actual.getDerecho() != null) {
                        cola.poner(actual.getDerecho());
                        elementosSigNivel++;
                    }
                }
                altura++; // Al pasar de nivel, la altura aumenta en una unidad
                elementosEnNivel = elementosSigNivel; // Actualizo cant elementos al avanzar de nivel
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

    /**
     * Devuelve una lista con los elementos del árbol en el recorrido por niveles.
     * Las variables 'elemEnNivel' y 'elemSigNivel' sirven para controlar la
     * iteración y poder recorrer individualmente todos los nodos de cada nivel,
     * sin que falten o se repitan.
     */
    public Lista listarNiveles() {
        Lista list = new Lista();

        if (this.raiz != null) {
            int elemEnNivel = 1; // Existe sólo 1 elemento en el primer nivel: la raíz
            Cola cola = new Cola();
            cola.poner(this.raiz); // Agrego la raíz a la cola
            list.insertar(this.raiz.getElem(), list.longitud() + 1); // Inserto elemento de la raíz en la lista

            // Con la estructura cola como auxiliar voy visitando cada nivel del árbol, nodo
            // a nodo y voy insertando sus elementos en ese mismo orden a la lista
            while (!cola.esVacia()) {
                int elemSigNivel = 0; // nro de elementos en siguiente nivel

                // Recorro la totalidad de los nodos en el nivel actual individualmente
                for (int i = 0; i < elemEnNivel; i++) {
                    // Obtengo el nodo del frente de la cola
                    NodoArbol actual = (NodoArbol) cola.obtenerFrente();

                    // Quito el frente de la cola porque ya visité el nodo
                    cola.sacar();

                    // Si hijo izquierdo no nulo, lo agrego a la cola e inserto en la lista
                    if (actual.getIzquierdo() != null) {
                        cola.poner(actual.getIzquierdo());
                        list.insertar(actual.getIzquierdo().getElem(), list.longitud() + 1);
                        elemSigNivel++;
                    }
                    // Si hijo derecho no nulo, lo agrego a la cola e inserto en la lista
                    if (actual.getDerecho() != null) {
                        cola.poner(actual.getDerecho());
                        list.insertar(actual.getDerecho().getElem(), list.longitud() + 1);
                        elemSigNivel++;
                    }
                }
                elemEnNivel = elemSigNivel; // Actualizo cantidad de elementos al avanzar de nivel
            }
        }
        return list;
    }

    /**
     * Genera y devuelve un árbol binario equivalente (igual estructura y contenido
     * de los nodos) que el árbol original
     */
    @Override
    public ArbolBin clone() {
        ArbolBin clon = new ArbolBin();
        clon.raiz = cloneAux(this.raiz);
        return clon;
    }

    /**
     * Método PRIVADO que recorre el árbol original recursivamente mientras va
     * creando
     * nuevos nodos con los hijos del original para insertarlos en el clon.
     */
    private NodoArbol cloneAux(NodoArbol nodo) {
        NodoArbol nuevoNodo = null;

        if (nodo != null) {
            // Creo el nuevo nodo con el elemento del nodo actual, por ahora sin hijos
            nuevoNodo = new NodoArbol(nodo.getElem(), null, null);

            // Seteo como hijo izquierdo el subárbol izquierdo del nodo pasado por parámetro
            nuevoNodo.setIzquierdo(cloneAux(nodo.getIzquierdo()));

            // Seteo como hijo derecho el subárbol derecho del nodo pasado por parámetro
            nuevoNodo.setDerecho(cloneAux(nodo.getDerecho()));
        }

        return nuevoNodo;
    }

    /**
     * Quita todos los elementos de la estructura.
     */
    public void vaciar() {
        this.raiz = null;
    }

    /**
     * Genera y devuelve una cadena de caracteres que indica cuál es la raíz del
     * árbol y quiénes son los hijos de cada nodo.
     */
    @Override
    public String toString() {
        String cadena;
        if (this.raiz != null) {
            cadena = toStringAux(this.raiz);
        } else {
            cadena = "Árbol vacío";
        }
        return cadena;
    }

    private String toStringAux(NodoArbol nodo) {
        // método Privado que recorre el árbol por niveles y va guardando los
        // elementos de cada nodo y sus hijos en un String para luego retornarlo
        String cadena = "";
        // si el arbol está vacío, esto no se ejecuta y devuelve una cadena vacía
        if (nodo != null) {
            int elementosEnNivel = 1; // Número de elementos en el nivel actual
            Cola cola = new Cola();
            cola.poner(this.raiz);

            // Mientras la cola no sea vacía
            while (!cola.esVacia()) {
                int elementosSigNivel = 0; // Número de elementos en el siguiente nivel
                // Recorremos todos los nodos del nivel actual y los insertamos en la lista
                for (int i = 0; i < elementosEnNivel; i++) {
                    // Obtengo el nodo actual de la cola
                    NodoArbol actual = (NodoArbol) cola.obtenerFrente();
                    // Sacamos el nodo actual de la cola
                    cola.sacar();
                    cadena += actual.getElem();
                    // Agregamos los hijos del nodo actual a la cola, si existen
                    if (actual.getIzquierdo() != null) {
                        cola.poner(actual.getIzquierdo());
                        cadena += " HI: " + actual.getIzquierdo().getElem();
                        elementosSigNivel++;
                    } else {
                        cadena += " HI: -";
                    }
                    if (actual.getDerecho() != null) {
                        cola.poner(actual.getDerecho());
                        cadena += " HD: " + actual.getDerecho().getElem() + "\n";
                        elementosSigNivel++;
                    } else {
                        cadena += " HD: - \n";
                    }
                }
                // Actualizamos el número de elementos
                elementosEnNivel = elementosSigNivel;
            }
        }
        return cadena;
    }

    /**
     * Devuelve una lista con todos los elementos almacenados en las hojas del árbol
     * listadas de izquierda a derecha.
     */
    public Lista frontera() {
        Lista list = new Lista();
        fronteraAux(this.raiz, list);
        return list;
    }

    /**
     * Método PRIVADO que recorre el árbol en preorden y almacena las hojas del
     * mismo
     * en una lista.
     */
    private void fronteraAux(NodoArbol nodo, Lista list) {
        if (nodo != null) {
            // Recorre primero los subárboles izquierdo y derecho en posorden
            fronteraAux(nodo.getIzquierdo(), list); // (1)
            fronteraAux(nodo.getDerecho(), list); // (2)

            // Inserta el elemento del nodo sólo si es una hoja
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                list.insertar(nodo.getElem(), list.longitud() + 1); // (3)
            }
        }
    }

    /**
     * Devuelve una lista con todos los ancestros de la primera aparición del
     * elemento pasado por parámetro (si no está, devuelve una lista vacía)
     */
    public Lista obtenerAncestros(Object elem) {
        Lista ancestros = new Lista();

        // Verificar si el árbol está vacío
        if (this.raiz != null) {
            // Verifico si el elemento se encuentra en el árbol
            NodoArbol nodo = obtenerNodo(this.raiz, elem);
            if (nodo != null && nodo != this.raiz) {
                // Busco el padre del elemento en cuestión
                NodoArbol nPadre = obtenerPadre(this.raiz, elem);
                while (nPadre != null) {
                    ancestros.insertar(nPadre.getElem(), ancestros.longitud() + 1);
                    // System.out.println(ancestros.toString());
                    if (nPadre == this.raiz) {
                        nPadre = null;
                    } else {
                        nPadre = obtenerPadre(this.raiz, nPadre.getElem());
                    }
                }
            }
        }

        return ancestros;
    }

    /**
     * Devuelve una lista de los descendientes de la primera aparición de un
     * elemento. Si hay elementos duplicados, obtiene los descendientes de la
     * primer aparición de elem.
     */
    public Lista obtenerDescendientes(Object elem) {
        Lista descendientes = new Lista();
        if (this.raiz != null) {
            NodoArbol nodo = obtenerNodo(this.raiz, elem);
            if (nodo != null) {
                descendientesAux(nodo, descendientes);
            }
        }
        return descendientes;
    }

    private void descendientesAux(NodoArbol nodo, Lista lis) {
        // método recursivo PRIVADO que recorre el árbol por niveles y almacena los
        // hijos del nodo pasado por parámetro en una lista. Es privado porque
        // uno de los parámetros es un nodo.
        if (nodo != null) {
            int elementosEnNivel = 1; // Número de elementos en el nivel actual
            Cola q = new Cola();
            q.poner(nodo);

            while (!q.esVacia()) {
                int elementosSigNivel = 0; // Número de elementos en el siguiente nivel

                // Recorremos todos los nodos del nivel actual y los insertamos en la lista
                for (int i = 0; i < elementosEnNivel; i++) {
                    // Obtengo el nodo actual de la cola
                    NodoArbol actual = (NodoArbol) q.obtenerFrente();

                    // Sacamos el nodo actual de la cola
                    q.sacar();

                    // Agregamos los hijos del nodo actual a la cola, si existen
                    if (actual.getIzquierdo() != null) {
                        q.poner(actual.getIzquierdo());
                        lis.insertar(actual.getIzquierdo().getElem(), lis.longitud() + 1);
                        elementosSigNivel++;
                    }
                    if (actual.getDerecho() != null) {
                        q.poner(actual.getDerecho());
                        lis.insertar(actual.getDerecho().getElem(), lis.longitud() + 1);
                        elementosSigNivel++;
                    }
                }
                // Actualizamos el número de elementos
                elementosEnNivel = elementosSigNivel;
            }
        }
    }

}
