package model;

/**
 *
 *
 * @author Mateusz Skolimowki
 */
public class Pionek{

	private boolean czyDamka;
	/**
	 * @return the czyDamka
	 */
	public boolean getCzyDamka()
	{
		return czyDamka;
	}

	private Wlasciciel gracz;
	
	/**
	 * @param gracz1
	 *
	 * @author Mateusz Skolimowski
	 */
	public Pionek(Wlasciciel gracz){
		this.gracz = gracz;
		czyDamka = false;
	}

	/**
	 * @param czyDamka the czyDamka to set
	 */
	public void setCzyDamka(boolean czyDamka)
	{
		this.czyDamka = czyDamka;
	}

	/**
	 * @return
	 *
	 * @author Mateusz Skolimowski
	 */
	public Wlasciciel getWlasciciel()
	{
		return gracz;
	}
}
