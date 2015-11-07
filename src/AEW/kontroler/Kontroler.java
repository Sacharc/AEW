package AEW.kontroler;

import java.util.concurrent.BlockingQueue;

import AEW.events.Event;
import AEW.model.Model;
import AEW.widok.Widok;

public class Kontroler {
    private Model model;
    private Widok widok;
    private BlockingQueue<Event> kolejka;

    /**
     * 
     * @param model
     * @param widok
     * @param kolejka
     */
    public Kontroler(Model model, Widok widok, BlockingQueue<Event> kolejka) {
        // TODO Strategia
        this.model = model;
        this.widok = widok;
        this.kolejka = kolejka;

    }

    /**
     * Petla glowna kontrolera
     */
    public void start() {
        // TODO
    }

}
