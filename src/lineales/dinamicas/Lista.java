package lineales.dinamicas;

/**
 *
 * @author Benjamín Morales <benjamin.morales at est.fi.uncoma.edu.ar>
 *         This year (2024) I want to implement this in English, at least the
 *         comments
 */

public class Lista {

    Nodo cabecera;

    public Lista() {
        cabecera = null;
    }

    /*
     * Adds the element passed as a parameter at the position 'pos',
     * such that the quantity of elements in the list increases by 1.
     * For a succesful insertion, the received position must be
     * 1 <= pos <= length(list)+1.
     * Returns true if the element can be inserted, false, otherwise.
     */
    public boolean insertar(Object elem, int pos) {
        // Detects and reports invalid position error
        boolean exito = false;
        int largo = this.longitud() + 1;
        boolean condInsercion = (pos >= 1) && (pos <= largo); // Verifies that 'pos' position is valid
        if (condInsercion) {
            if (pos == 1) { // Inserting in the first position
                Nodo nuevoNodo = new Nodo(elem, this.cabecera);
                this.cabecera = nuevoNodo;
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) { // if pos = 2, this will not run
                    aux = aux.getEnlace();
                    i++;
                }
                // Creates and links the new node with the one at pos+1
                Nodo nuevoNodo = new Nodo(elem, aux.getEnlace());
                // Now this links the auxiliary node (at pos-1) with the new one
                aux.setEnlace(nuevoNodo);
            }
            exito = true;
        }

        return exito;
    }

    /*
     * Deletes the element at the position 'pos'. Preconditions: list not empty and
     * valid position (pos >= 1 and pos <= length(list) + 1).
     * Returns true if the deletion was succesful, false otherwise.
     */
    public boolean eliminar(int pos) {
        boolean exito = false;
        int largo = this.longitud();

        if (this.cabecera != null && pos >= 1 && pos <= largo) {
            int i = 1;
            if (pos == 1) { // Special case: 'pos' = 1, deleting first element
                this.cabecera = this.cabecera.getEnlace();
            } else {
                Nodo aux = this.cabecera;
                while (i < pos - 1) { // Moves forward to the node at position 'pos - 1'
                    aux = aux.getEnlace();
                    i++;
                }
                // Links the node at pos-1 with the one at pos+1, jumping over and deleting the
                // one at parameter 'pos'
                aux.setEnlace(aux.getEnlace().getEnlace());

            }
            exito = true;
        }

        return exito;
    }

    /*
     * Returns the element in the 'pos' position. Precondition: valid position.
     */

    public Object recuperar(int pos) {
        Object elem = null;

        // Verifies that list is not empty and position 'pos' is valid
        if (this.cabecera != null && pos >= 1 && pos <= this.longitud()) {
            int i = 1;
            Nodo actual = this.cabecera;
            while (i < pos) { // Moves forward to the node at position 'pos'
                actual = actual.getEnlace();
                i++;
            }
            // Save the element of the node that is at position 'pos'
            elem = actual.getElem();
        }

        return elem;
    }

    /*
     * Returns the position where the first ocurrence of 'elem' is in the list.
     * If it is not found, it returns -1.
     */
    public int localizar(Object elem) {
        int position = -1;

        // Verifies that list is not empty
        if (this.cabecera != null) {
            boolean found = false; // Represents if 'elem' was found or not
            Nodo aux = this.cabecera; // Starts from the first element
            int i = 1;
            while (aux != null && !found) {
                if (aux.getElem().equals(elem)) {
                    // When the element is found, the position is saved and the
                    // 'found' variable is updated to break the while loop
                    position = i;
                    found = true;
                } else {
                    // If element is not found, moves forward to the next node
                    aux = aux.getEnlace();
                    i++;
                }
            }

        }

        return position;
    }

    // Empties the list, removing all of the elements inside.
    public void vaciar() {
        this.cabecera = null;
    }

    // Returns true if the list is empty; false otherwise.
    public boolean esVacia() {
        return this.cabecera == null;
    }

    /*
     * Returns an exact copy of the data in the original structure, respecting
     * its order, in another structure of the same type.
     */
    @Override
    public Lista clone() {
        Lista clon = new Lista(); // creates an empty list

        if (!this.esVacia()) { // if the list isn't empty
            Nodo aux = this.cabecera;
            Nodo ultimo;
            Nodo nuevo = new Nodo(aux.getElem(), null);
            clon.cabecera = nuevo;
            while (aux.getEnlace() != null) {
                // reference to the last cloned node, the 'nuevo' variable
                ultimo = nuevo;
                // moves to the next node in the original structure
                aux = aux.getEnlace();
                // copies the element of the original node on the new copy
                nuevo = new Nodo(aux.getElem(), null);
                // links the last copied node to the recently created one
                ultimo.setEnlace(nuevo);
            }
        }

        return clon;
    }

    /*
     * Returns the quantity of elements in the list.
     */
    public int longitud() {
        int count = 0;

        Nodo aux = this.cabecera;
        // Starts counting the nodes from the first position, one by one until
        // it gets to the last one (last position on the List)
        while (aux != null) {
            count++;
            aux = aux.getEnlace();
        }

        return count;
    }

    /*
     * Creates and returns a String formed by all the elements of the list, so
     * that it can print it. This method is for testing purposes only.
     */
    @Override
    public String toString() {
        String cadena = "";

        if (this.esVacia()) {
            cadena = "[]";
        } else {
            cadena = "[";
            Nodo aux = this.cabecera;
            while (aux.getEnlace() != null) {
                cadena += aux.getElem() + ",";
                aux = aux.getEnlace();
            }
            cadena += aux.getElem() + "]";
        }

        return cadena;
    }

}
