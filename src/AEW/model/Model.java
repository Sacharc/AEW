package AEW.model;

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

}
