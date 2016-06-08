package practica1;

import java.util.ArrayList;

/**
 *
 * @author clnx
 */
public class NodoEr {
    String identificador;
    Grafo grafo;
    Nodo retorno;
    public ArrayList<Nodo> reservadas;
    
    public NodoEr(String id,Grafo gf,Nodo ret){
        this.identificador = id;
        this.grafo = gf;
        this.retorno = ret;
        this.reservadas = new ArrayList<>();
    }
    
    public NodoEr(String id,Grafo gf,Nodo ret,ArrayList<Nodo> reserv){
        this.identificador = id;
        this.grafo = gf;
        this.retorno = ret;
        this.reservadas = reserv;
    }
    
    public void addHijos(Nodo h){
        this.reservadas.add(h);
    }
}