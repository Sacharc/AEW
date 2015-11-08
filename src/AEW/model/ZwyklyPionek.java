package AEW.model;

import java.util.ArrayList;

final class ZwyklyPionek extends Pionek {

    /**
     * konstruktor tworzacy pionek z wlascicielem
     * 
     * @param gracz1
     */
    public ZwyklyPionek(Wlasciciel gracz1) {
        wlasciciel = gracz1;
    }

    @Override
    boolean czyMozeRuszyc() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    void ruch(final Wspolrzedne w) {
        // TODO Auto-generated method stub
    }

    @Override
    int wykonajBicie(Kierunek k) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    ArrayList<Bicie> szukajBicia(Plansza p) {
        ArrayList<Bicie> bicia = new ArrayList<Bicie>();
        //TODO Sprawdzic bicia we wszystkich kierunkiach
        return bicia;
    }

}
