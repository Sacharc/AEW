package AEW.model;

/**
 *
 *
 * @author Mateusz Skolimowki
 */
public class Pole{

	private Pionek pionek;
	int x;
	int y;

	public Pole(int x, int y){
		this.pionek = null;
		this.x = x;
		this.y = y;
		
	}
	
	/**
	 * @param gracz1
	 *
	 * @author Mateusz Skolimowski
	 */
	public void addPionek(Wlasciciel gracz1)
	{
		this.pionek = new Pionek(gracz1);
	}
	
	public Pionek getPionek(){
		return this.pionek;
	}

	/**
	 * @return
	 *
	 * @author Mateusz Skolimowski
	 */
	public Wlasciciel removePionek()
	{
		Wlasciciel gracz;
		gracz = this.pionek.getWlasciciel();
		this.pionek = null;
		return gracz;
	}

	/**
	 * @return
	 *
	 * @author Mateusz Skolimowski
	 */
	public int getX()
	{
		return this.x;
	}
	
	/**
	 * @return
	 *
	 * @author Mateusz Skolimowski
	 */
	public int getY()
	{
		return this.y;
	}
}
