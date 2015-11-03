package AEW.model;

/**
 * Mo¿e zawieraæ pionek lub byæ puste
 * @author Mmm
 *
 */
class Pole {
    Pionek p;
    
    Pole() {
	p = null;
    }

    Pionek getP() {
        return p;
    }

    void setP(Pionek p) {
        this.p = p;
    }
    
    boolean isEmpty() {
	return p == null;
    }
    
    void clear() {
	p = null;
    }
}
