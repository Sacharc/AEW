package AEW.model;

/**
 * Przechowuje informacje o biciu tzn wspolrzedne pionka i kierunek
 */
class Bicie {
    /** Kierunek ruchu*/
    final private Kierunek k;
    /** Wspolrzedne z ktorych nastepuje ruch */
    final private Wspolrzedne w;

    /**
     * @param k kierunek w 
     * @param w Wspolrzedne z ktorych nastepuje ruch
     */
    Bicie(Kierunek k, Wspolrzedne w) {
        super();
        this.k = k;
        this.w = w;
    }

    /**
     * @return the k
     */
    final Kierunek getK() {
        return k;
    }

    /**
     * @return the w
     */
    final Wspolrzedne getW() {
        return w;
    }

}
