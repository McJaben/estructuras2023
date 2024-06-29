package tests.lineales;

import lineales.dinamicas.*;

public class TestLineales {
    public static void main(String[] args) {
        Cola q = new Cola();
        System.out.println("TEST de invertirVocalesDuplicarSinVocales()");
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
        System.out.println("invertirVocalesDuplicarSinVocales(q) debería dar: " + "--->"
                + "[e, a, #, a, #, q, w, r, t, y, q, w, r, t, y, #, s, j, s, j]\n");
        Lista list = invertirVocalesDuplicarSinVocales(q);
        System.out.println("Retorna ----> " + list.toString());

        System.out.println("TEST de generarSecuencia()");
        q.vaciar();
        q.poner('0');
        q.poner('1');
        q.poner('2');
        q.poner('3');
        q.poner('4');
        q.poner('5');
        q.poner('6');
        q.poner('7');
        q.poner('8');
        q.poner('9');
        System.out.println("Nueva cola:" + q.toString());
        Lista lis = generarSecuencia(q, 4);
        System.out.println("generarSecuencia(q, 4) debería dar: " + "---->"
                + "[3,2,1,0,0,1,2,3,$,7,6,5,4,4,5,6,7,$,9,8,8,9]");
        System.out.println("Retorna ---->" + lis.toString());


    }

    public static Lista invertirVocalesDuplicarSinVocales(Cola q) {
        Lista lista = new Lista();
        char aux = (char) q.obtenerFrente();
        Cola palabraActual = new Cola();
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

    /*
     * toma una cola q de caracteres y un número entero t, y devuelve una lista
     * en la forma especificada: a1'a1$a2'a2$...an'an, donde ai es una sucesión
     * de caracteres hasta t, y ai' es dicha sucesión de caracteres invertida
     */
    public static Lista generarSecuencia(Cola q, int t) {
        Lista lis = new Lista();
        if (!q.esVacia() && t > 0) {
            Cola sucesionActual = new Cola();
            Pila pilaInvertir = new Pila();
            char aux = (char) q.obtenerFrente();
            int recorridoCompleto = 0, cont = 0;
            // Comienzo el recorrido de la cola q
            while (!q.esVacia() && recorridoCompleto < 2) {
                // Voy guardando los caracteres en estructuras auxiliares
                if ((cont < t) && (aux != '#')) { // '#' es para marcar la últ subcadena
                    sucesionActual.poner(aux);
                    pilaInvertir.apilar(aux);
                    q.sacar();
                    cont++;
                } else {
                    while (!pilaInvertir.esVacia()) {
                        lis.insertar(pilaInvertir.obtenerTope(), lis.longitud() + 1);
                        pilaInvertir.desapilar();
                    }
                    while (!sucesionActual.esVacia()) {
                        lis.insertar(sucesionActual.obtenerFrente(), lis.longitud() + 1);
                        sucesionActual.sacar();
                    }
                    // Si no estoy en la última subcadena, inserto caracter de separación
                    if (aux != '#') {
                        lis.insertar('$', lis.longitud() + 1);
                    } else { // si estoy en la última subcadena, quito el '#' de marcado
                        q.sacar();
                    }
                    cont = 0; // reinicio el conteo
                }
                // Avanzo a la siguiente subcadena y chequeo si llegué al final con sucesiones
                // sin insertar
                if (q.esVacia() && cont > 0) {
                    q.poner('#'); // marco que llegué al final con caracteres pendientes
                    recorridoCompleto++;
                }
                if (!q.esVacia()) {
                    aux = (char) q.obtenerFrente(); // actualizo caracter
                }
            }
        }
        return lis;
    }
}
