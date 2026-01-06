public class Main {
    public static void main(String[] args) {
                JUEGO.jugar();
            }
        }
/*TABLERO t = new TABLERO();
        Scanner sc = new Scanner(System.in);

        boolean valido = false;

        while (!valido) {
            System.out.println("Introduce piezas blancas separadas por coma:");
            String lineaBlancas = sc.nextLine();
            System.out.println("Introduce piezas negras separadas por coma:");
            String lineaNegras = sc.nextLine();

            t.limpiarTablero(); // vaciar antes de colocar

            try {
                for (String s : lineaBlancas.split(",\\s*")) NOTACION.crear(s.trim(), true, t);
                for (String s : lineaNegras.split(",\\s*")) NOTACION.crear(s.trim(), false, t);

                valido = t.validarComposicion();
                if (!valido) System.out.println("Composición inválida, vuelva a introducir todas las piezas.");
            } catch (Exception e) {
                System.out.println("Entrada inválida, vuelva a intentarlo.");
                valido = false;
            }
        }

        t.mostrarTABLERO();*/
        /*
        Ejemplo de creacion de tablero
        Rg1, Tf1, h2, g2, f2, d4, e4, Ce5, a4, b3, c2, Ab2, Ta1

        Rc8, Td8, a7, b7, c7, Ae6, d5, e5, f7, g6, Ag7, h7, Th8.
        */