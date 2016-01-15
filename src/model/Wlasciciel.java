package model;

public enum Wlasciciel
{
	gracz1,gracz2;

	public Wlasciciel przeciwnyGracz(){
		if(this == Wlasciciel.gracz1)
			return Wlasciciel.gracz2;
		return Wlasciciel.gracz1;
	};
}
