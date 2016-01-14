package ai.algorytmEwolucyjny;

import ai.Komputer;
import model.Model;
import model.Wlasciciel;

public class KomputerEwolucyjny extends Komputer {

    /** Liczba symulowanych tur */
    static int dlugoscSymulacji = 2;

    private Populacja populacja;

    public KomputerEwolucyjny(Model model, Wlasciciel wlasciciel) {
        super(model, wlasciciel);
        populacja = new Populacja(model, wlasciciel);
    }

    @Override
    public void update() {
        populacja = new Populacja(model, wlasciciel);
        ruch();
        
        
        
        
        try {
            Thread.sleep(5000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("EWO");
        
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