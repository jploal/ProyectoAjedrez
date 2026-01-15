public class NOTACION {
    public static int col(char letra) {
        return Character.toLowerCase(letra) - 'a';
    }

    public static int fila(char numero) {
        return 8 - (numero - '0');
    }

    public static void crear(String texto, boolean blanco, TABLERO t) {
        char primera = texto.charAt(0);
        char colChar, filaChar;
        String tipo;

        if (Character.isUpperCase(primera)) {
            tipo = String.valueOf(primera);
            colChar = texto.charAt(1);
            filaChar = texto.charAt(2);
        } else {
            tipo = "P";
            colChar = texto.charAt(0);
            filaChar = texto.charAt(1);
        }

        int y = col(colChar);
        int x = fila(filaChar);

        switch (tipo) {
            case "R" -> new REY(x, y, blanco, t);
            case "T" -> new TORRE(x, y, blanco, t);
            case "C" -> new CABALLO(x, y, blanco, t);
            case "A" -> new ALFIL(x, y, blanco, t);
            case "D" -> new DAMA(x, y, blanco, t);
            case "P" -> new PEON(x, y, blanco, t);
        }
    }
}
