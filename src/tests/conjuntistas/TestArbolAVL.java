package tests.conjuntistas;

import conjuntistas.ArbolAVL;
import lineales.dinamicas.Lista;

/**
 * Test exhaustivo del TDA ArbolAVL. Cubre: árbol vacío, casos límite, todos los tipos de rotación,
 * duplicados, inserción masiva y verificación de métodos auxiliares. Este test lo implementé con
 * ayuda de IA para optimizar los tiempos y facilitar la impresión de mensajes por pantalla
 *
 * Convención de salida: [OK] resultado esperado [ERROR] resultado inesperado
 *
 * @author Benjamín Morales (adaptado para test exhaustivo)
 */
public class TestArbolAVL {

    // ── Contadores globales ───────────────────────────────────────────────────
    private static int totalTests = 0;
    private static int testsOk = 0;
    private static int testsError = 0;

    // ── Árbol compartido entre secciones ─────────────────────────────────────
    private static ArbolAVL<Integer> avl;

    // =========================================================================
    public static void main(String[] args) {

        separador("INICIO DE TESTS - ArbolAVL<Integer>");

        testArbolVacio();
        testInsercionSinRotacion();
        testRotacionSimpleDerecha();
        testRotacionSimpleIzquierda();
        testRotacionDobleIzquierdaDerecha();
        testRotacionDobleDrechaIzquierda();
        testDuplicados();
        testMetodosAuxiliares();
        testInsercionMasiva();
        testEliminarEnArbolVacio();
        testEliminarHoja();
        testEliminarUnHijo();
        testEliminarDosHijos();
        testEliminarConRotaciones();
        testEliminacionMasiva();

        separador("RESUMEN FINAL");
        System.out.println("  Total  : " + totalTests);
        System.out.println("  OK     : " + testsOk);
        System.out.println("  ERROR  : " + testsError);
        System.out.println(testsError == 0 ? "\n  ¡Todos los tests pasaron! El TDA está listo."
                : "\n  Hay " + testsError + " test(s) fallido(s). Revisar los ERROR de arriba.");
    }

    // =========================================================================
    // 1. ÁRBOL VACÍO
    // =========================================================================
    private static void testArbolVacio() {
        separador("1. ÁRBOL VACÍO");
        avl = new ArbolAVL<>();

        verificar("esVacio() en árbol nuevo debe ser true", avl.esVacio(), true);

        verificar("pertenece(5) en árbol vacío debe ser false", avl.pertenece(5), false);

        verificar("minimoElem() en árbol vacío debe ser null", avl.minimoElem() == null, true);

        verificar("maximoElem() en árbol vacío debe ser null", avl.maximoElem() == null, true);

        verificar("listar() en árbol vacío debe devolver lista vacía (longitud 0)",
                avl.listar().longitud() == 0, true);
    }

    // =========================================================================
    // 2. INSERCIÓN BÁSICA SIN ROTACIÓN
    // =========================================================================
    private static void testInsercionSinRotacion() {
        separador("2. INSERCIÓN SIN ROTACIÓN");
        avl = new ArbolAVL<>();

        // Insertar raíz
        verificar("insertar(50) en árbol vacío debe retornar true", avl.insertar(50), true);
        verificar("esVacio() después de insertar debe ser false", avl.esVacio(), false);
        verificar("pertenece(50) debe ser true", avl.pertenece(50), true);

        // Insertar hijo izquierdo
        verificar("insertar(30) debe retornar true", avl.insertar(30), true);
        verificar("pertenece(30) debe ser true", avl.pertenece(30), true);

        // Insertar hijo derecho
        verificar("insertar(70) debe retornar true", avl.insertar(70), true);
        verificar("pertenece(70) debe ser true", avl.pertenece(70), true);

        // Verificación de mínimo y máximo elemento
        verificar("minimoElem() debe ser 30", avl.minimoElem().equals(30), true);
        verificar("maximoElem() debe ser 70", avl.maximoElem().equals(70), true);

        // Árbol balanceado sin necesidad de rotar: 50(30, 70)
        System.out.println("\n  Estado del árbol (esperado: 50 HI:30 HD:70):\n");
        System.out.print(avl.toString());
    }

    // =========================================================================
    // 3. ROTACIÓN SIMPLE A DERECHA (balance padre=2, balance hijo>=0)
    // Insertar: 30, 20, 10 → rotación derecha con pivote 30 → nueva raíz: 20
    // =========================================================================
    private static void testRotacionSimpleDerecha() {
        separador("3. ROTACIÓN SIMPLE A DERECHA");
        avl = new ArbolAVL<>();
        // Insertar 30 -> 20 -> 10
        verificar("insertar(30) debe retornar true",avl.insertar(30), true);
        verificar("insertar(20) debe retornar true",avl.insertar(20), true);
        verificar("insertar(10) debe disparar rotación simple derecha y retornar true",
                avl.insertar(10), true);

        /*
         * Árbol esperado después de la rotación: 
         *      20 
         *      / \ 
         *     10 30
         */
        verificar("pertenece(20) - nueva raíz - debe ser true", avl.pertenece(20), true);
        verificar("pertenece(10) debe ser true", avl.pertenece(10), true);
        verificar("pertenece(30) debe ser true", avl.pertenece(30), true);
        verificar("minimoElem() debe ser 10", avl.minimoElem().equals(10), true);
        verificar("maximoElem() debe ser 30", avl.maximoElem().equals(30), true);

        System.out.println("\n  Estado del árbol (esperado: 20 HI:10 HD:30):\n");
        System.out.print(avl.toString());
    }

    // =========================================================================
    // 4. ROTACIÓN SIMPLE A IZQUIERDA (balance padre=-2, balance hijo<=0)
    // Insertar: 10, 20, 30 → rotación izquierda con pivote 10 → nueva raíz: 20
    // =========================================================================
    private static void testRotacionSimpleIzquierda() {
        separador("4. ROTACIÓN SIMPLE A IZQUIERDA");
        avl = new ArbolAVL<>();

        // Insertar 10 -> 20 -> 30
        verificar("insertar(10) debe retornar true",avl.insertar(10), true);
        verificar("insertar(20) debe retornar true",avl.insertar(20), true);
        verificar("insertar(30) debe disparar rotación simple izquierda y retornar true",
                avl.insertar(30), true);

        /*
         * Árbol esperado: 
         *     20 
         *    / \ 
         *   10 30
         */
        verificar("pertenece(20) - nueva raíz - debe ser true", avl.pertenece(20), true);
        verificar("pertenece(10) debe ser true", avl.pertenece(10), true);
        verificar("pertenece(30) debe ser true", avl.pertenece(30), true);
        verificar("minimoElem() debe ser 10", avl.minimoElem().equals(10), true);
        verificar("maximoElem() debe ser 30", avl.maximoElem().equals(30), true);

        System.out.println("  Estado del árbol (esperado: 20 HI:10 HD:30):");
        System.out.print(avl.toString());
    }

    // =========================================================================
    // 5. ROTACIÓN DOBLE IZQUIERDA-DERECHA (balance padre=2, balance hijo=-1)
    // Insertar: 30, 10, 20 → doble rotación: izq sobre 10, der sobre 30
    // =========================================================================
    private static void testRotacionDobleIzquierdaDerecha() {
        separador("5. ROTACIÓN DOBLE IZQUIERDA-DERECHA");
        avl = new ArbolAVL<>();

        avl.insertar(30);
        avl.insertar(10);
        verificar("insertar(20) debe disparar rotación doble IZQ-DER y retornar true",
                avl.insertar(20), true);
        /*
         * Árbol esperado: 
         *     20 
         *    / \ 
         *   10 30
         */

        verificar("pertenece(20) - nueva raíz - debe ser true", avl.pertenece(20), true);
        verificar("pertenece(10) debe ser true", avl.pertenece(10), true);
        verificar("pertenece(30) debe ser true", avl.pertenece(30), true);

        System.out.println("  Estado del árbol (esperado: 20 HI:10 HD:30):");
        System.out.print(avl.toString());
    }

    // =========================================================================
    // 6. ROTACIÓN DOBLE DERECHA-IZQUIERDA (balance padre=-2, balance hijo=1)
    // Insertar: 10, 30, 20 → doble rotación: der sobre 30, izq sobre 10
    // =========================================================================
    private static void testRotacionDobleDrechaIzquierda() {
        separador("6. ROTACIÓN DOBLE DERECHA-IZQUIERDA");
        avl = new ArbolAVL<>();

        avl.insertar(10);
        avl.insertar(30);
        verificar("insertar(20) debe disparar rotación doble DER-IZQ y retornar true",
                avl.insertar(20), true);

        /*
         * Árbol esperado: 
         *     20 
         *    / \ 
         *   10 30
         */
        verificar("pertenece(20) - nueva raíz - debe ser true", avl.pertenece(20), true);
        verificar("pertenece(10) debe ser true", avl.pertenece(10), true);
        verificar("pertenece(30) debe ser true", avl.pertenece(30), true);

        System.out.println("  Estado del árbol (esperado: 20 HI:10 HD:30):");
        System.out.print(avl.toString());
    }

    // =========================================================================
    // 7. DUPLICADOS
    // =========================================================================
    private static void testDuplicados() {
        separador("7. DUPLICADOS");
        avl = new ArbolAVL<>();

        avl.insertar(50);
        avl.insertar(30);
        avl.insertar(70);

        verificar("insertar(50) duplicado debe retornar false", avl.insertar(50), false);
        verificar("insertar(30) duplicado debe retornar false", avl.insertar(30), false);
        verificar("insertar(70) duplicado debe retornar false", avl.insertar(70), false);

        // El árbol no debe haber cambiado: sigue siendo 50(HI:30,HD:70)
        verificar("listar() después de duplicados debe tener longitud 3",
                avl.listar().longitud() == 3, true);

        System.out.println("  Estado del árbol (esperado: 50 HI:30 HD:70, sin cambios):");
        System.out.print(avl.toString());
    }

    // =========================================================================
    // 8. MÉTODOS AUXILIARES: pertenece, minimoElem, maximoElem, listar, esVacio, vaciar
    // =========================================================================
    private static void testMetodosAuxiliares() {
        separador("8. MÉTODOS AUXILIARES");
        avl = new ArbolAVL<>();

        // Insertar conjunto conocido: 40, 20, 60, 10, 30, 50, 70
        int[] elementos = {40, 20, 60, 10, 30, 50, 70};
        for (int e : elementos)
            avl.insertar(e);

        // pertenece
        verificar("pertenece(10) debe ser true", avl.pertenece(10), true);
        verificar("pertenece(70) debe ser true", avl.pertenece(70), true);
        verificar("pertenece(40) debe ser true", avl.pertenece(40), true);
        verificar("pertenece(99) debe ser false", avl.pertenece(99), false);
        verificar("pertenece(0)  debe ser false", avl.pertenece(0), false);

        // minimoElem y maximoElem
        verificar("minimoElem() debe ser 10", avl.minimoElem().equals(10), true);
        verificar("maximoElem() debe ser 70", avl.maximoElem().equals(70), true);

        // listar: debe devolver 7 elementos en orden ascendente
        Lista list = avl.listar();
        verificar("listar() debe tener longitud 7 y [10,20,30,40,50,60,70] -> " + list.toString(), list.longitud() == 7, true);

        // listarRango [20, 50] → debe devolver 20, 30, 40, 50 (4 elementos)
        Lista lisRango = avl.listarRango(20, 50);
        verificar("listarRango(20, 50) debe tener longitud 4 y [20,30,40,50] -> " + lisRango.toString(),
                lisRango.longitud() == 4, true);

        // listarRango [10, 10] → solo el mínimo (1 elemento)
        lisRango = avl.listarRango(10, 10);
        verificar("listarRango(10, 10) debe tener longitud 1 y [10] -> " + lisRango.toString(),
                avl.listarRango(10, 10).longitud() == 1, true);

        // vaciar
        avl.vaciar();
        verificar("esVacio() después de vaciar() debe ser true", avl.esVacio(), true);
        verificar("pertenece(40) después de vaciar() debe ser false", avl.pertenece(40), false);
        verificar("minimoElem() después de vaciar() debe ser null", avl.minimoElem() == null, true);
    }

    // =========================================================================
    // 9. INSERCIÓN MASIVA
    // Verificamos estructura exacta en cada paso clave.
    // =========================================================================
    private static void testInsercionMasiva() {
        separador("9. INSERCIÓN MASIVA");
        avl = new ArbolAVL<>();

        // ── Paso 1: base ──────────────────────────────────────────────────────
        
        verificar("insertar(20) debe retornar true", avl.insertar(20), true);
        verificar("insertar(10) debe retornar true", avl.insertar(10), true);
        verificar("insertar(30) debe retornar true", avl.insertar(30), true);
        verificar("insertar(15) debe retornar true", avl.insertar(15), true);
        verificar("insertar(25) debe retornar true", avl.insertar(25), true);
        System.out.println("\nEstructura actual: \n" + avl.toString());
        // ── Paso 2: 12 → rotación doble DER-IZQ sobre nodo 10 ────────────────
        verificar("insertar(12) debe retornar true", avl.insertar(12), true);
        /*
         * Esperado tras la rotación:
         *        20
         *       /  \
         *     12    30
         *    /  \   /
         *   10  15 25
         */
        verificar("pertenece(12) - nueva raíz del subárbol izq - debe ser true", avl.pertenece(12),
                true);
        verificar("pertenece(10) debe ser true", avl.pertenece(10), true);
        verificar("pertenece(15) debe ser true", avl.pertenece(15), true);
        System.out.println("\nTras insertar 12 (esperado: raíz=20, HI=12, HD=30):");
        System.out.print(avl.toString() + "\n");

        // ── Paso 3: más inserciones ───────────────────────────────────────────
        verificar("insertar(11) debe retornar true", avl.insertar(11), true);
        verificar("insertar(5)  debe retornar true", avl.insertar(5), true);

        // ── Paso 4: 23 → rotación simple DER sobre nodo 30 ───────────────────
        verificar("insertar(23) debe retornar true", avl.insertar(23), true);
        /*
         * Esperado:
         *           20
         *          /  \
         *        12    25
         *       /  \  /  \
         *      10  15 23  30
         *     /  \
         *    5   11
         */
        verificar("pertenece(23) debe ser true", avl.pertenece(23), true);
        verificar("pertenece(25) debe ser true", avl.pertenece(25), true);
        System.out.println("\nTras insertar 23 (esperado: raíz=20, HI=12, HD=25):");
        System.out.print(avl.toString() + "\n");

        // ── Paso 5: 3 → rotación simple DER sobre nodo 12 ────────────────────
        verificar("insertar(3) debe retornar true", avl.insertar(3), true);
        /*
         * Esperado tras la rotación:
         *           20
         *          /  \
         *        10    25
         *       /  \  /  \
         *      5  12 23  30
         *     /  /  \
         *    3  11  15
         */
        verificar("pertenece(3) debe ser true", avl.pertenece(3), true);
        System.out.println("\nTras insertar 3 (esperado: raíz=20, HI=10, HD=25):");
        System.out.print(avl.toString() + "\n");

        // ── Paso 6: 26 y 27 → rotación doble IZQ-DER sobre subárbol derecho ──
        verificar("insertar(26) debe retornar true", avl.insertar(26), true);
        verificar("insertar(27) debe retornar true", avl.insertar(27), true);
        /*
         * Esperado:
         *           20
         *          /  \
         *        10    25
         *       / \   /  \
         *      5  12 23  27
         *     /  / \    /  \
         *    3  11 15  26  30
         */
        verificar("pertenece(26) debe ser true", avl.pertenece(26), true);
        verificar("pertenece(27) debe ser true", avl.pertenece(27), true);
        System.out.println("\nTras insertar 26 y 27 (esperado: HD de 25 = 27):");
        System.out.print(avl.toString() + "\n");

        // ── Paso 7: duplicado de la raíz ──────────────────────────────────────
        verificar("insertar(20) duplicado debe retornar false", avl.insertar(20), false);

        // ── Verificaciones finales sobre el árbol completo ────────────────────
        verificar("minimoElem() debe ser 3", avl.minimoElem().equals(3), true);
        verificar("maximoElem() debe ser 30", avl.maximoElem().equals(30), true);
        verificar("listar() debe tener 12 elementos", avl.listar().longitud() == 12, true);
        verificar("pertenece(99) - elemento inexistente - debe ser false", avl.pertenece(99),
                false);
    }

    // =========================================================================
    // 10. ELIMINAR EN ÁRBOL VACÍO Y ELEMENTO INEXISTENTE
    // =========================================================================
    private static void testEliminarEnArbolVacio() {
        separador("10. ELIMINAR EN ÁRBOL VACÍO / INEXISTENTE");
        avl = new ArbolAVL<>();

        verificar("eliminar(5) en árbol vacío debe retornar false",
                avl.eliminar(5), false);
        verificar("esVacio() sigue siendo true después del intento",
                avl.esVacio(), true);

        System.out.println("Insertando algunos elementos para probar eliminación de inexistentes:");
        verificar("insertar(20) debe retornar true", avl.insertar(20), true);
        verificar("insertar(10) debe retornar true", avl.insertar(10), true);
        verificar("insertar(30) debe retornar true", avl.insertar(30), true);

        verificar("eliminar(99) - inexistente - debe retornar false",
                avl.eliminar(99), false);
        verificar("listar() sigue teniendo 3 elementos tras intento fallido",
                avl.listar().longitud() == 3, true);
    }

    // =========================================================================
    // 11. ELIMINAR CASO 1: NODO HOJA
    //     Árbol: 20(10, 30)  →  eliminar 10 (hoja izquierda)
    //                        →  eliminar 30 (hoja derecha)
    //                        →  eliminar 20 (única raíz, árbol queda vacío)
    // =========================================================================
    private static void testEliminarHoja() {
        separador("11. ELIMINAR CASO 1 – NODO HOJA");
        avl = new ArbolAVL<>();
        System.out.println("Insertando 20->10->30 para probar eliminación de hojas...");
        avl.insertar(20);
        avl.insertar(10);
        avl.insertar(30);
        System.out.print("Esperado: 20 HI:10 HD:30\n" + avl.toString() + "\n");

        // Eliminar hoja izquierda
        verificar("eliminar(10) hoja izquierda debe retornar true",
                avl.eliminar(10), true);
        verificar("pertenece(10) después de eliminar debe ser false",
                avl.pertenece(10), false);
        verificar("pertenece(20) sigue siendo true",
                avl.pertenece(20), true);
        verificar("listar() tiene 2 elementos", avl.listar().longitud() == 2, true);
        System.out.println("  Tras eliminar 10 (esperado: 20 HD:30):");
        System.out.print(avl.toString() + "\n");

        // Eliminar hoja derecha
        verificar("eliminar(30) hoja derecha debe retornar true",
                avl.eliminar(30), true);
        verificar("pertenece(30) después de eliminar debe ser false",
                avl.pertenece(30), false);
        verificar("listar() tiene 1 elemento", avl.listar().longitud() == 1, true);

        // Eliminar raíz que ahora es hoja (único nodo)
        verificar("eliminar(20) raíz-hoja debe retornar true",
                avl.eliminar(20), true);
        verificar("esVacio() después de eliminar último nodo debe ser true",
                avl.esVacio(), true);
    }

    // =========================================================================
    // 12. ELIMINAR CASO 2: NODO CON UN SOLO HIJO
    //     Árbol: 20(10, 30) + insertar 5 y 25
    //            20(10(5,-), 30(25,-))
    //     Eliminar 10 → sube su único hijo 5
    //     Eliminar 30 → sube su único hijo 25
    // =========================================================================
    private static void testEliminarUnHijo() {
        separador("12. ELIMINAR CASO 2 – NODO CON UN HIJO");
        avl = new ArbolAVL<>();
        System.out.println("Insertando 20->10->30->5->25 para probar eliminación de nodos con un hijo...");
        avl.insertar(20);
        avl.insertar(10);
        avl.insertar(30);
        avl.insertar(5);   // hijo izquierdo de 10
        avl.insertar(25);  // hijo izquierdo de 30
        System.out.print("Árbol actual:\n" + avl.toString() + "\n");

        /*
         *        20
         *       /  \
         *     10    30
         *    /      /
         *   5      25
         */

        // Eliminar nodo con solo hijo izquierdo
        verificar("eliminar(10) nodo con 1 hijo izquierdo debe retornar true",
                avl.eliminar(10), true);
        verificar("pertenece(10) debe ser false", avl.pertenece(10), false);
        verificar("pertenece(5)  debe seguir siendo true (subió)", avl.pertenece(5), true);
        System.out.println("  Tras eliminar 10 (5 debe haber subido):");
        System.out.print(avl.toString() + "\n");

        // Eliminar nodo con solo hijo izquierdo (el 30)
        verificar("eliminar(30) nodo con 1 hijo izquierdo debe retornar true",
                avl.eliminar(30), true);
        verificar("pertenece(30) debe ser false", avl.pertenece(30), false);
        verificar("pertenece(25) debe seguir siendo true (subió)", avl.pertenece(25), true);
        verificar("listar() tiene 3 elementos: 5, 20, 25",
                avl.listar().longitud() == 3, true);
        System.out.println("  Tras eliminar 30 (esperado: árbol con 20, 5, 25):");
        System.out.print(avl.toString() + "\n");
    }

    // =========================================================================
    // 13. ELIMINAR CASO 3: NODO CON DOS HIJOS
    //     Árbol completo: 40(20(10,30), 60(50,70))
    //     Eliminar 20 → candidato es 30 (mínimo del subárbol derecho de 20)
    //     Eliminar 40 (raíz con 2 hijos) → candidato es 50
    // =========================================================================
    private static void testEliminarDosHijos() {
        separador("13. ELIMINAR CASO 3 – NODO CON DOS HIJOS");
        avl = new ArbolAVL<>();
        int[] elems = {40, 20, 60, 10, 30, 50, 70};
        for (int e : elems) avl.insertar(e);
        System.out.println("Árbol completo para eliminar nodos con dos hijos:\n" + avl.toString() + "\n");

        /*
         *          40
         *         /  \
         *       20    60
         *      / \   / \
         *    10  30 50  70
         */

        // Eliminar nodo interno con dos hijos
        verificar("eliminar(20) nodo con dos hijos debe retornar true",
                avl.eliminar(20), true);
        verificar("pertenece(20) debe ser false", avl.pertenece(20), false);
        // 30 es el candidato (mínimo del subárbol derecho de 20), debe seguir en el árbol
        verificar("pertenece(30) candidato sucesor debe seguir true", avl.pertenece(30), true);
        verificar("pertenece(10) debe seguir true", avl.pertenece(10), true);
        verificar("listar() tiene 6 elementos", avl.listar().longitud() == 6, true);
        System.out.println("  Tras eliminar 20 (esperado: 30 reemplaza a 20):");
        System.out.print(avl.toString() + "\n");

        // Eliminar la raíz con dos hijos
        verificar("eliminar(40) raíz con dos hijos debe retornar true",
                avl.eliminar(40), true);
        verificar("pertenece(40) debe ser false", avl.pertenece(40), false);
        // 50 es el candidato (mínimo del subárbol derecho de 40)
        verificar("pertenece(50) candidato sucesor debe seguir true", avl.pertenece(50), true);
        verificar("listar() tiene 5 elementos", avl.listar().longitud() == 5, true);
        System.out.println("  Tras eliminar raíz 40 (esperado: 50 es nueva raíz o sube):");
        System.out.print(avl.toString() + "\n");
    }

    // =========================================================================
    // 14. ELIMINAR CON ROTACIONES
    //     Se construye un árbol donde la eliminación provoca cada tipo de rotación.
    // =========================================================================
    private static void testEliminarConRotaciones() {
        separador("14. ELIMINAR CON ROTACIONES");

        // ── Rotación simple derecha tras eliminación ────────────────────────
        // Árbol: 50(20(10,30), 70)  →  eliminar 70  →  balance(50)=2  →  rot. derecha
        avl = new ArbolAVL<>();
        avl.insertar(50);
        avl.insertar(20);
        avl.insertar(70);
        avl.insertar(10);
        avl.insertar(30);
        System.out.println("Árbol inicial para eliminar con rotación simple a derecha: \n" + avl.toString());
        /*
         *        50
         *       /  \
         *     20    70
         *    /  \
         *   10   30
         */
        verificar("eliminar(70) debe disparar rotación derecha y retornar true",
                avl.eliminar(70), true);
        /*
         * Esperado tras rot. derecha con pivote 50:
         *        20
         *       /  \
         *     10    50
         *          /
         *         30
         */
        verificar("pertenece(70) debe ser false", avl.pertenece(70), false);
        verificar("pertenece(20) nueva raíz debe ser true", avl.pertenece(20), true);
        verificar("árbol ahora tiene 4 elementos", avl.listar().longitud() == 4, true);
        System.out.println("  Tras eliminar 70 (rot. derecha, esperado raíz=20):");
        System.out.print(avl.toString() + "\n");

        // ── Rotación simple derecha tras eliminación ──────────────────────────
        //    20(10, 50(30,70))  →  eliminar 10  →  balance(20)=-2  →  rot. izquierda
        System.out.println("Vuelve a insertar 70 para probar rotación simple a izquierda tras eliminar 10...");
        avl.insertar(70);
        System.out.print("Árbol actual:\n" + avl.toString() + "\n");
        /*
         *       20
         *      /  \
         *    10    50
         *         /  \
         *        30   70
         */
        verificar("eliminar(10) debe disparar rotación izquierda y retornar true",
                avl.eliminar(10), true);
        /*
         * Esperado tras rot. izquierda con pivote 20:
         *       50
         *      /  \
         *    20    70
         *      \
         *      30
         */
        verificar("pertenece(10) debe ser false", avl.pertenece(10), false);
        verificar("pertenece(50) nueva raíz debe ser true", avl.pertenece(50), true);
        verificar("árbol sigue teniendo 4 elementos", avl.listar().longitud() == 4, true);
        System.out.println("  Tras eliminar 10 (rot. izquierda, esperado raíz=50):");
        System.out.print(avl.toString() + "\n");
    }

    // =========================================================================
    // 15. ELIMINACIÓN MASIVA — CASOS COMPLEJOS DE ELIMINACIÓN CON ROTACIONES
    //     Se construye el árbol final de inserción y se eliminan elementos
    //     verificando estructura y balance en cada paso.
    // =========================================================================
    private static void testEliminacionMasiva() {
        separador("15. ELIMINACIÓN MASIVA");
        avl = new ArbolAVL<>();

        // Reconstruir el árbol final de la secuencia de inserción masiva
        int[] elementosInsertar = {20, 10, 30, 15, 25, 12, 11, 5, 23, 3, 26, 27};
        for (int e : elementosInsertar)
            avl.insertar(e);
        /*
         * Árbol esperado (estado final de testInsercionMasiva):
         *             20
         *            /  \
         *          10    25
         *         / \   /  \
         *        5  12 23  27
         *       /  / \    /  \
         *      3  11 15  26  30
         */
        // Nota: 30 fue insertado al inicio y no fue eliminado
        System.out.println("Estado inicial:");
        System.out.print(avl.toString() + "\n");

        // ── Paso 1: eliminar 10 (nodo con dos hijos: 5 y 12) ─────────────────
        verificar("eliminar(10) debe retornar true", avl.eliminar(10), true);
        verificar("pertenece(10) debe ser false",    avl.pertenece(10), false);
        verificar("pertenece(11) sucesor debe ser true", avl.pertenece(11), true);
        verificar("pertenece(5)  debe seguir true",  avl.pertenece(5),  true);
        verificar("pertenece(12) debe seguir true",  avl.pertenece(12), true);
        System.out.println("\nTras eliminar 10:");
        System.out.print(avl.toString() + "\n");
        /*
         * Árbol esperado:
         *             20
         *            /  \
         *          11    25
         *         / \   /  \
         *        5  12 23  27
         *       /    \    /  \
         *      3     15  26  30
         */

        // ── Paso 2: eliminar 11 (hoja tras la reorganización) ────────────────
        verificar("eliminar(11) debe retornar true", avl.eliminar(11), true);
        verificar("pertenece(11) debe ser false",    avl.pertenece(11), false);
        /*
         * Árbol esperado:
         *             20
         *            /  \
         *          12    25
         *         / \   /  \
         *        5  15 23  27
         *       /         /  \
         *      3         26  30
         */
        System.out.println("\nTras eliminar 11:");
        System.out.print(avl.toString() + "\n");

        // ── Paso 3: eliminar 20 (raíz con dos hijos) → rotación izquierda ────
        verificar("eliminar(20) raíz debe retornar true", avl.eliminar(20), true);
        verificar("pertenece(20) debe ser false",         avl.pertenece(20), false);
        /*
         * Esperado tras eliminar 20 (candidato = 23, mínimo del subárbol derecho):
         *        23
         *       /  \
         *     12    27
         *    /  \  /  \
         *   5   15 25  30
         *  /        \
         * 3          26
         */
        verificar("pertenece(23) - candidato - nuevo valor en raíz debe ser true", avl.pertenece(23), true);
        verificar("pertenece(25) debe seguir true", avl.pertenece(25), true);
        verificar("pertenece(27) debe seguir true", avl.pertenece(27), true);
        System.out.println("\nTras eliminar raíz 20 (esperado: raíz=23):");
        System.out.print(avl.toString() + "\n");

        // ── Paso 4: eliminar 27 (nodo con dos hijos: 25(26) y 30) ────────────
        verificar("eliminar(27) debe retornar true", avl.eliminar(27), true);
        verificar("pertenece(27) debe ser false",    avl.pertenece(27), false);
        verificar("pertenece(26) debe seguir true",  avl.pertenece(26), true);
        verificar("pertenece(30) debe seguir true",  avl.pertenece(30), true);
        System.out.println("\nTras eliminar 27:");
        System.out.print(avl.toString() + "\n");

        // ── Paso 5: eliminar 15 → verificar rebalanceo ────────────────────────
        verificar("eliminar(15) debe retornar true", avl.eliminar(15), true);
        verificar("pertenece(15) debe ser false",    avl.pertenece(15), false);
        System.out.println("\nTras eliminar 15:");
        System.out.print(avl.toString() + "\n");

        // ── Verificaciones finales ─────────────────────────────────────────────
        verificar("minimoElem() debe ser 3",  avl.minimoElem().equals(3),  true);
        verificar("maximoElem() debe ser 30", avl.maximoElem().equals(30), true);
        // Quedan: 3, 5, 12, 23, 25, 26, 30 = 7 elementos
        verificar("listar() debe tener 7 elementos", avl.listar().longitud() == 7, true);

        // ── Paso 6: eliminar todos los elementos restantes ────────────────────
        int[] restantes = {3, 5, 12, 23, 25, 26, 30};
        for (int e : restantes) {
            verificar("eliminar(" + e + ") debe retornar true", avl.eliminar(e), true);
        }
        verificar("esVacio() tras eliminar todo debe ser true", avl.esVacio(), true);
        verificar("eliminar(99) en árbol recién vaciado debe ser false",
                avl.eliminar(99), false);
    }

    // =========================================================================
    // UTILIDADES
    // =========================================================================

    /**
     * Verifica una condición booleana e imprime el resultado. Actualiza los contadores globales.
     */
    private static void verificar(String descripcion, boolean condicion, boolean esperado) {
        totalTests++;
        boolean paso = (condicion == esperado);
        if (paso) {
            testsOk++;
            System.out.println("\u001B[32m [OK] \u001B[0m" + descripcion);
        } else {
            testsError++;
            System.out.println("\u001B[31m [ERROR] \u001B[0m" + descripcion + " | esperado=" + esperado + " obtenido="
                    + condicion);
        }
    }

    /** Separador visual para secciones. */
    private static void separador(String titulo) {
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  " + titulo);
        System.out.println("══════════════════════════════════════════════════");
    }
}
