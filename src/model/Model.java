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
		this.plansza.wypisz();
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
	
    public boolean wykonajBicie(Ruch r) {
        int x1 = r.getX1();
        int x2 = r.getX2();
        int y1 = r.getY1();
        int y2 = r.getY2();
        
        getPlansza().zmienPolozeniePionka(x1, y1, x2,y2);
        if(x1<x2 && y1<y2){
            getPlansza().getPole(x2-1, y2-1).removePionek();
        }
        else if(x1>x2 && y1<y2){
            getPlansza().getPole(x2+1, y2-1).removePionek();
        }
        else if(x1<x2 && y1>y2){
            getPlansza().getPole(x2-1, y2+1).removePionek();
        }
        else{
            getPlansza().getPole(x2+1, y2+1).removePionek();
        }
        czyscListy();
        getPlansza().sprawdzBiciaRuchyPionka(getPlansza().getPole(x2, y2));
        if(getPlansza().getListaBic().size() > 0){
            return true;
        }
        return false;
    }
	
	public void wykonajRuch(Ruch ruch) {
	    int x1 = ruch.getX1();
        int x2 = ruch.getX2();
        int y1 = ruch.getY1();
        int y2 = ruch.getY2();
        getPlansza().zmienPolozeniePionka(x1, y1, x2,y2);
	}
	
	public boolean wykonajRuchNr(int nr) {
	    if(!plansza.getListaBic().isEmpty())
	        return wykonajBicie(plansza.getListaBic().get(nr));
	    if(!plansza.getListaRuchu().isEmpty())
	        wykonajRuch(plansza.getListaRuchu().get(nr));
	    return false;
	}
};
