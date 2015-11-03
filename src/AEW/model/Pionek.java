package AEW.model;

/**
 * Abstrakcyjna klasa reprezentuj¹ca pionek na planszy
 * @author Mmm
 *
 */
abstract class Pionek {
    
    /**
     * Okresla wlasciciela pionka
     */
    Wlasciciel wlasciciel;

    /**
     * Sprawdza czy pionek moze siê ruszyc 
     * @return True jezeli tak
     */
    abstract boolean czyMozeRuszyc();
    
    /**
     * Wykonuje ruch. 
     * @param x Do mo¿liwej zmiany na kierunek np lewo prawo
     * @param y
     */
    abstract void ruch(final int x, final int y);
    
    /**
     * Wykonuje bicie w kierunku k
     * @param k kierunek bicia
     * @return Krotnoœæ bicia
     */
    abstract int wykonajBicie(final Kierunek k);

    Wlasciciel getWlasciciel() {
        return wlasciciel;
    }

    void setWlasciciel(Wlasciciel wlasciciel) {
        this.wlasciciel = wlasciciel;
    }
    
}
