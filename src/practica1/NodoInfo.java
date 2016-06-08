package practica1;

/**
 *
 * @author clnx
 */
public class NodoInfo{
    private Grafo afn; //automata finito no determinista
    private Nodo retorno; //nodo que contiene el metodo retorno y los id reservados en <hijos>
    
    public NodoInfo(Grafo afnd,Nodo ret){
        this.afn = afnd;
        this.retorno = ret;
    }
    
    public Grafo getAfn(){
        return this.afn;
    }
    
    public Nodo getRetorno(){
        return this.retorno;
    }
    
    public void setAfn(Grafo afnd){
        this.afn = afnd;
    }
    
    public void setRetorno(Nodo ret){
        this.retorno = ret;
    }
    
    public boolean reserv(){
        if(this.retorno.hijos.size()==5){ //no tiene reservadas
            return false;
        }else{
            return true;
        }
    }
}
