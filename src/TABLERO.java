public class TABLERO {
    private static final String RESET = "\u001B[0m";
    private static final String FONDO_BLANCO = "\u001B[107m";
    private static final String FONDO_NEGRO = "\u001B[100m";
    private static final String TEXTO_BLANCO = "\u001B[37m";
    private static final String TEXTO_NEGRO = "\u001B[30m";
    private static final String BORDE = "\u001B[42m";
    private static final int ANCHO = 6;
    public PIEZAS[][] getTablero() {
        return tablero;
    }

    public void setTablero(PIEZAS[][] tablero) {
        this.tablero = tablero;
    }

    private PIEZAS[][] tablero;

    public TABLERO() {
        tablero = new PIEZAS[8][8];
        limpiarTablero();
    }

        public void mostrarTABLERO() {
            int numerico = 8;
            for (int i = 0; i < ANCHO + 4; i++) {
                System.out.print(BORDE + " ㅤ " + RESET);
            }
            System.out.println();

            for (int fila = 0; fila < 8; fila++) {
                System.out.print(BORDE + " ㅤ " + RESET);
                for (int col = 0; col < 8; col++) {
                    boolean casillaBlanca = (fila + col) % 2 ==0;
                    String fondo = casillaBlanca ? FONDO_BLANCO : FONDO_NEGRO;

                    PIEZAS pieza = tablero[fila][col];
                    if (pieza != null) {
                        String colorTexto = pieza.isBlanco() ? TEXTO_NEGRO : TEXTO_BLANCO;
                        System.out.print(fondo + colorTexto + " " + pieza.getChar() + " " + RESET);
                    } else {
                        System.out.print(fondo + " ㅤ " + RESET);
                    }

                }
                System.out.print(BORDE +TEXTO_NEGRO +numerico-- + " ㅤ" + RESET);
                System.out.println();
            }
            for (int i = 0; i < ANCHO + 4; i++) {
                System.out.print(BORDE + " ㅤ " + RESET);
            }
            System.out.println();
            System.out.println("ㅤㅤㅤA ㅤB ㅤC ㅤD ㅤE ㅤF ㅤG ㅤH ");
        }
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
    public boolean validarComposicion() {

        int reyesBlancos = 0;
        int reyesNegros = 0;
        int peonesBlancos = 0;
        int peonesNegros = 0;
        int torresBlancas = 0, torresNegras = 0;
        int caballosBlancos = 0, caballosNegras = 0;
        int alfilesBlancos = 0, alfilesNegras = 0;
        int damasBlancas = 0, damasNegras = 0;

        boolean[][] ocupadas = new boolean[8][8];
        //recorrer tablero leyendo instancias de objetos, cuenta cada tipo de pieza
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                PIEZAS p = tablero[i][j];
                if (p != null) {

                    if (ocupadas[i][j]) return false; // duplicado
                    ocupadas[i][j] = true;

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

        return true;
    }


}
