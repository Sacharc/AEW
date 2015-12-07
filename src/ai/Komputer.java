package ai;

import model.Model;

public abstract class Komputer {
    protected Model model;
    /**
     * Wykonuje ruch lub krok bicia. Informuje czy zosta� do wykonania jaki� krok bicia
     * @return True jezeli zosta� do wykonania jaki� krok bicia
     */
    public abstract boolean update();
    
    public Komputer(Model model) {
        this.model = model;
    }
}
