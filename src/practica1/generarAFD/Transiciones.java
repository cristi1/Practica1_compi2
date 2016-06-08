package practica1.generarAFD;

import java.util.ArrayList;
import java.util.Map;
import practica1.*;

/**
 *
 * @author clnx
 */
public class Transiciones {

    ArrayList<Transicion> estadosNuevos = new ArrayList<>();

    public void AgregaTranciciones(Transicion transicion) {
        estadosNuevos.add(transicion);
    }

    public int AddListaTranciciones(ArrayList<Integer> subconjunto, ArrayList<String> terminales, Map estados, int numeroEstadoN, Transiciones transicionesTabla) {
        //crear subconjunto para cada terminal para cada terminal
        for (String terminal : terminales) {

            Transicion tran = new Transicion();
            boolean numC = true;
            int numTemp = numeroEstadoN;
            //recorrer el subconjunto 
            for (int item : subconjunto) {
                //con cada numero del subconjunto se busca en la hash de estados del grafo Thomson y de este nodo asociado optener todas sus transiciones a otros estados y con que se mueven
                for (NodoArista con : ((NodoThompson) estados.get(item)).aristas) {
                    //verifica si se la transicion se mueve con el terminal actual
                    if (terminal.equals(con.getContenido())) {
                        if (numC) {
                            numeroEstadoN += 1;
                            numC = false;
                        }
                            //se agregan todos los numeros del movimientoa una sola transicion 

                        //se agrega con que se movio ese movimiento 
                        tran.SetTrancicionCon(con.getContenido());
                        // aqui se agregan los estados a los que se movio con dicho terminal
                        tran.AddEstado(con.getDestino().getNumero());

                    }
                }

            }
            //Inicio
            //esto sirve para agregar a la tabla de transiciones o movimientos que se an generado en todo el proceso 
            if (tran.GetListaEstados().size() > 0) {
                boolean ban = false;
                //1Inicio
                //valida si ya se genero ese movimiento en el pasado para la transicion generada anteriormente
                ArrayList<Transicion> Ltran = transicionesTabla.GetListaEstados();
                for (Transicion tranGuardadas : Ltran) {
                    ArrayList<Integer> tranA = tranGuardadas.GetListaEstados();
                    ArrayList<Integer> tranB = tran.GetListaEstados();
                    if (compararListas(tranA,tranB)) {
                        //si ya se genero un estado antes se le asigna el mismo numero que se le asigno anteriormente
                        //regreso el int pasado por referencia a el numero original que tenia al ingresar al metodo
                        numeroEstadoN = numTemp;
                        //asigno el numero de estado que tiene la transicion encontrada en la lista
                        tran.setNumeroCerradura(tranGuardadas.GetEstadoTransicion());
                        ban = false;
                        break;
                    } else {
                        //sino no existe ninguna transcion asi se le asigna el numero 
                        tran.setNumeroCerradura(numeroEstadoN);
                        ban = true;
                    }
                }
                //1Fin
                if (ban) {
                    transicionesTabla.AgregaTranciciones(tran);
                }
                if (Ltran.isEmpty()) {
                    tran.setNumeroCerradura(numeroEstadoN);
                    transicionesTabla.AgregaTranciciones(tran);
                }
                this.estadosNuevos.add(tran);

            }

            //fin
        }
        return numeroEstadoN;
    }

    public void SetListaEstados(ArrayList<Transicion> lista) {
        this.estadosNuevos = lista;
    }

    public ArrayList<Transicion> GetListaEstados() {
        return estadosNuevos;
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
}