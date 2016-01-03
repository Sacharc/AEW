package model;

/**
 *
 *
 * @author Mateusz Skolimowki
 */
public class Pole{

    private Pionek pionek;
    int x;
    int y;

    public Pole(int x, int y){
        this.pionek = null;
        this.x = x;
        this.y = y;

    }

    public Pole(Pole pole) {
        this.x = pole.x;
        this.y = pole.y;
        if(pole.pionek != null)
            this.pionek = new Pionek(pole.pionek);

    }

    public void addPionek(Wlasciciel gracz1)
    {
        this.pionek = new Pionek(gracz1);
    }

    public Pionek getPionek(){
        return this.pionek;
    }

    public Wlasciciel removePionek()
    {
        Wlasciciel gracz;
        gracz = this.pionek.getWlasciciel();
        this.pionek = null;
        return gracz;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

}
