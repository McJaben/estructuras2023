package jerarquicas.dinamicas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

/**
 *
 * @author Benjamín Morales <benjamin.morales at est.fi.uncoma.edu.ar>
 */
public class ArbolBin {

    // atributos
    private NodoArbol raiz;

    // constructor vacío
    public ArbolBin() {
        this.raiz = null;
    }

    /*
     * Inserta elemNuevo como hijo del primer nodo encontrado en preorden igual
     * a elemPadre, como hijo izquierdo (I) o derecho (D), segun lo indique el
     * parametro posicion.
     */
    public boolean insertar(Object elemNuevo, Object elemPadre, char posicion) {

        boolean exito = true;

        if (this.raiz == null) {
            // si el arbol está vacío, pone elemNuevo en la raíz
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            // si el arbol no está vacío, busca al padre
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

    private NodoArbol obtenerNodo(NodoArbol nodo, Object buscado) {
        // metodo PRIVADO que busca un elemento y devuelve el nodo que
        // lo contiene. Si no se encuentra el elemento buscado, devuelve null

        NodoArbol resultado = null;

        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                // si el buscado es nodo, lo devuelve
                resultado = nodo;
            } else {
                // no es el buscado: busca primero en el HI
                resultado = obtenerNodo(nodo.getIzquierdo(), buscado);
                // si no lo encontró en el HI, busca en el HD
                if (resultado == null) {
                    resultado = obtenerNodo(nodo.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    // Devuelve falso si hay al menos un elemento cargado en el árbol, verdadero en
    // caso contrario.
    public boolean esVacio() {
        return this.raiz == null;
    }

    // Quita todos los elementos de la estructura.
    public void vaciar() {
        this.raiz = null;
    }

    /*
     * Devuelve la altura del árbol, es decir, la longitud del camino más largo
     * desde la raíz hasta una hoja
     */
    public int altura() {
        int altura;

        if (this.raiz == null) {
            // si el arbol está vacío, devuelve -1
            altura = -1;
        } else {
            altura = -1;
            int elementosEnNivel = 1; // Número de elementos en el nivel actual
            Cola q = new Cola();
            q.poner(this.raiz);

            while (!q.esVacia()) {
                int elementosSigNivel = 0; // Número de elementos en el siguiente nivel
                // Recorremos todos los nodos del nivel actual
                for (int i = 0; i < elementosEnNivel; i++) {
                    // Obtengo el nodo actual de la cola
                    NodoArbol actual = (NodoArbol) q.obtenerFrente();

                    // Sacamos el nodo actual de la cola
                    q.sacar();

                    // Agregamos los hijos del nodo actual a la cola, si existen
                    if (actual.getIzquierdo() != null) {
                        q.poner(actual.getIzquierdo());
                        elementosSigNivel++;
                    }
                    if (actual.getDerecho() != null) {
                        q.poner(actual.getDerecho());
                        elementosSigNivel++;
                    }
                }
                altura++; // Incrementamos la altura en 1
                elementosEnNivel = elementosSigNivel; // Actualizamos el num de elementos
            }
        }
        return altura;
    }

    /*
     * Devuelve el nivel de un elemento cargado en el árbol. Si el elemento no
     * existe, devuelve -1.
     */
    public int nivel(Object elemento) {
        return nivelAux(this.raiz, elemento, 0);
    }

    private int nivelAux(NodoArbol nodo, Object buscado, int nivelActual) {
        // método PRIVADO que busca un elemento y devuelve el nivel del nodo que
        // lo contiene. Si no se encuentra el elemento buscado, devuelve -1.

        int nivel = -1;

        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                // si encuentro buscado en el nodo, devuelvo el nivel actual
                nivel = nivelActual;
            } else {
                // no es el buscado: busca primero en el HI
                nivel = nivelAux(nodo.getIzquierdo(), buscado, nivelActual + 1);
                // si no lo encontró en el HI, busca en el HD
                if (nivel == -1) {
                    nivel = nivelAux(nodo.getDerecho(), buscado, nivelActual + 1);
                }
            }
        }
        return nivel;
    }

    // Dado un elemento, devuelve el valor almacenado en su nodo padre (primer
    // aparición).
    public Object padre(Object elemento) {
        Object elemPadre = null;
        if (this.raiz != null && this.raiz.getElem().equals(elemento)) {
            // el elemento es la raíz, y ésta no tiene padre
            elemPadre = null;
        } else {
            NodoArbol nPadre = obtenerPadre(this.raiz, elemento);
            if (nPadre != null) {
                elemPadre = nPadre.getElem();
            }
        }
        return elemPadre;
    }

    private NodoArbol obtenerPadre(NodoArbol nodo, Object buscado) {
        // metodo PRIVADO que busca un elemento y devuelve el padre del nodo que
        // lo contiene. Si no se encuentra el elemento buscado, devuelve null
        NodoArbol padreEncontrado = null;

        if (nodo != null) {
            // Verifico si buscado está en hijo izquierdo o derecho
            boolean exitoI = nodo.getIzquierdo() != null && nodo.getIzquierdo().getElem().equals(buscado);
            boolean exitoD = nodo.getDerecho() != null && nodo.getDerecho().getElem().equals(buscado);
            if (exitoI || exitoD) {
                // Se encontró al padre del elemento buscado
                padreEncontrado = nodo;
            } else {
                padreEncontrado = obtenerPadre(nodo.getIzquierdo(), buscado);
                if (padreEncontrado == null) {
                    padreEncontrado = obtenerPadre(nodo.getDerecho(), buscado);
                }
            }
        }
        return padreEncontrado;
    }

    /*
     * Devuelve una lista con los elementos del árbol binario en el recorrido en
     * preorden. Algoritmo del recorrido en preorden:
     * (1) visitar la raíz del subárbol(nodo)
     * (2) recorrer hijo_izquierdo(nodo) en Preorden
     * (3) recorrer hijo_derecho(nodo) en Preorden
     */
    public Lista listarPreorden() {
        Lista lisPre = new Lista();
        listarPreordenAux(this.raiz, lisPre);
        return lisPre;
    }

    private void listarPreordenAux(NodoArbol nodo, Lista lis) {
        // método recursivo PRIVADO que recorre el árbol en preorden y almacena sus
        // elementos en una lista. Es privado porque uno de los parámetros es un nodo.

        if (nodo != null) {
            // visita el elemento en el nodo
            lis.insertar(nodo.getElem(), lis.longitud() + 1); // (1)

            // recorre a sus hijos en preorden
            listarPreordenAux(nodo.getIzquierdo(), lis); // (2)
            listarPreordenAux(nodo.getDerecho(), lis); // (3)
        }
    }

    /*
     * Devuelve una lista con los elementos del árbol binario en el recorrido en
     * posorden. Algoritmo del recorrido en posorden:
     * (1) recorrer hijo_izquierdo(nodo) en Posorden
     * (2) recorrer hijo_derecho(nodo) en Posorden
     * (3) visitar la raíz del subárbol(nodo)
     */
    public Lista listarPosorden() {
        Lista lisPos = new Lista();
        listarPosordenAux(this.raiz, lisPos);
        return lisPos;
    }

    private void listarPosordenAux(NodoArbol nodo, Lista lis) {
        // método recursivo PRIVADO que recorre el árbol en posorden y almacena sus
        // elementos en una lista. Es privado porque uno de los parámetros es un nodo.

        if (nodo != null) {
            // recorre el subarbol izquierdo
            listarPosordenAux(nodo.getIzquierdo(), lis);

            // recorre el subarbol derecho
            listarPosordenAux(nodo.getDerecho(), lis);

            // visita la raíz del subárbol
            lis.insertar(nodo.getElem(), lis.longitud() + 1);

        }
    }

    /*
     * Devuelve una lista con los elementos del árbol binario en el recorrido en
     * inorden. Algoritmo del recorrido en inorden:
     * (1) recorrer hijo_izquierdo(nodo) en Inorden
     * (2) visitar la raíz del subárbol(nodo)
     * (3) recorrer hijo_derecho(nodo) en Inorden
     */
    public Lista listarInorden() {
        Lista lisIn = new Lista();
        listarInordenAux(this.raiz, lisIn);
        return lisIn;
    }

    private void listarInordenAux(NodoArbol nodo, Lista lis) {
        // método recursivo PRIVADO que recorre el árbol en inorden y almacena sus
        // elementos en una lista. Es privado porque uno de los parámetros es un nodo.
        if (nodo != null) {

            // recorre el subárbol izquierdo
            listarInordenAux(nodo.getIzquierdo(), lis);

            // visita la raíz del subárbol
            lis.insertar(nodo.getElem(), lis.longitud() + 1);

            // recorre el subárbol derecho
            listarInordenAux(nodo.getDerecho(), lis);
        }
    }

    /*
     * Devuelve una lista con los elementos del árbol binario en el recorrido por
     * niveles.
     */
    public Lista listarPorNiveles() {
        Lista lis = new Lista();

        // si el arbol está vacío, esto no se ejecuta y devuelve una lista vacía
        if (this.raiz != null) {
            int elementosEnNivel = 1; // Número de elementos en el nivel actual
            Cola q = new Cola();
            lis.insertar(this.raiz.getElem(), lis.longitud() + 1);
            q.poner(this.raiz);

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
        return lis;
    }

    /*
     * Genera y devuelve un árbol binario equivalente (igual estructura y contenido
     * de los nodos) que el árbol original.
     */
    @Override
    public ArbolBin clone() {
        ArbolBin newArbol = new ArbolBin();
        newArbol.raiz = cloneAux(this.raiz);
        return newArbol;
    }

    private NodoArbol cloneAux(NodoArbol nodo) {
        // método PRIVADO que recorre el árbol mientras va creando nodos con los
        // hijos del original para insertarlos en el clon
        NodoArbol newNodo = null;
        if (nodo != null) {
            newNodo = new NodoArbol(nodo.getElem(), null, null);
            newNodo.setIzquierdo(cloneAux(nodo.getIzquierdo()));
            newNodo.setDerecho(cloneAux(nodo.getDerecho()));
        }
        return newNodo;
    }

    /*
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
        String s = "";
        // si el arbol está vacío, esto no se ejecuta y devuelve una cadena vacía
        if (nodo != null) {
            int elementosEnNivel = 1; // Número de elementos en el nivel actual
            Cola q = new Cola();
            q.poner(this.raiz);

            // Mientras la cola no sea vacía
            while (!q.esVacia()) {
                int elementosSigNivel = 0; // Número de elementos en el siguiente nivel
                // Recorremos todos los nodos del nivel actual y los insertamos en la lista
                for (int i = 0; i < elementosEnNivel; i++) {
                    // Obtengo el nodo actual de la cola
                    NodoArbol actual = (NodoArbol) q.obtenerFrente();
                    // Sacamos el nodo actual de la cola
                    q.sacar();
                    s += actual.getElem();
                    // Agregamos los hijos del nodo actual a la cola, si existen
                    if (actual.getIzquierdo() != null) {
                        q.poner(actual.getIzquierdo());
                        s += " HI: " + actual.getIzquierdo().getElem();
                        elementosSigNivel++;
                    } else {
                        s += " HI: -";
                    }
                    if (actual.getDerecho() != null) {
                        q.poner(actual.getDerecho());
                        s += " HD: " + actual.getDerecho().getElem() + "\n";
                        elementosSigNivel++;
                    } else {
                        s += " HD: - \n";
                    }
                }
                // Actualizamos el número de elementos
                elementosEnNivel = elementosSigNivel;
            }
        }
        return s;
    }

    /*
     * Devuelve una lista con todos los elementos almacenados en las hojas del
     * árbol listadas de izquierda a derecha.
     */
    public Lista frontera() {
        Lista lis = new Lista();
        fronteraAux(this.raiz, lis);
        return lis;
    }

    private void fronteraAux(NodoArbol nodo, Lista lis) {
        // método recursivo PRIVADO que recorre el árbol en posorden y almacena sus
        // hojas en una lista. Es privado porque uno de los parámetros es un nodo.

        if (nodo != null) {

            // recorre el subarbol izquierdo
            fronteraAux(nodo.getIzquierdo(), lis);

            // recorre el subarbol derecho
            fronteraAux(nodo.getDerecho(), lis);

            // visita la raíz del subárbol
            if ((nodo.getIzquierdo() == null) && (nodo.getDerecho() == null)) {
                lis.insertar(nodo.getElem(), lis.longitud() + 1);
            }
        }
    }

    /*
     * Devuelve una lista de los ancestros de la primera aparición de un elemento.
     * Si hay elementos duplicados, va a devolver los ancestros de la primera
     * aparición.
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

    /*
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
