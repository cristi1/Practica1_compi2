package practica1;

import java.util.ArrayList;

/**
 *
 * @author clnx
 */
public class NodoConj {
    private String nombre;
    private int tipo;                              //0-->lista ó 1-->rango
    private ArrayList<Integer> componentes; //lista de componentes del conjunto  

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Integer> getComponentes() {
        return componentes;
    }

    public void setComponentes(ArrayList<Integer> componentes) {
        this.componentes = componentes;
    }
    
    public NodoConj(String nom,int tip){
        this.nombre = nom;
        this.tipo = tip;
        this.componentes = new ArrayList<>();
    }
    
    public void addComponent(int ascii){
        this.componentes.add(ascii);
    }
}