public abstract class PIEZAS {

    private boolean blanco;
    private int x;
    private int y;
    protected TABLERO tablero;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public boolean isBlanco() {
        return blanco;
    }

    public void setBlanco(boolean blanco) {
        this.blanco = blanco;
    }

    public PIEZAS(int x, int y,boolean blanco, TABLERO tablero) {
        this.x = x; //fila
        this.y = y; //columna
        this.blanco=blanco;
        this.tablero=tablero;
        tablero.getTablero()[x][y] = this;
    }
    public abstract char getChar();
    public abstract boolean movimientoValido(int nuevaX, int nuevaY);
    public boolean mover(int nuevaX, int nuevaY) {
        if (movimientoValido(nuevaX, nuevaY)) {
            // actualizar tablero: vaciar la casilla actual y mover la pieza
            tablero.getTablero()[x][y] = null;
            tablero.getTablero()[nuevaX][nuevaY] = this;
            x = nuevaX;
            y = nuevaY;

            return true;
        }
        return false;
    }
    }

