package Kuehlschrank;

public abstract class Getraenk extends Lebensmittel {
    public Getraenk(String name, int menge) {
        super(name, menge);
    }

    @Override
    public boolean essen() {
        return false;
    }

    @Override
    public abstract boolean trinken();


}
