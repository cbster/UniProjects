package Kuehlschrank;

public abstract class Speise extends Lebensmittel {
    public Speise(String name, int menge) {
        super(name, menge);
    }

    public Speise(int type, int menge) {
        super(type, menge);
    }


    @Override
    public abstract boolean essen();

    @Override
    public boolean trinken() {
        return false;
    }
}
