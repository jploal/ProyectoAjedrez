public class ALFIL extends PIEZAS{
    public ALFIL(int x, int y, boolean blanco, TABLERO tablero) {
        super(x, y, blanco, tablero);
    }
    @Override
    public boolean movimientoValido(int nuevaX, int nuevaY) {
        int dx = Math.abs(nuevaX - getX());
        int dy = Math.abs(nuevaY - getY());

        return dx == dy && dx != 0;
    }

    @Override
    public char getChar() {
        return isBlanco() ? '\u2657' : '\u265D';
    }
}
