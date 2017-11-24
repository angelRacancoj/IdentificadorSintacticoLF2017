package MiPila;

import Analizador.Token;
import java.util.Stack;

/**
 *
 * @author angelrg
 */
public class MiPila implements Cloneable{

    public int posicionPuntero;
    public Stack<Token> listaToken = new Stack<>();

    public MiPila(int posicionPuntero, Stack<Token> listaToken) {
        this.posicionPuntero = posicionPuntero;
        this.listaToken = listaToken;
    }

    public MiPila() {
    }

    public int getPosicionPuntero() {
        return posicionPuntero;
    }

    public void setPosicionPuntero(int posicionPuntero) {
        this.posicionPuntero = posicionPuntero;
    }

    public Stack<Token> getListaToken() {
        return listaToken;
    }

    public void setListaToken(Stack<Token> listaToken) {
        this.listaToken = listaToken;
    }

    @Override
    public MiPila clone() throws CloneNotSupportedException {
        return new MiPila(this.posicionPuntero, this.listaToken);
    }
}
