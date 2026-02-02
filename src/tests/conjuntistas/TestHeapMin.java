package tests.conjuntistas;

import conjuntistas.HeapMin;

public class TestHeapMin {

    static String sOk = "\u001B[32m OK! \u001B[0m";
    static String sErr = " \u001B[31m ERROR \u001B[0m";
    static String sBlue = "\u001B[34m";
    static String sMagenta = "\u001B[35m";
    static String sYellow = "\u001B[33m";
    static String sReset = "\u001B[0m";

    public static void main(String[] args) {

        System.out.println(sBlue + "**************************************");
        System.out.println("*          TEST HEAP MÍNIMO           *");
        System.out.println("**************************************" + sReset);

        System.out.println(sYellow
                + "\n---------------------------------------------------------------------------------------------------------------\n"
                + sReset);

        System.out.println(sMagenta + "********************************");
        System.out.println("*      Constructor vacío       *");
        System.out.println("********************************" + sReset);

        HeapMin<Integer> heap = new HeapMin<>();

        System.out.println("Verifico que el heap esté vacío, espera " + sOk + "---> "
                + (heap.esVacio() ? sOk : sErr));

        System.out.println("Intento recuperar mínimo en heap vacío, espera null ---> "
                + (heap.recuperarCima() == null ? sOk : sErr));

        System.out.println("Intento eliminar mínimo en heap vacío, espera " + sErr + " ---> "
                + (heap.eliminarCima() ? sOk : sErr));

        System.out.println(sYellow
                + "\n---------------------------------------------------------------------------------------------------------------\n"
                + sReset);

        System.out.println(sMagenta + "****************************************");
        System.out.println("*         Método insertar             *");
        System.out.println("****************************************" + sReset);

        System.out.println("Insertando elementos:");
        System.out.println("Inserta 50: " + (heap.insertar(50) ? sOk : sErr));
        System.out.println("Inserta 30: " + (heap.insertar(30) ? sOk : sErr));
        System.out.println("Inserta 70: " + (heap.insertar(70) ? sOk : sErr));
        System.out.println("Inserta 20: " + (heap.insertar(20) ? sOk : sErr));
        System.out.println("Inserta 40: " + (heap.insertar(40) ? sOk : sErr));
        System.out.println("Inserta 10: " + (heap.insertar(10) ? sOk : sErr));

        System.out.println("\nMínimo esperado: 10 ---> "
                + (heap.recuperarCima() == 10 ? sOk : sErr));

        // System.out.println("Contenido del heap (por niveles):");
        // System.out.println(heap.listar());

        System.out.println(sYellow
                + "\n---------------------------------------------------------------------------------------------------------------\n"
                + sReset);

        System.out.println(sMagenta + "****************************************");
        System.out.println("*     Eliminar mínimo                 *");
        System.out.println("****************************************" + sReset);

        System.out.println("Eliminando mínimo (10): "
                + (heap.eliminarCima() ? sOk : sErr));
        System.out.println("Nuevo mínimo esperado: 20 ---> "
                + (heap.recuperarCima() == 20 ? sOk : sErr));
        // System.out.println("Heap actual: " + heap.listar());

        System.out.println("Eliminando mínimo (20): "
                + (heap.eliminarCima() ? sOk : sErr));
        System.out.println("Nuevo mínimo esperado: 30 ---> "
                + (heap.recuperarCima() == 30 ? sOk : sErr));
        // System.out.println("Heap actual: " + heap.listar());

        System.out.println(sYellow
                + "\n---------------------------------------------------------------------------------------------------------------\n"
                + sReset);

        System.out.println(sMagenta + "****************************************");
        System.out.println("*     Vaciar heap                     *");
        System.out.println("****************************************" + sReset);

        // System.out.println("Vaciando heap...");
        // heap.vaciar();

        System.out.println("Verifico que el heap esté vacío, espera " + sOk + "---> "
                + (heap.esVacio() ? sOk : sErr));

        System.out.println("Recuperar mínimo luego de vaciar, espera null ---> "
                + (heap.recuperarCima() == null ? sOk : sErr));
    }
}
