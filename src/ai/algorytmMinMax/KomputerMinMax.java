package ai.algorytmMinMax;

import ai.Komputer;
import model.*;

import java.util.Iterator;

public class KomputerMinMax extends Komputer {

    private WezelGry aktualnyWezelGry;
    private static boolean poInicjalizacjiRozdzielenia = false;
    private final static int liczbaPoczatkowychPoziomow = 6;
    private final static int liczbaAktualizowanychPoziomow = 2;

    public KomputerMinMax(Model model, Wlasciciel wlasciciel) {
        super(model, wlasciciel);
    }

    public static Wlasciciel getWlasciciel(){
        return wlasciciel;
    }

    @Override
    public void update() {
        aktualnyWezelGry = this.szukajAktualnegoWezla(aktualnyWezelGry); //szuka posrod dzieci ostatniego aktualnego wezla
        this.aktualizujRozdzielanie();
        aktualnyWezelGry = aktualnyWezelGry.wybierzRuch(wlasciciel);
        while (ruch());
    }

    public boolean ruch() {
        Ruch najlepszyRuch = aktualnyWezelGry.getRuchDoWykonania();
        return model.wykonajRuch(najlepszyRuch);
    }

    private void aktualizujRozdzielanie() {
        if (this.poInicjalizacjiRozdzielenia) {
            for (int i = 0; i < liczbaAktualizowanychPoziomow; ++i) {
                aktualnyWezelGry.rozdziel(wlasciciel);
            }
        }
        else {
            aktualnyWezelGry = new WezelGry(model.getPlansza(), null, wlasciciel);
            for(int i = 0; i < liczbaPoczatkowychPoziomow; ++i){
                aktualnyWezelGry.rozdziel(wlasciciel);
            }
            this.poInicjalizacjiRozdzielenia = true;
        }
    }

    private WezelGry szukajAktualnegoWezla(WezelGry wezelGry){
        for(WezelGry wezelDziecko : wezelGry.getDzieci()){
            if(wezelDziecko.equalsByPlansza(model.getPlansza()))
                return wezelDziecko;
            if(wezelDziecko.getDzieci() != null && wezelDziecko.getDzieci().size() > 0 && wezelDziecko.getDzieci().get(0).getWlasciciel() == wlasciciel){
                szukajAktualnegoWezla(wezelDziecko); //jesli to jest wielokrotne bicie i nastepny node jest tego samego wlasciciela
            }
        }
        return null;
    }
}
