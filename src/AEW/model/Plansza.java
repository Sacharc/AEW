package AEW.model;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Plansza {
    public Pole[][] plansza = new Pole[8][8];
    
    private ArrayList<Bicie> aktywneBicia;
    /** Mapa setow pionkow*/
    private Map<Wlasciciel, Set<Pionek>> pionkiGracza;

    /**
     * konstruktor inicjujacy pola na planszy
     * 
     * @param gracz1
     * @param gracz2
     */
    Plansza(Wlasciciel gracz1, Wlasciciel gracz2) {
        pionkiGracza = new TreeMap<>();
        pionkiGracza.put(gracz1, new HashSet<Pionek>());
        pionkiGracza.put(gracz2, new HashSet<Pionek>());
        ustawPionki(gracz1, gracz2);
        aktywneBicia = new ArrayList<Bicie>();
    }
    
    /**
     * Sprawdza czy lista bic jest pusta
     * @return true jezeli lista bic jest pusta
     */
    boolean czyAktywneBicia() {
        return !aktywneBicia.isEmpty();
    }
    
    /**
     * Szuka bic dla podanego gracza
     * @param gracz
     */
    void szukajBic(Wlasciciel gracz) {
        aktywneBicia.clear();
        for(Pionek pionek : pionkiGracza.get(gracz)){
            ArrayList<Bicie> bicia = pionek.szukajBicia(this);
            aktywneBicia.addAll(bicia);
        }
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
        for (int numerKolumny = 0; numerKolumny < 8; numerKolumny++)
            plansza[numerWiersza][numerKolumny] = new Pole();
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
        for (int numerKolumny = 0; numerKolumny < 8; numerKolumny++) {
            // co drugie miejsce od poczatku stawiamy pionek
            if (numerKolumny % 2 == numerWiersza % 2)
                plansza[numerWiersza][numerKolumny] = new Pole();
            else {
                plansza[numerWiersza][numerKolumny] = new Pole(gracz);
                plansza[numerWiersza][numerKolumny].getP().setW(new Wspolrzedne(numerWiersza, numerKolumny));
                pionkiGracza.get(gracz).add(plansza[numerWiersza][numerKolumny].getP());
            }
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
