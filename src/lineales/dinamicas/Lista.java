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
        boolean condInsercion = (pos >= 1) && (pos <= largo); // Verifica que 'pos' sea válida
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
        int largo = this.longitud() + 1;

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
     * Returns the quantity of elements in the list.
     */
    public int longitud() {
        int count = 0;

        Nodo aux = this.cabecera;
        // Implementar

        return count;
    }
}
