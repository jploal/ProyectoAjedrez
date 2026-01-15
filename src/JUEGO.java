import java.util.Scanner;
import java.util.List;

public class JUEGO {
    private static TABLERO tablero;
    private static Scanner sc = new Scanner(System.in);

    public static void jugar() {
        inicializarTablero();
        tablero.mostrarTABLERO();
        boolean blancasTurno = pedirTurno();

        // EJECUTA UN SOLO MOVIMIENTO
        ejecutarMovimiento(blancasTurno);

        System.out.println("\nTablero tras el movimiento único:");
        tablero.mostrarTABLERO();
        System.out.println("Fin del programa.");
        System.exit(0);
    }

    private static void inicializarTablero() {
        tablero = new TABLERO();
        boolean valido = false;
        while (!valido) {
            System.out.println("Introduce piezas blancas (ej: Rg1, Tf1):");
            String lb = sc.nextLine();
            System.out.println("Introduce piezas negras:");
            String ln = sc.nextLine();
            tablero.limpiarTablero();
            try {
                for (String s : lb.split(",")) NOTACION.crear(s.trim(), true, tablero);
                for (String s : ln.split(",")) NOTACION.crear(s.trim(), false, tablero);
                valido = tablero.validarComposicion();
                if (!valido) System.out.println("Error en la composición.");
            } catch (Exception e) { System.out.println("Entrada inválida."); }
        }
    }

    private static boolean pedirTurno() {
        while (true) {
            System.out.println("¿Qué bando juega? (blancas/negras):");
            String turno = sc.nextLine().trim().toLowerCase();
            if (turno.equals("blancas")) return true;
            if (turno.equals("negras")) return false;
        }
    }

    private static void ejecutarMovimiento(boolean blancasTurno) {
        System.out.println("\nTurno de " + (blancasTurno ? "Blancas" : "Negras"));
        System.out.println("Introduce movimiento (ej: Th g8):");

        String linea = sc.nextLine().trim();
        String[] partes = linea.split("\\s+");
        if (partes.length < 2) { System.out.println("Error de formato."); System.exit(0); }

        String desde = partes[0];
        String hasta = partes[1];
        PIEZAS p = null;

        if (Character.isUpperCase(desde.charAt(0))) {
            char tipo = desde.charAt(0);
            char filtro = desde.charAt(1);
            List<PIEZAS> candidatos = tablero.buscarCandidatos(tipo, filtro, blancasTurno);

            if (candidatos.isEmpty()) {
                System.out.println("Movimiento inválido: no hay pieza " + tipo + " en " + filtro);
                System.out.println((blancasTurno ? "Blancas" : "Negras") + " ha perdido.");
                System.exit(0);
            } else if (candidatos.size() == 1) {
                p = candidatos.get(0);
            } else {
                System.out.println("Ambigüedad. Indique dato faltante:");
                if (Character.isDigit(filtro)) {
                    System.out.print("Columna (a-h): ");
                    char cEx = sc.nextLine().trim().toLowerCase().charAt(0);
                    for (PIEZAS c : candidatos) if (c.getY() == NOTACION.col(cEx)) p = c;
                } else {
                    System.out.print("Fila (1-8): ");
                    char fEx = sc.nextLine().trim().charAt(0);
                    for (PIEZAS c : candidatos) if (c.getX() == NOTACION.fila(fEx)) p = c;
                }
            }
        } else {
            p = tablero.getPiezaDesdeAlgebraica(desde);
        }

        if (p == null || p.isBlanco() != blancasTurno) {
            System.out.println("Movimiento inválido.");
            System.out.println((blancasTurno ? "Blancas" : "Negras") + " ha perdido.");
            System.exit(0);
        }

        int xDest = NOTACION.fila(hasta.charAt(1));
        int yDest = NOTACION.col(hasta.charAt(0));

        if (!p.mover(xDest, yDest)) {
            System.out.println("Movimiento ilegal.");
            System.out.println((blancasTurno ? "Blancas" : "Negras") + " ha perdido.");
            System.exit(0);
        }

        System.out.println("Movimiento correcto.");
        if (tablero.hayJaque(!blancasTurno)) System.out.println("¡JAQUE!");
    }
}
