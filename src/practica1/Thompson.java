package practica1;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author clnx
 */
public class Thompson {

    int contador = 0;

    public Grafo copiar(Grafo g) {
        Grafo grafoCopia = new Grafo(null, null);
        Map tablaH = new LinkedHashMap();
        NodoThompson nodoRetorno = retornaN(tablaH, g,grafoCopia, g.inicio);
        grafoCopia.inicio = nodoRetorno;
        return grafoCopia;
    }

    public NodoThompson retornaN(Map tablaH, Grafo GrafoAntiguo, Grafo GrafoNuevo, NodoThompson NodoGrafo) {
        NodoThompson nodoN = new NodoThompson(contador++);
        tablaH.put(NodoGrafo.hashCode(), nodoN);
        for (NodoArista nodoConexion : NodoGrafo.aristas) {
            if (!tablaH.containsKey(nodoConexion.destino.hashCode())) {
                NodoThompson nodoRetorno = retornaN(tablaH, GrafoAntiguo, GrafoNuevo, nodoConexion.destino);
  
                nodoN.aristas.add(new NodoArista(nodoConexion.contenido, nodoRetorno));
            } else {

                nodoN.aristas.add(new NodoArista(nodoConexion.contenido, ((NodoThompson) tablaH.get(nodoConexion.destino.hashCode()))));
            }
        }
        if (NodoGrafo.aristas.isEmpty()) {
            GrafoNuevo.aceptacion = nodoN;
        }
        return nodoN;
    }

    public Grafo crearGrafo(Nodo nodo) {
        Grafo hj1, hj2;
        String actual = nodo.getLexema();
        switch (actual) {
            case "+":
                hj1 = crearGrafo(nodo.hijos.get(0));
                Grafo copy = copiar(hj1);
                NodoThompson primero3 = new NodoThompson(contador++);
                primero3.aristas.add(new NodoArista("ε", hj1.inicio));
                primero3.aristas.add(new NodoArista("ε", copy.inicio));
                hj1.aceptacion.aristas.add(new NodoArista("ε", hj1.inicio));
                hj1.aceptacion.aristas.add(new NodoArista("ε", copy.inicio));
                Grafo tempy;
                tempy = new Grafo(primero3, copy.aceptacion);
                return tempy;

            case "*":
                hj1 = crearGrafo(nodo.hijos.get(0));
                NodoThompson primero2 = new NodoThompson(contador++);
                NodoThompson ultimo2 = new NodoThompson(contador++);
                hj1.aceptacion.aristas.add(new NodoArista("ε", hj1.inicio));
                primero2.aristas.add(new NodoArista("ε", hj1.inicio));
                primero2.aristas.add(new NodoArista("ε", ultimo2));
                hj1.aceptacion.aristas.add(new NodoArista("ε", ultimo2));
                Grafo tempx;
                tempx = new Grafo(primero2, ultimo2);
                return tempx;

            case "?":
                hj1 = crearGrafo(nodo.hijos.get(0));
                NodoThompson i = new NodoThompson(contador++);
                NodoThompson f = new NodoThompson(contador++);
                i.aristas.add(new NodoArista("ε", f));
                Grafo t;
                t = new Grafo(i, f);

                NodoThompson primero1 = new NodoThompson(contador++);
                NodoThompson ultimo1 = new NodoThompson(contador++);
                primero1.aristas.add(new NodoArista("ε", t.inicio));
                primero1.aristas.add(new NodoArista("ε", hj1.inicio));
                t.aceptacion.aristas.add(new NodoArista("ε", ultimo1));
                hj1.aceptacion.aristas.add(new NodoArista("ε", ultimo1));
                Grafo tempa;
                tempa = new Grafo(primero1, ultimo1);
                return tempa;

            case "|":
                hj1 = crearGrafo(nodo.hijos.get(0));
                hj2 = crearGrafo(nodo.hijos.get(1));

                NodoThompson primero = new NodoThompson(contador++);
                NodoThompson ultimo = new NodoThompson(contador++);
                primero.aristas.add(new NodoArista("ε", hj1.inicio));
                primero.aristas.add(new NodoArista("ε", hj2.inicio));
                hj1.aceptacion.aristas.add(new NodoArista("ε", ultimo));
                hj2.aceptacion.aristas.add(new NodoArista("ε", ultimo));
                Grafo temp;
                temp = new Grafo(primero, ultimo);
                return temp;
            case ".":
                hj1 = crearGrafo(nodo.hijos.get(0));
                hj2 = crearGrafo(nodo.hijos.get(1));

                hj1.aceptacion.aristas = hj2.inicio.aristas;
                Grafo tempc;
                tempc = new Grafo(hj1.inicio, hj2.aceptacion);
                return tempc;

            default:
                NodoThompson inid;
                NodoThompson find;
                inid = new NodoThompson(contador++);
                find = new NodoThompson(contador++);
                inid.aristas.add(new NodoArista(nodo.getLexema(), find));
                Grafo tempv;
                tempv = new Grafo(inid, find);
                return tempv;
        }
    }
}
