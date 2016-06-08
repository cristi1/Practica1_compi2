package practica1;

import java.util.ArrayList;

/**
 *
 * @author clnx
 * Clase que maneja los nodo thompson
 */
public class NodoThompson {
    
    public boolean numerable; //dice si es numerable o no (no pernitido al finalizar |)
    public int numero; //numero
    public ArrayList<NodoArista> aristas;
    public boolean acept;
    
    public NodoThompson(int cont){
        this.numerable=true;
        this.numero=cont;
        this.aristas = new ArrayList<>();
        this.acept = false;
    }
    
    public NodoThompson(){
        this.numerable=true;
        this.numero=-1;
        this.aristas = new ArrayList<>();
        this.acept = false;
    }
    
    public NodoThompson(boolean numer){
        this.numerable=numer;
        this.numero=-1;
        this.aristas = new ArrayList<>();
        this.acept = false;
    }
    
    public void addArista(NodoArista h){
        this.aristas.add(h);
    }
    
    public ArrayList<NodoArista> getArista(){
        return this.aristas;
    }
    
    public void setNodoDestino(ArrayList<NodoArista> NodoDestino) {
        this.aristas = NodoDestino;
    } 
    public int getNumero(){
        return this.numero;
    }
    
    public void setNumero(int num){
        this.numero = num;
    }
    
    public void setAcept(boolean aceptacion){
        this.acept = aceptacion;
    }
}
