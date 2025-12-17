public class PEON extends PIEZAS{
    /*public PIEZA(String Cc4){
        char a = Pc4.charAt(0);
        char b = Pc4.charAt(1);
        char c = Pc4.charAt(2);
        SWITCH (a){
            case 'P' -> (PEON(Integer.parseInt(b),Integer.parseInt(C),));
        }*/
    public PEON(int x, int y, boolean blanco) {
        super(x, y, blanco);
    }

    @Override
    public String toString() {
        return isBlanco() ? "P" : "p";
    }
    void MovimientoP(){

    }
}
