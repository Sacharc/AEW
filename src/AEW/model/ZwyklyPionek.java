package AEW.model;

final class ZwyklyPionek extends Pionek {

    /**
     * konstruktor tworzacy pionek z wlascicielem
     * 
     * @param gracz1
     */
    public ZwyklyPionek(Wlasciciel gracz1) {
        wlasciciel = gracz1;
    }

    @Override
    boolean czyMozeRuszyc() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    void ruch(int x, int y) {
        // TODO Auto-generated method stub
    }

    @Override
    int wykonajBicie(Kierunek k) {
        // TODO Auto-generated method stub
        return 0;
    }

}
