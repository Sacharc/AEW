package AEW.model;

/**
 * Klasa reprezentuje ruch.
 */
public class Ruch {
    final Wspolrzedne punktWejsciowy;
    final Wspolrzedne punktWyjsciowy;
    
    /**
     * @param punktWejsciowy
     * @param punktWyjsciowy
     */
    public Ruch(Wspolrzedne punktWejsciowy, Wspolrzedne punktWyjsciowy) {
        this.punktWejsciowy = punktWejsciowy;
        this.punktWyjsciowy = punktWyjsciowy;
    }


    Wspolrzedne getPunktWejsciowy() {
        return punktWejsciowy;
    }


    Wspolrzedne getPunktWyjsciowy() {
        return punktWyjsciowy;
    }
}
