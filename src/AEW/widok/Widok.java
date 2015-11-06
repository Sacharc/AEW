package AEW.widok;

import java.awt.BorderLayout;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import AEW.events.Event;
import AEW.model.Plansza;

public class Widok {
    
    private BlockingQueue<Event> kolejka;
    
    /**glowny panel w ktorym beda wyswietlane pola i pionki*/
	public PlanszaPanel glownyPanel;
	/**ramka w ktorej beda wszystkie elementy*/
	public JFrame ramka;

    public Widok(BlockingQueue<Event> kolejka, Plansza plansza) 
    {
    	this.kolejka = kolejka;
	
    	//parametry ramki
    	ramka = new JFrame("Wyszukiwacz");
    	ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	ramka.setBounds(100, 100, 800, 800);
	
    	glownyPanel = new PlanszaPanel(plansza);
    	ramka.add(glownyPanel, BorderLayout.CENTER);
    	
    	glownyPanel.setVisible(true);
    	ramka.setVisible(true);		
    }
    
}
