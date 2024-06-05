package lineales.dinamicas;

/**
 * *********** Autores ***********
 * - Kevin Manuel Quintero Martinez
 * - Benjamín Morales
 * - Angel Gabriel Avellaneda
 */

// Este es el que hice el 2023 con ayuda de Kevin y Ángel
public class Lista2 {

    private Nodo cabecera;

    // Crea y devuelve la lista vacía.
    public Lista2() {
        this.cabecera = null;
    }

    /*
     * Agrega el elemento pasado por parámetro en la posición pos, de manera que
     * la cantidad de elementos de la lista se incrementa en 1. Para una inserción
     * exitosa, la posición recibida debe ser 1 <= pos <= longitud(lista) + 1.
     * Devuelve true si se puede insertar correctamente y false en caso contrario.
     */
    public boolean insertar(Object nuevoElem, int pos) {
        // Insertar elemento nuevo en la posición pos.
        // Detecta y reporta error de posición inválida.
        boolean exito = true;
        int largo = this.longitud() + 1;

        if (pos < 1 || pos > largo) {
            exito = false; // Da falso si la posición es inválida.
        } else {
            if (pos == 1) { // Crea un nuevo nodo y se enlaza en la cabecera.
                this.cabecera = new Nodo(nuevoElem, this.cabecera);
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                // Recorre la estructura hasta el elemento anterior al que deseamos insertar
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }

                // Crea el nuevo nodo y lo enlaza con el siguiente.
                Nodo nuevo = new Nodo(nuevoElem, aux.getEnlace());
                // Enlaza el nodo anterior (pos-1) con el nodo nuevo
                aux.setEnlace(nuevo);
            }
        }

        /*
         * Nunca hay error de lista llena, entonces devuelve true, siempre que
         * la posición sea válida.
         */
        return exito;
    }

    /*
     * Borra el elemento de la posición pos, por lo que la cantidad de elementos de
     * la lista disminuye en uno.
     * Para una eliminación exitosa, la lista no debe estar vacía y la posición
     * recibida debe ser 1 <= pos <= longitud(lista).
     * Devuelve verdadero si se pudo eliminar correctamente y falso en caso
     * contrario.
     */
    public boolean eliminar(int pos) {
        boolean exito = false;
        int largo = this.longitud();

        if (this.cabecera != null && pos >= 1 && pos <= largo) {
            Nodo aux = this.cabecera;
            int i = 1;
            if (pos == 1) { // Caso especial: eliminar elemento en posicion 1
                this.cabecera = this.cabecera.getEnlace();
            } else { // Caso general
                // Avanza hasta el elemento en posición pos - 1
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                /*
                 * Enlaza al nodo en pos-1 con el que está en pos+1, haciendo un
                 * salto y eliminando al que está en pos pasada por parámetro
                 */
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
            exito = true;
        }
        return exito;
    }

    /*
     * Devuelve el elemento de la posición pos. La precondición es que la posición
     * sea válida.
     */
    public Object recuperar(int pos) {
        Object elemento = null;

        // Verifica que la lista no esté vacía y que la posición sea válida.
        if (!this.esVacia() && pos >= 1 && pos <= this.longitud()) {
            Nodo nodoActual = this.cabecera;
            int contador = 1;

            // Itera hasta llegar al nodo que está en la posición pos
            while (contador < pos) {
                nodoActual = nodoActual.getEnlace();
                contador++;
            }
            // Guarda el elemento que está en la posición pos del nodo.
            elemento = nodoActual.getElem();
        }
        return elemento;
    }

    /*
     * Devuelve la posición en la que se encuentra la primera ocurrencia de elem
     * dentro de la lista. En caso de no encontrarlo, devuelve -1.
     */
    public int localizar(Object elem) {
        int posicion = -1;
        boolean exito = false; // representa si se encontró o no el elem
        Nodo aux = this.cabecera; // Recorro desde la cabecera en adelante
        int i = 1;

        while (aux != null && !exito) {
            if (aux.getElem().equals(elem)) {
                /*
                 * Al encontrar el elemento, se guarda la posición y se actualiza
                 * la variable exito para cortar el bucle while
                 */
                posicion = i;
                exito = true;
            } else {
                // Si no se encuentra el elemento, avanzo al siguiente nodo
                aux = aux.getEnlace();
                i++;
            }
        }
        // Si no encuentra el elemento, posicion queda en -1 y se retorna ese valor
        return posicion;
    }

    /* Devuelve la cantidad de elementos de la lista. */
    public int longitud() {
        int contador = 0;
        Nodo aux = this.cabecera;

        /*
         * Arranca desde la cabecera y cuenta los nodos, uno por uno hasta llegar
         * al último (final de la lista)
         */
        while (aux != null) {
            contador++;
            aux = aux.getEnlace();
        }

        return contador;
    }

    /*
     * Devuelve verdadero si la lista no tiene elementos y falso en caso contrario.
     */
    public boolean esVacia() {
        return this.cabecera == null;
    }

    /*
     * Quita todos los elementos de la lista. Al ser nula la cabecera, no hay forma
     * de
     * alcanzar los demás nodos enlazados, por lo que el garbage collector se los
     * lleva
     */
    public void vaciar() {
        this.cabecera = null;
    }

    /*
     * Devuelve una copia exacta de los datos en la estructura original,
     * respetando el orden de los mismos, en otra estructura del mismo tipo.
     */
    @Override
    public Lista2 clone() {
        Lista2 listaClonada = new Lista2(); // crea una lista vacía

        // Si la lista es vacía, este if no se ejecuta y retorna una lista vacía
        if (this.cabecera != null) {
            /* Como la lista no es vacía, comienza a clonar desde la cabecera */
            listaClonada.cabecera = new Nodo(this.cabecera.getElem(), null);
            /*
             * Inicio el recorrido con el cursor aux1 apuntando a la posición 2
             * de la lista original, para ir una posición adelantado e ir enlazando
             * los nodos de la lista clonada
             */
            Nodo aux1 = this.cabecera.getEnlace();
            Nodo aux2 = listaClonada.cabecera;

            // Si el elemento en la posición 2 es nulo, no entra en el bucle
            while (aux1 != null) {
                // Itera mientras queden nodos por recorrer
                aux2.setEnlace(new Nodo(aux1.getElem(), null));
                aux2 = aux2.getEnlace();
                aux1 = aux1.getEnlace();
            }
        }

        return listaClonada;
    }

    /*
     * Crea y devuelve una cadena de caracteres formada por todos los elementos
     * de la lista para poder mostrarla por pantalla. Es recomendable utilizar este
     * método únicamente en etapa de prueba.
     */
    @Override
    public String toString() {
        String cadena = "[";
        Nodo iterador = this.cabecera;
        while (iterador != null) {
            // Recorre la estructura, nodo a nodo, hasta el final
            cadena += iterador.getElem();
            iterador = iterador.getEnlace();
            if (iterador != null) { // Para no imprimir una coma demás
                cadena += ",";
            }
        }
        cadena += "]";
        return cadena;
    }

    /*
     * Recorre la lista y devuelve true si tiene elementos repetidos. Caso
     * contrario,
     * retorna false. Precondición: la lista no debe estar vacía. Orden: O(n²)
     */
    public boolean elementosRepetidos() {
        boolean exito = false;

        if (this.cabecera != null) { // Verifica que la lista no está vacía
            Nodo aux = this.cabecera;
            Lista2 elemVistos = new Lista2();

            /*
             * Recorre toda la estructura hasta que encuentra un elemento repetido
             * o hasta que se acabe la lista (sin encontrar repetidos)
             */
            while ((aux != null) && !exito) {
                // Busca el elemento del nodo actual en la lista de elementos vistos
                if (elemVistos.localizar(aux.getElem()) != -1) {
                    exito = true; // Encontró un elemento repetido
                } else {
                    // Si no está repetido, se agrega a la lista de elementos vistos
                    elemVistos.insertar(aux.getElem(), elemVistos.longitud() + 1);
                }
                aux = aux.getEnlace();
            }
        }
        return exito;
    }

    // Forma alternativa de elementosRepetidos(). También es de orden O(n²)
    /*
     * Recorre la lista y devuelve true si tiene elementos repetidos. En caso
     * contrario, retorna false. Precondición: la lista no debe estar vacía
     */
    public boolean encontrarRepetidos() {
        boolean exito = false;

        if (this.cabecera != null) { // Verifica que la lista no está vacía.
            Nodo actual = this.cabecera;
            Nodo siguiente;

            /*
             * Recorre toda la estructura desde el nodo actual hasta que encuentra
             * un elemento repetido o hasta que se acabe la lista
             */
            while ((actual != null) && !exito) {
                siguiente = actual.getEnlace(); // Me posiciono a la derecha del nodo actual

                /* Compara el nodo actual con todos los que están a continuación, uno por uno */
                while ((siguiente != null) && !exito) {
                    if (actual.getElem().equals(siguiente.getElem())) {
                        exito = true;
                    }
                    siguiente = siguiente.getEnlace(); // Avanza al siguiente nodo
                }
                actual = actual.getEnlace(); // El nodo actual avanza una posición
            }
        }

        return exito;
    }
}
