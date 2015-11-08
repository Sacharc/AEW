package AEW.model;

public class Wspolrzedne {
    private int x;
    private int y;
    
    public Wspolrzedne(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
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
    
    public boolean czyPoprawne(){
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
