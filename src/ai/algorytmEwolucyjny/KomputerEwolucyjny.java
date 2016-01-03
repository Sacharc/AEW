package ai.algorytmEwolucyjny;

import ai.Komputer;
import model.Model;
import model.Wlasciciel;

public class KomputerEwolucyjny extends Komputer {

    /** Liczba symulowanych tur */
    static int dlugoscSymulacji = 50;

    private Populacja populacja;

    public KomputerEwolucyjny(Model model, Wlasciciel wlasciciel) {
        super(model, wlasciciel);
        populacja = new Populacja(model, wlasciciel);
    }

    @Override
    public void update() {
        populacja = new Populacja(model, wlasciciel);
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
        Osobnik.wykonajRuchGracza(model.getPlansza(), populacja.najlepszyRuch());

        return false;
    }

}