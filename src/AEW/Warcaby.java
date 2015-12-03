package AEW;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import AEW.model.Model;
import AEW.model.Wlasciciel;
import AEW.widok.Widok;

public class Warcaby {

    public static void main(String[] args) {
		//TODO Mati na drugi raz nie zostawiaj takich smaczkow, ze jak odpalam maina to mi komp prawie zwieche lapie na tych 1000 obiegach w mainie ;p
    	for(int i = 0 ; i < 1000 ; i++){
        Model model = new Model();
        Widok widok = new Widok();
        Wlasciciel aktualnyGracz = Wlasciciel.gracz1;
        widok.uaktualnij(model.getPlansza());
        while(model.getPlansza().sprawdzCzyKoniecGry()){
        	if(aktualnyGracz == Wlasciciel.gracz2)
         		model.zmianaWspolrzednych();
        	model.sprawdzDostepneRuchy(aktualnyGracz);
        	if(model.getPlansza().getListaBic().size() > 0){
        		while(model.wykonajBicie()){
        			widok.uaktualnij(model.getPlansza());
        		}
        	}
        	else{
        		if(model.getPlansza().getListaRuchu().size() > 0)
            	model.wykonajRuch();
        	}
        	if(aktualnyGracz == Wlasciciel.gracz2)
         		model.zmianaWspolrzednych();
        	model.czyscListy();
        	widok.uaktualnij(model.getPlansza());
        	if(aktualnyGracz == Wlasciciel.gracz1)
         		aktualnyGracz = Wlasciciel.gracz2;
         	else
         		aktualnyGracz = Wlasciciel.gracz1;
        	/*try {
        	    Thread.sleep(10);                 //1000 milliseconds is one second.
        	} catch(InterruptedException ex) {
        	    Thread.currentThread().interrupt();
        	}*/
        	
        }
        widok.zamknij();
    	}
    }

}
