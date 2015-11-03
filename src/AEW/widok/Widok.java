package AEW.widok;

import java.util.concurrent.BlockingQueue;

import AEW.events.Event;

public class Widok {
    
    private BlockingQueue<Event> kolejka;

    public Widok(BlockingQueue<Event> kolejka) {
	//TODO
	this.kolejka = kolejka;
    }
    
}
