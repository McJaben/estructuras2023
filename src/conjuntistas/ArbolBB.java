package conjuntistas;

import lineales.dinamicas.Cola;
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
        int comparacion = elemento.compareTo(n.getElem());
        if ((comparacion == 0)) {
            // Reportar error: Elemento repetido
            exito = false;
        } else if (comparacion < 0) {
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
                    // Si buscador es mayor = 'd' (HD), si es menor = 'i' (HI)
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
     * Recorre el árbol completo y devuelve una lista ordenada con los elementos que
     * se encuentran almacenados en él
     */
    public Lista listar() {
        Lista lis = new Lista();
        NodoABB<T> aux = this.raiz;
        if (aux != null) {
            int posInicial = 0; // posicion inicial al insertar en la lista
            this.listarAux(lis, aux, posInicial);
        }
        return lis;
    }

    /*
     * Método auxiliar y privado, que recorre la estructura de forma recursiva.
     * list: lista a manipular
     * n: nodo
     * pos: posición del último elemento insertado
     * Retorna entero: posición del
     */
    private int listarAux(Lista list, NodoABB<T> n, int pos) {
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
     * Recorre parte del árbol (sólo lo necesario) y devuelve una lista ordenada
     * con los elementos que se encuentran almacenados en él.
     */
    public Lista listarRango(T minElem, T maxElem) {
        Lista lis = new Lista();
        NodoABB<T> raiz = this.raiz;
        if (raiz != null) {
            this.listarRangoAux(lis, raiz, minElem, maxElem);
        }
        return lis;
    }

    /*
     * Método auxiliar y privado, que recorre la estructura de forma recursiva.
     * list: lista a manipular
     * n: nodo
     */
    private void listarRangoAux(Lista list, NodoABB<T> n, T min, T max) {
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
     * Recorre la rama correspondiente y devuelve el elemento más pequeño almacenado
     * en el árbol.
     * Si el árbol está vacío, devuelve null
     */
    public T minimoElem() {
        T elem = null;
        // Como es un árbol ordenado, el menor de los elementos es el que se encuentra más a la izquierda
        NodoABB<T> n = this.raiz;

        while (n != null) {
            elem = n.getElem();
            n = n.getIzquierdo();
        }

        return elem;
    }

    /*
     * Recorre la rama correspondiente y devuelve el elemento más grande almacenado
     * en el árbol.
     */
    public T maximoElem() {
        T elem = null;
        // Como es un árbol ordenado, el mayor de los elementos es el que se encuentra más a la derecha
        NodoABB<T> n = this.raiz;
        
        while (n != null) {
            elem = n.getElem();
            n = n.getDerecho();
        }

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

    private String toStringAux(NodoABB<T> nodo) {
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
                    NodoABB<T> actual = (NodoABB<T>) cola.obtenerFrente();
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
