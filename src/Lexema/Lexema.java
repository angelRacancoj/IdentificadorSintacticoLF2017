package Lexema;

import Analizador.Token;

/**
 *
 * @author angelrg
 */
public class Lexema {

    private String nombre;
    private Token token;
    private int columna;
    private int fila;

    public Lexema(String nombre, Token token, int columna, int fila) {
        this.nombre = nombre;
        this.token = token;
        this.columna = columna;
        this.fila = fila;
    }

    public Lexema() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Token getToken() {
        return token;
    }
    public String getTokenString(){
        return token.toString();
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

}
