package model;

/**
 *
 *
 * @author Mateusz Skolimowki
 */
public class Pionek{

    private Wlasciciel gracz;
	private boolean czyDamka;
	
    public Pionek(Wlasciciel gracz){
        this.gracz = gracz;
        czyDamka = false;
    }

    public Pionek(Pionek pionek) {
        this.gracz = pionek.gracz;
        this.czyDamka = pionek.czyDamka;
    }
	
	/**
	 * @return the czyDamka
	 */
	public boolean getCzyDamka()
	{
		return czyDamka;
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
