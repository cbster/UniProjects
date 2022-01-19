package Kuehlschrank;

public final class Mate extends Getraenk {

    public Mate(String name) {
        super(name, 500);
    }

    @Override
    public boolean trinken() {
        if (menge < 100) {
            menge = 0;
            return false;
        } else {
            menge = menge - 100;
            return true;
        }
    }

}
