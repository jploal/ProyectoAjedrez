public class TABLERO {
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
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                if (tablero[fila][col] != null) {
                    System.out.print("[" + tablero[fila][col].getChar() + "]");
                } else {
                    System.out.print("[ㅤ]");
                }
            }
            System.out.println();
        }
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
        
        public boolean caminoLibre(int x1, int y1, int x2, int y2) {
            int dx = Integer.compare(x2, x1);
            int dy = Integer.compare(y2, y1);

            int x = x1 + dx;
            int y = y1 + dy;

            while (x != x2 || y != y2) {
                if (tablero[x][y] != null) return false;
                x += dx;
                y += dy;
            }
            
        return true;
    }
        
}
