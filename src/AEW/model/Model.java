package AEW.model;

public class Model
{
	/**zmienna reprezentujaca plansze z pionkami*/
	private Plansza plansza;

	/**
	 * konstruktor inicjujacy plansze pionkami.
	 * 
	 * @param gracz1
	 * @param gracz2
	 *
	 * @author Mateusz Skolimowski
	 */
	public Model(Wlasciciel gracz1, Wlasciciel gracz2)
	{
		this.plansza = new Plansza(gracz1,gracz2);
	}

}
