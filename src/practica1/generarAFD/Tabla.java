package practica1.generarAFD;
import practica1.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 *
 * @author clnx
 */
public class Tabla {

    Map estados; // es una tabla que contien <numero estado, NodoThomson
    ArrayList<String> terminales; // es una lista de terminales para esa exprecion regular
    int EstadoActual; // controla el numero de estado que se crea 
    int EstadoGrafoAFNDAceptacion; // este es el numero de el estado de el grafo Thomson que es el terminal
    Transiciones transicionesTabla = new Transiciones();//transiciones ya ejecutadas e ingresadas a la tabla de transiciones
    Transiciones EstadosTabla = new Transiciones();//una lista transiciones que an aparecido en toda la ejecucion sin importar se les aplico subconjunto o no 
    ArrayList<NodoTabla> ListaElementosTabla = new ArrayList<>(); // cada uno de estos nodoTabla es una Fila de la tabla de Transiciones 

    public Tabla(Map estados, ArrayList<String> terminales){
        this.estados = estados;
        this.terminales = terminales;
    }

    public void inicio(NodoThompson Raiz){
        //fila que se agregara a la tabla 
        NodoTabla tabla = new NodoTabla();
        SubConjuntos sub = new SubConjuntos();
        //agrego el primer nodo del Grafo Thomson para que aga la cerradula de esto
        sub.metodoCerradura(new LinkedHashMap(), Raiz);
        //obtengo el numero terminal
        EstadoGrafoAFNDAceptacion = EsAceptacion(new LinkedHashMap(), Raiz);
        System.out.println("estados de acept: "+EstadoGrafoAFNDAceptacion);
        //aqui le ingreso las cerraduras a este estado 
        tabla.subconjunto.SetListaEstados(sub.GetListaEstados());
        //este metodo solo valida que si el subconjunto tiene el estado de aceptacion del grafo
        tabla.ContieneAceptacion(EstadoGrafoAFNDAceptacion);

        tabla.estado = EstadoActual;
        //agregamos las transiciones con la lista de numeros de subconjunto , los terminales , la hash de nodos Thomson , el numero 
        //REVISARRR------------------------------------*******************
        EstadoActual = tabla.trasiciones.AddListaTranciciones(tabla.subconjunto.GetListaEstados(), terminales, estados,  EstadoActual, EstadosTabla);

        ListaElementosTabla.add(tabla);

        for (Transicion t : tabla.trasiciones.GetListaEstados()){
            ArrayList<Transicion> tran = transicionesTabla.GetListaEstados();
            if (tran.size() == 0){
                RecorrerTabla(t);
            }
            else{
                for (int numN = 0; numN < tran.size(); numN++){
                    ArrayList<Integer> tranA = t.GetListaEstados();
                    ArrayList<Integer> tranB = tran.get(numN).GetListaEstados();
                    if (!compararListas(tranA,tranB)){
                        RecorrerTabla(t);
                    }
                }
            }
        }

    }

    public boolean compararListas(ArrayList<Integer> lista1, ArrayList<Integer> lista2){
        boolean bandera = false;
        if(lista1.size() == lista2.size()){
            for(int i= 0 ; i<lista2.size();i++){
                if(lista1.contains(lista2.get(i))){
                    bandera = true;
                }else{
                    bandera = false;
                    break;
                }
            }
        }else{
            bandera = false;
        }
         return bandera;
    }

    private void RecorrerTabla(Transicion transicion){
        NodoTabla tabla = new NodoTabla();
        //Inicio
        //aqui valido si ya se ejecuto esa transicion antes
        ArrayList<Transicion> tran = transicionesTabla.GetListaEstados();
        for (Transicion tranGuardadas : tran)
        {
            ArrayList<Integer> tranA = tranGuardadas.GetListaEstados();
            ArrayList<Integer> tranB = transicion.GetListaEstados();

            if (compararListas(tranA, tranB)){
                return;
            }
        }
        //fin
        //se agrega la transicion que no se a ejecutado a las que ya fueron
        transicionesTabla.AgregaTranciciones(transicion);
        for (int nEstado : transicion.GetListaEstados())
        {
            SubConjuntos sub = new SubConjuntos();
            // aqui utilizo la hash de estados y le ingreso la llave que es el numero de estado en grafo Thomson para que me devuelva el nodo asociado
            sub.metodoCerradura(new LinkedHashMap(), (NodoThompson)estados.get(nEstado));
            //agrego el subconjunto creado para esa transicion 
            //aqui va agregando a una misma lista ya que tiene que agregar el subconjunto de cada uno de los numeros en la transicion
            tabla.subconjunto.SetListaEstados(sub.GetListaEstados());
            //valida si esta fila o estado es de aceptacion
            tabla.ContieneAceptacion(EstadoGrafoAFNDAceptacion);
        }
        //aqui agrego el numero de estado a esta fila , ya que el numero es asociado a cada transicion nueva
        tabla.estado = transicion.GetEstadoTransicion();
        //REVISAR*************************
        EstadoActual = tabla.trasiciones.AddListaTranciciones(tabla.subconjunto.GetListaEstados(), terminales, estados,  EstadoActual, EstadosTabla);
        ListaElementosTabla.add(tabla);
        for (Transicion t : tabla.trasiciones.GetListaEstados()){

            RecorrerTabla(t);

        }
        if (tabla.trasiciones.GetListaEstados().size() == 0){
            ListaElementosTabla.add(tabla);
        }
    }

    public GrafoAFD GenerarGrafoAFD(Tabla Fila){
        ////////aca con la con la tabla ya terminada se agregan para que se realice el grafo simplificado
        ArrayList<NodoThompson> listaNodos = new ArrayList<>();
        for (NodoTabla nodo : Fila.ListaElementosTabla){
            NodoThompson nodoInicial = new NodoThompson();
            nodoInicial.setNumero(nodo.estado);
            System.out.println(nodo.estado);
            nodoInicial.setAcept(nodo.Aceptacion);
            System.out.println(nodo.Aceptacion);
            listaNodos.add(nodoInicial);
        }
        for (int i = 0; i < listaNodos.size(); i++){
            NodoThompson NodoG = listaNodos.get(i);
            NodoTabla nodo = Fila.ListaElementosTabla.get(i);
            for (Transicion transiciones : nodo.trasiciones.GetListaEstados()){
                NodoThompson Retorno = BuscarNodo(listaNodos, transiciones.GetEstadoTransicion());
                if (Retorno != null){
                    NodoArista con = new NodoArista(transiciones.GetTrancicionCon(),Retorno);
                    NodoG.addArista(con);
                }
            }
        }
        GrafoAFD Grafo = new GrafoAFD();
        if (listaNodos.size() > 0){
            Grafo.setInicio(listaNodos.get(0));
        }
        return Grafo;
    }

    public NodoThompson BuscarNodo(ArrayList<NodoThompson> listaNodos, int num){
        for (NodoThompson it : listaNodos){
            if (it.getNumero() == num){
                return it;
            }
        }
        return null;
    }

    private int EsAceptacion(Map EstadosGrafo, NodoThompson inicio){
        int numR = 0;
        EstadosGrafo.put(inicio.hashCode(), inicio);
        for (NodoArista nodoConexion : inicio.aristas){
            if (!EstadosGrafo.containsKey(nodoConexion.getDestino().hashCode())){
                return EsAceptacion(EstadosGrafo, nodoConexion.getDestino());
            }
        }
        if (inicio.aristas.size() == 0){
            return inicio.getNumero();
        }
        return numR;
    }

}