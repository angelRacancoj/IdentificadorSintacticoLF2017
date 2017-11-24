package SintaxisCorrecta;

/**
 *
 * @author angelrg
 */
public class SitaxisId {
    private String estructura;
    private int fila;
    private int columna;

    public SitaxisId(String estructura, int fila, int columna) {
        this.estructura = estructura;
        this.fila = fila;
        this.columna = columna;
    }

    public String getEstructura() {
        return estructura;
    }

    public void setEstructura(String estructura) {
        this.estructura = estructura;
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
