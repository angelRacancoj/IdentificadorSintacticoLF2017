package Analizador;

import Lexema.Lexema;
import MiPila.MiPila;
import SintaxisCorrecta.SitaxisId;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author angelrg
 */
public class IdentSintactico {

    private LinkedList<Lexema> listaLexemas = new LinkedList<>();
    private LinkedList<MiPila> listaSintax = new LinkedList<>();
    private LinkedList<Token> listaTokens = new LinkedList<>();
    private LinkedList<SitaxisId> listaSintaxis = new LinkedList<>();
    private int noPila;
    private String textoSalida;

    public void crearNuevaLista(List<Token> listaT) {
        listaSintax.add(new MiPila());
        int cantListas = listaSintax.size();
        for (int i = 0; i < listaT.size(); i++) {
            listaSintax.get((cantListas - 1)).setPosicionPuntero(i * 5);
            listaSintax.get((cantListas - 1)).getListaToken().push(listaT.get(i));
            System.out.println(String.valueOf(listaSintax.get(cantListas - 1).getListaToken().peek()));
        }
    }

    public void listaPrueba() {
        listaSintax.clear();
        literal();
        ciclo1();
        condicional1();
        asignacion1();
    }

    public List<SitaxisId> analizador(List<Lexema> listaLexe) {
        listaSintax.clear();
        listaSintax.add(new MiPila());
        listaSintax.get((0)).setPosicionPuntero(0);
        listaSintax.get((0)).getListaToken().push(Token.S1);

        boolean esCiclo = false;
        boolean esCondicion = false;
        boolean condicionValida = false;

        String cadenaSintaxis = "";
        String textoTemporal = "";
        textoSalida = "";

        listaLexemas.clear();
        listaLexemas.addAll(listaLexe);
        boolean seguir = true;
        noPila = 0;
        int i = 0;
        int j = 0;
        int ciclo = 0;

        int fila = 0;
        int columna = 0;
        //se encarga de almacenar cuantas veces se ha realizado un movimiente
        int tokensRecorridos = 0;

        Token tokenPila = listaSintax.get(0).getListaToken().peek();

        while (seguir) {
            if (i > (listaLexemas.size() - 1)) {
                seguir = false;
                break;
            }
            Token miToken = listaLexemas.get(i).getToken();
            String nombreVar = listaLexemas.get(i).getNombre();
            System.out.println("var name:" + nombreVar);

            switch (tokenPila) {
                case S1:
                    esCiclo = false;
                    esCondicion = false;
                    condicionValida = false;
                    listaPrueba();
                    if (miToken == Token.ESCRIBIR) {
                        eliminarPilasDiferentes(miToken);
                        tokenPila = Token.ESCRIBIR;
                    } else if (miToken == Token.POR_CADA) {
                        eliminarPilasDiferentes(miToken);
                        tokenPila = Token.POR_CADA;
                    } else if (miToken == Token.SI) {
                        eliminarPilasDiferentes(miToken);
                        tokenPila = Token.SI;
                    } else if (miToken == Token.VARIABLE) {
                        eliminarPilasDiferentes(miToken);
                        tokenPila = Token.VARIABLE;
                    } else {
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        i++;
                    }
                    break;

                case ESCRIBIR:
                    if (listaSintax.get(j).getListaToken().peek() == miToken) {
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        columna = listaLexemas.get(i).getColumna();
                        fila = listaLexemas.get(i).getFila();
                        listaSintax.get(j).getListaToken().pop();
                        i++;
                        tokensRecorridos++;
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                    } else {
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        i++;
                        tokensRecorridos++;
                    }
                    break;

                case LITERAL:
                    if (listaSintax.get(j).getListaToken().peek() == miToken) {
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        textoTemporal = contatenarString(textoTemporal, (listaLexemas.get(i).getNombre().replace("\"", "") + "\n"));
                        listaSintax.get(j).getListaToken().pop();
                        i++;
                        tokensRecorridos++;
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                    } else {
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        i++;
                        tokensRecorridos++;
                    }
                    break;

                case FIN:
                    if (listaSintax.get(j).getListaToken().peek() == miToken) {
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        listaSintax.get(j).getListaToken().pop();
                        i++;
                        if (listaSintax.get(j).getListaToken().empty()) {
                            listaSintax.clear();
                            listaSintax.add(new MiPila());
                            listaSintax.get((0)).setPosicionPuntero(0);
                            listaSintax.get((0)).getListaToken().push(Token.S1);
                            System.out.println("Es un literal: " + cadenaSintaxis);
                            listaSintaxis.add(new SitaxisId(cadenaSintaxis, fila, columna));
                            fila = 0;
                            columna = 0;
                            if (esCiclo) {
                                for (int k = 0; k < ciclo; k++) {
                                    System.out.println(textoTemporal);
                                    textoSalida = contatenarString(textoSalida, textoTemporal) + "\n";
                                }
                            } else if (esCondicion) {
                                if (condicionValida) {
                                    System.out.println(textoTemporal);
                                    textoSalida = contatenarString(textoSalida, textoTemporal) + "\n";
                                }
                            } else {
                                System.out.println(textoTemporal);
                                    textoSalida = contatenarString(textoSalida, textoTemporal) + "\n";
                            }
                            textoTemporal = "";
                            cadenaSintaxis = "";
                            tokensRecorridos = 0;
                            ciclo = 0;
                        }
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                    } else if (listaSintax.size() == 2) {
                        listaSintax.removeFirst();
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                        i = listaSintax.get(j).getPosicionPuntero();
                    } else {
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, listaLexemas.get(i).getNombre());
                        tokensRecorridos++;
                    }
                    break;

                case POR_CADA:
                    if (listaSintax.get(j).getListaToken().peek() == miToken) {
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        columna = listaLexemas.get(i).getColumna();
                        fila = listaLexemas.get(i).getFila();
                        listaSintax.get(j).getListaToken().pop();
                        i++;
                        tokensRecorridos++;
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                        esCiclo = true;
                    } else {
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        i++;
                        tokensRecorridos++;
                    }
                    break;

                case NUMERO:
                    if (listaSintax.get(j).getListaToken().peek() == miToken) {
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        ciclo = Integer.parseInt(listaLexemas.get(i).getNombre());
                        listaSintax.get(j).getListaToken().pop();
                        i++;
                        tokensRecorridos++;
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                    } else {
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        i++;
                        tokensRecorridos++;
                    }
                    break;

                case INICIAR:
                    if (listaSintax.get(j).getListaToken().peek() == miToken) {
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        listaSintax.get(j).getListaToken().pop();
                        i++;
                        tokensRecorridos++;
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                    } else {
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        i++;
                        tokensRecorridos++;
                    }
                    break;

                case P3:
                    if (listaSintax.get(j).getListaToken().peek() == Token.P3) {
                        listaSintax.get(j).getListaToken().pop();
                        listaSintax.add(new MiPila());
                        listaSintax.get(j).getListaToken().push(Token.FIN);
                        listaSintax.get(j).setPosicionPuntero(i);
                        listaSintax.get(j + 1).getListaToken().push(Token.FIN);
                        listaSintax.get(j + 1).getListaToken().push(Token.P2);
                        listaSintax.get(j + 1).setPosicionPuntero(i);
                        leerUltimoDato();
                        tokensRecorridos++;
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                    } else {
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        i++;
                        tokensRecorridos++;
                    }
                    break;

                case P2:
                    if (listaSintax.get(j).getListaToken().peek() == Token.P2) {
                        listaSintax.get(j).getListaToken().pop();
                        listaSintax.add(new MiPila());
                        listaSintax.get(j).getListaToken().push(Token.FIN);
                        listaSintax.get(j).getListaToken().push(Token.LITERAL);
                        listaSintax.get(j).getListaToken().push(Token.ESCRIBIR);
                        listaSintax.get(j).setPosicionPuntero(i);
                        listaSintax.get(j + 1).getListaToken().push(Token.FIN);
                        listaSintax.get(j + 1).getListaToken().push(Token.P4);
                        listaSintax.get(j + 1).getListaToken().push(Token.L1);
                        listaSintax.get(j + 1).setPosicionPuntero(i);
                        leerUltimoDato();
                        tokensRecorridos++;
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                    } else {
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        i++;
                        tokensRecorridos++;
                    }
                    break;

                case L1:
                    if (listaSintax.get(j).getListaToken().peek() == Token.L1) {
                        listaSintax.get(j).getListaToken().pop();
                        listaSintax.get(j).getListaToken().push(Token.FIN);
                        listaSintax.get(j).getListaToken().push(Token.LITERAL);
                        listaSintax.get(j).getListaToken().push(Token.ESCRIBIR);
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                    } else {
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        i++;
                        tokensRecorridos++;
                    }
                    break;

                case P4:
                    if (listaSintax.get(j).getListaToken().peek() == Token.P4) {
                        listaSintax.get(j).getListaToken().pop();
                        listaSintax.add(new MiPila());
                        listaSintax.get(j).getListaToken().push(Token.FIN);
                        listaSintax.get(j).getListaToken().push(Token.LITERAL);
                        listaSintax.get(j).getListaToken().push(Token.ESCRIBIR);
                        listaSintax.get(j).setPosicionPuntero(i);
                        listaSintax.get(j + 1).getListaToken().push(Token.FIN);
                        listaSintax.get(j + 1).getListaToken().push(Token.P4);
                        listaSintax.get(j + 1).getListaToken().push(Token.L1);
                        listaSintax.get(j + 1).setPosicionPuntero(i);
                        leerUltimoDato();
                        tokensRecorridos++;
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                    } else {
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        i++;
                        tokensRecorridos++;
                    }
                    break;

                case SI:
                    if (listaSintax.get(j).getListaToken().peek() == miToken) {
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        columna = listaLexemas.get(i).getColumna();
                        fila = listaLexemas.get(i).getFila();
                        listaSintax.get(j).getListaToken().pop();
                        i++;
                        tokensRecorridos++;
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                        esCondicion = true;
                    } else {
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        i++;
                        tokensRecorridos++;
                    }
                    break;

                case I3:
                    if (listaSintax.get(j).getListaToken().peek() == Token.I3) {
                        listaSintax.get(j).getListaToken().pop();
                        listaSintax.add(new MiPila());
                        listaSintax.get(j).getListaToken().push(Token.I1);
                        listaSintax.get(j).getListaToken().push(Token.VERDADERO);
                        listaSintax.get(j).setPosicionPuntero(i);
                        listaSintax.get(j + 1).getListaToken().push(Token.I1);
                        listaSintax.get(j + 1).getListaToken().push(Token.FALSO);
                        listaSintax.get(j + 1).setPosicionPuntero(i);
                        leerUltimoDato();
                        tokensRecorridos++;
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                    } else {
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        i++;
                        tokensRecorridos++;
                    }
                    break;

                case VERDADERO:
                    if (listaSintax.get(j).getListaToken().peek() == miToken) {
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        listaSintax.get(j).getListaToken().pop();
                        i++;
                        tokensRecorridos++;
                        condicionValida = true;
                        listaSintax.removeLast();
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                    } else if (listaSintax.size() == 2) {
                        listaSintax.removeFirst();
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                    } else {
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        i++;
                        tokensRecorridos++;
                    }
                    break;

                case FALSO:
                    if (listaSintax.get(j).getListaToken().peek() == miToken) {
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        listaSintax.get(j).getListaToken().pop();
                        i++;
                        tokensRecorridos++;
                        condicionValida = false;
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                    } else {
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        i++;
                        tokensRecorridos++;
                    }
                    break;

                case I1:
                    if (listaSintax.get(j).getListaToken().peek() == Token.I1) {
                        listaSintax.get(j).getListaToken().pop();
                        listaSintax.get(j).getListaToken().push(Token.I4);
                        listaSintax.get(j).getListaToken().push(Token.ENTONCES);
                        leerUltimoDato();
                        tokensRecorridos++;
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                    } else {
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        i++;
                        tokensRecorridos++;
                    }
                    break;

                case ENTONCES:
                    if (listaSintax.get(j).getListaToken().peek() == miToken) {
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        listaSintax.get(j).getListaToken().pop();
                        i++;
                        tokensRecorridos++;
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                    } else {
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        i++;
                        tokensRecorridos++;
                    }
                    break;

                case I4:
                    if (listaSintax.get(j).getListaToken().peek() == Token.I4) {
                        listaSintax.get(j).getListaToken().pop();
                        listaSintax.add(new MiPila());
                        listaSintax.get(j).getListaToken().push(Token.FIN);
                        listaSintax.get(j).setPosicionPuntero(i);
                        listaSintax.get(j + 1).getListaToken().push(Token.FIN);
                        listaSintax.get(j + 1).getListaToken().push(Token.I2);
                        listaSintax.get(j + 1).setPosicionPuntero(i);
                        leerUltimoDato();
                        tokensRecorridos++;
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                    } else {
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        i++;
                        tokensRecorridos++;
                    }
                    break;

                case I2:
                    if (listaSintax.get(j).getListaToken().peek() == Token.I2) {
                        listaSintax.get(j).getListaToken().pop();
                        listaSintax.add(new MiPila());
                        listaSintax.get(j).getListaToken().push(Token.FIN);
                        listaSintax.get(j).getListaToken().push(Token.LITERAL);
                        listaSintax.get(j).getListaToken().push(Token.ESCRIBIR);
                        listaSintax.get(j).setPosicionPuntero(i);
                        listaSintax.get(j + 1).getListaToken().push(Token.FIN);
                        listaSintax.get(j + 1).getListaToken().push(Token.I5);
                        listaSintax.get(j + 1).getListaToken().push(Token.L1);
                        listaSintax.get(j + 1).setPosicionPuntero(i);
                        leerUltimoDato();
                        tokensRecorridos++;
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                    } else {
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        i++;
                        tokensRecorridos++;
                    }
                    break;

                case I5:
                    if (listaSintax.get(j).getListaToken().peek() == Token.I5) {
                        listaSintax.get(j).getListaToken().pop();
                        listaSintax.add(new MiPila());
                        listaSintax.get(j).getListaToken().push(Token.FIN);
                        listaSintax.get(j).getListaToken().push(Token.LITERAL);
                        listaSintax.get(j).getListaToken().push(Token.ESCRIBIR);
                        listaSintax.get(j).setPosicionPuntero(i);
                        listaSintax.get(j + 1).getListaToken().push(Token.FIN);
                        listaSintax.get(j + 1).getListaToken().push(Token.I5);
                        listaSintax.get(j + 1).getListaToken().push(Token.L1);
                        listaSintax.get(j + 1).setPosicionPuntero(i);
                        leerUltimoDato();
                        tokensRecorridos++;
                        tokenPila = listaSintax.get(j).getListaToken().peek();
                    } else {
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        i++;
                        tokensRecorridos++;
                    }
                    break;

                case ERROR:
                    esCiclo = false;
                    esCondicion = false;
                    if (Token.FIN == miToken) {
                        cadenaSintaxis = contatenarString(cadenaSintaxis, nombreVar);
                        listaSintax.get(j).getListaToken().pop();
                        i++;
                        if (listaSintax.get(j).getListaToken().empty()) {
                            listaSintax.clear();
                            listaSintax.add(new MiPila());
                            listaSintax.get((0)).setPosicionPuntero(0);
                            listaSintax.get((0)).getListaToken().push(Token.S1);
                            System.out.println("Es un error: " + cadenaSintaxis);
                            textoTemporal = "";
                            cadenaSintaxis = "";
                            tokensRecorridos = 0;
                            tokenPila = Token.S1;
                        }
                    } else {
                        i++;
                        tokenPila = Token.ERROR;
                        cadenaSintaxis = contatenarString(cadenaSintaxis, listaLexemas.get(i).getNombre());
                        tokensRecorridos++;
                    }
                    break;
            }
        }
return listaSintaxis;
    }

    private void literal() {
        listaTokens.clear();
        listaTokens.add(Token.FIN);
        listaTokens.add(Token.LITERAL);
        listaTokens.add(Token.ESCRIBIR);
        crearNuevaLista(listaTokens);
    }

    private void ciclo1() {
        listaTokens.clear();
        listaTokens.add(Token.P3);
        listaTokens.add(Token.INICIAR);
        listaTokens.add(Token.NUMERO);
        listaTokens.add(Token.POR_CADA);
        crearNuevaLista(listaTokens);
    }

    private void condicional1() {
        listaTokens.clear();
        listaTokens.add(Token.I3);
        listaTokens.add(Token.SI);
        crearNuevaLista(listaTokens);
    }

    private void asignacion1() {
        listaTokens.clear();
        listaTokens.add(Token.FIN);
        listaTokens.add(Token.O1);
        listaTokens.add(Token.IGUAL);
        listaTokens.add(Token.VARIABLE);
        crearNuevaLista(listaTokens);
    }

    public void leerUltimoDato() {
        for (int i = 0; i < listaSintax.size(); i++) {
            System.out.println(String.valueOf("Numero de lista: " + i + " token: " + listaSintax.get(i).getListaToken().peek()) + " Puntero: " + listaSintax.get(i).getPosicionPuntero());
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    private boolean listaVacia() {
        return listaSintax.isEmpty();
    }

    public void eliminarPilasDiferentes(Token elToken) {
        for (int i = (listaSintax.size() - 1); i >= 0; i--) {
            if (listaSintax.get(i).getListaToken().peek() != elToken) {
                listaSintax.remove(i);
            }
        }
    }

    private String contatenarString(String principal, String agregar) {
        principal += (agregar + " ");
        return principal;
    }

    public String getTextoSalida() {
        return textoSalida;
    }

    public void setTextoSalida(String textoSalida) {
        this.textoSalida = textoSalida;
    }

    public LinkedList<SitaxisId> getListaSintaxis() {
        return listaSintaxis;
    }

    public void setListaSintaxis(LinkedList<SitaxisId> listaSintaxis) {
        this.listaSintaxis = listaSintaxis;
    }
}
