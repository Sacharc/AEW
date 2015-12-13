package ai.algorytmEwolucyjny;

import ai.Komputer;
import model.Model;
import model.Wlasciciel;

public class KomputerEwolucyjny extends Komputer {

    /** Liczba symulowanych tur */
    static int dlugoscSymulacji = 500;

    private Populacja populacja;

    public KomputerEwolucyjny(Model model, Wlasciciel wlasciciel) {
        super(model, wlasciciel);
        populacja = new Populacja(model, wlasciciel);
    }

    @Override
    public void update() {

        populacja.generujLosowe();
        populacja.usunPierwszy();
        // Dorzucam polowe losowych dla roznorodnosci

        while(ruch())
            populacja.usunPierwszy();
    }

    /**
     *
     */
    private boolean ruch() {
        for(int i = 0; i < dlugoscSymulacji; i++) {
            populacja.ocen();
            populacja.rozmnazaj();
        }
        int nrRuchu = model.liczbaRuchow();
        Double ruch = populacja.najlepszyRuch() * nrRuchu;
        return model.wykonajRuchNr(ruch.intValue());
    }

}
