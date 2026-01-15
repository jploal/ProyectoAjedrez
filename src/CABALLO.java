public class CABALLO extends PIEZAS{
    public CABALLO(int x, int y, boolean blanco, TABLERO tablero) {

        super(x, y, blanco, tablero);
    }
    @Override
    public boolean movimientoValido(int nuevaX, int nuevaY) {
        int dx = Math.abs(nuevaX - getX());
        int dy = Math.abs(nuevaY - getY());

        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }

    @Override
    public char getChar() {
        return isBlanco() ? '\u2658' : '\u265E';
    }
}
