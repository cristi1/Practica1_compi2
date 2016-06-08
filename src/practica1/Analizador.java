package practica1;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import practica1.generarAFD.GrafoAFD;
import practica1.generarAFD.Tabla;

/**
 *
 * @author clnx
 */
public class Analizador {
    private Imprimir imp = new Imprimir();
    //private Nodo arbol;
    private ArrayList<Nodo> errores = new ArrayList<>();
    private Thompson pruebaThompson = new Thompson();
    private Map<String, NodoConj> tabConj = new HashMap<>(); //tabla de conjuntos encontrados
    private Map<Integer, GrafoAFD> tabAutomatas = new HashMap<>(); //tabla que contendra los AFD de cada ER encontrada
    private Map<String, NodoInfo> tabEr = new HashMap<>();
    private int cont=0;

    public ArrayList<Nodo> getErrores() {
        return errores;
    }

    public void setErrores(ArrayList<Nodo> errores) {
        this.errores = errores;
    }

    public Map<String, NodoConj> getTabConj() {
        return tabConj;
    }

    public void setTabConj(Map<String, NodoConj> tabConj) {
        this.tabConj = tabConj;
    }

    public Map<Integer, GrafoAFD> getTabAutomatas() {
        return tabAutomatas;
    }

    public void setTabAutomatas(Map<Integer, GrafoAFD> tabAutomatas) {
        this.tabAutomatas = tabAutomatas;
    }

    public Map<String, NodoInfo> getTabEr() {
        return tabEr;
    }

    public void setTabEr(Map<String, NodoInfo> tabEr) {
        this.tabEr = tabEr;
    }
    
    public void reiniciar(){
        tabConj.clear(); //borro lista de conjuntos
        tabEr.clear(); //borro lista de Er
        tabAutomatas.clear();
    }
    
    public Nodo iniciarAnalisis(String cadena){
        Nodo arbol = null;
        try{
        System.out.println("inicio analisis");
        Lexico scanner=new Lexico(new BufferedReader( new StringReader(cadena)));
        sintactico parser=new sintactico(scanner);
        try {
            parser.parse();
        } catch (Exception ex) {
            Logger.getLogger(ide.class.getName()).log(Level.SEVERE, null, ex);
        }
        arbol=parser.raiz;
        reiniciar();
        recorrer(arbol);
        errores=scanner.errores;
         //crea la tabla
        System.out.println("Finalizo Analisis...");
        } catch (Exception ex) {
        System.out.println("Analisis no Realizado "+ ex); 
        }
        return arbol;
    }
    
    public void recorrer(Nodo arbol){
        if(arbol!=null){
            String actual = arbol.getLexema();
            switch(actual){
                case "CONJ":
                    String nombre;
                    int tipo,ini,fin;
                    nombre = arbol.hijos.get(0).getLexema();
                    if(tabConj.containsKey(nombre)){
                        Nodo e = new Nodo("error semantico/nombre ya definido",nombre,arbol.hijos.get(0).getFila(),arbol.hijos.get(0).getColumna());
                        errores.add(e); //agregado a lista de errores por ya existir el nombre
                    }else{
                        if(arbol.hijos.get(1).getLexema().equals("LISTA")){
                            tipo = 0;
                        }else{
                            tipo = 1;                           //RANGO
                        }
                        NodoConj nodo = new NodoConj(nombre,tipo);
                        if(tipo==1){
                            Nodo temp = arbol.hijos.get(1);
                            ini = temp.hijos.get(0).getLexema().charAt(0);
                            fin = temp.hijos.get(1).getLexema().charAt(0);
                            nodo.addComponent(ini);
                            nodo.addComponent(fin);
                        }else{
                            Nodo aux = arbol.hijos.get(1);
                            for(Nodo n:aux.hijos){
                                ini = n.getLexema().charAt(0);
                                nodo.addComponent(ini); //agrega ascii ala lista de componentes de dicho conjunto
                            }
                        }
                        tabConj.put(nombre, nodo); //agregando ala hashtable de conjuntos
                    }
                    break;
                case "ER":
                    cont++;
                    Map nodos = new LinkedHashMap();
                    Grafo thompson=pruebaThompson.crearGrafo(arbol.hijos.get(1));
                    imp.printAFN(nodos,thompson,String.valueOf(cont));
                    //para generar AFD
                    Map atr1 = thompson.retornoTransicion(new LinkedHashMap(), thompson.inicio);
                    ArrayList<String> atr2 = thompson.ListarTerminales(new LinkedHashMap(), new ArrayList<String>(), thompson.inicio);
                    Tabla tabla = new Tabla(atr1, atr2);
                    tabla.inicio(thompson.inicio);
                    GrafoAFD grafoAFD = tabla.GenerarGrafoAFD(tabla); //retorno de AFD
                    grafoAFD.setId(arbol.hijos.get(0).getLexema());
                    Map nodosAFD= new LinkedHashMap();
                    imp.printAFD(nodosAFD,grafoAFD,String.valueOf(cont));
                    NodoInfo info = new NodoInfo(thompson,arbol.hijos.get(2)); //almacena afn, retorno y reservadas si existen
                    tabEr.put(arbol.hijos.get(0).getLexema(),info); //almacena las ER con su id
                    tabAutomatas.put(cont, grafoAFD);
                    System.out.println(cont + " --> " +grafoAFD.getId());
                    break;
                default:
                    for(Nodo nd:arbol.hijos){
                        recorrer(nd);
                    }
            }
        }
    }
    
}