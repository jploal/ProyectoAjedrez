public class REY extends PIEZAS{
    public REY(int x, int y, boolean blanco, TABLERO tablero) {
        super(x, y, blanco, tablero);
    }
    @Override
    public boolean movimientoValido(int nuevaX, int nuevaY) {
        int dx = Math.abs(nuevaX - getX());
        int dy = Math.abs(nuevaY - getY());

        return dx <= 1 && dy <= 1 && (dx + dy != 0);

    }

    @Override
    public char getChar() {
        return isBlanco() ? '\u2654' : '\u265A';
    }
}
