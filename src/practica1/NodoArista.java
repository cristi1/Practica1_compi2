package practica1;

public class NodoArista {
    //NodoThompson inicio;
    NodoThompson destino;
    String contenido;
    
    public NodoArista(String cont, NodoThompson dest){
        this.contenido = cont;
        this.destino = dest;
    }
    
    public void setDestino(NodoThompson dest){
        this.destino=dest;
    }
    
    public NodoThompson getDestino(){
        return this.destino;
    }
    
    public String getContenido(){
        return this.contenido;
    }
}
