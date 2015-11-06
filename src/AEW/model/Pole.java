package AEW.model;

/**
 * Mo�e zawiera� pionek lub by� puste
 * @author Mmm
 *
 */
public class Pole {
    Pionek p;
    
    public Pole() {
	p = null;
    }
    

    /**
     * tworzy pole z pionkiem
     * 
	 * @param gracz1 - wlasciciel pionka
	 *
	 * @author Mateusz Skolimowski
	 */
	public Pole(Wlasciciel gracz1)
	{
		p = new ZwyklyPionek(gracz1);
	}

	public Pionek getP() {
        return p;
    }

    void setP(Pionek p) {
        this.p = p;
    }
    
    boolean isEmpty() {
	return p == null;
    }
    
    void clear() {
	p = null;
    }
}
