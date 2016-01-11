package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Plansza{

    private List <Ruch> listaRuchow = new <Ruch>ArrayList();
    private List <Ruch> listaBic = new <Ruch>ArrayList();

    private Pole pola[][];

    public Plansza(Wlasciciel gracz1, Wlasciciel gracz2)
    {
        initPola();
        this.pola[3][0].addPionek(gracz1);
        this.pola[4][1].addPionek(gracz2);
        this.pola[5][2].addPionek(gracz2);
        //this.pola[i][2*j+1].addPionek(gracz1);
        /*for(int i = 0 ; i < 8 ; i++){
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
        }*/
    }

    public Plansza(Plansza p) {
        this.pola = new Pole[8][8];
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                this.pola[i][j] = new Pole(p.pola[i][j]);
    }

    public Plansza(Plansza plansza, boolean pobierzListy){
        this.pola = new Pole[8][8];
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                this.pola[i][j] = new Pole(plansza.pola[i][j]);
        this.listaRuchow = new ArrayList<Ruch>(plansza.listaRuchow);
        this.listaBic = new ArrayList<Ruch>(plansza.listaBic);;
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

    public void zmienPolozeniePionka(int x1,int y1, int x2, int y2){
        boolean czyBylDamka = false;
        if(pola[x1][y1].getPionek().getCzyDamka())
            czyBylDamka = true;
        Wlasciciel gracz = pola[x1][y1].removePionek();
        pola[x2][y2].addPionek(gracz);
        if(x2 == 7 || czyBylDamka){
            pola[x2][y2].getPionek().setCzyDamka(true);
        }
    }

    public Pole getPole(int i, int j)
    {
        return this.pola[i][j];
    }

    public void czyscListyRuchowBic(){
        listaRuchow = new <Ruch>ArrayList();
        listaBic = new <Ruch>ArrayList();
    }

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
                listaRuchow.add(new Ruch(p.getX(),p.getY(),p.getX()+1,p.getY()-1, false));
            }
            //sprawdzamy czy jest damka
            if(p.getPionek().getCzyDamka()){
                //sprawdzamy wieloktorny ruch w dol lewo
                int x=p.getX()+2;
                int y=p.getY()-2;
                while(x <= 7 && y >= 0 && this.pola[x][y].getPionek() == null){
                    listaRuchow.add(new Ruch(p.getX(),p.getY(),x,y, false));
                    x++;
                    y--;
                }
                //sprawdzamy wielokrotny ruch w gore prawo
                if(p.getX()>0){
                    x=p.getX()-1;
                    y=p.getY()-1;
                    while(x >= 0 && y >= 0 && this.pola[x][y].getPionek() == null){
                        listaRuchow.add(new Ruch(p.getX(),p.getY(),x,y, false));
                        x--;
                        y--;
                    }
                }
            }
            if(p.getX() < 6 && p.getY() > 1){
                if(this.pola[p.getX()+1][p.getY()-1].getPionek() != null && p.getPionek().getWlasciciel() != pola[p.getX()+1][p.getY()-1].getPionek().getWlasciciel() && this.pola[p.getX()+2][p.getY()-2].getPionek() == null){
                    listaBic.add(new Ruch(p.getX(),p.getY(),p.getX()+2,p.getY()-2, true));

                }
                else if(p.getPionek().getCzyDamka()){
                    int x = p.getX()+2;
                    int y = p.getY()-2;
                    while(x <= 6 && y >= 1){
                        if(this.pola[x][y].getPionek() != null && p.getPionek().getWlasciciel() != pola[x][y].getPionek().getWlasciciel() && this.pola[x+1][y-1].getPionek() == null){
                            listaBic.add(new Ruch(p.getX(),p.getY(),x+1,y-1, true));
                            break;
                        }
                        x++;
                        y--;
                    }
                }
            }
            if(p.getX() > 1 && p.getY() > 1){
                if(this.pola[p.getX()-1][p.getY()-1].getPionek() != null && p.getPionek().getWlasciciel() != pola[p.getX()-1][p.getY()-1].getPionek().getWlasciciel() && this.pola[p.getX()-2][p.getY()-2].getPionek() == null){
                    listaBic.add(new Ruch(p.getX(),p.getY(),p.getX()-2,p.getY()-2, true));
                }
                else if(p.getPionek().getCzyDamka()){
                    int x = p.getX()-2;
                    int y = p.getY()-2;
                    while(x >= 1 && y >= 1){
                        if(this.pola[x][y].getPionek() != null && p.getPionek().getWlasciciel() != pola[x][y].getPionek().getWlasciciel() && this.pola[x-1][y-1].getPionek() == null){
                            listaBic.add(new Ruch(p.getX(),p.getY(),x-1,y-1, true));
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
                listaRuchow.add(new Ruch(p.getX(),p.getY(),p.getX()+1,p.getY()+1, false));
            if(p.getPionek().getCzyDamka()){
                //wielokrotny ruch w prawo dol
                int x=p.getX()+2;
                int y=p.getY()+2;
                while(x <= 7 && y <= 7 && this.pola[x][y].getPionek() == null){
                    listaRuchow.add(new Ruch(p.getX(),p.getY(),x,y, false));
                    x++;
                    y++;
                }
                //wielokrotny ruch w prawo gore
                if(p.getX() > 0){
                    x=p.getX()-1;
                    y=p.getY()+1;
                    while(x >= 0 && y <= 7 && this.pola[x][y].getPionek() == null){
                        listaRuchow.add(new Ruch(p.getX(),p.getY(),x,y, false));
                        x--;
                        y++;
                    }
                }
            }
            if(p.getX() < 6 && p.getY() < 6){
                if(this.pola[p.getX()+1][p.getY()+1].getPionek() != null && p.getPionek().getWlasciciel() != pola[p.getX()+1][p.getY()+1].getPionek().getWlasciciel() && this.pola[p.getX()+2][p.getY()+2].getPionek() == null){
                    listaBic.add(new Ruch(p.getX(),p.getY(),p.getX()+2,p.getY()+2, true));
                }
                else if(p.getPionek().getCzyDamka()){
                    int x = p.getX()+2;
                    int y = p.getY()+2;
                    while(x <= 6 && y <= 6){
                        if(this.pola[x][y].getPionek() != null && p.getPionek().getWlasciciel() != pola[x][y].getPionek().getWlasciciel() && this.pola[x+1][y+1].getPionek() == null){
                            listaBic.add(new Ruch(p.getX(),p.getY(),x+1,y+1, true));
                            break;
                        }
                        x++;
                        y++;
                    }
                }
            }
            if(p.getX() > 1 && p.getY() < 6){
                if(this.pola[p.getX()-1][p.getY()+1].getPionek() != null && p.getPionek().getWlasciciel() != pola[p.getX()-1][p.getY()+1].getPionek().getWlasciciel() && this.pola[p.getX()-2][p.getY()+2].getPionek() == null){
                    listaBic.add(new Ruch(p.getX(),p.getY(),p.getX()-2,p.getY()+2, true));
                }
                else if(p.getPionek().getCzyDamka()){
                    int x = p.getX()-2;
                    int y = p.getY()+2;
                    while(x >= 1 && y <= 6){
                        if(this.pola[x][y].getPionek() != null && p.getPionek().getWlasciciel() != pola[x][y].getPionek().getWlasciciel() && this.pola[x-1][y+1].getPionek() == null){
                            listaBic.add(new Ruch(p.getX(),p.getY(),x-1,y+1, true));
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
     * @return true jezeli da sie grac dalej
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
                    else
                        if(p.getPionek().getWlasciciel() == Wlasciciel.gracz2){
                            czyJestPionekGracza2 = true;
                        }
                }
            }
        }
        return czyJestPionekGracza1 && czyJestPionekGracza2;
    }

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
                zmienionaPlansza[7-p.getX()][7-p.getY()] = p;
                p.setX(7-p.getX());
                p.setY(7-p.getY());
            }
        }
        this.pola = zmienionaPlansza;
    }

    public List <Ruch> getListaBic()
    {
        return listaBic;
    }

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
        if(!listaBic.isEmpty())
            return listaBic.size();
        return listaRuchow.size();
    }

    /**
     * Liczy pionki gracza
     * @param wlasciciel
     * @return liczba pionkow gracza
     */
    public int liczPionkiGracza(Wlasciciel wlasciciel) {
        int liczba = 0;
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++) {
                if (pola[i][j].getPionek() != null) {
                    if (pola[i][j].getPionek().getWlasciciel() == wlasciciel)
                        liczba++;
                }
            }
        return liczba;
    }

    /**
     * Sprawdza kto wygral
     * @return Gracz ktory wygral
     */
    public Wlasciciel ktoWygral() {
        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                if (pola[i][j].getPionek() != null)
                    return pola[i][j].getPionek().getWlasciciel();
        return null;
    }


    public boolean wykonajBicie(Ruch r) {
        int x1 = r.getX1();
        int x2 = r.getX2();
        int y1 = r.getY1();
        int y2 = r.getY2();

        zmienPolozeniePionka(x1, y1, x2,y2);
        if(x1<x2 && y1<y2){
            getPole(x2-1, y2-1).removePionek();
        }
        else if(x1>x2 && y1<y2){
            getPole(x2+1, y2-1).removePionek();
        }
        else if(x1<x2 && y1>y2){
            getPole(x2-1, y2+1).removePionek();
        }
        else{
            getPole(x2+1, y2+1).removePionek();
        }
        czyscListyRuchowBic();
        sprawdzBiciaRuchyPionka(getPole(x2, y2));
        if(getListaBic().size() > 0){
            return true;
        }
        return false;
    }

    public void wykonajZwyklyRuch(Ruch ruch) {
        int x1 = ruch.getX1();
        int x2 = ruch.getX2();
        int y1 = ruch.getY1();
        int y2 = ruch.getY2();
        zmienPolozeniePionka(x1, y1, x2, y2);
    }

    public boolean wykonajRuch(Ruch ruch){
        if(listaBic.contains(ruch))
            return this.wykonajBicie(ruch);
        else if(listaRuchow.contains(ruch))
            this.wykonajZwyklyRuch(ruch);
        return false;
    }

    public boolean wykonajRuchNr(int nr) {
        if(!getListaBic().isEmpty())
            return wykonajBicie(getListaBic().get(nr));
        if(!getListaRuchu().isEmpty())
            wykonajZwyklyRuch(getListaRuchu().get(nr));
        return false;
    }

    public boolean wykonajRuchNr(double nr) {
        int numer = (int)(nr * getLiczbaRuchow());
        return wykonajRuchNr(numer);
    }

    public Statystyki getStatystyki() {
        int liczbaPionkowGracz1 = 0;
        int liczbaDamekGracz1 = 0;
        int liczbaPionkowGracz2 = 0;
        int liczbaDamekGracz2 = 0;

        for(Pole[] linia : this.pola) {
            for(Pole p : linia) {
                Pionek pionek = p.getPionek();
                if(pionek != null) {
                    if(pionek.getWlasciciel() == Wlasciciel.gracz1) {
                        liczbaPionkowGracz1++;
                        if(pionek.getCzyDamka())
                            liczbaDamekGracz1++;
                    }
                    else {
                        liczbaPionkowGracz2++;
                        if(pionek.getCzyDamka())
                            liczbaDamekGracz1++;
                    }
                }
            }
        }
        return new Statystyki(liczbaPionkowGracz1, liczbaDamekGracz1, liczbaPionkowGracz2, liczbaDamekGracz2);
    }

    public boolean equalsByPola(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plansza plansza = (Plansza) o;

        return Arrays.deepEquals(pola, plansza.pola);

    }
}
