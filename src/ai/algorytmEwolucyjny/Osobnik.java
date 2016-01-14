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
    static int liczbaGenow = 12;
    /** Ile procent podlega mutacji */
    static int prawdopodobienstwoMutacji = 5;
    /** O ile procent zmienia si cecha*/
    static double wspolczynnikMutacji = 1.1;

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
        for(int i = 0; i < liczbaGenow; i++) {
            Double av = (m.ruchy.get(i) + t.ruchy.get(i))/2;
            Double inc = av * wspolczynnikMutacji;
            Double value = av;

            if(rand.nextInt(100) < prawdopodobienstwoMutacji) {
                /*
                if(rand.nextBoolean()) {
                    if(inc < 1)
                        value = inc;
                    else
                        value = 0.9999; //opcje z MINFLOAT snie dzialaja
                }
                else
                    value = (2 - wspolczynnikMutacji) * av;*/
                value = rand.nextDouble();
            }
            this.ruchy.add(value);
        }
    }
    //TODO
    private void generujLosowo() {
        Random rand = new Random();
        for(int i = 0; i < liczbaGenow; i++)
            ruchy.add(new Double(rand.nextDouble()));
    }

    int getOcena() {
        return ocena;
    }

    static void wykonajRuchGracza(Plansza plansza, double gen) {

        double nextGen = gen;

        do {
            gen = nextGen;
            int liczbaRuchow = plansza.getLiczbaRuchow();
            double t = gen * liczbaRuchow;
            nextGen = t - (int)t;
        } while (plansza.wykonajRuchNr(gen));
    }

    public void ocenOsobnika() {
        Plansza plansza = new Plansza(model.getPlansza());
        Wlasciciel aktualnyGracz = wlasciciel;
        int tura = 0;
        ocena = 0;

        for(Double gen : ruchy) {
            if(plansza.sprawdzCzyKoniecGry()) {
                if(aktualnyGracz != wlasciciel)
                    plansza.zmianaWspolrzednych();
                
                plansza.sprawdzDostepneRuchy(aktualnyGracz);
                if (plansza.getLiczbaRuchow() == 0) {
                    if(aktualnyGracz != wlasciciel)
                        plansza.zmianaWspolrzednych();
                    break;
                }

                wykonajRuchGracza(plansza, gen);

                if(aktualnyGracz != wlasciciel)
                    plansza.zmianaWspolrzednych();
/*                else {
                    Statystyki statystyki = plansza.getStatystyki();
                    int ocenaGracza = statystyki.getOcenaGracza(wlasciciel);
                    int ocenaPrzeciwnika = statystyki.getOcenaPrzeciwnika(wlasciciel);
                    ocena += statystyki.ocenaRoznica(wlasciciel) * (liczbaGenow - tura);
                }*/
                
                aktualnyGracz = aktualnyGracz.przeciwnyGracz();
                plansza.czyscListyRuchowBic();
                tura++;
            }
            
            Statystyki statystyki = plansza.getStatystyki();
            ocena = statystyki.ocenaRoznica(wlasciciel);

/*            Statystyki statystyki = plansza.getStatystyki();
            int ocenaGracza = statystyki.getOcenaGracza(wlasciciel);
            int ocenaPrzeciwnika = statystyki.getOcenaPrzeciwnika(wlasciciel);
            ocena = ocenaGracza - 2 *ocenaPrzeciwnika;*/
        }
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
        ruchy.remove(0);
        dodajLosowy();
    }

    private void dodajLosowy() {
        Random rand = new Random();
        ruchy.add(rand.nextDouble());
    }

    /**
     * @return Pierwszy ruch w sciezce
     */
    public Double najlepszyRuch() {
        Double t = ruchy.get(0);
        ruchy.remove(0);
        dodajLosowy();
        return t;
    }

}