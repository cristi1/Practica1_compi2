package practica1;

import java.util.ArrayList;

/**
 *
 * @author clnx
 */
public class Nodo {
    public String lexema;
    public String token;
    public String lexEncontrado;
    public int fila;
    public int columna;
    public String tipo;
    public ArrayList<Nodo> hijos;
    
    public Nodo(String token,String lexema, int fila, int columna){
        this.lexema = lexema;
        this.token = token;
        this.fila = fila;
        this.columna = columna;
        this.hijos = new ArrayList<>();
    }
    
    public Nodo(String token,String lexema,String tipo,int row, int col){ //generar token reconocido
        this.token=token;
        this.lexema=lexema;
        this.tipo=tipo;
        this.fila=row;
        this.columna=col;
    }
    
    public Nodo(String string, int row, int column){ //generar error 
        this.lexema = string;
        this.fila = row;
        this.columna = column;
    }
    
    public Nodo(String lexEncont,String tokRet,String lexema,String tipo,int row,int col){
        this.lexEncontrado=lexEncont;
        this.token=tokRet;
        this.lexema=lexema;
    }
    
    public void addHijo(Nodo nuevo){
        this.hijos.add(nuevo);
    }
    
    public String getTipo(){
        return this.tipo;
    }
    
    public String getToken(){
        return this.token;
    }
    
    public String getLexema(){
        return this.lexema;
    }
    
    public int getFila(){
        return this.fila;
    }
    
    public int getColumna(){
        return this.columna;
    }
    
    public ArrayList getHijos(){
        return this.hijos;
    }
}