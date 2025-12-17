public class PIEZAS {

    private boolean blanco;
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public boolean isBlanco() {
        return blanco;
    }

    public void setBlanco(boolean blanco) {
        this.blanco = blanco;
    }

    public PIEZAS(int x, int y,boolean blanco) {
        this.x = x;
        this.y = y;
        this.blanco=blanco;
    }
}
