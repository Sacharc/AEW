package AEW.model;

import java.util.ArrayList;

public class Model
{
	/**
	 * @return the plansza
	 */
	public Plansza getPlansza()
	{
		return plansza;
	}

	/**zmienna reprezentujaca plansze z pionkami*/
	private Plansza plansza;

	/**
	 * konstruktor inicjujacy plansze pionkami.
	 * 
	 * @param gracz1
	 * @param gracz2
	 */
	public Model(Wlasciciel gracz1, Wlasciciel gracz2)
	{
		this.plansza = new Plansza(gracz1,gracz2);
	}
	
	/**
     * 
     * @return Zwraca listê wszystkich dostêpnych ruchów danego gracza
     */
    public ArrayList<Ruch> dostepneRuchy() {
        //TODO
        return null;
    }
	
	/**
	 * Probuje wykonac ruch
	 * @param ruch
	 */
	public void wykonajRuch(final Ruch ruch) {
	    //TODO
	}
	
	/**
	 * Sprawdza czy ruch jest na liscie i go wykonuje
	 * @param ruch 
	 * @return true jezeli wykonano
	 */
	public boolean sprawdzWykonajRuch(final Ruch ruch) {
        return false;
	    //TODO
	}

	/**
	 * Sprawdza czy pionek moze zostac ruszony
	 * Jezeli gracz ma bicie to musi je wykonac - nie moze ruszyc pionkiem nie majacym bicia
	 * @return true jezeli pionek moze zostac ruszony
	 */
	public boolean czyMaRuch() {
	    //TODO
	    return true;
	}
	
	/**
	 * Sprawdza czy lista bic nie jest pusta
	 * @return
	 */
	private boolean czySaBicia() {
	    //TODO
        return false;
	}

}
