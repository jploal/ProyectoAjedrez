public class ALFIL extends PIEZAS{
    public ALFIL(int x, int y, boolean blanco, TABLERO tablero) {
        super(x, y, blanco, tablero);
    }
    @Override
    public boolean movimientoValido(int nuevaX, int nuevaY) {
        int dx = Math.abs(nuevaX - getX());
        int dy = Math.abs(nuevaY - getY());

        if (dx == dy && dx != 0) {
            return tablero.caminoLibre(getX(), getY(), nuevaX, nuevaY);
        }

        return false;
    }

    @Override
    public char getChar() {
        return isBlanco() ? '\u2657' : '\u265D';
    }
}
