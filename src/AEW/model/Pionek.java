package AEW.model;

/**
 * Abstrakcyjna klasa reprezentujaca pionek na planszy

 */
public abstract class Pionek {
    
    /**
     * Okresla wlasciciela pionka
     */
    Wlasciciel wlasciciel;

    /**
     * Sprawdza czy pionek moze sie ruszyc 
     * @return True jezeli tak
     */
    abstract boolean czyMozeRuszyc();
    
    /**
     * Wykonuje ruch. 
     * @param x Do mo�liwej zmiany na kierunek np lewo prawo
     * @param y
     */
    abstract void ruch(final int x, final int y);
    
    /**
     * Wykonuje bicie w kierunku k
     * @param k kierunek bicia
     * @return Krotnosc bicia
     */
    abstract int wykonajBicie(final Kierunek k);

    public Wlasciciel getWlasciciel() {
        return wlasciciel;
    }

    void setWlasciciel(Wlasciciel wlasciciel) {
        this.wlasciciel = wlasciciel;
    }
    
}
