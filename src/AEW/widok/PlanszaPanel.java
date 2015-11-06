package AEW.widok;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import AEW.model.Plansza;

/**
 * plansza do wyswietlania pol z pionkami
 *
 * @author Mateusz Skolimowki
 */
public class PlanszaPanel extends JPanel
{	
	public JPanel [][] siatkaPaneli = new JPanel[8][8];
	
	/**
	 * konstruktor inicjujacy glowny panel
	 *
	 * @author Mateusz Skolimowski
	 * @param plansza 
	 */
	public PlanszaPanel(Plansza plansza)
	{	
		setLayout(new GridLayout(8, 8));
		for(int i = 0 ; i < 8 ; i++)
		{
			for(int j = 0 ; j < 8 ; j++)
			{
				siatkaPaneli[i][j] = new JPanel();
				siatkaPaneli[i][j].setSize(100, 100);
				if(i%2 == 0)
				{
					if( j%2 == 0 ) 
						siatkaPaneli[i][j].setBackground(Color.BLACK);
					else
						siatkaPaneli[i][j].setBackground(Color.WHITE);
				}
				else
				{
					if(j%2 == 1 )
						siatkaPaneli[i][j].setBackground(Color.BLACK);
					else
						siatkaPaneli[i][j].setBackground(Color.WHITE);
				}
				siatkaPaneli[i][j].setVisible(true);
				add(siatkaPaneli[i][j]);
			}
		}
	}

}
