package practica1;

/**
 *
 * @author clnx
 */
public class Caracter {
    private int caracter;
    private int fila;
    private int columna;
    
    public Caracter(int Char, int row, int column){
        this.caracter = Char;
        this.fila = row;
        this.columna = column;
    }
    
    public int getCaracter() {
        return caracter;
    }

    public void setCaracter(int caracter) {
        this.caracter = caracter;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
}
