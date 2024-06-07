package tests.jerarquicas;

import jerarquicas.dinamicas2023.ArbolGen;
import lineales.dinamicas.Lista;

/**
 *
 * @author Benjamín Morales <benjamin.morales at est.fi.uncoma.edu.ar>
 */

public class TestArbolGen {
        static String sOk = "\u001B[32m OK! \u001B[0m"; // mensaje OK! en verde
        static String sErr = " \u001B[31m ERROR \u001B[0m"; // mensaje ERROR en rojo
        static String sBlue = "\u001B[34m"; // color azul
        static String sMagenta = "\u001B[35m"; // color magenta
        static String sYellow = "\u001B[33m"; // color amarillo
        static String sReset = "\u001B[0m"; // Resetear color

        public static void main(String[] args) {
                System.out.println(sBlue + "********************************");
                System.out.println("*     TEST ÁRBOL GENÉRICO      *");
                System.out.println("********************************" + sReset);

                System.out.println(sYellow
                                + "\n---------------------------------------------------------------------------------------------------------------\n"
                                + sReset);

                System.out.println(sMagenta + "********************************");
                System.out.println("*      Constructor vacío       *");
                System.out.println("********************************" + sReset);

                ArbolGen arbol = new ArbolGen();
                System.out.println(
                                "Verifico que el árbol sea vacío, espera " + sOk + "--->"
                                                + ((arbol.esVacio()) ? sOk : sErr));

                System.out.println("Probando vaciar árbol vacío");
                arbol.vaciar();
                System.out.println(
                                "Probando método altura() en árbol vacío, espera -1: "
                                                + ((arbol.altura()) == -1 ? sOk : sErr));
                System.out.println("Busco un elemento inexistente en el árbol vacio con método pertenece()."
                                + " Tiene que dar " + sErr + " ---> " + ((arbol.pertenece(20)) ? sOk : sErr));

                System.out.println(sYellow
                                + "\n---------------------------------------------------------------------------------------------------------------\n"
                                + sReset);
                System.out.println(sMagenta + "********************************");
                System.out.println("*   Probando método insertar   *");
                System.out.println("********************************" + sReset);

                System.out.println("Inserto 20 en la raíz " + ((arbol.insertar(20, 1)) ? sOk : sErr));
                System.out.println("\n toString()  deberia dar:\n"
                                + "\n        20"
                                + "\n");
                System.out.println("Imprimiendo árbol en pantalla:");
                System.out.println(arbol.toString());
                System.out.println("");
                System.out.println("Compruebo la altura del árbol con un único elemento (la raíz):  " + arbol.altura());
                System.out.println("Busco el nivel de raiz. Tiene que dar 0 y" + sOk + " ---> " + arbol.nivel(20)
                                + (((int) arbol.nivel(20) == 0) ? sOk : sErr));
                System.out.println("Inserto el 19 como hijo de 20 " + ((arbol.insertar(19, 20)) ? sOk : sErr));
                System.out.println("\n toString()  deberia dar:\n"
                                + "\n        20 "
                                + "\n    +---+  "
                                + "\n    |      "
                                + "\n   19      "
                                + "\n");
                System.out.println("Imprimiendo árbol:");
                System.out.println(arbol.toString());
                System.out.println("");
                System.out.println("Busco el nivel de elemento 19. Tiene que dar 1 y" + sOk + " ---> "
                                + (((int) arbol.nivel(19) == 1) ? sOk : sErr));
                System.out.println("Inserto el 13 como hijo de 19 " + ((arbol.insertar(13, 19)) ? sOk : sErr));
                System.out.println("Inserto el 17 como hijo de 19 " + ((arbol.insertar(17, 19)) ? sOk : sErr));
                System.out.println("La altura del árbol deberia dar 2:  " + arbol.altura());
                System.out.println("Busco el nivel de elemento 13. Tiene que dar 2 y" + sOk + " ---> "
                                + (((int) arbol.nivel(13) == 2) ? sOk : sErr));
                System.out.println("Inserto el 25 como hijo de 20 " + ((arbol.insertar(25, 20)) ? sOk : sErr));
                System.out.println("Inserto el 30 como hijo de 25 " + ((arbol.insertar(30, 25)) ? sOk : sErr));
                System.out.println("Inserto el 35 como hijo de 25 " + ((arbol.insertar(35, 25)) ? sOk : sErr));
                System.out.println("Inserto el 39 como hijo de 25 " + ((arbol.insertar(39, 25)) ? sOk : sErr));
                System.out.println("Inserto el 40 como hijo de 30 " + ((arbol.insertar(40, 30)) ? sOk : sErr));
                System.out.println("Inserto el 45 como hijo de 30 " + ((arbol.insertar(45, 30)) ? sOk : sErr));
                System.out.println("Inserto el 55 como hijo de 30 " + ((arbol.insertar(55, 30)) ? sOk : sErr));
                System.out.println("\n toString()  deberia dar: \n"
                                + "\n                              20"
                                + "\n                 +------------+----------+"
                                + "\n                 |                       |"
                                + "\n                 19                      25"
                                + "\n             +---+---+           +-------+-----+"
                                + "\n             |       |           |       |      |"
                                + "\n             13      17          30      35     39"
                                + "\n                           +-----+-----+"
                                + "\n                           |     |     |"
                                + "\n                           40    45    55"
                                + "\n" + arbol.toString());
                System.out.println("\n");
                System.out.println("Insertar con un padre inexistente debería dar" + sErr + "---> "
                                + ((arbol.insertar(15, 2) ? sOk : sErr)));
                System.out.println(
                                "Insertar elemento duplicado (20) con padre existente (13) debería dar" + sOk + "--->"
                                                + (arbol.insertar(20, 13) ? sOk : sErr));
                System.out.println("Consulto la altura del árbol, debería dar 3 " + "---> " + arbol.altura());
                System.out.println("Chequeo si el árbol es vacío, debería dar" + sErr + "--->"
                                + ((arbol.esVacio()) ? sOk : sErr));
                System.out.println(sYellow
                                + "\n---------------------------------------------------------------------------------------------------------------\n"
                                + sReset);
                System.out.println(sMagenta + "********************************");
                System.out.println("*       Probando clone()       *");
                System.out.println("********************************" + sReset);
                System.out.println("Clonando el árbol");
                ArbolGen clon = arbol.clone();
                System.out.println("Compruebo altura del clone, espera 3 ---> " + clon.altura());
                System.out.println("\n clon.toString()  deberia dar: \n"
                                + "\n                              20"
                                + "\n                 +------------+----------+"
                                + "\n                 |                       |"
                                + "\n                 19                      25"
                                + "\n             +---+---+           +-------+-----+"
                                + "\n             |       |           |       |     |"
                                + "\n             13      17          30      35    39"
                                + "\n             +              +-----+-----+"
                                + "\n             |              |     |     |"
                                + "\n             20             40    45    55"
                                + "\n" + "Imprimiendo clon:\n" + clon.toString());
                System.out.println("\n");

                System.out.println("Inserto 4 como hijo de 17 en CLON" + ((clon.insertar(4, 17) ? sOk : sErr)));
                System.out.println("Inserto 58 como hijo de 39 en CLON" + ((clon.insertar(58, 39) ? sOk : sErr)));
                System.out.println("\n" + sBlue + "CLON toString() \n" + clon.toString() + "\n\n");
                System.out.println("\n" + sMagenta + "ORIGINAL toString() \n" + arbol.toString() + "\n\n" + sReset);

                System.out.println("Vacío el clon");
                clon.vaciar();
                System.out.println("Chequeo si es vacío, debería dar" + sOk + "--->" + ((clon.esVacio()) ? sOk : sErr));
                System.out.println("\n" + sBlue + "CLON toString():\n" + clon.toString() + "\n" + sReset);
                System.out.println("Busco al padre de 17 en árbol clon vacio. Tiene que dar " + sErr + " ---> "
                                + ((clon.padre(17) != null) ? sOk : sErr));
                System.out.println("Busco a 25 en árbol clon con pertenece(). Tiene que dar " + sErr + " ---> "
                                + ((clon.pertenece(20)) ? sOk : sErr));

                System.out.println(sYellow
                                + "\n---------------------------------------------------------------------------------------------------------------\n"
                                + sReset);
                System.out.println(sMagenta + "********************************");
                System.out.println("* Probando métodos de búsqueda *");
                System.out.println("********************************" + sReset);

                System.out.println(sBlue + "\nMétodo padre():\n" + sReset);
                System.out.println("Busco al padre de 55. Tiene que dar 30: " + sOk + " ---> "
                                + (((int) arbol.padre(55) == 30) ? sOk : sErr));
                System.out.println("Busco al padre de 13. Tiene que dar 19: " + sOk + " ---> "
                                + (((int) arbol.padre(13) == 19) ? sOk : sErr));
                System.out.println("Busco al padre de 35. Tiene que dar 25: " + sOk + " ---> "
                                + (((int) arbol.padre(35) == 25) ? sOk : sErr));
                System.out.println("Busco al padre de 40. Tiene que dar 30: " + sOk + " ---> "
                                + (((int) arbol.padre(40) == 30) ? sOk : sErr));
                System.out.println("Busco al padre de raiz. Tiene que dar null: " + sOk + " ---> "
                                + ((arbol.padre(10) == null) ? sOk : sErr));
                System.out.println("Busco al padre de elemento inexistente. Tiene que dar: " + sErr + " ---> "
                                + ((arbol.padre(2024) != null) ? sOk : sErr));

                System.out.println(sBlue + "\nMétodo pertenece():\n" + sReset);
                System.out.println("Busco a raíz con pertenece(). Tiene que dar" + sOk + "--->"
                                + ((arbol.pertenece(20)) ? sOk : sErr));
                System.out.println("Busco al elemento 45 con pertenece(). Tiene que dar" + sOk + "--->"
                                + ((arbol.pertenece(45) ? sOk : sErr)));
                System.out.println("Busco al elemento 17 con pertenece(). Tiene que dar" + sOk + "--->"
                                + ((arbol.pertenece(17) ? sOk : sErr)));
                System.out.println("Busco a elemento inexistente con pertenece(). Tiene que dar" + sErr + "--->"
                                + ((arbol.pertenece(2024) ? sOk : sErr)));

                System.out.println(sBlue + "\nMétodo ancestros():\n" + sReset);
                System.out.println(
                                "Busco ancentros de raiz debería dar lista vacía: " + arbol.ancestros(20).toString());
                System.out.println("Busco ancentros de 13 deberia dar [20, 19]: " + arbol.ancestros(13).toString());
                System.out.println("Busco ancentros de 55 deberia dar [20, 25, 30]: " + arbol.ancestros(55).toString());
                System.out.println("Busco ancentros de elemento inexistente, debería dar lista vacía: "
                                + arbol.ancestros(3333).toString());

                System.out.println(sYellow
                                + "\n---------------------------------------------------------------------------------------------------------------\n"
                                + sReset);
                System.out.println(sMagenta + "*******************************");
                System.out.println("*   Probando método nivel()   *");
                System.out.println("*******************************" + sReset);
                System.out.println("Consulto el nivel de raíz. Tiene que dar 0 y" + sOk + "--->"
                                + (((int) arbol.nivel(20) == 0) ? sOk : sErr));
                System.out.println("Consulto el nivel de elemento 45. Tiene que dar 3 y" + sOk + "--->"
                                + (((int) arbol.nivel(45) == 3) ? sOk : sErr));
                System.out.println("Consulto el nivel de elemento 17. Tiene que dar 2 y" + sOk + "--->"
                                + (((int) arbol.nivel(17) == 2) ? sOk : sErr));
                System.out.println("Consulto el nivel de elemento inexistente. Tiene que dar -1 y" + sOk + "--->"
                                + (((int) arbol.nivel(2024) == -1) ? sOk : sErr));

                System.out.println(sYellow
                                + "\n---------------------------------------------------------------------------------------------------------------\n"
                                + sReset);
                System.out.println(sMagenta + "*******************************");
                System.out.println("*     Probando recorridos     *");
                System.out.println("*******************************" + sReset);

                System.out.println("\n toString()  deberia dar: \n"
                                + "\n                              20"
                                + "\n                 +------------+----------+"
                                + "\n                 |                       |"
                                + "\n                 19                      25"
                                + "\n             +---+---+           +-------+-----+"
                                + "\n             |       |           |       |      |"
                                + "\n             13      17          30      35     39"
                                + "\n             +             +-----+-----+"
                                + "\n             |             |     |     |"
                                + "\n             20            40    45    55"
                                + "\n" + arbol.toString());
                System.out.println("\n");
                System.out.println("\n");
                System.out.println("Recorrido en preOrden.\n Tiene que dar: "
                                + "\n [20, 19, 13, 20, 17, 25, 30, 40, 45, 55, 35, 39]"
                                + "\n --> " + arbol.listarPreorden().toString());
                System.out.println("\n");
                System.out.println("Recorrido en posOrden.\n Tiene que dar: "
                                + "\n [20, 13, 17, 19, 40, 45, 55, 30, 35, 39, 25, 20]"
                                + "\n --> " + arbol.listarPosorden().toString());
                System.out.println("\n");
                System.out.println("Recorrido en InOrden.\n Tiene que dar: "
                                + "\n [20, 13, 19, 17, 20, 40, 30, 45, 55, 25, 35, 39]"
                                + "\n --> " + arbol.listarInorden().toString());
                System.out.println("\n");
                System.out.println("Recorrido por Niveles.\n Tiene que dar: "
                                + "\n [20, 19, 25, 13, 17, 30, 35, 39, 20, 40, 45, 55]"
                                + "\n --> " + arbol.listarPorNiveles().toString());
                System.out.println("\n");
                
                System.out.println(sMagenta + "********************************");
                System.out.println("*  Probando verificarCamino()  *");
                System.out.println("********************************" + sReset);

                System.out.println("Inserto 17 como hijo de 25 en el árbol");
                arbol.insertar(17, 25);
                System.out.println("\n toString()  deberia dar: \n"
                                + "\n                              20"
                                + "\n                 +------------+----------+"
                                + "\n                 |                       |"
                                + "\n                 19                      25"
                                + "\n             +---+---+           +-------+-----+-----"
                                + "\n             |       |           |       |     |    |"
                                + "\n             13      17          30      35    39   17"
                                + "\n             +             +-----+-----+"
                                + "\n             |             |     |     |"
                                + "\n             20            40    45    55"
                                + "\n" + arbol.toString());

                Lista prueba = new Lista();
                prueba.insertar(20, prueba.longitud()+1);
                prueba.insertar(19, prueba.longitud()+1);
                prueba.insertar(17, prueba.longitud()+1);
                System.out.println("Lista pasada por parámetro a verificarCamino(): " + prueba.toString());
                System.out.println("Verificar camino debería devolver " + sOk + "--->"
                                + ((arbol.verificarCamino(prueba)) ? sOk : sErr));

                System.out.println(sMagenta + "********************************");
                System.out.println("*  Probando listarEntreNiveles  *");
                System.out.println("*********************************" + sReset);
                System.out.println("\n toString()  deberia dar: \n"
                + "\n                              20"
                + "\n                 +------------+----------+"
                + "\n                 |                       |"
                + "\n                 19                      25"
                + "\n             +---+---+           +-------+-----+-----"
                + "\n             |       |           |       |     |    |"
                + "\n             13      17          30      35    39   17"
                + "\n             +             +-----+-----+"
                + "\n             |             |     |     |"
                + "\n             20            40    45    55"
                + "\n" + arbol.toString());
                System.out.println("Listar entre niveles con niv1 = 1 y niv2 = 2 debería dar: "
                                + "\n [19, 13, 17, 25, 30, 35, 39, 17]"
                                + "\n --> " + arbol.listarEntreNiveles(1, 2));
                                System.out.println("\n");
                System.out.println("Listar entre niveles con niv1 = 0 y niv2 = 1 debería dar: "
                                + "\n [20, 19, 25]"
                                + "\n --> " + arbol.listarEntreNiveles(0,1));
                                System.out.println("\n");
                System.out.println("Listar entre niveles con niv1 = 0 y niv2 = 2 debería dar: "
                                + "\n [20, 19, 13, 17, 25, 30, 35, 39, 17]"
                                + "\n --> " + arbol.listarEntreNiveles(0, 2));
                                System.out.println("\n");
                System.out.println("Listar entre niveles con niv1 = 2 y niv2 = 3 debería dar: "
                                + "\n [13, 20, 17, 30, 40, 45, 55, 35, 39, 17]"
                                + "\n --> " + arbol.listarEntreNiveles(2, 3));
                                System.out.println("\n");
                
                System.out.println("Probando con niveles que no existen, ej: nivel 4.");
                System.out.println("Listar entre niveles con niv1 = 2 y niv2 = 4 debería dar igual que entre nivel 2 y 3: "
                                + "\n [13, 20, 17, 30, 40, 45, 55, 35, 39, 17]"
                                + "\n --> " + arbol.listarEntreNiveles(2, 3));
                                System.out.println("\n");
                
                System.out.println("Probando con nivel minimo mayor al nivel maximo del arbol:");
                System.out.println("Listar entre niveles con niv1 = 4 y niv2 = 5 debería dar lista vacia:"
                                + "\n --> " + arbol.listarEntreNiveles(4, 5));
                                System.out.println("\n");       
        }

}
