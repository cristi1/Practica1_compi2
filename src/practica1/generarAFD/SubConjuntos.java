package practica1.generarAFD;

import java.util.ArrayList;
import java.util.Map;
import practica1.*;

/**
 *
 * @author clnx
 */
public class SubConjuntos {

    ArrayList<Integer> estadosEpsilon = new ArrayList<>();

    public void agrregarSubconjunto(int num) {
        estadosEpsilon.add(num);
    }

    public void SetListaEstados(ArrayList<Integer> lista){
        for(int estado : lista){
                if (!estadosEpsilon.contains(estado)) {
                    estadosEpsilon.add(estado);
            }
        }
    }

    public ArrayList<Integer> GetListaEstados() {
        return estadosEpsilon;
    }

    public void metodoCerradura(Map EstadosGrafo, NodoThompson inicio){
        EstadosGrafo.put(inicio.hashCode(), inicio);
        for(NodoArista nodoConexion : inicio.aristas){
                if (EstadosGrafo.size() == 1) {
                    estadosEpsilon.add(inicio.getNumero());
            }
            if (nodoConexion.getContenido().equals("ε")) {
                if (!EstadosGrafo.containsKey(nodoConexion.getDestino().hashCode())) {
                    if (!estadosEpsilon.contains(nodoConexion.getDestino().getNumero())) {
                        metodoCerradura(EstadosGrafo, nodoConexion.getDestino());
                        estadosEpsilon.add(nodoConexion.getDestino().getNumero());
                    }
                }
            }
        }
        if (inicio.aristas.isEmpty()) {
            estadosEpsilon.add(inicio.getNumero());
        }
    }
}