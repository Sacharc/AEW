package AEW.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Model{


	/**zmienna reprezentujaca plansze z pionkami*/
	private Plansza plansza;
	
	/**
	 * @return the plansza
	 */
	public Plansza getPlansza()
	{
		return plansza;
	}
	
	/**
	 * konstruktor inicjujacy plansze pionkami.
	 */
	public Model(){
		this.plansza = new Plansza(Wlasciciel.gracz1,Wlasciciel.gracz2);
		this.plansza.wypisz();
	}

	
	public void sprawdzDostepneRuchy(Wlasciciel aktualnyGracz)
	{
		this.plansza.sprawdzDostepneRuchy(aktualnyGracz);
	}

	/**
	 * 
	 *
	 * @author Mateusz Skolimowski
	 */
	public void zmianaWspolrzednych()
	{
		this.getPlansza().zmianaWspolrzednych();
	}


	/**
	 * 
	 *
	 * @author Mateusz Skolimowski
	 */
	public void czyscListy()
	{
		getPlansza().czyscListyRuchowBic();
	}

	/**
	 * 
	 *
	 * @author Mateusz Skolimowski
	 */
	public boolean wykonajBicie()
	{
        Scanner in = new Scanner(System.in);
		System.out.println("musisz wykonac bicie. Lista pionkow ktore moga wykonac bicie : ");
		getPlansza().wypiszBicia();
    	/*System.out.println("podaj x");
    	int x1 = in.nextInt();
    	System.out.println("podaj y");
    	int y1 = in.nextInt();
    	System.out.println("podaj x2");
    	int x2 = in.nextInt();
    	System.out.println("podaj y2");
    	int y2 = in.nextInt();*/
		
		/**************************************************************************************/
		/***************					TUTAJ ZMIENIAC						 **************/
		/**************************************************************************************/
		int x1,x2,y1,y2;
		//tutaj algorytm jak bedzie bil gracz 1
		if(plansza.getPole(plansza.getListaBic().get(0).getX1(), plansza.getListaBic().get(0).getY1()).getPionek().getWlasciciel() == Wlasciciel.gracz1){					
			Random rand = new Random();
			int r = rand.nextInt(plansza.getListaBic().size());
			x1 = plansza.getListaBic().get(r).getX1();
			x2 = plansza.getListaBic().get(r).getX2();
			y1 = plansza.getListaBic().get(r).getY1();
			y2 = plansza.getListaBic().get(r).getY2();
		}
		//tutaj algorytm jak bedzie bil gracz 2
		else{
			Random rand = new Random();
			int r = rand.nextInt(plansza.getListaBic().size());
			x1 = plansza.getListaBic().get(r).getX1();
			x2 = plansza.getListaBic().get(r).getX2();
			y1 = plansza.getListaBic().get(r).getY1();
			y2 = plansza.getListaBic().get(r).getY2();
		}
    	/**************************************************************************************/
    	/**************************************************************************************/
    	/**************************************************************************************/
    	
    	getPlansza().zmienPolozeniePionka(x1, y1, x2,y2);
    	if(x1<x2 && y1<y2){
    		getPlansza().getPole(x2-1, y2-1).removePionek();
    	}
    	else if(x1>x2 && y1<y2){
    		getPlansza().getPole(x2+1, y2-1).removePionek();
    	}
    	else if(x1<x2 && y1>y2){
    		getPlansza().getPole(x2-1, y2+1).removePionek();
    	}
    	else{
    		getPlansza().getPole(x2+1, y2+1).removePionek();
    	}
    	czyscListy();
    	getPlansza().sprawdzBiciaRuchyPionka(getPlansza().getPole(x2, y2));
    	if(getPlansza().getListaBic().size() > 0){
    		return true;
    	}
    	return false;
	}

	/**
	 * 
	 *
	 * @author Mateusz Skolimowski
	 */
	public void wykonajRuch()
	{
		Scanner in = new Scanner(System.in);
		/*System.out.println("ruch do wykonania. Lista pionkow ktore moga wykonac ruch : ");
		getPlansza().wypiszRuchy();
    	System.out.println("podaj x");
    	int x1 = in.nextInt();
    	System.out.println("podaj y");
    	int y1 = in.nextInt();
    	System.out.println("podaj x2");
    	int x2 = in.nextInt();
    	System.out.println("podaj y2");
    	int y2 = in.nextInt();*/
		/**************************************************************************************/
		/***************					TUTAJ ZMIENIAC						 **************/
		/**************************************************************************************/
		int x1,x2,y1,y2;
		//tutaj algorytm jak bedzie poruszal gracz 1
		if(plansza.getPole(plansza.getListaRuchu().get(0).getX1(), plansza.getListaRuchu().get(0).getY1()).getPionek().getWlasciciel() == Wlasciciel.gracz1){					
			Random rand = new Random();
			int r = rand.nextInt(plansza.getListaRuchu().size());
			x1 = plansza.getListaRuchu().get(r).getX1();
			x2 = plansza.getListaRuchu().get(r).getX2();
			y1 = plansza.getListaRuchu().get(r).getY1();
			y2 = plansza.getListaRuchu().get(r).getY2();
			getPlansza().zmienPolozeniePionka(x1, y1, x2,y2);
		}
		//tutaj algorytm jak bedzie poruszal gracz 2
		else{
			Random rand = new Random();
			int r = rand.nextInt(plansza.getListaRuchu().size());
			x1 = plansza.getListaRuchu().get(r).getX1();
			x2 = plansza.getListaRuchu().get(r).getX2();
			y1 = plansza.getListaRuchu().get(r).getY1();
			y2 = plansza.getListaRuchu().get(r).getY2();
			getPlansza().zmienPolozeniePionka(x1, y1, x2,y2);
		}
    	
    	/**************************************************************************************/
    	/**************************************************************************************/
    	/**************************************************************************************/
	}
};
