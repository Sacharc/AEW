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
    
    /**
     * Sprawdza czy wspolrzedne naleza do planszy
     * @return true jezeli wspolrzedne sa poprawne
     */
    public boolean czyPoprawne(){
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
    
    /**
     * Odejmuje wspolrzedna 
     * @param w
     * @return
     */
    Wspolrzedne odejmij(Wspolrzedne w) {
        return new Wspolrzedne(this.x - w.x, this.y - w.y);
    }
    
    /**
     * Dodaje wspolrzedna
     * @param w
     * @return
     */
    Wspolrzedne dodaj(Wspolrzedne w) {
        return new Wspolrzedne(this.x + w.x, this.y + w.y);
    }
    
    /**
     * Generuje wektor jednostkowy w zadanym kierunku np (1 1) (1 -1)
     * @param k Kierunek wektora jednostkowego
     * @return Wektor jednostkowy o zadanym kierunku
     */
    static Wspolrzedne wektorJednostkowy(Kierunek k) {
        switch(k) {
        case NW:
            return new Wspolrzedne(1, -1);
        case NE:
            return new Wspolrzedne(1, 1);
        case SE:
            return new Wspolrzedne(-1, 1);
        case SW:
            return new Wspolrzedne(-1, -1);
        }
        return null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Wspolrzedne other = (Wspolrzedne) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}
