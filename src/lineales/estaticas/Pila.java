/*
 * Clase Pila de la materia EDAT. Apunte 2.
 */
package lineales.estaticas;

/************* Autores ***********
 * Lucas Zuñiga, Legajo FAI-3305
 * Benjamín Morales, Legajo FAI-3370
 * Facundo Ariel Muñoz, Legajo FAI-3291
 */

public class Pila {

    private Object[] arreglo;
    private int tope;
    private static final int TAMANIO = 10;

    /*
     * Que TAMANIO sea estática quiere decir que todas las instancias de la clase
     * comparten ese tamaño
     */
    public Pila() {
        // crea y devuelve la pila vacía
        this.arreglo = new Object[TAMANIO];
        this.tope = -1;
    }

    // de aplicacion
    public boolean apilar(Object nuevoElem) {
        /*
         * Pone el elemento nuevoElem en el tope de la pila. Si se pudo apilar,
         * devuelve true, y si no, false.
         */
        boolean exito = false; // Pila llena, tope = TAMANIO -1

        if (this.tope < TAMANIO - 1) { // TAMANIO es estática (de clase)
            // pone el elemento en el tope de la pila e incrementa tope
            this.tope++;
            this.arreglo[tope] = nuevoElem;
            exito = true;
        }

        return exito;
    }

    public boolean desapilar() {
        /*
         * Saca el elemento del tope de la pila. Devuelve true si la pila tenía
         * elementos al momento de desapilar y falso en caso de que esté vacía.
         */
        boolean exito = false;
        if (this.tope != -1) {
            this.arreglo[this.tope] = null;
            this.tope--; // el tope ahora es el elemento anterior al que acabo de desapilar
            exito = true;
        }
        return exito;
    }

    public Object obtenerTope() {
        Object elemento = null;
        if (this.tope != -1) {
            elemento = this.arreglo[this.tope];
        }
        return elemento;
    }

    public boolean esVacia() {
        // Devuelve verdadero si la pila está vacía. Falso en caso contrario.
        // El valor -1 de 'tope' representa una pila vacía.
        return (this.tope == -1);
    }

    public void vaciar() {
        // Saca todos los elementos de la pila hasta que queda vacía.
        while (this.tope != -1) { // El bucle se ejecuta mientras no sea vacía
            this.arreglo[this.tope] = null;
            this.tope--;
        }
    }

    @Override
    public Pila clone() {
        /*
         * Devuelve una copia exacta de los datos en la estructura original, y
         * respetando el orden de los mismos en otra estructura del mismo tipo.
         */

        Pila clon = new Pila();
        clon.tope = this.tope;

        // Manipulo la estructura de manera directa y eficiente.
        for (int i = 0; i <= this.tope; i++) {
            clon.arreglo[i] = this.arreglo[i];
        }

        return clon;
    }

    @Override
    public String toString() {
        String s = "";

        if (this.esVacia()) { // Verifica que la pila no esté vacía
            s = "Pila vacía";
        } else {
            s = "[";
            for (int i = 0; i <= this.tope; i++) { // bucle que recorre toda la pila
                s += this.arreglo[i];
                if (i < this.tope) {
                    s += ",";
                }
            }
            s += "]";
        }

        return s;
    }
}
