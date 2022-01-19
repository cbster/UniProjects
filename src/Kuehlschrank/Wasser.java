package Kuehlschrank;

public final class Wasser extends Getraenk {

    public Wasser(String name, int menge) {
        super(name, menge);
    }

    @Override
    public boolean trinken() {
        if (menge < 200) {
            menge = 0;
            return false;
        } else {
            menge = menge - 200;
            return true;
        }
    }

}
