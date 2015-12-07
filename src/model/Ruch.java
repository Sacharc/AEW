package model;

/**
 *
 *
 * @author Mateusz Skolimowki
 */
public class Ruch
{
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	
	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 *
	 * @author Mateusz Skolimowski
	 */
	public Ruch(int x1, int y1, int x2, int y2)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public int getX1()
	{
		return x1;
	}
	
	public int getY1()
	{
		return y1;
	}
	
	public int getX2()
	{
		return x2;
	}
	
	public int getY2()
	{
		return y2;
	}

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ruch other = (Ruch) obj;
        if (x1 != other.x1)
            return false;
        if (x2 != other.x2)
            return false;
        if (y1 != other.y1)
            return false;
        if (y2 != other.y2)
            return false;
        return true;
    }
	
}
