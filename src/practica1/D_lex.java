package practica1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import practica1.generarAFD.GrafoAFD;

/**
 *
 * @author clnx
 */
public class D_lex {
    
    private String entrada;
    private Analizador analizador;
    private String yytext;
    private int yyline;
    private int yycolumn;
    private int automataActual; //posicion o numero de automata que esta en analisis
    private int charActual; //posicion en la lista del char que se esta analizando  
    
    /*public D_lex(String ent,Analizador analiz){ //a utilizarse
        this.entrada = ent;
        this.analizador = analiz;
    }*/
    
    public D_lex(String ent){ //probando cadenaas
        this.entrada = ent;
        //incializo variables
        this.yytext = "";
        this.yyline = 0;
        this.yycolumn = 0;
        this.automataActual = 1;
        this.charActual = 0;
        listaChar();
    }
    
    private void listaChar(){ //metodo que reconoce todos los caracteres y los guarda en una estructura junto a su # de line y # de columna
        ArrayList<Caracter> lista = new ArrayList<>(); //lista de caracteres encontrados
        int sizeEntrada,ascii,line,column;
        char caracter;
        sizeEntrada = this.entrada.length();
        line = 1;
        column = 0;
        System.out.println("numero de caracteres: "+sizeEntrada);
        for(int i = 0; i<sizeEntrada; i++){
            caracter = this.entrada.charAt(i);
            ascii = caracter;
            //yycolumn = yycolumn +1;
            switch(ascii){
                case 9: //si encuentra \t 
                    column = column + 10;
                    break;
                case 10: //si encuentra \n
                    line = line + 1;
                    column = 0;
                    break;
                default:
                    column = column +1;      
            }
            Caracter nodo = new Caracter(ascii,line,column);
            lista.add(nodo); //agrega nodo ala lista de caracteres encontrados
        }
        if(lista.size()>0){
            generarToken(lista); //envio lista para que se analice y genere el listado de tokens
        }
    }
    
    private void generarToken(ArrayList<Caracter> listChar){
        GrafoAFD automata= buscarAutomata(listChar.get(this.charActual));
        if(automata != null){
            
        }else{
            System.out.println("VERIFICAR ERROR!!\nNo fue reconocido por ningun automata \nfalta el de error o verifique");
        }
    }
    
    private boolean recorrerAutomata(NodoThompson nodo, ArrayList<Caracter> listChar){ //reconoce token atraves del afd
        
    }
    
    private GrafoAFD buscarAutomata(Caracter c){
        boolean conjunto;
        int encontrado=-777,ini,fin;
        NodoConj temporal;
        for(int i=this.automataActual; i<analizador.getTabAutomatas().size();i++){
            this.automataActual = i; //numero de automata en analisis
            GrafoAFD actual = analizador.getTabAutomatas().get(i);
            for(NodoArista arista: actual.getInicio().getArista()){
                conjunto = false;
                switch(arista.getContenido()){
                    case "\"\t\"":
                        encontrado = 9;
                        break;
                    case "\"\\\n\"":
                        encontrado = 10;
                        break;
                    case "\\\"":
                        encontrado = 34;
                        break;
                    case "\\\'":
                        encontrado = 39;
                        break;
                    case "[:blanco:]":
                        encontrado = 0; //Conjunto de espacios en blanco, tabuladores y Saltos de línea.
                        break;
                    case "[:todo:]":
                        encontrado = -1; //Conjunto de cualquier caracter excepto salto de línea (\n)
                        break;
                    default:
                        if(arista.getContenido().contains("\"")){ //si es una cadena
                            System.out.println("se encontro una cadena: "+arista.getContenido());
                        }else{
                            conjunto = true;
                        }
                }
                temporal = analizador.getTabConj().get(arista.getContenido());
                if(conjunto){
                    if(temporal.getTipo() == 1){ //si es rango
                        ini = temporal.getComponentes().get(0);
                        fin = temporal.getComponentes().get(1);        
                        if(ini>= c.getCaracter() && fin<=c.getCaracter()){ 
                            return actual; //reconocido en un rango
                        }
                    }else{ //si es conjunto
                        for(int k:temporal.getComponentes()){
                            if(c.getCaracter() == k){ 
                                return actual; //reconocido en una lista
                            }
                        }
                    }
                }else{
                    if(c.getCaracter() == encontrado){
                        return actual; //coincide con algun caracter especial del lenguaje
                    }
                    if((c.getCaracter() == 9 || c.getCaracter() == 10 || c.getCaracter() == 32)&& encontrado == 0){
                        return actual; //viene espacio o salto de linea o corrimiento de carro y en el automata se encuentra [:todo:]
                    }
                    if(encontrado == -1){
                        return actual; //cuando es un automata con el reservado [:todo:] acepta cualquier caracter
                    }
                }
            }
        }
        return null;
    }
    
    //private NodoThompson verificar(){}
}