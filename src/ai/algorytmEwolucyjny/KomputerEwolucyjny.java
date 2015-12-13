package ai.algorytmEwolucyjny;

import ai.Komputer;
import model.Model;

public class KomputerEwolucyjny extends Komputer {

    /** Liczba symulowanych tur */
    static int dlugoscSymulacji = 500;

    private Populacja populacja;

    public KomputerEwolucyjny(Model model) {
        super(model);
        populacja = new Populacja(model);

        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean update() {

        // TODO Auto-generated method stub

        for(int i = 0; i < dlugoscSymulacji; i++) {
            populacja.ocen();
            populacja.rozmnazaj();
        }
        int nrRuchu = model.liczbaRuchow();
        Double ruch = populacja.najlepszyRuch() * nrRuchu;
        model.wykonajRuchNr(ruch.intValue());
        return false;
    }
}
