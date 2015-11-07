package AEW;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import AEW.events.Event;
import AEW.kontroler.Kontroler;
import AEW.model.Model;
import AEW.model.Plansza;
import AEW.model.Pole;
import AEW.model.Wlasciciel;
import AEW.widok.PlanszaPanel;
import AEW.widok.PolePanel;
import AEW.widok.Widok;

public class Warcaby {

    public static void main(String[] args) {
        Wlasciciel gracz1 = Wlasciciel.gracz1;
        Wlasciciel gracz2 = Wlasciciel.komputer;
        BlockingQueue<Event> kolejka = new LinkedBlockingQueue<Event>();
        Model model = new Model(gracz1, gracz2);
        Widok widok = new Widok(kolejka, model.getPlansza());
        Kontroler kontroler = new Kontroler(model, widok, kolejka);
        kontroler.start();
    }

}
