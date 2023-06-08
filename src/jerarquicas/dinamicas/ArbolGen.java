/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas.dinamicas;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;

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
        boolean exito = false;

        if (this.raiz == null) {
            // si el árbol está vacío, inserta elemNuevo como raíz del mismo
            this.raiz = new NodoGen(elemNuevo, null, null);
            exito = true;
        } else {
            // Busca al nodo con la primera aparición de elemPadre
            NodoGen nodoPadre = obtenerNodo(this.raiz, elemPadre);
            if (nodoPadre != null) {
                // si encontró al nodo padre, entonces puede insertar elemNuevo
                // Crea nuevoNodo con elemNuevo y enlaces null
                NodoGen nuevoNodo = new NodoGen(elemNuevo, null, null);
                if (nodoPadre.getHijoIzquierdo() == null) {
                    // si el nodo padre no tenía hijos, inserta nuevoNodo como hijo izquierdo
                    nodoPadre.setHijoIzquierdo(nuevoNodo);
                    exito = true;
                } else {
                    // si nodoPadre tiene hijoIzquierdo, entonces recorre los hermanos derechos
                    // de HI hasta que llega al último hermano
                    NodoGen ultimoHermano = nodoPadre.getHijoIzquierdo();
                    while (ultimoHermano.getHermanoDerecho() != null) {
                        ultimoHermano = ultimoHermano.getHermanoDerecho();
                    }
                    ultimoHermano.setHermanoDerecho(nuevoNodo);
                    exito = true;
                }
            }
        }

        return exito;
    }

    private NodoGen obtenerNodo(NodoGen nodo, Object buscado) {
        // metodo PRIVADO que busca un elemento y devuelve el nodo que
        // lo contiene. Si no se encuentra el elemento buscado, devuelve null
        NodoGen resultado = null;

        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                // si el buscado es nodo, lo devuelve
                resultado = nodo;
            } else {
                // obtengo el hijo izquierdo del nodo
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null && resultado == null) {
                    // recorre los hijos de nodo hasta que no tenga más hijos
                    // o encuentre al nodo con el elemento buscado
                    resultado = obtenerNodo(hijo, buscado);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return resultado;
    }

    /*
     * Devuelve verdadero si el elemento pasado por parámetro está en el árbol.
     * Falso en caso contrario.
     */
    public boolean pertenece(Object elemento) {
        return perteneceAux(this.raiz, elemento);
    }

    private boolean perteneceAux(NodoGen nodo, Object buscado) {
        // método recursivo privado que recorre el árbol para verificar si
        // algún nodo contiene al elemento buscado. Devuelve true si lo encontró.
        // False en caso contrario
        boolean exito = false;
        if (nodo != null) {
            // Hace la visita al nodo y compara su elemento con el buscado
            if (nodo.getElem().equals(buscado)) {
                exito = true;
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null && !exito) {
                    if (perteneceAux(hijo, buscado)) { // llamado recursivo
                        exito = true;
                    } else {
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }
        }
        return exito;
    }

    /*
     * Si el elemento se encuentra en el árbol, devuelve una lista con los ancestros
     * del elemento. Si el elemento no está en el árbol, devuelve la lista vacía.
     */
    public Lista ancestros(Object elem) {
        Lista ancestros = new Lista();
        ancestrosAux(this.raiz, elem, ancestros);
        return ancestros;
    }

    private boolean ancestrosAux(NodoGen nodo, Object buscado, Lista lis) {
        // método privado recursivo que recorre el árbol buscando los ancestros
        // del elemento pasado por parámetro y los inserta en una lista
        boolean encontrado = false;

        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                encontrado = true;
            } else {
                NodoGen hijo = nodo.getHijoIzquierdo();
                while (hijo != null && !encontrado) {
                    // Busco mientras hayan hijos y no haya encontrado al elemento
                    encontrado = ancestrosAux(hijo, buscado, lis);
                    hijo = hijo.getHermanoDerecho();
                }

                if (encontrado) {
                    lis.insertar(nodo.getElem(), lis.longitud() + 1);
                }
            }
        }
        return encontrado;
    }

    // Devuelve true si el árbol es vacío, falso en caso contrario.
    public boolean esVacio() {
        return this.raiz == null;
    }

    /*
     * Devuelve la altura del árbol, es decir la longitud del camino más largo
     * desde la raíz hasta una hoja.
     * Nota: un árbol vacío tiene altura -1 y una hoja tiene altura 0.
     */
    public int altura() {
        return alturaAux(this.raiz);
    }

    private int alturaAux(NodoGen nodo) {
        // método PRIVADO que recorre el árbol y calcula la longitud del camino más
        // largo desde la raíz hasta una hoja
        int aux = -1;
        int resultado = -1;
        if (nodo != null) {
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                aux = alturaAux(hijo);
                if (aux > resultado) {
                    resultado = aux;
                }
                hijo = hijo.getHermanoDerecho();
            }
            resultado++;
        }
        return resultado;
    }

    // Devuelve el nivel de un elemento en el árbol. Si este no existe, devuelve -1.
    public int nivel(Object elemento) {
        return nivelAux(this.raiz, elemento, 0);
    }

    private int nivelAux(NodoGen nodo, Object buscado, int nivelActual) {
        // método PRIVADO que busca un elemento y devuelve el nivel del nodo que
        // lo contiene. Si no se encuentra el elemento buscado, devuelve -1.

        int nivel = -1;

        if (nodo != null) {
            if (nodo.getElem().equals(buscado)) {
                // si encuentro buscado en el nodo, devuelvo el nivel actual
                nivel = nivelActual;
            } else {
                // no es el buscado: busca primero en el HEI y luego en HD
                NodoGen hijo = nodo.getHijoIzquierdo();
                boolean exito = false;
                while (hijo != null && !exito) {
                    nivel = nivelAux(hijo, buscado, nivelActual + 1);
                    if (nivel == -1) {
                        hijo = hijo.getHermanoDerecho(); // avanza al HD si no encue
                    } else {
                        exito = true;
                    }
                }

            }
        }
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
     * (2.1) recorrer hijo i en Preorden
     * Fin algoritmo
     */
    public Lista listarPreorden() {
        Lista lis = new Lista();
        listarPreordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPreordenAux(NodoGen nodo, Lista lis) {
        // método recursivo PRIVADO que recorre el árbol en preorden y almacena sus
        // elementos en una lista. Es privado porque uno de los parámetros es un nodo.

        if (nodo != null) {
            lis.insertar(nodo.getElem(), lis.longitud() + 1); // (1)
            // continúo con los hijos del nodo
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) { // (2)
                listarPreordenAux(hijo, lis); // (2.1)
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    /*
     * Devuelve una lista con los elementos del árbol en el recorrido en posorden.
     * Si el árbol es vacío, devuelve una lista vacía.
     * Algoritmo posorden(nodo)
     * (1) Para cada hijo i de nodo
     * (1.1) recorrer hijo i en Preorden
     * (2) visitar la raíz del subárbol (nodo)
     * Fin algoritmo
     */
    public Lista listarPosorden() {
        Lista lis = new Lista();
        listarPosordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPosordenAux(NodoGen nodo, Lista lis) {
        // método recursivo PRIVADO que recorre el árbol en posorden y almacena sus
        // elementos en una lista. Es privado porque uno de los parámetros es un nodo.

        if (nodo != null) {
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) { // (1)
                listarPosordenAux(hijo, lis);
                ; // (1.1)
                hijo = hijo.getHermanoDerecho();
            }
            lis.insertar(nodo.getElem(), lis.longitud() + 1); // (2)
        }
    }

    /*
     * Devuelve una lista con los elementos del árbol en el recorrido en inorden.
     * Si el árbol es vacío, devuelve una lista vacía.
     * Algoritmo inorden(nodo)
     * (1) recorrer en inorden el primer hijo de nodo
     * (2) visitar la raíz del subárbol (nodo)
     * (3) Para cada hijo i de nodo (donde i >= 2)
     * (3.1) recorrer hijo i en inorden
     * Fin algoritmo
     */
    public Lista listarInorden() {
        Lista salida = new Lista();
        listarInordenAux(this.raiz, salida);
        return salida;
    }

    private void listarInordenAux(NodoGen n, Lista ls) {
        // método recursivo PRIVADO que recorre el árbol en inorden y almacena sus
        // elementos en una lista. Es privado porque uno de los parámetros es un nodo.
        if (n != null) {
            // llamado recursivo con primer hijo de n (1)
            if (n.getHijoIzquierdo() != null) {
                listarInordenAux(n.getHijoIzquierdo(), ls);
            }

            // visita del nodo n (2)
            ls.insertar(n.getElem(), ls.longitud() + 1);

            // llamados recursivos con los otros hijos de n (3)
            if (n.getHijoIzquierdo() != null) {
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarInordenAux(hijo, ls); // (3.1)
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    /*
     * Devuelve una lista con los elementos del árbol en el recorrido por niveles.
     * Si el árbol es vacío, devuelve una lista vacía.
     */
    public Lista listarPorNiveles() {
        Lista lis = new Lista();

        Cola q = new Cola();
        q.poner(this.raiz);

        while (!q.esVacia()) {
            NodoGen nodo = (NodoGen) q.obtenerFrente();
            q.sacar();
            lis.insertar(nodo.getElem(), lis.longitud() + 1);
            NodoGen hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                q.poner(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }

        return lis;
    }

    /*
     * Genera y devuelve un árbol genérico que es equivalente (igual estructura
     * y contenido de los nodos) que el árbol original.
     */
    @Override
    public ArbolGen clone() {
        ArbolGen clon = new ArbolGen();
        clon.raiz = cloneAux(this.raiz);
        return clon;
    }

    private NodoGen cloneAux(NodoGen nodo) {
        // método recursivo PRIVADO que recorre el árbol mientras va creando nodos con
        // los hijos (y hermanos derechos) del original para insertarlos en el clon
        NodoGen nodoClonado = new NodoGen(nodo.getElem(), null, null);

        // Clonar los hijos del nodo
        if (nodo.getHijoIzquierdo() != null) {
            nodoClonado.setHijoIzquierdo(cloneAux(nodo.getHijoIzquierdo()));
        }

        // Clonar los hermanos del nodo
        if (nodo.getHermanoDerecho() != null) {
            nodoClonado.setHermanoDerecho(cloneAux(nodo.getHermanoDerecho()));
        }

        return nodoClonado;
    }

    // Quita todos los elementos de la estructura.
    public void vaciar() {
        this.raiz = null;
    }

    /*
     * Genera y devuelve una cadena de caracteres que indica cuál es la raíz del
     * árbol y quienes son los hijos de cada nodo.
     */
    @Override
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

    /*
     * Devuelve un número que representa el grado del árbol (el mayor
     * grado de los nodos que contiene el árbol)
     * Nota: el grado de un nodo es la cantidad de hijos que tiene
     */
    public int grado() {
        return gradoAux(this.raiz);
    }

    private int gradoAux(NodoGen nodo) {
        // método recursivo privado
        int grado = 0;
        // Agregar código
        return grado;
    }

    public int gradoSubArbol() {
        return gradoSubArbolAux(this.raiz);
    }

    private int gradoSubArbolAux(NodoGen nodo) {
        int grado = 0;
        // Agregar código
        return grado;
    }

    public Lista listaQueJustificaLaAltura() {
        Lista actual = new Lista();
        Lista resultado = new Lista();
        if (this.raiz != null) {
            resultado = listaQueJustificaLaAlturaAux(this.raiz, actual, resultado);
        }
        return resultado;
    }

    private Lista listaQueJustificaLaAlturaAux(NodoGen n, Lista actual, Lista resultado) {
        Lista blablabla = new Lista(); // cambiar luego
        /*
         * CASO BASE: n es hoja
         * Ver si actual es más larga que la otra lista...
         * Si actual es más larga, devolver un clon de actual
         */

        // Agregar código

        /*
         * CASO RECURSIVO: n tiene hijos
         * Llamar con todos los hijos de n recibiendo la lista resultado
         * enviada por sus hijos... luego retornar la última lista
         * recibida de sus hijos
         */
        return blablabla; // cambiar
    }
}
