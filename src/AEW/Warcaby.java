package AEW;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import AEW.events.Event;
import AEW.kontroler.Kontroler;
import AEW.model.Model;
import AEW.widok.Widok;

public class Warcaby {
    
    public static void main(String[] args) {
    System.out.println("Sacharczuk wez sprawdz czy dziala :D");
	BlockingQueue<Event> kolejka = new LinkedBlockingQueue<Event>();
	Model model = new Model();
	Widok widok = new Widok(kolejka);
	Kontroler kontroler = new Kontroler(model, widok, kolejka);
	kontroler.start();
    }

}
