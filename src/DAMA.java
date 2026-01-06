public class DAMA extends PIEZAS{
        public DAMA(int x, int y, boolean blanco, TABLERO tablero) {
            super(x, y, blanco, tablero);
        }
        @Override
        public boolean movimientoValido(int nuevaX, int nuevaY) {
            int dx = Math.abs(nuevaX - getX());
            int dy = Math.abs(nuevaY - getY());

            // combina TORRE y ALFIL
            return (dx == dy && dx != 0) || (dx == 0 && dy != 0) || (dx != 0 && dy == 0);
        }

        @Override
        public char getChar() {
            return isBlanco() ? '\u2655' : '\u265B';
        }
    }
