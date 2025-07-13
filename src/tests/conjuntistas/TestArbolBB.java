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

		System.out.println("\nIntento insertar repetido 30: " + "espera:" + sErr + "--->"
				+ (arbol.insertar(30) ? sOk : sErr)); // debe dar false
		System.out.println("¿Pertenece 40? " + "espera:" + sOk + "--->"
				+ (arbol.pertenece(40) ? sOk : sErr)); // true
		System.out.println("¿Pertenece 80? " + "espera:" + sOk + "--->"
				+ (arbol.pertenece(80) ? sOk : sErr)); // true
		System.out.println("¿Pertenece 100? " + "espera:" + sErr + "--->"
				+ (arbol.pertenece(100) ? sOk : sErr)); // false

		System.out.println("Vaciando el árbol...");
		arbol.vaciar();
		System.out.println("Verifico que el árbol sea vacío, espera " + sOk + "--->"
				+ ((arbol.esVacio()) ? sOk : sErr));
		// Podés seguir con pruebas de eliminar, listarRango, obtenerMinimo, obtenerMaximo, etc.
	}
}

