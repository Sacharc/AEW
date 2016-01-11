import ai.Komputer;
import ai.algorytmEwolucyjny.KomputerEwolucyjny;
import ai.algorytmMinMax.KomputerMinMax;
import model.Model;
import model.Wlasciciel;
import widok.Widok;

/**
BUGS
 Gra powinna sie konczyc jezeli ktorys z graczy nie ma ruchu
 Damki przeskakuja swoje pionki
 Damki przeskakuja kilka pionkow przeciwnika bijac ostatni co pozwala im pobic caly rzad
 Damki po biciu staja dokladnie za pioniem przeciwnika a moga stanac gdzie chca - DO ROZWAZENIA
 */


public class Warcaby {

    private static void spij(){
        try {
            Thread.sleep(500);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        //TODO Mati na drugi raz nie zostawiaj takich smaczkow, ze jak odpalam maina to mi komp prawie zwieche lapie na tych 1000 obiegach w mainie ;p

        //jakiestam zmienne do statystyk
        int zwyciestwaGracz1 = 0;
        int zwyciestwaGracz2 = 0;
        
        boolean czySkonczylySieRuchy;

        for(int i = 0 ; i < 20 ; i++){
            Model model = new Model();
            Widok widok = new Widok();
            czySkonczylySieRuchy = false;
            //            Komputer komputer1 = new KomputerLosowy(model, Wlasciciel.gracz1);
            //Komputer komputer1 = new KomputerLosowy(model, Wlasciciel.gracz1);
            Komputer komputer2 = new KomputerEwolucyjny(model, Wlasciciel.gracz2);
            Komputer komputer1 = new KomputerMinMax(model, Wlasciciel.gracz1);
            Wlasciciel aktualnyGracz = Wlasciciel.gracz1;
            widok.uaktualnij(model.getPlansza());
            while(model.getPlansza().sprawdzCzyKoniecGry() && !czySkonczylySieRuchy){
                switch(aktualnyGracz) {
                case gracz1:
                    model.sprawdzDostepneRuchy(aktualnyGracz);
                    if(model.getPlansza().getListaBic().isEmpty() && model.getPlansza().getListaRuchu().isEmpty()){
                    	czySkonczylySieRuchy = true;
                    	zwyciestwaGracz2++;
                    	break;
                    }
                    komputer1.update();
                    widok.uaktualnij(model.getPlansza());
                    aktualnyGracz = Wlasciciel.gracz2;
                    break;
                case gracz2:
                    model.zmianaWspolrzednych();
                    model.sprawdzDostepneRuchy(aktualnyGracz);
                    if(model.getPlansza().getListaBic().isEmpty() && model.getPlansza().getListaRuchu().isEmpty()){
                    	czySkonczylySieRuchy = true;
                    	zwyciestwaGracz1++;
                    	break;
                    }
                    komputer2.update();
                    model.zmianaWspolrzednych();
                    widok.uaktualnij(model.getPlansza());
                    aktualnyGracz = Wlasciciel.gracz1;
                    break;
                }
                model.czyscListy();
                spij();
            }
            //System.out.println(model.getPlansza().ktoWygral());
            if(!czySkonczylySieRuchy){
            	if(model.getPlansza().ktoWygral() == Wlasciciel.gracz1)
            		zwyciestwaGracz1++;
            	else
            		zwyciestwaGracz2++;
            }
            widok.zamknij();
            System.out.println("Wygrane gracz1 " + zwyciestwaGracz1 + " Wygrane gracz2 " + zwyciestwaGracz2);
        }

    }

}
