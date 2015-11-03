package AEW.model;

/**
 * Abstrakcyjna klasa reprezentuj�ca pionek na planszy
 * @author Mmm
 *
 */
abstract class Pionek {
    
    /**
     * Sprawdza czy pionek moze si� ruszyc 
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
     * @return Krotno�� bicia
     */
    abstract int wykonajBicie(final Kierunek k);

}
