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
    boolean ruch(Wspolrzedne punktDocelowy, Plansza plansza) {
        if(plansza.czyWolne(punktDocelowy)) {
            // Sprawdzam czy nastepuje przesuniecie o wektor jednostkowy jezeli tak to wykonuje ruch
            Wspolrzedne wektor = w.odejmij(punktDocelowy);
            for(Kierunek kierunek : Kierunek.values())
                if(wektor.equals(Wspolrzedne.wektorJednostkowy(kierunek))) {
                    plansza.getPole(w).clear();
                    plansza.getPole(punktDocelowy).setP(this);
                    setW(punktDocelowy);
                    return true;
                }
        }
        return false;
    }

    @Override
    int wykonajBicie(Kierunek k) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    ArrayList<Bicie> szukajBicia(Plansza p) {
        ArrayList<Bicie> bicia = new ArrayList<Bicie>();
        //Sprawdza bicia we wszystkich kierunkiach
        for(Kierunek kierunek : Kierunek.values()) {
            Wspolrzedne wektor = Wspolrzedne.wektorJednostkowy(kierunek);
            Wspolrzedne przesuniete = w.dodaj(wektor);
            if(przesuniete.czyPoprawne() && !p.getPole(przesuniete).isEmpty())
                if(p.getPole(przesuniete).getP().getW().equals(p.getPole(w).getP().getW())) {
                    przesuniete.dodaj(wektor);
                    if(przesuniete.czyPoprawne() && p.getPole(przesuniete).isEmpty())
                        bicia.add(new Bicie(kierunek, w));
                }
        }
        return bicia;
    }
}