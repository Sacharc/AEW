package model;


public class Model{


    /**zmienna reprezentujaca plansze z pionkami*/
    private Plansza plansza;

    /**
     * @return the plansza
     */
    public Plansza getPlansza()
    {
        return plansza;
    }

    /**
     * konstruktor inicjujacy plansze pionkami.
     */
    public Model(){
        this.plansza = new Plansza(Wlasciciel.gracz1,Wlasciciel.gracz2);
        plansza = new Plansza(plansza);
    }


    public void sprawdzDostepneRuchy(Wlasciciel aktualnyGracz)
    {
        this.plansza.sprawdzDostepneRuchy(aktualnyGracz);
    }

    public void zmianaWspolrzednych()
    {
        this.getPlansza().zmianaWspolrzednych();
    }

    public void czyscListy()
    {
        getPlansza().czyscListyRuchowBic();
    }

    public int liczbaRuchow() {
        return plansza.getLiczbaRuchow();
    }

    public boolean wykonajRuchNr(int nr) {
        return plansza.wykonajRuchNr(nr);
    }

    public boolean wykonajRuch(Ruch ruch){
        return plansza.wykonajRuch(ruch);
    }
};
