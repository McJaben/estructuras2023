package tests.conjuntistas;

import conjuntistas.*;

public class TestArbolBB {
    static String sOk = "\u001B[32m OK! \u001B[0m"; // mensaje OK! en verde
    static String sErr = " \u001B[31m ERROR \u001B[0m"; // mensaje ERROR en rojo
    static String sBlue = "\u001B[34m"; // color azul
    static String sMagenta = "\u001B[35m"; // color magenta
    static String sYellow = "\u001B[33m"; // color amarillo
    static String sReset = "\u001B[0m"; // Resetear color

    public static void main(String[] args) {
        System.out.println(sBlue + "**************************************");
        System.out.println("*   TEST ÁRBOL BINARIO DE BÚSQUEDA   *");
        System.out.println("**************************************" + sReset);

        System.out.println(sYellow
                + "\n---------------------------------------------------------------------------------------------------------------\n"
                + sReset);

        System.out.println(sMagenta + "********************************");
        System.out.println("*      Constructor vacío       *");
        System.out.println("********************************" + sReset);
        ArbolBB<Integer> arbol = new ArbolBB<>();

        System.out.println("Verifico que el árbol sea vacío, espera " + sOk + "--->"
                + ((arbol.esVacio()) ? sOk : sErr));

        System.out.println("Probando vaciar árbol vacío");
        arbol.vaciar();
        System.out.println("Verifico que el árbol sea vacío, espera " + sOk + "--->"
                + ((arbol.esVacio()) ? sOk : sErr));

        System.out.println("método altura() pendiente de implementación");
        // TODO: Implementar altura()
        // System.out.println(
        // "Probando método altura() en árbol vacío, espera -1: "
        // + ((arbol.altura()) == -1 ? sOk : sErr));

        System.out.println(sYellow
                + "\n---------------------------------------------------------------------------------------------------------------\n"
                + sReset);
        System.out.println(sMagenta + "****************************************");
        System.out.println("*     Métodos insertar y pertenece     *");
        System.out.println("****************************************" + sReset);

        System.out.println("Busco un elemento inexistente en el árbol vacio con método pertenece()."
                + " Tiene que dar" + sErr + "--->" + ((arbol.pertenece(20)) ? sOk : sErr));
        System.out.println("Insertar elementos:");
        System.out.println("Inserta 50: " + (arbol.insertar(50) ? sOk : sErr));
        System.out.println("Inserta 30: " + (arbol.insertar(30) ? sOk : sErr));
        System.out.println("Inserta 70: " + (arbol.insertar(70) ? sOk : sErr));
        System.out.println("Inserta 20: " + (arbol.insertar(20) ? sOk : sErr));
        System.out.println("Inserta 40: " + (arbol.insertar(40) ? sOk : sErr));
        System.out.println("Inserta 60: " + (arbol.insertar(60) ? sOk : sErr));
        System.out.println("Inserta 80: " + (arbol.insertar(80) ? sOk : sErr));
        System.out.println("Inserta 25: " + (arbol.insertar(25) ? sOk : sErr));
        System.out.println("Inserta 35: " + (arbol.insertar(35) ? sOk : sErr));
        System.out.println("Inserta 90: " + (arbol.insertar(90) ? sOk : sErr));

        System.out.println("\nIntento insertar repetido 30: " + "espera:" + sErr + "--->"
                + (arbol.insertar(30) ? sOk : sErr)); // debe dar false
        System.out.println("¿Pertenece 40? " + "espera:" + sOk + "--->"
                + (arbol.pertenece(40) ? sOk : sErr)); // true
        System.out.println("¿Pertenece 80? " + "espera:" + sOk + "--->"
                + (arbol.pertenece(80) ? sOk : sErr)); // true
        System.out.println("¿Pertenece 100? " + "espera:" + sErr + "--->"
                + (arbol.pertenece(100) ? sOk : sErr)); // false

        System.out.println(sYellow
                + "\n---------------------------------------------------------------------------------------------------------------\n"
                + sReset);
        System.out.println(sMagenta + "****************************************");
        System.out.println("*     Métodos listar y listarRango     *");
        System.out.println("****************************************" + sReset);

        System.out.println("Listar árbol...");
        System.out.println(arbol.listar());
        System.out.println("Listando rango de 15 a 40" + " espera: [20,25,30,35,40]" + " ---> "
                + arbol.listarRango(15, 40));

        System.out.println("Listando rango de 30 a 60" + " espera: [30,35,40,50,60]" + " ---> "
                + arbol.listarRango(30, 60));

        System.out.println("Listando rango de 25 a 90" + " espera: [25,30,35,40,50,60,70,80,90]" + " ---> "
                + arbol.listarRango(25, 90));

        System.out.println(sYellow
                + "\n---------------------------------------------------------------------------------------------------------------\n"
                + sReset);
        System.out.println(sMagenta + "****************************************");
        System.out.println("*     Métodos minimoElem y maximoElem     *");
        System.out.println("****************************************" + sReset);
        int aux = arbol.minimoElem();
        System.out.println("Obteniendo el mínimo elemento del árbol " + "espera 20 y " + sOk + " ---> " + aux
                + (aux == 20 ? sOk : sErr));
        aux = arbol.maximoElem();
        System.out.println("Obteniendo el máximo elemento del árbol " + "espera 90 y " + sOk + " ---> " + aux
                + (aux == 90 ? sOk : sErr));

        System.out.println(sYellow
                + "\n---------------------------------------------------------------------------------------------------------------\n"
                + sReset);
        System.out.println(sMagenta + "****************************************");
        System.out.println("*     Métodos eliminar y vaciar     *");
        System.out.println("****************************************" + sReset);

        System.out.println("Árbol actual: " + arbol.listar());
        System.out.println("Eliminando elemento 30, espera: " + sOk + " y [20,25,35,40,50,60,70,80,90]" + " ---> "
                + (arbol.eliminar(30) ? sOk : sErr) + " y " + arbol.listar());

        System.out.println("Eliminando elemento 25, espera: " + sOk + " y [20,35,40,50,60,70,80,90]" + " ---> "
                + (arbol.eliminar(25) ? sOk : sErr) + " y " + arbol.listar());

        System.out.println("Eliminando elemento 80, espera: " + sOk + " y [20,35,40,50,60,70,90]" + " ---> "
                + (arbol.eliminar(80) ? sOk : sErr) + " y " + arbol.listar());

        System.out.println("Vaciando el árbol...");
        arbol.vaciar();
        System.out.println("Verifico que el árbol sea vacío, espera " + sOk + "--->"
                + ((arbol.esVacio()) ? sOk : sErr));
    }
}
