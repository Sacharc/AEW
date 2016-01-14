import ai.Komputer;
import ai.algorytmEwolucyjny.KomputerEwolucyjny;
import ai.algorytmMinMax.KomputerMinMax;
import ai.losowy.KomputerLosowy;
import jdk.internal.dynalink.beans.StaticClass;
import model.Model;
import model.Plansza;
import model.Ruch;
import model.Statystyki;
import model.Wlasciciel;
import widok.Widok;

/**
 B��dy
 Damki po biciu staja dokladnie za pioniem przeciwnika a moga stanac gdzie chca - TAK JEST GIT :D
 */


public class Warcaby {

    private static void spij(){
        try {
            Thread.sleep(10);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static void main(String[] args) {
        //jakiestam zmienne do statystyk
        int zwyciestwaGracz1 = 0;
        int zwyciestwaGracz2 = 0;
        
        int staraOcena1, staraOcena2;
        int nowaOcena1, nowaOcena2;
        int licznik;
        
        boolean czySkonczylySieRuchy;

        for(int i = 0 ; i < 20 ; i++){
            staraOcena1 = 0;
            staraOcena2 = 0;
            licznik = 8;
            
            Model model = new Model();
            Widok widok = new Widok();
            czySkonczylySieRuchy = false;
//            Komputer komputer1 = new KomputerLosowy(model, Wlasciciel.gracz1);
           Komputer komputer2 = new KomputerLosowy(model, Wlasciciel.gracz2);
            Komputer komputer1 = new KomputerMinMax(model, Wlasciciel.gracz1);
           // Komputer komputer2 = new KomputerEwolucyjny(model, Wlasciciel.gracz2);

            Wlasciciel aktualnyGracz = Wlasciciel.gracz1;
            widok.uaktualnij(model.getPlansza());
            while(model.getPlansza().sprawdzCzyKoniecGry() && !czySkonczylySieRuchy) {
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
                    
                    /**************************************************************************/
                    Statystyki statystyki = model.getPlansza().getStatystyki();
                    nowaOcena1 = statystyki.getOcenaGracza(aktualnyGracz);
                    nowaOcena2 = statystyki.getOcenaPrzeciwnika(aktualnyGracz);
                    if(nowaOcena1 == staraOcena1 && nowaOcena2 == staraOcena2){
                        if(licznik > 0) 
                            licznik--;
                        else 
                            czySkonczylySieRuchy = true;
                        }
                    else
                        licznik = 8;
                    staraOcena1 = nowaOcena1;
                    staraOcena2 = nowaOcena2;
                    /**************************************************************************/
                    
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
            System.out.print("Wygrane ");
            komputer1.identyfikuj();
            System.out.print( zwyciestwaGracz1 + " Wygrane ");
            komputer2.identyfikuj();
            System.out.println(zwyciestwaGracz2);
        }

    }

}
