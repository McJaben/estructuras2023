package lineales.estaticas;

/**
 *
 * @author Benjamín Morales <benjamin.morales at est.fi.uncoma.edu.ar>
 */
public class Cola {

    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int TAMANIO = 10;

    //Crea y devuelve una cola vacía.
    public Cola() {
        this.arreglo = new Object[TAMANIO];
        this.frente = 0;
        this.fin = 0;
    }

    /* Pone el elemento al final de la cola. Devuelve verdadero si el elemento
     * se pudo agregar en la estructura y falso en caso contrario
     */
    public boolean poner(Object nuevoElem) {
        boolean exito = true;

        if (this.fin + 1 == this.frente) {
            //Verifico si la cola está llena.
            exito = false;
        } else {
            //Cuando la cola no está llena.
            //Falta completar esto
            
        }

        return exito;
    }

    public boolean sacar() {
        boolean exito = true;

        if (this.esVacia()) {
            exito = false;
        } else {
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % TAMANIO;
        }

        return exito;
    }

    public boolean esVacia() {
        return this.frente == this.fin;
    }

}
