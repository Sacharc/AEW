package AEW.events;

import AEW.model.Wspolrzedne;

/**
 * event przenoszacy informacje o miejscu klinieca
 *
 * @author Mateusz Skolimowki
 */
public class klikniecieEvent extends Event{
	
	private Wspolrzedne wspl;
	
	/**
	 * @param x
	 * @param y
	 *
	 * @author Mateusz Skolimowski
	 */
	public klikniecieEvent(int x, int y){
		wspl = new Wspolrzedne(x, y);
	}
	
	public Wspolrzedne getWspolrzedne(){
		return wspl;
	}

}
