package AEW.widok;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import AEW.model.Pole;
import AEW.model.Wlasciciel;

/**
 * klasa do reprezentacji wizualnej jednego pola
 *
 * @author Mateusz Skolimowki
 */
public class PolePanel extends JPanel
{
	
	private Pole pole;

	/**
	 * 
	 *
	 * @author Mateusz Skolimowski
	 * @param pole 
	 */
	public PolePanel(Pole pole)
	{
		this.pole = pole;
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if(pole.getP() != null)
		{
			Ellipse2D elip = new Ellipse2D.Double(12.5,12.5,75,75);
			if(pole.getP().getWlasciciel() == Wlasciciel.gracz1)
				g2d.setColor(Color.RED);
			else
				g2d.setColor(Color.BLUE);
			g2d.fill(elip);
		}
	}

}
