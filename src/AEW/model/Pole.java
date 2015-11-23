package AEW.model;

/**
 * Zawiera pionek lub jest puste 
 */
public class Pole {
    Pionek p;
    final boolean moznaStanac;

    Pole() {
        moznaStanac = false;
    }

    /**
     * @param moznaStanac
     */
    Pole(boolean moznaStanac) {
        this.moznaStanac = moznaStanac;
    }

    /**
     * tworzy pole z pionkiem
     * 
     * @param gracz1 - wlasciciel pionka
     */
    Pole(Wlasciciel gracz1) {
        p = new ZwyklyPionek(gracz1);
        moznaStanac = true;
    }

    /**
     * @return pionek stoj¹cy na polu null jezeli puste
     */
    public Pionek getP() {
        return p;
    }

    void setP(Pionek p) {
        this.p = p;
    }
    
    boolean czyMoznaStanac() {
        return moznaStanac;
    }

    /**
     * Informuje czy pole jest puste
     * @return true jezeli puste
     */
    boolean isEmpty() {
        return p == null;
    }
    
    /**
     * Ustawia wartosc pola na puste
     */
    void clear() {
        p = null;
    }
}
