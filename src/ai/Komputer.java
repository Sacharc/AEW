package ai;

import model.Model;
import model.Wlasciciel;

public abstract class Komputer {
    protected Model model;

    protected Wlasciciel wlasciciel;

    /**
     * Wykonuje ruch lub bicie
     */
    public abstract void update();
    
    public abstract void identyfikuj();

    public Komputer(Model model, Wlasciciel wlasciciel) {
        this.model = model;
        this.wlasciciel = wlasciciel;
    }
}
