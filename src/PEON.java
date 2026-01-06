public class PEON extends PIEZAS{

    public PEON(int x, int y, boolean blanco, TABLERO tablero) {
        super(x, y, blanco, tablero);
    }
    @Override
    public char getChar() {
        return isBlanco() ? '\u2659' : '\u265F';
    }

    @Override
    public boolean movimientoValido(int nuevaX, int nuevaY) {

        int xActual = getX();
        int yActual = getY();

        // Mover recto, misma columna
        if (yActual != nuevaY) return false;

        if (isBlanco()) {
            // Blancos suben (x disminuye)
            if (xActual == 6 && nuevaX == 4) return true; // primer movimiento, 2 pasos
            if (nuevaX == xActual - 1) return true;       // 1 paso
        } else {
            // Negros bajan (x aumenta)
            if (xActual == 1 && nuevaX == 3) return true; // primer movimiento, 2 pasos
            if (nuevaX == xActual + 1) return true;       // 1 paso
        }

        return false;
    }
    public boolean MovimientoP(int nuevaX, int nuevaY) {
        if (movimientoValido(nuevaX, nuevaY)) {
            setX(nuevaX);
            setY(nuevaY);
            return true;
        }
        return false;
    }
}
