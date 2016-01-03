package ai.algorytmEwolucyjny;

import ai.Komputer;
import model.Model;
import model.Wlasciciel;

public class KomputerEwolucyjny extends Komputer {

    /** Liczba symulowanych tur */
    static int dlugoscSymulacji = 20;

    private Populacja populacja;

    public KomputerEwolucyjny(Model model, Wlasciciel wlasciciel) {
        super(model, wlasciciel);
        populacja = new Populacja(model, wlasciciel);
    }

    @Override
    public void update() {

        /*populacja.usunPierwszy();
        populacja.usunPierwszy();
        populacja.generujLosowe();*/

        populacja = new Populacja(model, wlasciciel);

        // Dorzucam polowe losowych dla roznorodnosci

        /*while(ruch())
            populacja.usunPierwszy();*/
        ruch();
    }

    /**
     *
     */
    private boolean ruch() {
        for(int i = 0; i < dlugoscSymulacji; i++) {
            populacja.rozmnazaj();
            populacja.ocen();
        }
        Double ruch;
        do{
            int nrRuchu = model.liczbaRuchow();
            ruch = populacja.najlepszyRuch() * nrRuchu;
        } while (model.wykonajRuchNr(ruch.intValue()));
        return false;
    }

}
