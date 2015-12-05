package widok;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import model.Pole;
import model.Wlasciciel;

/**
 * klasa do reprezentacji wizualnej jednego pola
 */
public class PolePanel extends JPanel {

    private Pole pole;

    /**
     * @param pole
     */
    public PolePanel(Pole pole) {
        this.pole = pole;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (pole.getPionek() != null) {
            Ellipse2D elip = new Ellipse2D.Double(12.5, 12.5, 75, 75);
            if (pole.getPionek().getWlasciciel() == Wlasciciel.gracz1)
                g2d.setColor(Color.RED);
            else
                g2d.setColor(Color.BLUE);
            g2d.fill(elip);
            if(pole.getPionek().getCzyDamka()){
            	Ellipse2D elip2 = new Ellipse2D.Double(37.5, 37.5, 25, 25);
            	g2d.setColor(Color.BLACK);
            	g2d.fill(elip2);
            }
        }
    }

}
