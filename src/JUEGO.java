import java.util.Scanner;
public class JUEGO {
        private static TABLERO tablero;
        private static Scanner sc = new Scanner(System.in);

        public static void jugar() {

            inicializarTablero();

            System.out.println("Tablero inicial:");
            tablero.mostrarTABLERO();

            boolean blancasTurno = pedirTurno();

            ejecutarMovimiento(blancasTurno);

            System.out.println("Tablero después del movimiento:");
            tablero.mostrarTABLERO();
        }

        // ---------------------
        // Inicializar el tablero con piezas
        private static void inicializarTablero() {
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
                    }
                } catch (Exception e) {
                    System.out.println("Entrada inválida, vuelva a intentarlo.");
                    valido = false;
                }
            }
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
                    turnoblancas = false;
                    entradaValida = true;
                }
                else {
                    System.out.println("Entrada no válida, escribe 'blancas' o 'negras'.");
                }
            }

            return turnoblancas;
        }


    // Ejecutar un movimiento de la pieza elegida
        private static void ejecutarMovimiento(boolean blancasTurno) {
            System.out.println((blancasTurno ? "Blancas" : "Negras") + " mueve:");
            System.out.println("Introduce movimiento en notación algebraica (ej: e2 e4):");

            String desde = sc.next();
            String hasta = sc.next();

            PIEZAS p = tablero.getPiezaDesdeAlgebraica(desde);

            // Si no hay pieza en la casilla seleccionada
            if (p == null) {
                System.out.println("Movimiento inválido: no hay pieza en " + desde);
                System.out.println((blancasTurno ? "Blancas" : "Negras") + " ha perdido.");
                System.exit(0); // termina la partida
            }

            // Si la pieza no corresponde al turno
            if (p.isBlanco() != blancasTurno) {
                System.out.println("Movimiento inválido: esa pieza no corresponde al turno.");
                System.out.println((blancasTurno ? "Blancas" : "Negras") + " ha perdido.");
                System.exit(0);
            }

            // Traducir destino a índices
            int xDestino = NOTACION.fila(hasta.charAt(1));
            int yDestino = NOTACION.col(hasta.charAt(0));

            // Validar movimiento según reglas de la pieza
            if (!p.mover(xDestino, yDestino)) {
                System.out.println("Movimiento inválido: no se puede mover " + desde + " a " + hasta);
                System.out.println((blancasTurno ? "Blancas" : "Negras") + " ha perdido.");
                System.exit(0);
            }

            System.out.println("Movimiento realizado: " + desde + " → " + hasta);

            if (JAQUE.hayJaque(!blancasTurno, tablero)) {
                System.out.println("¡JAQUE!");
            }
    
            }

}

