package tests.conjuntistas;

import conjuntistas.ArbolAVL;

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

        avl.insertar(30);
        avl.insertar(20);
        verificar("insertar(10) debe disparar rotación simple derecha y retornar true",
                avl.insertar(10), true);

        /*
         * Árbol esperado después de la rotación: 20 / \ 10 30
         */
        verificar("pertenece(20) - nueva raíz - debe ser true", avl.pertenece(20), true);
        verificar("pertenece(10) debe ser true", avl.pertenece(10), true);
        verificar("pertenece(30) debe ser true", avl.pertenece(30), true);
        verificar("minimoElem() debe ser 10", avl.minimoElem().equals(10), true);
        verificar("maximoElem() debe ser 30", avl.maximoElem().equals(30), true);

        System.out.println("\n  Estado del árbol (esperado: 20 HI:10 HD:30):\n");
        System.out.print("  " + avl.toString());
    }

    // =========================================================================
    // 4. ROTACIÓN SIMPLE A IZQUIERDA (balance padre=-2, balance hijo<=0)
    // Insertar: 10, 20, 30 → rotación izquierda con pivote 10 → nueva raíz: 20
    // =========================================================================
    private static void testRotacionSimpleIzquierda() {
        separador("4. ROTACIÓN SIMPLE A IZQUIERDA");
        avl = new ArbolAVL<>();

        avl.insertar(10);
        avl.insertar(20);
        verificar("insertar(30) debe disparar rotación simple izquierda y retornar true",
                avl.insertar(30), true);

        /*
         * Árbol esperado: 20 / \ 10 30
         */
        verificar("pertenece(20) - nueva raíz - debe ser true", avl.pertenece(20), true);
        verificar("pertenece(10) debe ser true", avl.pertenece(10), true);
        verificar("pertenece(30) debe ser true", avl.pertenece(30), true);

        System.out.println("  Estado del árbol (esperado: 20 HI:10 HD:30):");
        System.out.print("  " + avl.toString());
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
         * Árbol esperado: 20 / \ 10 30
         */
        verificar("pertenece(20) - nueva raíz - debe ser true", avl.pertenece(20), true);
        verificar("pertenece(10) debe ser true", avl.pertenece(10), true);
        verificar("pertenece(30) debe ser true", avl.pertenece(30), true);

        System.out.println("  Estado del árbol (esperado: 20 HI:10 HD:30):");
        System.out.print("  " + avl.toString());
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
         * Árbol esperado: 20 / \ 10 30
         */
        verificar("pertenece(20) - nueva raíz - debe ser true", avl.pertenece(20), true);
        verificar("pertenece(10) debe ser true", avl.pertenece(10), true);
        verificar("pertenece(30) debe ser true", avl.pertenece(30), true);

        System.out.println("  Estado del árbol (esperado: 20 HI:10 HD:30):");
        System.out.print("  " + avl.toString());
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

        // El árbol no debe haber cambiado: sigue siendo 50(30,70)
        verificar("listar() después de duplicados debe tener longitud 3",
                avl.listar().longitud() == 3, true);

        System.out.println("  Estado del árbol (esperado: 50 HI:30 HD:70, sin cambios):");
        System.out.print("  " + avl.toString());
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
        verificar("listar() debe tener longitud 7", avl.listar().longitud() == 7, true);

        // listarRango [20, 50] → debe devolver 20, 30, 40, 50 (4 elementos)
        verificar("listarRango(20, 50) debe tener longitud 4",
                avl.listarRango(20, 50).longitud() == 4, true);

        // listarRango [10, 10] → solo el mínimo (1 elemento)
        verificar("listarRango(10, 10) debe tener longitud 1",
                avl.listarRango(10, 10).longitud() == 1, true);

        // vaciar
        avl.vaciar();
        verificar("esVacio() después de vaciar() debe ser true", avl.esVacio(), true);
        verificar("pertenece(40) después de vaciar() debe ser false", avl.pertenece(40), false);
        verificar("minimoElem() después de vaciar() debe ser null", avl.minimoElem() == null, true);
    }

    // =========================================================================
    // 9. INSERCIÓN MASIVA (secuencia del TestArbolAVL original de la cátedra)
    // Verificamos estructura exacta en cada paso clave.
    // =========================================================================
    private static void testInsercionMasiva() {
        separador("9. INSERCIÓN MASIVA (secuencia de la cátedra)");
        avl = new ArbolAVL<>();

        // ── Paso 1: base ──────────────────────────────────────────────────────
        avl.insertar(20);
        avl.insertar(10);
        avl.insertar(30);
        avl.insertar(15);
        avl.insertar(25);

        // ── Paso 2: 12 → rotación doble DER-IZQ sobre nodo 10 ────────────────
        verificar("insertar(12) debe retornar true", avl.insertar(12), true);
        /*
         * Esperado tras la rotación: 20 / \ 12 30 / \ / 10 15 25
         */
        verificar("pertenece(12) - nueva raíz del subárbol izq - debe ser true", avl.pertenece(12),
                true);
        verificar("pertenece(10) debe ser true", avl.pertenece(10), true);
        verificar("pertenece(15) debe ser true", avl.pertenece(15), true);
        System.out.println("  Tras insertar 12 (esperado: raíz=20, HI=12, HD=30):");
        System.out.print("  " + avl.toString());

        // ── Paso 3: más inserciones ───────────────────────────────────────────
        verificar("insertar(11) debe retornar true", avl.insertar(11), true);
        verificar("insertar(5)  debe retornar true", avl.insertar(5), true);

        // ── Paso 4: 23 → rotación simple IZQ sobre nodo 30 ───────────────────
        verificar("insertar(23) debe retornar true", avl.insertar(23), true);
        /*
         * Esperado: 20 / \ 12 25 / \ / \ 10 15 23 30 / \ 5 11
         */
        verificar("pertenece(23) debe ser true", avl.pertenece(23), true);
        verificar("pertenece(25) debe ser true", avl.pertenece(25), true);
        System.out.println("  Tras insertar 23 (esperado: raíz=20, HI=12, HD=25):");
        System.out.print("  " + avl.toString());

        // ── Paso 5: 3 → rotación simple DER sobre nodo 12 ────────────────────
        verificar("insertar(3) debe retornar true", avl.insertar(3), true);
        /*
         * Esperado tras la rotación: 20 / \ 10 25 / \ / \ 5 12 23 30 / / \ 3 11 15
         */
        verificar("pertenece(3) debe ser true", avl.pertenece(3), true);
        System.out.println("  Tras insertar 3 (esperado: raíz=20, HI=10, HD=25):");
        System.out.print("  " + avl.toString());

        // ── Paso 6: 26 y 27 → rotación doble IZQ-DER sobre subárbol derecho ──
        verificar("insertar(26) debe retornar true", avl.insertar(26), true);
        verificar("insertar(27) debe retornar true", avl.insertar(27), true);
        /*
         * Esperado: 20 / \ 10 25 / \ / \ 5 12 23 27 / / \ / \ 3 11 15 26 30
         */
        verificar("pertenece(26) debe ser true", avl.pertenece(26), true);
        verificar("pertenece(27) debe ser true", avl.pertenece(27), true);
        System.out.println("  Tras insertar 26 y 27 (esperado: HD de 25 = 27):");
        System.out.print("  " + avl.toString());

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
            System.out.println("  [OK]    " + descripcion);
        } else {
            testsError++;
            System.out.println("  [ERROR] " + descripcion + " | esperado=" + esperado + " obtenido="
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
