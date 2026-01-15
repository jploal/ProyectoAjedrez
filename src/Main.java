public class Main {
    public static void main(String[] args) {
                JUEGO.jugar();
            }
        }
        /*
        Ejemplo de creacion de tablero
        Rg1, Tf1, h2, g2, f2, d4, e4, Ce5, a4, b3, c2, Ab2, Ta1
        Rc8, Td8, a7, b7, c7, Ae6, d5, e5, f7, g6, Ag7, h7, Th8
        blancas


       probar movimientos:

        comer con peon: d4 e 5
        torre mover (invalido) a1 a5 (atraviesa), a1 b2 (correcto no mueve diagonal)
        alfil mover (invalido) b2 d4 (error come mismo color)
        rey mover (invalido) g1 g3
        conclusion -> corregir colision, arreglar movimiento peon, movimiento comer peon, capturas de todsa las piezas, ordnear
        y limpiar codigo

        */