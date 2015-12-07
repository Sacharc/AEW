
import ai.Komputer;
import ai.losowy.KomputerLosowy;
import model.Model;
import model.Wlasciciel;
import widok.Widok;

/**
 B³êdy
 Gra powinna sie konczyc jezeli ktorys z graczy nie ma ruchu
 Damki przeskakuja swoje pionki
 Damki przeskakuja kilka pionkow przeciwnika bijac ostatni co pozwala im pobic caly rzad
 Damki po biciu staja dokladnie za pioniem przeciwnika a moga stanac gdzie chca - DO ROZWAZENIA
 */


public class Warcaby {

    public static void main(String[] args) {
		//TODO Mati na drugi raz nie zostawiaj takich smaczkow, ze jak odpalam maina to mi komp prawie zwieche lapie na tych 1000 obiegach w mainie ;p
    	for(int i = 0 ; i < 10 ; i++){
        Model model = new Model();
        Widok widok = new Widok();
        Komputer komputer1 = new KomputerLosowy(model);
        Komputer komputer2 = new KomputerLosowy(model);
        Wlasciciel aktualnyGracz = Wlasciciel.gracz1;
        widok.uaktualnij(model.getPlansza());
        while(model.getPlansza().sprawdzCzyKoniecGry()){
            
        	switch(aktualnyGracz) {
        	case gracz1:
        	    model.sprawdzDostepneRuchy(aktualnyGracz);
        	    while(komputer1.update())
        	        widok.uaktualnij(model.getPlansza());
        	    widok.uaktualnij(model.getPlansza());
        	    aktualnyGracz = Wlasciciel.gracz2;
        	    break;
        	case gracz2:
        	    model.zmianaWspolrzednych();
        	    model.sprawdzDostepneRuchy(aktualnyGracz);
        	    while(komputer2.update());
                    //widok.uaktualnij(model.getPlansza()); //do mocnego przemyslenia bo trzeba by bylo zmieniac strony dwukrotni tzn przed i po
        	    model.zmianaWspolrzednych();
        	    widok.uaktualnij(model.getPlansza());
        	    aktualnyGracz = Wlasciciel.gracz1;
        	    break;
        	}
        	model.czyscListy();
        	
        	try {
        	    Thread.sleep(700);                 //1000 milliseconds is one second.
        	} catch(InterruptedException ex) {
        	    Thread.currentThread().interrupt();
        	}
        	
        }
        widok.zamknij();
    	}
    }

}
