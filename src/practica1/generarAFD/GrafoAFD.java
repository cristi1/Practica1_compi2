package practica1.generarAFD;
import practica1.*;

/**
 *
 * @author clnx
 */
public class GrafoAFD {

    NodoThompson inicio;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInicio(NodoThompson inicio) {
        this.inicio = inicio;
        this.id = "";
    }

    public NodoThompson getInicio() {
        return this.inicio;
    }
}