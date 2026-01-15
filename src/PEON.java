public class PEON extends PIEZAS {

    public PEON(int x, int y, boolean blanco, TABLERO tablero) {
        super(x, y, blanco, tablero);
    }

    @Override
    public char getChar() {
        return isBlanco() ? '\u2659' : '\u265F';
    }

    @Override
    public boolean movimientoValido(int nuevaX, int nuevaY) {
        PIEZAS destino = tablero.getTablero()[nuevaX][nuevaY];

        // Movimiento recto, misma columna
        if (getY() == nuevaY) {
            if (isBlanco()) {
                // Primer movimiento 2 pasos
                if (getX() == 6 && nuevaX == 4 && tablero.getTablero()[5][getY()] == null && destino == null)
                    return true;
                // 1 paso
                if (nuevaX == getX() - 1 && destino == null) return true;
            } else {
                // Primer movimiento 2 pasos
                if (getX() == 1 && nuevaX == 3 && tablero.getTablero()[2][getY()] == null && destino == null)
                    return true;
                // Movimiento normal
                if (nuevaX == getX() + 1 && destino == null) return true;
            }
            return false;
        }

        // Movimiento diagonal
        if (Math.abs(getY() - nuevaY) == 1) {
            if (isBlanco() && nuevaX == getX() - 1 && destino != null && !destino.isBlanco())
                return true;
            if (!isBlanco() && nuevaX == getX() + 1 && destino != null && destino.isBlanco())
                return true;
        }

        return false; // cualquier otro movimiento no válido
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
