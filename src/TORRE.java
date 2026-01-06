public class TORRE extends PIEZAS{
    public TORRE(int x, int y, boolean blanco, TABLERO tablero) {
        super(x, y, blanco, tablero);
    }
    @Override
    public boolean movimientoValido(int nuevaX, int nuevaY) {
        int dx = nuevaX - getX();
        int dy = nuevaY - getY();

        // horizontal o vertical puro
        return (dx == 0 && dy != 0) || (dx != 0 && dy == 0);
    }
    @Override
    public char getChar() {
        return isBlanco() ? '\u2656' : '\u265C';
    }
}
