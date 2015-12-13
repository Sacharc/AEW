package ai.algorytmEwolucyjny;

import java.util.ArrayList;
import java.util.Comparator;

import model.Model;

/** ZOACZYC CZY SORTUJA SIE W DOBREJ KOLEJNOSCI*/

public class Populacja {
    /** Liczba osobnikow w populacji */
    static int liczbaOsobnikow = 200;

    Model model;

    private ArrayList<Osobnik> populacja;

    public Populacja(Model model) {
        this.model = model;
        this.populacja = new ArrayList<Osobnik>(liczbaOsobnikow);
        for(int i = 0; i < liczbaOsobnikow; i++)
            populacja.add(new Osobnik(model));
    }

    public void ocen() {
        for(Osobnik osobnik : populacja) {
            osobnik.ocenOsobnika();
        }
        populacja.sort(new Comparator<Osobnik>() {
            @Override
            public int compare(Osobnik o1, Osobnik o2) {
                return o1.compareTo(o2);
            }
        });
    }

    /**
     *  Tworzy nowa populacje zostawiajac najlepsze ze starej i na ich podtawie pochodne
     */
    public void rozmnazaj() {
        usunGorszaPolowe();
        for(int i = 0; i < liczbaOsobnikow/4; i+=2) {
            populacja.add(new Osobnik(populacja.get(i), populacja.get(i + 1)));
            populacja.add(new Osobnik(populacja.get(i), populacja.get(liczbaOsobnikow/2 - i)));
        }
    }

    /**
     *  Usuwa gorsza polowe populacji
     */
    private void usunGorszaPolowe() {
        while(populacja.size() > liczbaOsobnikow/2)
            populacja.remove(populacja.size()-1);
    }

    /**
     * Zostawia najlepsza polowe druga jest losowa
     */
    public void generujLosowe() {
        usunGorszaPolowe();
        for(int i = 0; i < liczbaOsobnikow/2; i++)
            populacja.add(new Osobnik(model));
    }


    /**
     *  Usuwa pierwszy element kazdego osbnika
     */
    public void usunPierwszy() {
        for(Osobnik osobnik : populacja)
            osobnik.usunPierwszy();
    }

    public Double najlepszyRuch() {
        return populacja.get(0).najlepszyRuch();
    }

}
