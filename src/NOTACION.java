public class NOTACION {
//Convertir a algebraico
    public static int col(char letra) {
        return letra - 'a';
    }

    public static int fila(char numero) {
        return 8 - (numero - '0');
    }
    //creacion con algebraico
    public static void crear(String texto, boolean blanco, TABLERO t) {

<<<<<<< Updated upstream
=======
        if (texto.length() < 2){
            throw new IllegalArgumentException("Notación demasiado corta");
        }

>>>>>>> Stashed changes
        char primera = texto.charAt(0);
        char colChar, filaChar;

        String tipo;

        if (Character.isUpperCase(primera)) {
            tipo = String.valueOf(primera);
            colChar = texto.charAt(1);
            filaChar = texto.charAt(2);
        } else {
            tipo = "P"; // peón
            colChar = texto.charAt(0);
            filaChar = texto.charAt(1);
        }

        int y = col(colChar);
        int x = fila(filaChar);

        if (t.getPieza(x, y) !=null) {
            throw new IllegalArgumentException(
                    "Casilla ocupada: " + colChar + filaChar
            );
        }

        switch (tipo) {
            case "R" -> new REY(x, y, blanco, t);
            case "T" -> new TORRE(x, y, blanco, t);
            case "C" -> new CABALLO(x, y, blanco, t);
            case "A" -> new ALFIL(x, y, blanco, t);
            case "D" -> new DAMA(x, y, blanco, t);
            case "P" -> new PEON(x, y, blanco, t);
            default  -> throw new IllegalArgumentException("Pieza inválida: " + tipo);
        }
    }
}
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
