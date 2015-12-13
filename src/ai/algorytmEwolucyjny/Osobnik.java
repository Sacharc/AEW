package ai.algorytmEwolucyjny;

import java.util.ArrayList;
import java.util.Random;

import model.Model;
import model.Plansza;
import model.Statystyki;
import model.Wlasciciel;

class Osobnik implements Comparable<Osobnik>{
    ArrayList<Double> ruchy;
    /** Liczba przewidywanych ruchow */
    static int liczbaGenow = 100;
    /** Ile procent podlega mutacji */
    static int prawdopodobienstwoMutacji = 2;
    /** O ile procent zmienia siê cecha*/
    static double wspolczynnikMutacji = 1.05;

    Model model;

    Wlasciciel wlasciciel;

    private int ocena;

    public Osobnik(Model model, Wlasciciel wlasciciel) {
        this.ruchy = new ArrayList<Double>(liczbaGenow);
        this.model = model;
        this.wlasciciel = wlasciciel;
        generujLosowo();
    }

    public Osobnik(Osobnik m, Osobnik t) {
        this.model = m.model;
        this.wlasciciel = m.wlasciciel;
        this.ruchy = new ArrayList<Double>(liczbaGenow);
        Random rand = new Random();
        for(int i = 0; i < ruchy.size(); i++) {
            Double av = (m.ruchy.get(i) + t.ruchy.get(i))/2;
            this.ruchy.add(av);
            if(rand.nextInt(100) < prawdopodobienstwoMutacji) {
                if(rand.nextBoolean()) {
                    Double inc = this.ruchy.get(i) * wspolczynnikMutacji;
                    if(inc > 1)
                        this.ruchy.set(i, inc);
                    else
                        this.ruchy.set(i, (double) 1);
                }
                else
                    this.ruchy.set(i, 2 - wspolczynnikMutacji);
            }
        }
    }

    private void generujLosowo() {
        Random rand = new Random();
        for(int i = 0; i < ruchy.size(); i++)
            ruchy.add(new Double(rand.nextDouble()));
    }

    int getOcena() {
        return ocena;
    }

    public void ocenOsobnika() {
        Plansza plansza = new Plansza(model.getPlansza());
        Wlasciciel aktualnyGracz = wlasciciel;
        int tura = 0;
        for(Double gen : ruchy) {
            if(plansza.sprawdzCzyKoniecGry()) {
                tura++;
                if (plansza.getLiczbaRuchow() == 0)
                    break;

                switch(aktualnyGracz) {
                case gracz1:
                    plansza.sprawdzDostepneRuchy(aktualnyGracz);

                    if(!plansza.wykonajRuchNr((int)(gen * plansza.getLiczbaRuchow())))
                        aktualnyGracz = Wlasciciel.gracz2;
                    break;
                case gracz2:
                    plansza.zmianaWspolrzednych();
                    plansza.sprawdzDostepneRuchy(aktualnyGracz);
                    if(!plansza.wykonajRuchNr((int)(gen * plansza.getLiczbaRuchow())))
                        aktualnyGracz = Wlasciciel.gracz1;
                    plansza.zmianaWspolrzednych();
                    break;
                }
                plansza.czyscListyRuchowBic();
            }
        }
        if(plansza.sprawdzCzyKoniecGry()) {
            ocena = 200 - tura;
            return;
        }
        Statystyki statystyki = plansza.getStatystyki();
        int ocenaGracza = statystyki.getOcenaGracza(wlasciciel);
        int ocenaPrzeciwnika = statystyki.getOcenaPrzeciwnika(wlasciciel);
        ocena = 2 * ocenaGracza - ocenaPrzeciwnika;
    }


    @Override
    public int compareTo(Osobnik osobnikj) {
        if(this.ocena < osobnikj.ocena)
            return 1;
        if(this.ocena > osobnikj.ocena)
            return -1;
        return 0;
    }

    /**
     *  Usuwa pierwszy ruch
     */
    public void usunPierwszy() {
        Random rand = new Random();
        ruchy.remove(0);
        ruchy.add(rand.nextDouble());
    }

    /**
     * @return Pierwszy ruch w sciezce
     */
    public Double najlepszyRuch() {
        return ruchy.get(0);
    }

}
