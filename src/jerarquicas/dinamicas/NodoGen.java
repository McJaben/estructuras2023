/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jerarquicas.dinamicas;

/**
 * 
 * @author Benjamín Morales <benjamin.morales at est.fi.uncoma.edu.ar>
 */
public class NodoGen {
    // Atributos
    private Object elem;
    private NodoGen hijoIzquierdo;
    private NodoGen hermanoDerecho;
    
    // Constructor
    public NodoGen(Object elemento, NodoGen hijoI, NodoGen hermanoD) {
        this.elem = elemento;
        this.hijoIzquierdo = hijoI;
        this.hermanoDerecho = hermanoD;
    }
    
    // Observadores
    public Object getElem() {
        return elem;
    }

    public NodoGen getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public NodoGen getHermanoDerecho() {
        return hermanoDerecho;
    }
    
    // Modificadores
    public void setElem(Object elem) {
        this.elem = elem;
    }

    public void setHijoIzquierdo(NodoGen hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public void setHermanoDerecho(NodoGen hermanoDerecho) {
        this.hermanoDerecho = hermanoDerecho;
    }
    
    
}
