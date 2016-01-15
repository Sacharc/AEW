package model;

public class Ruch
{
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private boolean bicie;
	
	
	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param bicie
	 */
	public Ruch(int x1, int y1, int x2, int y2, boolean bicie)
	{
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.bicie = bicie;
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

	public boolean isBicie() {
		return bicie;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Ruch ruch = (Ruch) o;

		if (x1 != ruch.x1) return false;
		if (y1 != ruch.y1) return false;
		if (x2 != ruch.x2) return false;
		if (y2 != ruch.y2) return false;
		return bicie == ruch.bicie;

	}

	@Override
	public int hashCode() {
		int result = x1;
		result = 31 * result + y1;
		result = 31 * result + x2;
		result = 31 * result + y2;
		result = 31 * result + (bicie ? 1 : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Ruch{" +
				"x1=" + x1 +
				", y1=" + y1 +
				", x2=" + x2 +
				", y2=" + y2 +
				", bicie=" + bicie +
				'}';
	}
}
