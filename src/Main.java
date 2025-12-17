public class Main {
    public static void main(String[] args) {
        TABLERO tablero = new TABLERO();
        PEON peonBlanco = new PEON(6, 0, true);
        PEON peonNegro  = new PEON(1, 0, false);
        tablero.getTablero()[peonBlanco.getX()][peonBlanco.getY()] = peonBlanco;
        tablero.getTablero()[peonNegro.getX()][peonNegro.getY()]   = peonNegro;
        tablero.mostrarTABLERO();
        }
    }