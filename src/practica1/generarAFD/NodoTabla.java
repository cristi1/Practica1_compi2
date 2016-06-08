package practica1.generarAFD;

import java.util.ArrayList;

/**
 *
 * @author clnx
 */
public class NodoTabla {
    
    public int estado;
    public boolean Aceptacion = false;
    public SubConjuntos subconjunto = new SubConjuntos();
    public Transiciones trasiciones = new Transiciones();

    public void ContieneAceptacion(int numeroA){
        ArrayList<Integer> ListaEstados = subconjunto.GetListaEstados();
        if (ListaEstados.contains(numeroA)){
            Aceptacion = true;
            //System.out.println("estado de acept (NodoTabla): "+numeroA);
        }
    }
}