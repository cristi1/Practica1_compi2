package practica1;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author clnx
 */
public class Grafo {
    NodoThompson inicio;
    NodoThompson aceptacion;
    
    public Grafo(NodoThompson ini,NodoThompson fin){
        this.inicio = ini;
        this.aceptacion = fin;
    }
    
    public void setInicio(NodoThompson ini){
        this.inicio = ini;
    }
    
    public void setAceptacion(NodoThompson acept){
        this.aceptacion = acept;
    }
    
    public NodoThompson getInicio(){
        return this.inicio;
    }
    
    public NodoThompson getAceptacion(){
        return this.aceptacion;
    }
    
    public Map retornoTransicion(Map tablaH, NodoThompson nodo) {
        tablaH.put(nodo.getNumero(), nodo);
        for (NodoArista nodoConexion : nodo.aristas) {
            if (!tablaH.containsKey(nodoConexion.destino.getNumero())) {
                retornoTransicion(tablaH, nodoConexion.destino);
            }
        }
        return tablaH;
    }
    
    public ArrayList<String> ListarTerminales(Map tablaH, ArrayList<String> lista, NodoThompson nodo) {
        tablaH.put(nodo.hashCode(), nodo);
        for (NodoArista nodoConexion : nodo.aristas) {
            if (!tablaH.containsKey(nodoConexion.destino.hashCode())) {
                ListarTerminales(tablaH, lista, nodoConexion.destino);
                if (!nodoConexion.contenido.equals("ε") && !lista.contains(nodoConexion.contenido)) {
                    lista.add(nodoConexion.contenido);
                }
            }
        }
        return lista;
    }
}