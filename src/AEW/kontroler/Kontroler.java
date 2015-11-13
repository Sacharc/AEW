package AEW.kontroler;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import AEW.events.Event;
import AEW.model.Model;
import AEW.widok.Widok;

public class Kontroler {
    private Model model;
    private Widok widok;
    private BlockingQueue<Event> kolejka;
    /**mapa strategii - na podstawie klucza (Event wysylany przez View) wybiera wartosc (strategie ktora go obsluzy)*/
	private HashMap<Class<? extends Event>, Strategie> mapaStrategii;

    /**
     * 
     * @param model
     * @param widok
     * @param kolejka
     */
    public Kontroler(Model model, Widok widok, BlockingQueue<Event> kolejka) {
        // TODO Strategia
        this.model = model;
        this.widok = widok;
        this.kolejka = kolejka;

    }

    /**
     * Petla glowna kontrolera
     */
    public void start() {
    	while(true){
			try{
				Event event = kolejka.take();
				//Strategie strategia = this.mapaStrategii.get(event.getClass());
				//strategia.wykonajStrategie();
			}
			catch(InterruptedException e){
				//nie obslugujemy bo kontroller ma poprostu czekac na eventy
			}
		}
    }
    
    /**
	 * klasa bazowa po ktorej dziedzicza wszystkie strategie
	 */
	private abstract class Strategie{
		abstract public void wykonajStrategie();
	}

}
