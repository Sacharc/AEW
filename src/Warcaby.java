import java.util.Scanner;

import ai.Komputer;
import ai.algorytmEwolucyjny.KomputerEwolucyjny;
import ai.algorytmMinMax.KomputerMinMax;
import ai.losowy.KomputerLosowy;
import model.Model;
import model.Statystyki;
import model.Wlasciciel;
import widok.Widok;

public class Warcaby {

    private static void spij(){
        try {
            Thread.sleep(450);                 //1000 milliseconds is one second.
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

        System.out.println("wybierz opcje (np. '1'): \n1.Ewolucyjny vs Losowy \n2.Losowy vs MinMax \n3.Ewolucyjny vs MinMax");
        Scanner odczyt = new Scanner(System.in);
        String komenda = odczyt.nextLine();
        for(int i = 0 ; i < 100 ; i++){
            staraOcena1 = 0;
            staraOcena2 = 0;
            licznik = 8;
            
            staraOcena1 = 0;
            staraOcena2 = 0;
            licznik = 8;
            
            Model model = new Model();
            Widok widok = new Widok();
            widok.uaktualnij(model.getPlansza());
            czySkonczylySieRuchy = false;
            Komputer komputer1 = null;
            Komputer komputer2 = null;
            if(komenda.equals("1")){
            	komputer1 = new KomputerEwolucyjny(model, Wlasciciel.gracz1);
            	komputer2 = new KomputerLosowy(model, Wlasciciel.gracz2);
            }
            else if(komenda.equals("2")){
            	komputer1 = new KomputerLosowy(model, Wlasciciel.gracz1);
            	komputer2 = new KomputerMinMax(model, Wlasciciel.gracz2);
            }
            else{
            	komputer1 = new KomputerEwolucyjny(model, Wlasciciel.gracz1);
            	komputer2 = new KomputerMinMax(model, Wlasciciel.gracz2);
            }

            
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
                //spij();
                
            }
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
