package practica1;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;
import practica1.generarAFD.GrafoAFD;

/**
 *
 * @author clnx
 */
public class Imprimir {
    
    public void printAFD(Map recorridos, GrafoAFD afd,String nombre){
        try{
            String path;
            path = "/home/clnx/Escritorio/U/1sem2016/Compi2/Junio_2016/"+nombre+"afd.dot";
            File creardot = new File(path); 
            FileWriter escritor = new FileWriter(creardot,false);
            try (PrintWriter impresor = new PrintWriter(escritor)) {
                impresor.println("digraph afd {");
                impresor.println("rankdir=LR;");
                impresor.println("start[shape = point, width = \"0\", height = \"0\"];");
                impresor.println("node[shape=circle];");
                impresor.println("start -> node" + afd.getInicio().hashCode());
                impresor.println(printNodoAFD(afd.getInicio(), recorridos));
                impresor.println("\n}");
            }
    	}catch(Exception er){	
    		System.out.println(er.getMessage());
    		er.printStackTrace();
    	}  
            try{
                String dot="/home/clnx/Escritorio/U/1sem2016/Compi2/Junio_2016/"+nombre+"afd.dot";
                String png="/home/clnx/Escritorio/U/1sem2016/Compi2/Junio_2016/"+nombre+"afd.png";
                ProcessBuilder pbuilder;
                pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", png, dot );
                pbuilder.redirectErrorStream( true );
                pbuilder.start();
            } catch (Exception e) { e.printStackTrace(); } 
    }
    
    public void printAFN(Map recorridos, Grafo afnd,String nombre){
        try{
            String path;
            path = "/home/clnx/Escritorio/U/1sem2016/Compi2/Junio_2016/"+nombre+"afn.dot";
            File creardot = new File(path); //Crear un objeto File que se encarga de crear un arch. esp. en su constructor
            FileWriter escritor = new FileWriter(creardot,false); //false para que vaya al inicio del arch. a escribir 
            try (PrintWriter impresor = new PrintWriter(escritor)) {
                impresor.println("digraph afn {");
                impresor.println("rankdir=LR;");
                impresor.println("start[shape = point, width = \"0\", height = \"0\"];");
                impresor.println("node[shape = doublecircle ]; node" + afnd.aceptacion.hashCode());
                impresor.println("node[shape=circle];");
                impresor.println("start -> node" + afnd.getInicio().hashCode());
                impresor.println(printNodoAFN(afnd.inicio, recorridos));
                impresor.println("\n}");
            }
    	}catch(Exception er){	
    		System.out.println(er.getMessage());
    		er.printStackTrace();
    	}  
            try{
                String dot="/home/clnx/Escritorio/U/1sem2016/Compi2/Junio_2016/"+nombre+"afn.dot";
                String png="/home/clnx/Escritorio/U/1sem2016/Compi2/Junio_2016/"+nombre+"afn.png";
                ProcessBuilder pbuilder;
                pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", png, dot );
                pbuilder.redirectErrorStream( true );
                pbuilder.start();
            } catch (Exception e) { e.printStackTrace(); } 
    }
    
    public String printNodoAFD(NodoThompson nodo, Map recorridos){
        String r = "";
        if (!recorridos.containsKey(nodo.hashCode())){ 
            if(nodo.acept){
                r = "\n node" + nodo.hashCode()+ "[shape = doublecircle; label=\"" + nodo.getNumero() + "\"];";
            }else{
                r = "\n node" + nodo.hashCode()+ "[label=\"" + nodo.getNumero() + "\"];";
            }
            recorridos.put(nodo.hashCode(), nodo.hashCode());
            for(NodoArista item: nodo.getArista()){
                String contenido,cnt;
                contenido = item.getContenido();
                cnt = contenido.replaceAll("\"", " ");
                r += "\n node" + nodo.hashCode() + "->" + "node" + item.getDestino().hashCode() + "[label=\"" + cnt +"\" ];";
                r += printNodoAFD(item.getDestino(), recorridos);
            }
        }
        return r;
    }
    
    public String printNodoAFN(NodoThompson nodo, Map recorridos){
    String r = "";
    if (!recorridos.containsKey(nodo.hashCode())){ 
        r = "\n node" + nodo.hashCode()+ "[label=\"" + nodo.getNumero() + "\"];";
        recorridos.put(nodo.hashCode(), nodo.hashCode());
        for(NodoArista item: nodo.getArista()){
            String contenido,cnt;
            contenido = item.getContenido();
            cnt = contenido.replaceAll("\"", " ");
            r += "\n node" + nodo.hashCode() + "->" + "node" + item.getDestino().hashCode() + "[label=\"" + cnt +"\" ];";
            r += printNodoAFN(item.getDestino(), recorridos);
        }
    }
    return r;
    }
    
    public String GraficarNodo(Nodo nodo)
    {
        String r = "";    
        if (nodo.getToken() != null)
        {
            System.out.println("Valor"+nodo.getLexema());
            String a = nodo.getLexema();
            String A = a.replaceAll("\"", " ");
            r = "\n node" + nodo.hashCode() + "[label=\""+nodo.getToken() +":"+ A + "\"];";
        }
        if(nodo.hijos.size() > 0)
        {
            for (Nodo hijo : nodo.hijos) {
                r += "\n node"+ nodo.hashCode() + "--"+"node" + hijo.hashCode() + ";";
            }
            for (Nodo hijo : nodo.hijos) {
                r += GraficarNodo(hijo);
            }
        }
        return r;
    }
    
    public void printArbol(Nodo nodoR) 
    {
        try{
            File creardot = new File("/home/clnx/Escritorio/U/1sem2016/Compi2/Junio_2016/arbol.dot"); //Crear un objeto File que se encarga de crear un arch. esp. en su constructor
            FileWriter escritor = new FileWriter(creardot,false); //false para que vaya al inicio del arch. a escribir 
            try (PrintWriter impresor = new PrintWriter(escritor)) {
                impresor.println("graph arbol{");
                String a = GraficarNodo(nodoR);
                impresor.println(a);
                impresor.println("\n }");
            }
    	}catch(Exception er){	
    		System.out.println(er.getMessage());
    		er.printStackTrace();
    	}  
            try{
                String dot="/home/clnx/Escritorio/U/1sem2016/Compi2/Junio_2016/arbol.dot";
                String png="/home/clnx/Escritorio/U/1sem2016/Compi2/Junio_2016/arbol.png";
                ProcessBuilder pbuilder;
                pbuilder = new ProcessBuilder( "dot", "-Tpng", "-o", png, dot );
                pbuilder.redirectErrorStream( true );
                pbuilder.start();
            } catch (Exception e) { e.printStackTrace(); } 
    }
    
}
