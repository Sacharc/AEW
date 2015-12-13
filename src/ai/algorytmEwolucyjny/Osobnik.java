package ai.algorytmEwolucyjny;

import java.util.ArrayList;
import java.util.Random;

import model.Model;
import model.Plansza;

class Osobnik implements Comparable<Osobnik>{
    //double ruchy[];
    ArrayList<Double> ruchy;
    /** Liczba przewidywanych ruchow */
    static int liczbaGenow = 100;
    /** Ile procent podlega mutacji */
    static int prawdopodobienstwoMutacji = 2;
    /** O ile procent zmienia siê cecha*/
    static double wspolczynnikMutacji = 1.05;

    Model model;

    private int ocena;

    public Osobnik(Model model) {
        this.ruchy = new ArrayList<Double>(liczbaGenow);
        this.model = model;
        generujLosowo();
    }

    public Osobnik(Osobnik m, Osobnik t) {
        this.model = m.model;
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
            ruchy.set(i, rand.nextDouble());
    }

    int getOcena() {
        return ocena;
    }

    public void ocenOsobnika() {
        Plansza plansza = new Plansza(model.getPlansza());
        for(Double gen : ruchy) {
            if(plansza.sprawdzCzyKoniecGry()) {
                if (plansza.getLiczbaRuchow() == 0)
                    break;
                //TODO
            }
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
