import java.util.ArrayList;
import java.util.List;

public class TABLERO {
<<<<<<< Updated upstream
    public PIEZAS[][] getTablero() {
        return tablero;
    }

    public void setTablero(PIEZAS[][] tablero) {
        this.tablero = tablero;
    }

=======

    private static final String RESET = "\u001B[0m";
    private static final String FONDO_BLANCO = "\u001B[107m";
    private static final String FONDO_NEGRO = "\u001B[100m";
    private static final String TEXTO_BLANCO = "\u001B[37m";
    private static final String TEXTO_NEGRO = "\u001B[30m";
    private static final String BORDE = "\u001B[42m";
    private static final int ANCHO = 6;
>>>>>>> Stashed changes
    private PIEZAS[][] tablero;

    public TABLERO() {
        tablero = new PIEZAS[8][8];
        limpiarTablero();
    }

        public void mostrarTABLERO() {
            for (int fila = 0; fila < 8; fila++) {
                for (int col = 0; col < 8; col++) {
                    if (tablero[fila][col] != null) {
                        System.out.print("[" + tablero[fila][col].getChar() + "]");
                    } else {
                        System.out.print("[ㅤ]");
                    }
                }
<<<<<<< Updated upstream
                System.out.println();
            }
        }
=======
            }
            System.out.print(BORDE + TEXTO_NEGRO + numerico-- + " ㅤ" + RESET);
            System.out.println();
        }
        for (int i = 0; i < ANCHO + 4; i++) {
            System.out.print(BORDE + " ㅤ " + RESET);
        }
        System.out.println();
        System.out.println("ㅤㅤㅤA ㅤB ㅤC ㅤD ㅤE ㅤF ㅤG ㅤH ");
    }

>>>>>>> Stashed changes
    // vacía el tablero
    public void limpiarTablero() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                tablero[i][j] = null;
    }
    //traduce algebraico a array
    public PIEZAS getPiezaDesdeAlgebraica(String pos) {
        int x, y;
        if (pos.length() == 3) {
            y = NOTACION.col(pos.charAt(1));
            x = NOTACION.fila(pos.charAt(2));
        } else { // e2
            y = NOTACION.col(pos.charAt(0));
            x = NOTACION.fila(pos.charAt(1));
        }
        return tablero[x][y];
    }
<<<<<<< Updated upstream
    public boolean validarComposicion() {

=======

    public boolean validarComposicion() {

>>>>>>> Stashed changes
        int reyesBlancos = 0;
        int reyesNegros = 0;
        int peonesBlancos = 0;
        int peonesNegros = 0;
        int torresBlancas = 0, torresNegras = 0;
        int caballosBlancos = 0, caballosNegras = 0;
        int alfilesBlancos = 0, alfilesNegras = 0;
        int damasBlancas = 0, damasNegras = 0;

        boolean[][] ocupadas = new boolean[8][8];
<<<<<<< Updated upstream
        //recorrer tablero leyendo instancias de objetos, cuenta cada tipo de pieza
=======
        // recorrer tablero leyendo instancias de objetos, cuenta cada tipo de pieza
>>>>>>> Stashed changes
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                PIEZAS p = tablero[i][j];
                if (p != null) {

                    if (ocupadas[i][j]) return false; // duplicado
                    ocupadas[i][j] = true;
<<<<<<< Updated upstream

                    if (p instanceof REY) {
                        if (p.isBlanco()) reyesBlancos++;
                        else reyesNegros++;
                    }
=======

                    if (p instanceof REY) {
                        if (p.isBlanco()) reyesBlancos++;
                        else reyesNegros++;
                    }

                    if (p instanceof PEON) {
                        if (p.isBlanco()) peonesBlancos++;
                        else peonesNegros++;
                    }

                    if (p instanceof TORRE) {
                        if (p.isBlanco()) torresBlancas++;
                        else torresNegras++;
                    }

                    if (p instanceof CABALLO) {
                        if (p.isBlanco()) caballosBlancos++;
                        else caballosNegras++;
                    }

                    if (p instanceof ALFIL) {
                        if (p.isBlanco()) alfilesBlancos++;
                        else alfilesNegras++;
                    }

                    if (p instanceof DAMA) {
                        if (p.isBlanco()) damasBlancas++;
                        else damasNegras++;
                    }
                }
            }
        }

        // Validar reyes
        if (reyesBlancos != 1 || reyesNegros != 1) return false;

        // Validar peones
        if (peonesBlancos > 8 || peonesNegros > 8) return false;

        // Si hay 8 peones, no puede haber piezas superiores adicionales
        if (peonesBlancos == 8) {
            if (torresBlancas > 2 || caballosBlancos > 2 || alfilesBlancos > 2 || damasBlancas > 1)
                return false;
        }
        if (peonesNegros == 8) {
            if (torresNegras > 2 || caballosNegras > 2 || alfilesNegras > 2 || damasNegras > 1)
                return false;
        }
        //Validar que no esten los 2 en jaque
        if (hayJaque(true) && hayJaque(false)){
            return false;
        }

        return true;
    }

    // Calcula si hay obstaculos durante el movimiento
    public boolean caminoLibre(int x1, int y1, int x2, int y2) {
        // Integer.compare: si (a>b)=1, si (a=b)=0, si (a<b)=-1, representan la direccion del movimiento en cada eje
        // si dx=1 dy=0, se mueve en la columna (dx=1 hacia abajo, dx=-1 hacia arriba)
        // si dx=0 dy=1, se mueve solo en la fila (dy=1 hacia derecha, dy=-1 hacia izquierda)
        // las demás combinaciones son diagonales en las diferentes 4 diagonales para dama y alfil
        PIEZAS p = tablero[x1][y1];
        if (p instanceof CABALLO) return true;
>>>>>>> Stashed changes

                    if (p instanceof PEON) {
                        if (p.isBlanco()) peonesBlancos++;
                        else peonesNegros++;
                    }

<<<<<<< Updated upstream
                    if (p instanceof TORRE) {
                        if (p.isBlanco()) torresBlancas++;
                        else torresNegras++;
                    }

=======
        //int x e int y es la primera posiciona a comprobar, la propia (x1,y1) no tiene sentido comprobarla
        int x = x1 + dx;
        int y = y1 + dy;
        //while x no sea el x destino(x2) e y no sea la y destino (y2) recorrer las posiciones de "viaje"
        while (x != x2 || y != y2) {
            if (tablero[x][y] != null) return false;
            x += dx;
            y += dy;
        }

        return true;
    }

    //Alguna pieza se puede mover hasta el rey
    // boolean reyBlanco,!blancaTurno, es decir si soy blancas, busca rey negro
    public boolean hayJaque(boolean reyBlanco) {

        REY rey = null;
        //for que busca al rey
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                PIEZAS p = tablero[i][j];
                //busca instancia de rey del color que quiero
                if (p instanceof REY && p.isBlanco() == reyBlanco) {
                    rey = (REY) p; //creamos una "pieza" sobre la que hacer el get
                }
            }
        }
        //REDUNDANTE
        //validarComposicion ASEGURA 1 REY DE CADA COLOR
        //DEFIENDE DE BUGS???
        //SE PUEDE ELIMINAR SIN PROBLEMAS
        if (rey == null) return false;

        int rx = rey.getX();
        int ry = rey.getY();
        //for para recorrer tablero (otra vez)
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                PIEZAS p = tablero[i][j];
                //registramos piezas
                //SOLO NOS INTERESA PIEZA CON COLOR DIFERENTE AL REY
                if (p != null && p.isBlanco() != reyBlanco) {
                    //REVISAMOS SI ESAS PIEZAS TIENEN MOVIMIENTO VALIDO AL REY
                    if (!p.movimientoValido(rx, ry)) continue;
                    //SI CABALLO AUTOMATICAMENTE JAQUE
>>>>>>> Stashed changes
                    if (p instanceof CABALLO) {
                        if (p.isBlanco()) caballosBlancos++;
                        else caballosNegras++;
                    }

                    if (p instanceof ALFIL) {
                        if (p.isBlanco()) alfilesBlancos++;
                        else alfilesNegras++;
                    }

                    if (p instanceof DAMA) {
                        if (p.isBlanco()) damasBlancas++;
                        else damasNegras++;
                    }
                }
            }
        }
<<<<<<< Updated upstream
=======

        return false;
    }
>>>>>>> Stashed changes

        // Validar reyes
        if (reyesBlancos != 1 || reyesNegros != 1) return false;

        // Validar peones
        if (peonesBlancos > 8 || peonesNegros > 8) return false;

        // Si hay 8 peones, no puede haber piezas superiores adicionales
        if (peonesBlancos == 8) {
            if (torresBlancas > 2 || caballosBlancos > 2 || alfilesBlancos > 2 || damasBlancas > 1)
                return false;
        }
        if (peonesNegros == 8) {
            if (torresNegras > 2 || caballosNegras > 2 || alfilesNegras > 2 || damasNegras > 1)
                return false;
        }

        return true;
    }


}
