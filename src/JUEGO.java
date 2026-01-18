import java.util.Scanner;
public class JUEGO {
    private static TABLERO tablero;
    private static Scanner sc = new Scanner(System.in);

    public static void jugar() {

        boolean blancasTurno = inicializarTablero();

        System.out.println("Tablero inicial:");
        tablero.mostrarTABLERO();

        ejecutarMovimiento(blancasTurno);

        System.out.println("Tablero después del movimiento:");
        tablero.mostrarTABLERO();
    }

    // ---------------------
    // Inicializar el tablero con piezas
    private static boolean inicializarTablero() {
        tablero = new TABLERO();
        boolean valido = false;

        while (!valido) {
            System.out.println("Introduce piezas blancas separadas por coma:");
            String lineaBlancas = sc.nextLine();
            System.out.println("Introduce piezas negras separadas por coma:");
            String lineaNegras = sc.nextLine();

            tablero.limpiarTablero();

            try {
                for (String s : lineaBlancas.split(",\\s*"))
                    NOTACION.crear(s.trim(), true, tablero);

                for (String s : lineaNegras.split(",\\s*"))
                    NOTACION.crear(s.trim(), false, tablero);

                valido = tablero.validarComposicion();

                if (!valido) {
                    System.out.println("Composición inválida, vuelva a introducir todas las piezas.");
                    continue;
                }

                boolean jaqueBlancas = tablero.hayJaque(true);
                boolean jaqueNegras  = tablero.hayJaque(false);

                if (jaqueBlancas)
                    System.out.println("Las blancas están en jaque.");

                if (jaqueNegras)
                    System.out.println("Las negras están en jaque.");
                // si está en jaque, mueve ese color
                if (jaqueBlancas) return true;    // blancas
                if (jaqueNegras)  return false;   // negras

                return pedirTurno();


            } catch (Exception e) {
                System.out.println("Entrada inválida, vuelva a intentarlo.");
                valido = false;
            }
        }return true;
    }

    // Pedir que bando juega
    private static boolean pedirTurno() {
        boolean turnoblancas = false;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println("¿Qué bando juega? (blancas/negras):");
            String turno = sc.nextLine().trim().toLowerCase();

            if (turno.equals("blancas")) {
                turnoblancas = true;
                entradaValida = true;
            }
            else if (turno.equals("negras")) {
                entradaValida = true;
            }
            else {
                System.out.println("Entrada no válida, escribe 'blancas' o 'negras'.");
            }
        }

        return turnoblancas;
    }


    //Movimiento de la pieza elegida
    private static void ejecutarMovimiento(boolean blancasTurno) {
        System.out.println((blancasTurno ? "Blancas" : "Negras") + " mueve:");
        System.out.println("Introduce movimiento en notación algebraica (ej: e2, Af3, Ce3, Cce3):");

        String mov = sc.next().trim();
        PIEZAS p = null;
        char tipo;
        int xDest = -1, yDest = -1;

        try {
            if (Character.isUpperCase(mov.charAt(0))) {
                // Pieza que no es peón
                tipo = mov.charAt(0);

                if (mov.length() == 3) { // Ce3
                    yDest = NOTACION.col(mov.charAt(1));
                    xDest = NOTACION.fila(mov.charAt(2));
                    validarIndices(xDest, yDest, mov);

                    p = tablero.buscarPiezaClase(tipo, xDest, yDest, blancasTurno);

                } else if (mov.length() == 4) {  //Cce3 o C2e3
                    char diferencia = mov.charAt(1);
                    yDest = NOTACION.col(mov.charAt(2));
                    xDest = NOTACION.fila(mov.charAt(3));
                    validarIndices(xDest, yDest, mov);

                    p = tablero.piezaAmbigua(tipo, diferencia, xDest, yDest, blancasTurno);

                } else if (mov.length() == 5) { // movimiento completo Cc5d7
                    int yOrigen = NOTACION.col(mov.charAt(1));
                    int xOrigen = NOTACION.fila(mov.charAt(2));
                    yDest = NOTACION.col(mov.charAt(3));
                    xDest = NOTACION.fila(mov.charAt(4));
                    validarIndices(xOrigen, yOrigen, mov);
                    validarIndices(xDest, yDest, mov);

                    p = tablero.getPieza(xOrigen, yOrigen);
                    if (p == null || !p.movimientoValido(xDest, yDest)) {
                        throw new IllegalArgumentException();
                    }
                    p.mover(xDest, yDest);
                    p = null; // ya movida, salimos
                }

            } else { // Peón
                if (mov.length() == 2) { // e4
                    yDest = NOTACION.col(mov.charAt(0));
                    xDest = NOTACION.fila(mov.charAt(1));
                    validarIndices(xDest, yDest, mov);

                    p = tablero.buscarPiezaClase('P', xDest, yDest, blancasTurno);

                } else if (mov.length() == 3) { // aa3 (ambiguedad por columna)
                    char diferencia = mov.charAt(0);
                    yDest = NOTACION.col(mov.charAt(1));
                    xDest = NOTACION.fila(mov.charAt(2));
                    validarIndices(xDest, yDest, mov);

                    p = tablero.piezaAmbigua('P', diferencia, xDest, yDest, blancasTurno);
                }
            }

            if (p == null && mov.length() != 5) {
                throw new IllegalArgumentException();
            }
        } else {
            p = tablero.getPiezaDesdeAlgebraica(desde);
        }

            // Simular movimiento y verificar jaque
            if (p != null) {
                if (!tablero.piezaEsPineada(p, xDest, yDest)) {
                    System.out.println("Movimiento inválido: te dejas en jaque.");
                    System.exit(0);
                }
                p.mover(xDest, yDest);

                // Coronación si es peón
                if (p instanceof PEON) {
                    ((PEON) p).coronar(tablero);
                }
            }

            if (tablero.hayJaque(!blancasTurno)) {
                System.out.println("¡JAQUE!");
            }

        } catch (Exception e) {
            System.out.println("Movimiento inválido o mal formateado: " + mov);
            System.exit(0);
        }
    }
    //Se asegura de que en los movimientos no se sale del tablero, a9, b0
    private static void validarIndices(int x, int y, String mov) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new IllegalArgumentException("Movimiento fuera del tablero: " + mov);
        }
    }

        System.out.println("Movimiento correcto.");
        if (tablero.hayJaque(!blancasTurno)) System.out.println("¡JAQUE!");
    }
}

