package tests.lineales;

import lineales.dinamicas.Pila;
//import lineales.estaticas.Pila;

public class TestPila {

    public static void main(String args[]) {
        // Probando constructor vacío
        System.out.println("Creando nueva pila...");
        Pila p = new Pila();
        System.out.println("Pila original: " + p.toString() + "\n"); //Pila vacía, probando toString()
        // Probando apilar()
        System.out.println("Apila 'as'. Devuelve: " + ((p.apilar("as"))) + " " + p.toString());
        System.out.println("Apila 'es'. Devuelve: " + ((p.apilar("es"))) + " " + p.toString());
        System.out.println("Apila 'is'. Devuelve: " + ((p.apilar("is"))) + " " + p.toString());
        System.out.println("Apila 'os'. Devuelve: " + ((p.apilar("os"))) + " " + p.toString());
        System.out.println("Apila 'us'. Devuelve: " + ((p.apilar("us"))) + " " + p.toString());
        System.out.println("Apila 'az'. Devuelve: " + ((p.apilar("az"))) + " " + p.toString());
        System.out.println("Apila 'ez'. Devuelve: " + ((p.apilar("ez"))) + " " + p.toString());
        System.out.println("Apila 'iz'. Devuelve: " + ((p.apilar("iz"))) + " " + p.toString());
        System.out.println("Apila 'oz'. Devuelve: " + ((p.apilar("oz"))) + " " + p.toString());
        System.out.println("Apila 'uz'. Devuelve: " + ((p.apilar("uz"))) + " " + p.toString());
        System.out.println("Apila 'zz'. Espera true en dinamica y false en estática. Devuelve: " + ((p.apilar("zz"))) + " " + p.toString() + "\n");
        if (p.obtenerTope().equals("zz")) {
            System.out.println("Si pudo apilar 'zz', lo desapila");
            p.desapilar();
        }
        System.out.println("Pila original: " + p.toString() + "\n");

        // Probando esVacia()
        System.out.println("esVacia() espera false: " + p.esVacia() + "\n");
        // Probando obtenerTope()
        System.out.println("Obtiene tope, espera 'uz': " + p.obtenerTope() + "\n");

        //Probando método clone()
        System.out.println("Clonando la pila...");
        Pila clon = p.clone();

        // Mostramos pila original y clon
        System.out.println("Pila original: " + p.toString());
        System.out.println("Clon: " + clon.toString() + "\n");
        
        // Probando desapilar() en pila original
        System.out.println("Desapilar en pila original espera true: " + p.desapilar() + " " + p.toString());
        System.out.println("Desapilar en pila original espera true: " + p.desapilar() + " " + p.toString() + "\n");
        // Mostramos pila original y clon
        System.out.println("Pila original: " + p.toString());
        System.out.println("Clon: " + clon.toString() + "\n");

        // Probamos método vaciar()
        System.out.println("Vaciando la pila original...");
        p.vaciar();
        System.out.println("Mostrando pila original (debe estar vacía): " + p.toString() + "\n");
        // Probamos desapilar() con pila vacía
        System.out.println("Desapilar en pila original, espera false: " + p.desapilar() + ". Estado de la pila original: " + p.toString() + "\n");
        // Probamos obtenerTope() con pila vacía
        System.out.println("Obtiene tope de pila original, espera null: " + p.obtenerTope() + "\n");
        
        // Mostramos pila original y clon
        System.out.println("Comprobamos que sólo se modificó la pila original, mientras que clon se mantiene intacto:");
        System.out.println("Pila original: " + p.toString());
        System.out.println("Clon: " + clon.toString() + "\n");
    }
}
