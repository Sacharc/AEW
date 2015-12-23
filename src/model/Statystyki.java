package model;

/**
 * Klasa zawiera statystki pozwalajace graczom komputerowym na ocene sytuacji na planszy
 */
public class Statystyki {
    private int liczbaPionkowGracz1;
    private int liczbaDamekGracz1;
    private int liczbaPionkowGracz2;
    private int liczbaDamekGracz2;

    /**
     * @param liczbaPionkowGracz1
     * @param liczbaDamekGracz1
     * @param liczbaPionkowGracz2
     * @param liczbaDamekGracz2
     */
    public Statystyki(int liczbaPionkowGracz1, int liczbaDamekGracz1, int liczbaPionkowGracz2, int liczbaDamekGracz2) {
        super();
        this.liczbaPionkowGracz1 = liczbaPionkowGracz1;
        this.liczbaDamekGracz1 = liczbaDamekGracz1;
        this.liczbaPionkowGracz2 = liczbaPionkowGracz2;
        this.liczbaDamekGracz2 = liczbaDamekGracz2;
    }

    public Statystyki() {
        this.liczbaPionkowGracz1 = 0;
        this.liczbaDamekGracz1 = 0;
        this.liczbaPionkowGracz2 = 0;
        this.liczbaDamekGracz2 = 0;
    }

    public int getLiczbaPionkowGracza(Wlasciciel wlasciciel) {
        if(wlasciciel == Wlasciciel.gracz1)
            return liczbaPionkowGracz1;
        return liczbaPionkowGracz2;
    }

    public int getLiczbaPionkowPrzeciwnika (Wlasciciel wlasciciel) {
        if(wlasciciel == Wlasciciel.gracz1)
            return liczbaPionkowGracz2;
        return liczbaDamekGracz1;
    }

    public int getLiczbaDamekGracza(Wlasciciel wlasciciel) {
        if(wlasciciel == Wlasciciel.gracz1)
            return liczbaDamekGracz1;
        return liczbaDamekGracz2;
    }

    public int getLiczbaDamekPrzeciwnika(Wlasciciel wlasciciel) {
        if(wlasciciel == Wlasciciel.gracz1)
            return liczbaDamekGracz2;
        return liczbaDamekGracz1;
    }

    public int getOcenaGracza(Wlasciciel wlasciciel) {
        return 3 * getLiczbaDamekGracza(wlasciciel) + getLiczbaPionkowGracza(wlasciciel);
    }

    public int getOcenaPrzeciwnika(Wlasciciel wlasciciel) {
        return 3 * getLiczbaDamekPrzeciwnika(wlasciciel) + getLiczbaPionkowPrzeciwnika(wlasciciel);
    }
}
