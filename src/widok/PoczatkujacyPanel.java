package widok;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Plansza;

/**
 *
 *
 * @author Mateusz Skolimowki
 */
public class PoczatkujacyPanel extends JPanel{
	public PolePanel [][] siatkaPaneli = new PolePanel[8][8];
	
	private Plansza plansza;
	
	/**
	 * konstruktor inicjujacy glowny panel
	 * @param plansza 
	 */
	public PoczatkujacyPanel(){	
		this.plansza = plansza;
		setLayout(null);
		JButton b1 = new JButton("one");
		JButton b2 = new JButton("two");
		JButton b3 = new JButton("three");
		JButton b4 = new JButton("four");

		add(b1);
		add(b2);
		add(b3);
		add(b4);
		b1.setBounds(100,100,100,100);
		b2.setBounds(100,200,100,100);
		b3.setBounds(200,100,100,100);
		b4.setBounds(200,200,100,100);
	}
}
