package AEW.model;

import java.util.ArrayList;


public class Plansza {
    public Pole[][] plansza = new Pole[8][8];
    
    private ArrayList<Bicie> aktywneBicia;

    /**
     * konstruktor inicjujacy pola na planszy
     * 
     * @param gracz1
     * @param gracz2
     */
    Plansza(Wlasciciel gracz1, Wlasciciel gracz2) {
        ustawPionki(gracz1, gracz2);
        aktywneBicia = new ArrayList<Bicie>();
        
    }
    
    boolean czyAktywneBicia() {
        return !aktywneBicia.isEmpty();
    }

    /**
     * tworzy pola na planszy z pionkami lub bez
     * 
     * @param gracz1
     * @param gracz2
     */
    private void ustawPionki(Wlasciciel gracz1, Wlasciciel gracz2) {
        for (int i = 0; i < 8; i++) {
            if (i < 3)
                wypelnijWiersz(gracz1, i);
            else if (i > 4)
                wypelnijWiersz(gracz2, i);
            else
                wypelnijWiersz(i);
        }
    }

    /**
     * wypelnia wiersz pustymi polami
     * 
     * @param numerWiersza
     */
    private void wypelnijWiersz(int numerWiersza) {
        for (int j = 0; j < 8; j++)
            plansza[numerWiersza][j] = new Pole();
    }

    /**
     * wypelnia wiersz pionkami i pustymi polami. Dla wierszy parzystych
     * uzueplnia od pierwszego pola. dla nieparzystych uzupelnia od drugiego
     * pola.
     * 
     * @param gracz
     * @param numerWiersza
     */
    public void wypelnijWiersz(Wlasciciel gracz, int numerWiersza) {
        for (int j = 0; j < 8; j++) {
            // co drugie miejsce od poczatku stawiamy pionek
            if (j % 2 == numerWiersza % 2)
                plansza[numerWiersza][j] = new Pole();
            else
                plansza[numerWiersza][j] = new Pole(gracz);
        }
    }

    /**
     * Zwraca pole o podonach wspolrzednych
     * 
     * @param x
     * @param y
     * @return Pole (x, y) lub null jezeli wspolrzedne nie naleza do planszy
     */
    public Pole getPole(int x, int y) {
        if (czyNalezy(x, y))
            return plansza[x][y];
        return null;
    }

    private boolean czyNalezy(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
