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
        inicializarTablero();
    }

    private void inicializarTablero() {
        // Vaciar tablero
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                tablero[fila][col] = null;
            }
        }
    }
        public void mostrarTABLERO() {
            for (int fila = 0; fila < 8; fila++) {
                for (int col = 0; col < 8; col++) {
                    if (tablero[fila][col] != null) {
                        System.out.print("[" + tablero[fila][col] + "]");
                    } else {
                        System.out.print("[ ]");
                    }
                }
                System.out.println();
            }
        }
    }
