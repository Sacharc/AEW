package ai;

import model.Model;

public abstract class Komputer {
    protected Model model;
    /**
     * Wykonuje ruch lub krok bicia. Informuje czy zosta³ do wykonania jakiœ krok bicia
     * @return True jezeli zosta³ do wykonania jakiœ krok bicia
     */
    public abstract boolean update();
    
    public Komputer(Model model) {
        this.model = model;
    }
}
