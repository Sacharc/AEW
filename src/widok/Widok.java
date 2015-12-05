package widok;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Plansza;

public class Widok {

    /** glowny panel w ktorym beda wyswietlane pola i pionki */
    public JPanel glownyPanel;
    /** ramka w ktorej beda wszystkie elementy */
    public JFrame ramka;

    public Widok() {
    	/*
        this.kolejka = kolejka;
        // parametry ramki
        ramka = new JFrame("Warcaby");
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setBounds(100, 100, 800, 800);

        glownyPanel = new PlanszaPanel(plansza);
        ramka.add(glownyPanel, BorderLayout.CENTER);

        glownyPanel.setVisible(true);
        ramka.addMouseListener(new MouseListener(){
			
			@Override
			public void mouseReleased(MouseEvent e){
			}
			
			@Override
			public void mousePressed(MouseEvent e){
			}
			
			@Override
			public void mouseExited(MouseEvent e){
			}
			
			@Override
			public void mouseEntered(MouseEvent e){
			}
			
			@Override
			public void mouseClicked(MouseEvent e){
				kolejka.offer(new klikniecieEvent((e.getX() - e.getX()%100)/100,(e.getY() - e.getY()%100)/100));
			}
		});
        ramka.setVisible(true);
        */
        // parametry ramki
        ramka = new JFrame("Warcaby");
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.setBounds(100, 100, 800, 800);

        glownyPanel = new PoczatkujacyPanel();
        ramka.add(glownyPanel, BorderLayout.CENTER);

        glownyPanel.setVisible(true);
        ramka.setVisible(true);
    }

    /**
     * funkcja tworzy nowy watek aby zmienic widok
     * 
     * @param tab
     *            - tablica na podstawie ktorej rysuje pola
     */
    public void uaktualnij(Plansza plansza) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ramka.remove(glownyPanel);
                glownyPanel = new PlanszaPanel(plansza);
                ramka.add(glownyPanel);
                ramka.validate();
            }
        });
    }
    
    public void zamknij(){
    	ramka.setVisible(false); //you can't see me!
    	ramka.dispose();
    }
}
