package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Mateusz Skolimowki
 */
public class Plansza{
	
	private List <Ruch> listaRuchow = new <Ruch>ArrayList();
	private List <Ruch> listaBic = new <Ruch>ArrayList();
	
	private Pole pola[][];

	/**
	 * @param gracz1
	 * @param gracz2
	 *
	 * @author Mateusz Skolimowski
	 */
	public Plansza(Wlasciciel gracz1, Wlasciciel gracz2)
	{
		initPola();
		for(int i = 0 ; i < 8 ; i++){
			if(i<3){
				if(i%2==0){
					wypelnijLinieParzysta(i,gracz1);
				}
				else{
					wypelnijLinieNieParzysta(i,gracz1);
				}
			}
			if(i>4){
				if(i%2==0){
					wypelnijLinieParzysta(i,gracz2);
				}
				else{
					wypelnijLinieNieParzysta(i,gracz2);
				}
			}
		}
	}
	
	public Plansza(Plansza p) {
	    this.pola = new Pole[8][8];
	    for(int i = 0; i < 8; i++)
	        for(int j = 0; j < 8; j++)
                this.pola[i][j] = new Pole(p.pola[i][j]);
	}
	
	private void initPola(){
		this.pola = new Pole[8][8];
		for(int i = 0 ; i < 8 ; i ++ ){
			for(int j = 0 ; j < 8 ; j ++){
				this.pola[i][j] = new Pole(i,j);
			}
		}
	}
	
	private void wypelnijLinieParzysta(int i, Wlasciciel gracz1){
		for(int j = 0 ; j<4 ; j++){
			this.pola[i][2*j+1].addPionek(gracz1);
		}
	}
	
	private void wypelnijLinieNieParzysta(int i, Wlasciciel gracz1){
		for(int j = 0 ; j<4 ; j++){
			this.pola[i][2*j].addPionek(gracz1);
		}
	}
	
	public void wypisz(){
		for(Pole[] linia : this.pola){
			for(Pole p : linia){
				if(p.getPionek()!=null)
					System.out.print("X");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}

	public void zmienPolozeniePionka(int x1,int y1, int x2, int y2){
		boolean czyBylDamka = false;
		if(pola[x1][y1].getPionek().getCzyDamka())
			czyBylDamka = true;
		Wlasciciel gracz = pola[x1][y1].removePionek();
		pola[x2][y2].addPionek(gracz);
		if(x2 == 7 || czyBylDamka){
			System.out.println("EEEE DAMKA");
			pola[x2][y2].getPionek().setCzyDamka(true);
		}
	}
	
	/**
	 * @param i
	 * @param j
	 * @return
	 *
	 * @author Mateusz Skolimowski
	 */
	public Pole getPole(int i, int j)
	{
		return this.pola[i][j];
	}
	
	public void czyscListyRuchowBic(){
		listaRuchow = new <Ruch>ArrayList();
		listaBic = new <Ruch>ArrayList();
	}

	/**
	 * 
	 *
	 * @author Mateusz Skolimowski
	 * @param aktualnyGracz 
	 */
	public void sprawdzDostepneRuchy(Wlasciciel aktualnyGracz)
	{
		for(Pole[] linia : this.pola){
			for(Pole p : linia){
				if(p.getPionek()!=null){
					if(p.getPionek().getWlasciciel() == aktualnyGracz){
						sprawdzBiciaRuchyPionka(p);
					}
				}
			}
		}
	}
	
	public void sprawdzBiciaRuchyPionka(Pole p){
		/* sprawdzamy ruchy w lewo */
		if(p.getY() != 0){
			//pojedynczy ruch w dol lewo
			if(p.getX() != 7 && this.pola[p.getX()+1][p.getY()-1].getPionek() == null){
				listaRuchow.add(new Ruch(p.getX(),p.getY(),p.getX()+1,p.getY()-1));
			}
			//sprawdzamy czy jest damka
			if(p.getPionek().getCzyDamka()){
				//sprawdzamy wieloktorny ruch w dol lewo
				int x=p.getX()+2;
				int y=p.getY()-2;
				while(x <= 7 && y >= 0 && this.pola[x][y].getPionek() == null){
					listaRuchow.add(new Ruch(p.getX(),p.getY(),x,y));
					x++;
					y--;
				}
				//sprawdzamy wielokrotny ruch w gore prawo
				if(p.getX()>0){
					x=p.getX()-1;
					y=p.getY()-1;
					while(x >= 0 && y >= 0 && this.pola[x][y].getPionek() == null){
						listaRuchow.add(new Ruch(p.getX(),p.getY(),x,y));
						x--;
						y--;
					}
				}
			}
			if(p.getX() < 6 && p.getY() > 1){
				if(this.pola[p.getX()+1][p.getY()-1].getPionek() != null && p.getPionek().getWlasciciel() != pola[p.getX()+1][p.getY()-1].getPionek().getWlasciciel() && this.pola[p.getX()+2][p.getY()-2].getPionek() == null){
					listaBic.add(new Ruch(p.getX(),p.getY(),p.getX()+2,p.getY()-2));
					
				}
				else if(p.getPionek().getCzyDamka()){
					int x = p.getX()+2;
					int y = p.getY()-2;
					while(x <= 6 && y >= 1){
						if(this.pola[x][y].getPionek() != null && p.getPionek().getWlasciciel() != pola[x][y].getPionek().getWlasciciel() && this.pola[x+1][y-1].getPionek() == null){
							listaBic.add(new Ruch(p.getX(),p.getY(),x+1,y-1));
							break;
						}
						x++;
						y--;
					}
				}
			}
			if(p.getX() > 1 && p.getY() > 1){
				if(this.pola[p.getX()-1][p.getY()-1].getPionek() != null && p.getPionek().getWlasciciel() != pola[p.getX()-1][p.getY()-1].getPionek().getWlasciciel() && this.pola[p.getX()-2][p.getY()-2].getPionek() == null){
					listaBic.add(new Ruch(p.getX(),p.getY(),p.getX()-2,p.getY()-2));
				}
				else if(p.getPionek().getCzyDamka()){
					int x = p.getX()-2;
					int y = p.getY()-2;
					while(x >= 1 && y >= 1){
						if(this.pola[x][y].getPionek() != null && p.getPionek().getWlasciciel() != pola[x][y].getPionek().getWlasciciel() && this.pola[x-1][y-1].getPionek() == null){
							listaBic.add(new Ruch(p.getX(),p.getY(),x-1,y-1));
							break;
						}
						x--;
						y--;
					}
				}
			}
		}
		/* sprawdzamy ruch w prawo */ 
		if(p.getY() != 7){
			//pojedyncz ruch w prawo dol
			if(p.getX() != 7 && this.pola[p.getX()+1][p.getY()+1].getPionek() == null)
				listaRuchow.add(new Ruch(p.getX(),p.getY(),p.getX()+1,p.getY()+1));
			if(p.getPionek().getCzyDamka()){
				//wielokrotny ruch w prawo dol
				int x=p.getX()+2;
				int y=p.getY()+2;
				while(x <= 7 && y <= 7 && this.pola[x][y].getPionek() == null){
					System.out.println("wchodze x="+x+" y="+y);
					listaRuchow.add(new Ruch(p.getX(),p.getY(),x,y));
					x++;
					y++;
				}
				//wielokrotny ruch w prawo gore
				if(p.getX() > 0){
					x=p.getX()-1;
					y=p.getY()+1;
					while(x >= 0 && y <= 7 && this.pola[x][y].getPionek() == null){
						listaRuchow.add(new Ruch(p.getX(),p.getY(),x,y));
						x--;
						y++;
					}
				}
			}
			if(p.getX() < 6 && p.getY() < 6){
				if(this.pola[p.getX()+1][p.getY()+1].getPionek() != null && p.getPionek().getWlasciciel() != pola[p.getX()+1][p.getY()+1].getPionek().getWlasciciel() && this.pola[p.getX()+2][p.getY()+2].getPionek() == null){
					listaBic.add(new Ruch(p.getX(),p.getY(),p.getX()+2,p.getY()+2));
				}
				else if(p.getPionek().getCzyDamka()){
					int x = p.getX()+2;
					int y = p.getY()+2;
					while(x <= 6 && y <= 6){
						if(this.pola[x][y].getPionek() != null && p.getPionek().getWlasciciel() != pola[x][y].getPionek().getWlasciciel() && this.pola[x+1][y+1].getPionek() == null){
							listaBic.add(new Ruch(p.getX(),p.getY(),x+1,y+1));
							break;
						}
						x++;
						y++;
					}
				}
			}
			if(p.getX() > 1 && p.getY() < 6){
				if(this.pola[p.getX()-1][p.getY()+1].getPionek() != null && p.getPionek().getWlasciciel() != pola[p.getX()-1][p.getY()+1].getPionek().getWlasciciel() && this.pola[p.getX()-2][p.getY()+2].getPionek() == null){
					listaBic.add(new Ruch(p.getX(),p.getY(),p.getX()-2,p.getY()+2));
				}
				else if(p.getPionek().getCzyDamka()){
					int x = p.getX()-2;
					int y = p.getY()+2;
					while(x >= 1 && y <= 6){
						if(this.pola[x][y].getPionek() != null && p.getPionek().getWlasciciel() != pola[x][y].getPionek().getWlasciciel() && this.pola[x-1][y+1].getPionek() == null){
							listaBic.add(new Ruch(p.getX(),p.getY(),x-1,y+1));
							break;
						}
						x--;
						y++;
					}
				}
			}
		}
	}
	/**
	 * @return
	 *
	 * @author Mateusz Skolimowski
	 */
	public boolean sprawdzCzyKoniecGry()
	{
		boolean czyJestPionekGracza1 = false;
		boolean czyJestPionekGracza2 = false;
		for(Pole[] linia : this.pola){
			for(Pole p : linia){
				if(p.getPionek() != null){
					if(p.getPionek().getWlasciciel() == Wlasciciel.gracz1){
						czyJestPionekGracza1 = true;
					}
					if(p.getPionek().getWlasciciel() == Wlasciciel.gracz2){
						czyJestPionekGracza2 = true;
					}
				}
			}
		}
		return czyJestPionekGracza1 && czyJestPionekGracza2;
	}

	/**
	 * 
	 *
	 * @author Mateusz Skolimowski
	 */
	public void zmianaWspolrzednych()
	{
		Pole[][] zmienionaPlansza = new Pole[8][8];
		for(int i = 0 ; i < 8 ; i ++ ){
			for(int j = 0 ; j < 8 ; j ++){
				zmienionaPlansza[i][j] = new Pole(i,j);
			}
		}
		for(Pole[] linia : this.pola){
			for(Pole p : linia){
				if(p.getPionek()!=null){
					zmienionaPlansza[7-p.getX()][7-p.getY()].addPionek(p.getPionek().getWlasciciel());
					if(p.getPionek().getCzyDamka())
						zmienionaPlansza[7-p.getX()][7-p.getY()].getPionek().setCzyDamka(true);
				}
			}
		}
		this.pola = zmienionaPlansza;
	}

	/**
	 * @return
	 *
	 * @author Mateusz Skolimowski
	 */
	public List <Ruch> getListaBic()
	{
		return listaBic;
	}
	
	/**
	 * @return
	 *
	 * @author Mateusz Skolimowski
	 */
	public List <Ruch> getListaRuchu()
	{
		return listaRuchow;
	}
	
	public List <Ruch> getMozliweRuchy() {
	    if(listaBic.isEmpty())
	        return listaRuchow;
	    return listaBic;
	}
	
	public int getLiczbaRuchow() {
	    return getMozliweRuchy().size();
	}

	/**
	 * 
	 *
	 * @author Mateusz Skolimowski
	 */
	public void wypiszBicia()
	{
		for(Ruch b : listaBic){
			System.out.println(b.getX1()+";"+b.getY1()+"   "+b.getX2()+";"+b.getY2());
		}
	}

	/**
	 * 
	 *
	 * @author Mateusz Skolimowski
	 */
	public void wypiszRuchy()
	{
		for(Ruch r : listaRuchow){
			System.out.println(r.getX1()+";"+r.getY1()+"   "+r.getX2()+";"+r.getY2());
		}
	}

}
