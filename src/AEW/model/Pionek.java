package AEW.model;

import java.util.ArrayList;

/**
 * Abstrakcyjna klasa reprezentujaca pionek na planszy

 */
public abstract class Pionek {
    
    /**
     * Okresla wlasciciela pionka
     */
    Wlasciciel wlasciciel;
    /**
     * Je¿eli siê nie przyda to do wywalenia narazie roboczo zostawiam
     */
    Wspolrzedne w;

    /**
     * Sprawdza czy pionek moze sie ruszyc 
     * @return True jezeli tak
     */
    abstract boolean czyMozeRuszyc();
    
    /**
     * Wykonuje ruch.
     * @param punktDocelowy 
     * @param plansza
     * @return czy ruch sie powiodl
     */
    abstract boolean ruch(final Wspolrzedne punktDocelowy, Plansza plansza);
    
    /**
     * Wykonuje bicie w kierunku k
     * @param k kierunek bicia
     * @return Krotnosc bicia
     */
    abstract int wykonajBicie(final Kierunek k);
    
    /**
     * Szuka bic danego pionka
     * @param p
     * @return Lista bic
     */
    abstract ArrayList<Bicie> szukajBicia(Plansza p);

    public Wlasciciel getWlasciciel() {
        return wlasciciel;
    }

    void setWlasciciel(Wlasciciel wlasciciel) {
        this.wlasciciel = wlasciciel;
    }

    Wspolrzedne getW() {
        return w;
    }

    void setW(Wspolrzedne w) {
        this.w = w;
    }

}
