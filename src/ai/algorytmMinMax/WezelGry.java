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
    private boolean czyJestDoWykonaniaNastepneBicie = false; //czy jest do wykonania ktores z kolei bicie, tj. drugie, trzecie, itd.

    public WezelGry(Plansza plansza, Ruch ruchDoWykonania, Wlasciciel wlasciciel) {
        this.dzieci = new LinkedList<WezelGry>();
        this.plansza = plansza;
        this.wlasciciel = wlasciciel;
        this.ruchDoWykonania = ruchDoWykonania;
    }

    public WezelGry(Plansza plansza, Ruch ruchDoWykonania, Wlasciciel wlasciciel, boolean czyJestDoWykonaniaNastepneBicie) {
        this.dzieci = new LinkedList<WezelGry>();
        this.plansza = plansza;
        this.wlasciciel = wlasciciel;
        this.ruchDoWykonania = ruchDoWykonania;
        this.czyJestDoWykonaniaNastepneBicie = czyJestDoWykonaniaNastepneBicie;
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

    public boolean isCzyJestDoWykonaniaNastepneBicie() {
        return czyJestDoWykonaniaNastepneBicie;
    }

    public void setCzyJestDoWykonaniaNastepneBicie(boolean czyJestDoWykonaniaNastepneBicie) {
        this.czyJestDoWykonaniaNastepneBicie = czyJestDoWykonaniaNastepneBicie;
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
            plansza.sprawdzDostepneRuchy(this.wlasciciel);
            List<Ruch> ruchy = plansza.getMozliweRuchy();
            for(Ruch ruch : ruchy){
                //czy to bicie,
                if(plansza.getListaBic().contains(ruch)){
                    this.rozdzielBicie(plansza, this.wlasciciel);
                }
                //czy zwykly ruch
                else if(plansza.getListaRuchu().contains(ruch)){
                    Plansza planszaPoRuchu = new Plansza(plansza, true);
                    planszaPoRuchu.wykonajRuch(ruch);
                    planszaPoRuchu.czyscListyRuchowBic();
//                    if(!isPierwszyRaz)
                        planszaPoRuchu.zmianaWspolrzednych();
                    dzieci.add(new WezelGry(planszaPoRuchu, ruch, this.wlasciciel.przeciwnyGracz()));
                }
            }
        }
    }

    //TODO root(max) -> min -> max -> min itd ... koncze na wezlach bez dzieci
    public double minmax (){
        if(dzieci.isEmpty()){
            return this.plansza.getStatystyki().ocenaRoznica(KomputerMinMax.getWlasciciel());
        }

        if(this.wlasciciel == KomputerMinMax.getWlasciciel()){ //czyli sterowany kompem minmax
            double max = Double.MIN_VALUE;
            for(WezelGry wezelGry : dzieci){
                max = Math.max(max, wezelGry.minmax());
            }
            return max;
        }
        else {
            double min = Double.MAX_VALUE;
            for(WezelGry wezelGry : dzieci){
                min = Math.min(min, wezelGry.minmax());
            }
            return min;
        }
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
            double wartoscWezla = wezelGry.minmax();
            if(najlepszyWezel == null || this.czyNajlepszyWezel(wlasciciel, najlepszyWynik, wartoscWezla)){
                najlepszyWynik = wartoscWezla;
                najlepszyWezel = wezelGry;
            }
        }
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
                Plansza planszaPoRuchu = new Plansza(plansza, true);
                boolean dalejBicia = planszaPoRuchu.wykonajRuch(ruch);
                if(dalejBicia) { //sa jakies dalsze bicia
                    planszaPoRuchu.czyscListyRuchowBic();
                    this.dzieci.add(new WezelGry(planszaPoRuchu, ruch, wlasciciel, true));

                }
                else{ //nie ma juz nastepnych bic - kolej przeciwnika
                    planszaPoRuchu.czyscListyRuchowBic();
                    planszaPoRuchu.zmianaWspolrzednych();
                    this.dzieci.add(new WezelGry(planszaPoRuchu, ruch, wlasciciel.przeciwnyGracz()));
                }
            }
        }
        for(WezelGry wezelGry : this.dzieci) {
            if (wezelGry.czyJestDoWykonaniaNastepneBicie){
                Plansza planszaKopia = new Plansza(wezelGry.plansza, true);
                planszaKopia.sprawdzDostepneRuchy(wlasciciel);
                wezelGry.rozdzielBicie(planszaKopia, wlasciciel);
            }
        }
    }

    private boolean czyNajlepszyWezel(Wlasciciel wlasciciel, double maxWartosc, double wartoscWezla){
        if(wlasciciel == KomputerMinMax.getWlasciciel() && wartoscWezla > maxWartosc)
            return true;
        return false;
    }
}
