package Kuehlschrank;

public final class Brot extends Speise {
    public Brot(int type, int menge) {
        super(type, menge);
    }

    @Override
    public boolean essen() {
        if (menge < 50) {
            menge = 0;
            return false;
        } else {
            menge = menge - 50;
            return true;
        }
    }
}
