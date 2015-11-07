package AEW.model;

import AEW.widok.PolePanel;

public class Plansza {
    public Pole[][] plansza = new Pole[8][8];

    /**
     * konstruktor inicjujacy pola na planszy
     * 
     * @param gracz1
     * @param gracz2
     */
    Plansza(Wlasciciel gracz1, Wlasciciel gracz2) {
        ustawPionki(gracz1, gracz2);
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
        for (int j = 0; j < 8; j++) {
            plansza[numerWiersza][j] = new Pole();
        }
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
                plansza[numerWiersza][j] = new Pole(gracz);
            else
                plansza[numerWiersza][j] = new Pole();
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
        return x > -1 && x < 8 && y > -1 && y < 8;
    }
}
