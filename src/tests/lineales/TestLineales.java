package tests.lineales;

import lineales.dinamicas.*;

public class TestLineales {
    public static void main(String[] args) {
        Cola q = new Cola();
        q.poner('a');
        q.poner('b');
        q.poner('c');
        q.poner('d');
        q.poner('e');
        q.poner('f');
        q.poner('#');
        q.poner('a');
        q.poner('b');
        q.poner('c');
        q.poner('d');
        q.poner('#');
        q.poner('q');
        q.poner('w');
        q.poner('r');
        q.poner('t');
        q.poner('y');
        q.poner('#');
        q.poner('s');
        q.poner('j');

        System.out.println("Cola q: " + q.toString());
        System.out.println("q.invertirVocalesDuplicarSinVocales debería dar: " + "--->"
                + "[e, a, #, a, #, q, w, r, t, y, q, w, r, t, y, #, s, j, s, j]\n");
        Lista list = invertirVocalesDuplicarSinVocales(q);
        System.out.println("Retorna ----> " + list.toString());

    }

    public static Lista invertirVocalesDuplicarSinVocales(Cola q) {
        Lista lista = new Lista();
        char aux = (char) q.obtenerFrente();
        Cola palabraActual = q.clone();
        Pila vocales = new Pila();
        int recorridoCompleto = 0;
        while (!q.esVacia() && recorridoCompleto < 2) {
            if (aux != '#' && aux != '$') { // Recorro palabra actual
                palabraActual.poner(aux);
                if (aux == 'a' || aux == 'e' || aux == 'i' || aux == 'o' || aux == 'u') {
                    vocales.apilar(aux);
                }
            } else { // Ya recorrí la palabra actual, inserto en la lista segun corresponda
                if (!vocales.esVacia()) { // Si hay vocales, las inserto invertidas
                    while (!vocales.esVacia()) {
                        lista.insertar(vocales.obtenerTope(), lista.longitud() + 1);
                        vocales.desapilar();
                    }
                } else { // Si no hay vocales, inserto la palabra duplicada
                    Cola clonPalabraActual = palabraActual.clone();
                    int iteracion = 0; // para controlar la duplicación
                    while (!palabraActual.esVacia() && iteracion < 2) {
                        // Duplica la palabra actual para insertarla en la lista, itera 2 veces
                        lista.insertar(palabraActual.obtenerFrente(), lista.longitud() + 1);
                        palabraActual.sacar();
                        if (palabraActual.esVacia()) {
                            iteracion++;
                            palabraActual = clonPalabraActual.clone();
                        }
                    }
                    clonPalabraActual.vaciar();
                }
                palabraActual.vaciar();
                // Inserto '#' si no estoy en la última palabra
                if (aux != '$') {
                    lista.insertar(aux, lista.longitud() + 1);
                }
            }
            // Avanzo al siguiente elemento de la cola original
            q.sacar();
            if (q.esVacia()) { // Si llegué a la última palabra, marco con '$'
                q.poner('$');
                recorridoCompleto++;
            }
            aux = (char) q.obtenerFrente(); // actualizo caracter del frente de la cola
        }
        return lista;
    }
}
