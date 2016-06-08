package practica1.generarAFD;

import java.util.ArrayList;

/**
 *
 * @author clnx
 */
public class Transicion {

    ArrayList<Integer> estados = new ArrayList<>();//lista de las transiciones
    String TransicionCon = "";//terminar con que se mueve
    int numeroCerradura;//numero cerradura a donde va 
    String Tipo;
    
    public void SetListaEstados(ArrayList<Integer> lista) {
        this.estados = lista;
    }

    public ArrayList<Integer> GetListaEstados() {
        return estados;
    }

    public void AddEstado(int estado) {
        this.estados.add(estado);
    }

    public int GetEstadoTransicion() {
        return numeroCerradura;
    }

    public void SetTrancicionCon(String t) {
        this.TransicionCon = t;
    }

    public String GetTrancicionCon() {
        return this.TransicionCon;
    }

    public void setNumeroCerradura(int num) {
        this.numeroCerradura = num;
    }
    
    public String GetTipo(){
        return Tipo;
    }
    
    public void setTipo(String tipo){
        this.Tipo = tipo;
    }
}
