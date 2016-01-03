package ai.algorytmMinMax;

import model.Plansza;
import model.Ruch;
import model.Wlasciciel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by michto on 19.12.15.
 */
public class WezelGry {
    private List<WezelGry> dzieci;
    private Plansza plansza;
    private Ruch ruchDoWykonania;
    private Wlasciciel wlasciciel;

    public WezelGry(Plansza plansza, Ruch ruchDoWykonania, Wlasciciel wlasciciel) {
        this.dzieci = new LinkedList<WezelGry>();
        this.plansza = plansza;
        this.wlasciciel = wlasciciel;
        this.ruchDoWykonania = ruchDoWykonania;
    }

    public List<WezelGry> getDzieci() {
        return dzieci;
    }

    public void setDzieci(List<WezelGry> dzieci) {
        this.dzieci = dzieci;
    }

    public Plansza getPlansza() {
        return plansza;
    }

    public void setPlansza(Plansza plansza) {
        this.plansza = plansza;
    }

    public Ruch getRuchDoWykonania() {
        return ruchDoWykonania;
    }

    public void setRuchDoWykonania(Ruch ruchDoWykonania) {
        this.ruchDoWykonania = ruchDoWykonania;
    }

    public Wlasciciel getWlasciciel() {
        return wlasciciel;
    }

    public void setWlasciciel(Wlasciciel wlasciciel) {
        this.wlasciciel = wlasciciel;
    }

    public void rozdziel(Wlasciciel wlasciciel){
        for(WezelGry wezelGry : dzieci){
            wezelGry.rozdziel(wezelGry.wlasciciel.przeciwnyGracz());
        }

        //jesli nie ma dzieci to je zrob, jak sa to idz dalej w glab, 3 argument zapisuje ruch potrzebny do uzyskania takiego wezla
        if (dzieci.isEmpty()){
            plansza.czyscListyRuchowBic();
            plansza.sprawdzDostepneRuchy(wlasciciel);
            List<Ruch> ruchy = plansza.getMozliweRuchy();
            for(Ruch ruch : ruchy){
                //czy to bicie
                if(plansza.getListaBic().contains(ruch)){
                    this.rozdzielBicie(plansza, wlasciciel);
                }
                //czy zwykly ruch
                else if(plansza.getListaRuchu().contains(ruch)){
                    Plansza planszaPoRuchu = new Plansza(plansza, true);
                    planszaPoRuchu.wykonajRuch(ruch);
                    planszaPoRuchu.czyscListyRuchowBic();
//                    if(!isPierwszyRaz)
                        planszaPoRuchu.zmianaWspolrzednych();
                    dzieci.add(new WezelGry(planszaPoRuchu, ruch, wlasciciel));
                }

            }
        }
    }

    public double minmax (Wlasciciel wlasciciel){
        if(dzieci.isEmpty()){
            return this.plansza.getStatystyki().ocenaRoznica(wlasciciel);
        }

//        if(wlasciciel == KomputerMinMax.getWlasciciel()){ //czyli sterowany kompem minmax
            double max = Double.MIN_VALUE;
            for(WezelGry wezelGry : dzieci){
                max = Math.max(max, wezelGry.minmax(wlasciciel));
            }
            return max;
//        }
//        else {
//            double min = Double.MAX_VALUE;
//            for(WezelGry wezelGry : dzieci){
//                min = Math.min(min, wezelGry.minmax(wlasciciel.przeciwnyGracz()));
//            }
//            return min;
//        }
    }

    public WezelGry wybierzRuch(Wlasciciel wlasciciel){
        if(dzieci.isEmpty()){
            return null;
        }

        WezelGry najlepszyWezel = null;
        double najlepszyWynik;
        if(wlasciciel == KomputerMinMax.getWlasciciel())
            najlepszyWynik = Double.MIN_EXPONENT;
        else
            najlepszyWynik = Double.MAX_EXPONENT;
        for(WezelGry wezelGry : dzieci){
            double wartoscWezla = wezelGry.minmax(wlasciciel);
            System.out.println(wartoscWezla + " wezel to " + wezelGry);
            if(najlepszyWezel == null || this.czyNajlepszyWezel(wlasciciel, najlepszyWynik, wartoscWezla)){
                najlepszyWynik = wartoscWezla;
                najlepszyWezel = wezelGry;
            }
        }
        System.out.println();
        return najlepszyWezel; //zwraca wezel ktory nalezy wybrac
    }

    public boolean equalsByPlansza(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WezelGry wezelGry = (WezelGry) o;

        if (plansza != null ? !plansza.equalsByPola(wezelGry.plansza) : wezelGry.plansza != null) return false;
        return wlasciciel == wezelGry.wlasciciel;
    }

    private void rozdzielBicie(Plansza plansza, Wlasciciel wlasciciel){
        //rozpatrywana jest plansza posiadajaca mozliwe wielokrotne bicia
        List<Ruch> ruchy = plansza.getListaBic();
        if(ruchy.size() > 0){
            for(Ruch ruch : ruchy){
                Plansza planszaPoRuchu = new Plansza(this.plansza, true);
                planszaPoRuchu.wykonajRuch(ruch);
                planszaPoRuchu.czyscListyRuchowBic();
                planszaPoRuchu.sprawdzDostepneRuchy(wlasciciel);
                if(!(planszaPoRuchu.getListaBic() != null && planszaPoRuchu.getListaBic().size() > 0)) { //zmieniamy planszy
                    planszaPoRuchu.czyscListyRuchowBic();
                    planszaPoRuchu.zmianaWspolrzednych();
                }
                else{
                }
                this.dzieci.add(new WezelGry(planszaPoRuchu, ruch, wlasciciel));
            }
        }
        for(WezelGry wezelGry : this.dzieci){
            Plansza planszaKopia = new Plansza(plansza);
            wezelGry.rozdzielBicie(planszaKopia, wlasciciel);
        }
    }

    private boolean czyNajlepszyWezel(Wlasciciel wlasciciel, double maxWartosc, double wartoscWezla){
        if(wlasciciel == KomputerMinMax.getWlasciciel() && wartoscWezla > maxWartosc)
            return true;
//        else if(wartoscWezla*(-1) > maxWartosc*(-1))
//            return true;
        return false;
    }
}
