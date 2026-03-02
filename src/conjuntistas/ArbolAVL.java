package conjuntistas;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;

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

    /*
     * Devuelve verdadero si el elemento recibido por parámetro está en el árbol y falso en caso
     * contrario.
     */
    public boolean pertenece(T elem) {
        return perteneceAux(this.raiz, elem);
    }

    /*
     * Método auxiliar y privado, que recorre la estructura de forma recursiva.
     */
    private boolean perteneceAux(NodoAVL<T> n, T elemento) {
        boolean exito = false;
        if (n != null) {
            int comparacion = elemento.compareTo(n.getElem());
            if ((comparacion == 0)) {
                // Elemento encontrado
                exito = true;
            } else if (comparacion < 0) {
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
     * Implementación iterativa del método pertenece(). Devuelve verdadero si el elemento recibido
     * por parámetro está en el árbol, falso en caso contrario.
     */
    public boolean perteneceIterativo(T elemento) {
        NodoAVL<T> actual = this.raiz;
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
     * Recorre el árbol completo y devuelve una lista ordenada con los elementos que se encuentran
     * almacenados en él
     */
    public Lista listar() {
        Lista lis = new Lista();
        NodoAVL<T> aux = this.raiz;
        if (aux != null) {
            int posInicial = 0; // posicion inicial al insertar en la lista
            this.listarAux(lis, aux, posInicial);
        }
        return lis;
    }

    /*
     * Método auxiliar y privado, que recorre la estructura de forma recursiva. list: lista a
     * manipular n: nodo pos: posición del último elemento insertado Retorna entero: posición del
     */
    private int listarAux(Lista list, NodoAVL<T> n, int pos) {
        int aux = pos;
        if (n != null) {
            // Si tiene HI, sigo recorriendo por la rama izquierda
            if (n.getIzquierdo() != null) {
                aux = this.listarAux(list, n.getIzquierdo(), pos);
            }
            aux++; // Incremento la posición
            list.insertar(n.getElem(), aux);
            if (n.getDerecho() != null) {
                aux = this.listarAux(list, n.getDerecho(), aux);
            }
        }
        return aux;
    }

    /*
     * Recorre parte del árbol (sólo lo necesario) y devuelve una lista ordenada con los elementos
     * que se encuentran almacenados en él.
     */
    public Lista listarRango(T minElem, T maxElem) {
        Lista lis = new Lista();
        NodoAVL<T> raiz = this.raiz;
        if (raiz != null) {
            this.listarRangoAux(lis, raiz, minElem, maxElem);
        }
        return lis;
    }

    /*
     * Método auxiliar y privado, que recorre la estructura de forma recursiva. list: lista a
     * manipular n: nodo
     */
    private void listarRangoAux(Lista list, NodoAVL<T> n, T min, T max) {
        if (n != null) {
            T valorNodo = n.getElem();

            // Si n es mayor a min, recorrer HI
            if (valorNodo.compareTo(min) > 0) {
                this.listarRangoAux(list, n.getIzquierdo(), min, max);
            }

            // Si n está dentro del rango [min, max], insertar n
            if (valorNodo.compareTo(min) >= 0 && valorNodo.compareTo(max) <= 0) {
                list.insertar(valorNodo, list.longitud() + 1);
            }

            // Si n es menor a max, recorrer HD
            if (valorNodo.compareTo(max) < 0) {
                this.listarRangoAux(list, n.getDerecho(), min, max);
            }
        }
    }

    /*
     * Recorre la rama correspondiente y devuelve el elemento más pequeño almacenado en el árbol. Si
     * el árbol está vacío, devuelve null
     */
    public T minimoElem() {
        T elem = null;
        // Como es un árbol ordenado, el menor de los elementos es el que se encuentra más a la
        // izquierda
        NodoAVL<T> n = this.raiz;

        while (n != null) {
            elem = n.getElem();
            n = n.getIzquierdo();
        }

        return elem;
    }

    /*
     * Recorre la rama correspondiente y devuelve el elemento más grande almacenado en el árbol.
     */
    public T maximoElem() {
        T elem = null;
        // Como es un árbol ordenado, el mayor de los elementos es el que se encuentra más a la
        // derecha
        NodoAVL<T> n = this.raiz;

        while (n != null) {
            elem = n.getElem();
            n = n.getDerecho();
        }

        return elem;
    }

    /*
     * Devuelve falso si hay al menos un elemento en el árbol. Verdadero en caso contrario.
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

    /**
     * Genera y devuelve una cadena de caracteres que indica cuál es la raíz del árbol y quiénes son
     * los hijos de cada nodo.
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

    private String toStringAux(NodoAVL<T> nodo) {
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
                    @SuppressWarnings("unchecked")
                    NodoAVL<T> actual = (NodoAVL<T>) cola.obtenerFrente();
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
}
