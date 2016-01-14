package ai.algorytmMinMax;

import ai.Komputer;
import model.*;

import java.util.Iterator;

public class KomputerMinMax extends Komputer {

    private WezelGry aktualnyWezelGry;
    private static Wlasciciel wlasciciel2;
    private static boolean poInicjalizacjiRozdzielenia = false;
    private final static int liczbaPoczatkowychPoziomow = 4;
    private final static int liczbaAktualizowanychPoziomow = 8;

    public KomputerMinMax(Model model, Wlasciciel wlasciciel) {
        super(model, wlasciciel);
        this.wlasciciel2 = wlasciciel;
    }

    public static Wlasciciel getWlasciciel(){
        return wlasciciel2;
    }

    @Override
    public void update() {
        this.aktualizujRozdzielanie();
        aktualnyWezelGry = aktualnyWezelGry.wybierzRuch(wlasciciel);
        while (ruch()){
            aktualnyWezelGry = aktualnyWezelGry.wybierzRuch(wlasciciel);
        }
    }

    public boolean ruch() {
        if(aktualnyWezelGry.getRuchDoWykonania() != null){
        Ruch najlepszyRuch = aktualnyWezelGry.getRuchDoWykonania();
            boolean czyJeszczeBicie = model.wykonajRuch(najlepszyRuch);
            return czyJeszczeBicie;
        }
        return false;
    }

//    private void aktualizujRozdzielanie() {
//        if (this.poInicjalizacjiRozdzielenia) {
//            aktualnyWezelGry = this.szukajAktualnegoWezla(aktualnyWezelGry); //szuka posrod dzieci ostatniego aktualnego wezla
//            for (int i = 0; i < liczbaAktualizowanychPoziomow; ++i) {
//                aktualnyWezelGry.rozdziel(wlasciciel);
//            }
//        }
//        else {
//            aktualnyWezelGry = new WezelGry(model.getPlansza(), null, wlasciciel);
//            for(int i = 0; i < liczbaPoczatkowychPoziomow; ++i){
//                aktualnyWezelGry.rozdziel(wlasciciel);
//            }
//            this.poInicjalizacjiRozdzielenia = true;
//        }
//    }

    private void aktualizujRozdzielanie() {
        aktualnyWezelGry = new WezelGry(model.getPlansza(), null, wlasciciel);
        for(int i = 0; i < liczbaPoczatkowychPoziomow; ++i){
            aktualnyWezelGry.rozdziel(wlasciciel);
        }
        this.poInicjalizacjiRozdzielenia = true;
    }

    @Override
    public void identyfikuj() {
        System.out.print("KomputerMinMax ");
        
    }
}
