package Kuehlschrank;

public abstract class Lebensmittel {
    private final String name;
    protected int menge; // in Milliliter bzw. Gramm

    public Lebensmittel(String name, int menge) {
        this.name = name;
        this.menge = menge;
    }

    public Lebensmittel(int type, int menge) {  // Second constructor for bread as we are given a type instead of a name
        switch (type) {
            case 0 -> this.name = "Weißbrot";
            case 1 -> this.name = "Schwarzbrot";
            case 2 -> this.name = "Mischbrot";
            default -> this.name = "Spezialbrot";
        }
        this.menge = menge;  // Menge is still given as in the constructor above
    }


    public abstract boolean essen();

    public abstract boolean trinken();


    public String toString() {
        return String.format("%s (%s, %s %s)", this.getClass().getSimpleName(), this.name, this.menge, (this instanceof Getraenk) ? "ml" : "g");  // Overrides java.lang.Object.toString()
    }
}