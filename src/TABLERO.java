public class TABLERO {

    private static final String RESET = "\u001B[0m";
    private static final String FONDO_BLANCO = "\u001B[107m";
    private static final String FONDO_NEGRO = "\u001B[100m";
    private static final String TEXTO_BLANCO = "\u001B[37m";
    private static final String TEXTO_NEGRO = "\u001B[30m";
    private static final String BORDE = "\u001B[42m";
    private static final int ANCHO = 6;
    private PIEZAS[][] tablero;

    public TABLERO() {
        tablero = new PIEZAS[8][8];
        limpiarTablero();
    }

    public PIEZAS[][] getTablero() {
        return tablero;
    }

    public void setPieza(int x, int y, PIEZAS p) {
        if (x < 0 || x > 7 || y < 0 || y > 7) return;
        tablero[x][y]= p;
    }

    public PIEZAS getPieza(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) return null;
        return tablero[x][y];
    }

    public void setTablero(PIEZAS[][] tablero) {
        this.tablero = tablero;
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
                boolean casillaBlanca = (fila + col) % 2 == 0;
                String fondo = casillaBlanca ? FONDO_BLANCO : FONDO_NEGRO;

                PIEZAS pieza = tablero[fila][col];
                if (pieza != null) {
                    String colorTexto = pieza.isBlanco() ? TEXTO_NEGRO : TEXTO_BLANCO;
                    System.out.print(fondo + colorTexto + " " + pieza.getChar() + " " + RESET);
                } else {
                    System.out.print(fondo + " ㅤ " + RESET);
                }
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

    // vacía el tablero
    public void limpiarTablero() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                tablero[i][j] = null;
    }

    // Traduce algebraico a array
    public PIEZAS buscarPiezaClase(char tipo, int xDest, int yDest, boolean blancasTurno) {

        //busca de forma algebraica las piezas Ce5 busca caballo que pueda ir a e5

        PIEZAS objetivo = null;
        int contador = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                PIEZAS p = tablero[i][j];
                if (p == null) {
                    continue;
                }
                if (p.isBlanco() != blancasTurno) {
                    continue;
                }

                boolean alcanza = switch (tipo) {
                    case 'T' -> p instanceof TORRE;
                    case 'C' -> p instanceof CABALLO;
                    case 'A' -> p instanceof ALFIL;
                    case 'D' -> p instanceof DAMA;
                    case 'R' -> p instanceof REY;
                    default -> p instanceof PEON;
                };

                if (!alcanza) {
                    continue;
                }

                if (p.movimientoValido(xDest, yDest) &&
                        piezaEsPineada(p, xDest, yDest)) {

                    contador++;
                    objetivo = p;
                }
            }
        }

        if (contador == 1) return objetivo;
        return null;
    }

    public PIEZAS piezaAmbigua(char tipo, char filtro, int xDest, int yDest, boolean blancasTurno) {

        PIEZAS objetivo = null;
        int contador = 0;
        //si el filtro es la fila, (C2e3 EJEMPLO)
        // crea una variable valorFiltro que valora si es la instancia que he elegido
        boolean esFila = Character.isDigit(filtro);
        int valorFiltro = esFila ? NOTACION.fila(filtro) : NOTACION.col(filtro);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                PIEZAS p = tablero[i][j];
                if (p == null) continue;
                if (p.isBlanco() != blancasTurno) continue;

                boolean coincide = switch (tipo) {
                    case 'T' -> p instanceof TORRE;
                    case 'C' -> p instanceof CABALLO;
                    case 'A' -> p instanceof ALFIL;
                    case 'D' -> p instanceof DAMA;
                    case 'R' -> p instanceof REY;
                    default -> p instanceof PEON;
                };

                if (!coincide) continue;
                // instancia de CABALLO en la fila/columna que yo he puesto en el filtro?
                if (esFila && p.getX() != valorFiltro) continue;
                if (!esFila && p.getY() != valorFiltro) continue;

                if (p.movimientoValido(xDest, yDest) &&
                        piezaEsPineada(p, xDest, yDest)) {

                    contador++;
                    objetivo = p;
                }
            }
        }

        if (contador == 1) return objetivo;
        return null; // 0 o >1 → inválido
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
        // recorrer tablero leyendo instancias de objetos, cuenta cada tipo de pieza
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

        int dx = Integer.compare(x2, x1);
        int dy = Integer.compare(y2, y1);

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
                    if (p instanceof CABALLO) {
                        return true;
                    }
                    //SI ES OTRA SUBCLASE HAY QUE ASEGURAR QUE NO HAYA NINGUNA PIEZA BLOQUEANDO, caminoLibre
                    if (caminoLibre(p.getX(), p.getY(), rx, ry)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean piezaEsPineada(PIEZAS p, int destinoX, int destinoY) {

        if (!p.movimientoValido(destinoX, destinoY)) return false;

        int viejaX = p.getX();
        int viejaY = p.getY();

        //guardo la pieza que hay en destino es decir si pruebo Ae6 y en e6 habia un caballo y es
        //comido, aunque es una "simulacion" por la condicion del programa ese caballlo desapareceria
        PIEZAS guardada = tablero[destinoX][destinoY];

        // Simula movimiento
        tablero[viejaX][viejaY] = null;
        tablero[destinoX][destinoY] = p;
        p.setX(destinoX);
        p.setY(destinoY);

        // hay "auto-jaque" en ese movimiento?
        boolean jaque = hayJaque(p.isBlanco());

        //devuelvo el tablero a la posicion anterior a la simulacion, el caballo anterior del ejemplo volveria a existir
        tablero[destinoX][destinoY] = guardada;
        tablero[viejaX][viejaY] = p;
        p.setX(viejaX);
        p.setY(viejaY);

        return !jaque;
    }


}
