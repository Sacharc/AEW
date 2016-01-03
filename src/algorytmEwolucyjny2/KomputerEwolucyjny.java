package algorytmEwolucyjny2;

import ai.Komputer;
import model.Model;
import model.Wlasciciel;

public class KomputerEwolucyjny extends Komputer {

    /** Liczba symulowanych tur */
    static int dlugoscSymulacji = 20;

    /** HACK*/
    static boolean czyWygenerowano = false;

    private Populacja populacja;

    public KomputerEwolucyjny(Model model, Wlasciciel wlasciciel) {
        super(model, wlasciciel);
        populacja = new Populacja(model, wlasciciel);
    }

    @Override
    public void update() {
        if(!czyWygenerowano)
            populacja = new Populacja(model, wlasciciel);
        ruch();
    }

    /**
     *
     */
    private boolean ruch() {
        if(!czyWygenerowano) {
            for(int i = 0; i < dlugoscSymulacji; i++) {
                populacja.rozmnazaj();
                populacja.ocen();
            }
            czyWygenerowano = true;
        }

        /*Double ruch;
        do{
            int nrRuchu = model.liczbaRuchow();
            ruch = populacja.najlepszyRuch() * nrRuchu;
        } while (model.wykonajRuchNr(ruch.intValue()));
        return false;*/
        Osobnik.wykonajRuchGracza(model.getPlansza(), populacja.najlepszyRuch());
        return false;

    }

}
