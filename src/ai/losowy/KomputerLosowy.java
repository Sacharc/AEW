package ai.losowy;

import java.util.Random;

import ai.Komputer;
import model.Model;
import model.Wlasciciel;

public class KomputerLosowy extends Komputer{

    public KomputerLosowy(Model model, Wlasciciel wlasciciel) {
        super(model, wlasciciel);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void update() {
        while(ruch());
    }

    public boolean ruch() {
        Random rand = new Random();
        if (model.getPlansza().getLiczbaRuchow() != 0) {
            int r = rand.nextInt(model.getPlansza().getLiczbaRuchow());
            return model.wykonajRuchNr(r);
        }
        return false;
    }

}
