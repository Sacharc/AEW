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
    static int liczbaGenow = 10;
    /** Ile procent podlega mutacji */
    static int prawdopodobienstwoMutacji = 3;
    /** O ile procent zmienia siê cecha*/
    static double wspolczynnikMutacji = 1.005;

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
                if(rand.nextBoolean()) {
                    if(inc < 1)
                        value = inc;
                    else
                        value = 0.9999; //opcje z MINFLOAT snie dzialaja
                }
                else
                    value = (2 - wspolczynnikMutacji) * av;
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

    public void ocenOsobnika() {
        Plansza plansza = new Plansza(model.getPlansza());
        Wlasciciel aktualnyGracz = wlasciciel;
        int tura = 0;
        ocena = 0;
        for(int i = 0; i < liczbaGenow; i++) {
            if(plansza.sprawdzCzyKoniecGry()) {
                if(aktualnyGracz == Wlasciciel.gracz1) {
                    plansza.sprawdzDostepneRuchy(aktualnyGracz);
                    if (plansza.getLiczbaRuchow() == 0)
                        break;
                    while(i < liczbaGenow && plansza.wykonajRuchNr(ruchy.get(i))) {
                        i++;
                    }
                    aktualnyGracz = Wlasciciel.gracz2;
                }
                else {
                    plansza.zmianaWspolrzednych();
                    plansza.sprawdzDostepneRuchy(aktualnyGracz);
                    if (plansza.getLiczbaRuchow() == 0) {
                        plansza.zmianaWspolrzednych();
                        break;
                    }
                    while(i < liczbaGenow && plansza.wykonajRuchNr(ruchy.get(i))) {
                        i++;
                    }
                    plansza.zmianaWspolrzednych();
                    aktualnyGracz = Wlasciciel.gracz1;
                }


                Statystyki statystyki = plansza.getStatystyki();
                int ocenaGracza = statystyki.getOcenaGracza(wlasciciel);
                int ocenaPrzeciwnika = statystyki.getOcenaPrzeciwnika(wlasciciel);
                /*                System.out.println("ocenaGracza " + ocenaGracza);
                System.out.println("ocenaPrzeciwnika " + ocenaPrzeciwnika);*/
                ocena += (ocenaGracza - ocenaPrzeciwnika)*(liczbaGenow - tura);

                plansza.czyscListyRuchowBic();
            }

            /*
            Statystyki statystyki = plansza.getStatystyki();
            int ocenaGracza = statystyki.getOcenaGracza(wlasciciel);
            int ocenaPrzeciwnika = statystyki.getOcenaPrzeciwnika(wlasciciel);

            System.out.println("ocenaGracza " + ocenaGracza);
            System.out.println("ocenaPrzeciwnika " + ocenaPrzeciwnika);

            ocena += (ocenaGracza - ocenaPrzeciwnika)*(liczbaGenow - tura);
             */

            /*
            for(Double gen : ruchy) {
                if(plansza.sprawdzCzyKoniecGry()) {
                    tura++;

                    if(aktualnyGracz == Wlasciciel.gracz1) {
                        plansza.sprawdzDostepneRuchy(aktualnyGracz);
                        if (plansza.getLiczbaRuchow() == 0)
                            break;


                        if(!plansza.wykonajRuchNr(gen))
                            aktualnyGracz = Wlasciciel.gracz2;
                    }
                    else {
                        plansza.zmianaWspolrzednych();
                        plansza.sprawdzDostepneRuchy(aktualnyGracz);
                        if (plansza.getLiczbaRuchow() == 0) {
                            plansza.zmianaWspolrzednych();
                            break;
                        }
                        if(!plansza.wykonajRuchNr(gen))
                            aktualnyGracz = Wlasciciel.gracz1;
                        plansza.zmianaWspolrzednych();

                    }

                    plansza.czyscListyRuchowBic();
                }
            }*/

            /*            if(plansza.sprawdzCzyKoniecGry()) {
                if(plansza.ktoWygral() == wlasciciel)
                    ocena = 200 - tura;
                else
                    ocena = -200 + tura;
                return;
            }

            Statystyki statystyki = plansza.getStatystyki();
            int ocenaGracza = statystyki.getOcenaGracza(wlasciciel);
            int ocenaPrzeciwnika = statystyki.getOcenaPrzeciwnika(wlasciciel);
            ocena = 2 * ocenaGracza - ocenaPrzeciwnika;*/

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
