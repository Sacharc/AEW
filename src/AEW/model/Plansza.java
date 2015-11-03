package AEW.model;

class Plansza {
    Pole [][] plansza;
    
    Plansza() {
	//TODO
    }
    
    /**
     * Zwraca pole o podonach wspolrzednych
     * @param x
     * @param y
     * @return Pole (x, y) lub null jezeli wspolrzedne nie naleza do planszy
     */
    Pole getPole(int x, int y) {
	if (x > -1 && x < 8 && y > -1 && y < 8)
	    return plansza[x][y];
	return null;
    }

}
