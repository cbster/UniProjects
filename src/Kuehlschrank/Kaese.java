package Kuehlschrank;

public final class Kaese extends Speise {
    public Kaese(String name, int menge) {
        super(name, menge);
    }

    @Override
    public boolean essen() {
        if (menge < 20) {
            menge = 0;
            return false;
        } else {
            menge = menge - 20;
            return true;
        }
    }
}
