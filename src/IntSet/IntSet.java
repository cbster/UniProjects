package IntSet;

/**
 * Mengen nichtnegativer ganzer Zahlen in kompakter Speicherrepraesentation: ob eine Zahl in der Menge enthalten ist, wir durch EIN BIT im Speicher erfasst!
 * Beispiel:
 *
 * IntSet set = new IntSet(8);
 * int a[] = { 1, 3, 4, 5 };
 * set.include( a );
 *
 * ... +---+---+---+---+---+---+---+---+
 * ... | 0 | 0 | 1 | 1 | 1 | 0 | 1 | 0 |
 * ... +---+---+---+---+---+---+---+---+
 */
public class IntSet {
    int arrayLength;
    int intValue;

    /**
     * konstruiert ein leere Zahlenmenge der Kapazitaet n: eine Menge, die (nichtnegative ganze) Zahlen im Bereich 0 bis n-1 als Elemente enthalten kann
     * @param n die Kapazitaet der Menge
     */
    public IntSet(int n) {
        arrayLength = n;
        this.intValue = 0;
    }

    /**
     * Used to create an IntSet that is not empty
     * @param n die Kapazitaet der Menge
     * @param m die IntValue der Menge
     */
    public IntSet(int n, int m) {
        arrayLength = n;
        this.intValue = m;
    }

    /**
     * ermittelt die Kapazitaet der Menge
     * @return die Kapazitaet der Menge
     */
    public int capacity() {
        return arrayLength;
    }

    /**
     * erzeugt aus this eine neue(identisch belegte) Zahlenmenge, die Werte im Bereicht 0 bis n-1 als Elemente enthalten kann
     * this bleibt unveraendert !
     *
     * Erzeugt eine Ausnahme, falls n kleiner ist, als die Kapazitaet von this
     * @param n die Kapazitaet der (groesseren) Ergebnismenge
     * @return die Ergebnismenge mit vergroesserter Kapazitaet
     * @throws SetError beim Versuch, die Menge zu verkleinern
     */
    public IntSet enlarge(int n) throws SetError {
        if (n <= this.capacity()) {
            throw new SetError("n smaller than or equal to existing set capacity");
        }
        IntSet newIntSet = new IntSet(n);
        newIntSet.intValue = this.intValue;
        return newIntSet;
    }

    /**
     * @param e eine nichtnegative ganze Zahl
     * @return ist e in dieser Menge enthalten?
     */
    public boolean element(int e) {
        return (((1 << e) & intValue) > 0);  // TODO test??
    }

    /**
     * erzeugt aus s1 und s2 die Vereinigungsmenge
     * es wird eine Menge der Kapazitaet der groesseren Kapazitaet der beiden Mengen erzeugt
     * s1 und s2 bleiben unveraendert!
     * @param s1 Mengen, die
     * @param s2 verknuepft werden sollen
     * @return die Vereinigungsmenge
     */
    public static IntSet union(IntSet s1, IntSet s2) {
        IntSet newIntSet = new IntSet((Math.max(s1.capacity(), s2.capacity())));
        newIntSet.intValue = s1.intValue | s2.intValue;
        return newIntSet;
    }

    /**
     * erzeugt aus s1 und s2 die Durchschnittsmenge
     * es wird eine Menge der Kapazitaet der groesseren Kapazitaet der beiden Mengen erzeugt
     * s1 und s2 bleiben unveraendert !
     * @param s1 Mengen, die
     * @param s2 verknuepft werden sollen
     * @return die Durchschnittsmenge
     */
    public static IntSet intersection(IntSet s1, IntSet s2) {
        IntSet newIntSet = new IntSet((Math.max(s1.capacity(), s2.capacity())));
        newIntSet.intValue = s1.intValue & s2.intValue;
        return newIntSet;
    }

    /**
     * erzeugt aus s1 und s2 die Differenzmenge
     * die Resultatmenge hat die Kapazitaet von s1 !
     * s1 und s2 bleiben unveraendert !
     * @param s1 Mengen, die
     * @param s2 verknuepft werden sollen
     * @return die Differenzmenge
     */
    public static IntSet difference(IntSet s1, IntSet s2) {
        IntSet newIntSet = new IntSet(s1.capacity());
        newIntSet.intValue = s1.intValue ^ s2.intValue;
        return newIntSet;
    }

    /**
     * nimmt die Zahl e in diese Menge auf (durch Setzen des entsprechenden Bits)
     * @param e eine nichtnegative ganze Zahl
     * @throws SetError falls die Kapazitaet der Menge fuer e nicht ausreicht
     */
    public void include(int e) throws SetError {
        if (e > arrayLength) {
            throw new SetError("Set too small");
        } else {
            intValue = intValue | (1 << e);
        }
    }

    /**
     * nimmt alle Elemente aus dem Array es in die Menge auf
     * @param es ein Array von nichtnegativen ganzen Zahlen
     * @throws SetError falls die Kapazitaet der Menge fuer ein Element aus es nicht ausreicht
     */
    public void include(int[] es) throws SetError {
        for (int e : es) {
            include(e);
            }
        }

    /**
     * berechnet die Komplementaermenge zu dieser Menge: eine Menge gleicher Kapazitaet, die genau alle Elemente enthaelt, die nicht in this enthalten sind
     * this bleibt unveraendert !
     * @return die Komplementaermenge zu this
     */
    public IntSet complement() {
        IntSet complementSet = new IntSet(arrayLength);
        complementSet.intValue = ~intValue;
        return complementSet;
    }

    /**
     * Stringrepraesentation dieser Menge z.B. "{ 1 3 4 5 }"
     * @return die Menge als String
     */
    public String toString() {
        int counter = 0;
        String bitsString = bits();
        StringBuilder result = new StringBuilder("{ ");
        for (char bit : bitsString.toCharArray()) {
            if (counter >= arrayLength) {
                break;
            }
            if (bit == 49) {
                result.append(" ").append(counter).append(" ");
            }
            counter++;
        }
        return result.append(" }").toString();
    }

    /**
     * Stringrepraesentation der Bits dieser Menge beginnend mit Bit 0 z.B. "01011100"
     * @return die Bits der Menge als String
     */
    public String bits() {
        StringBuilder bitsString = new StringBuilder();
        bitsString.append(Integer.toBinaryString(intValue));
        if (bitsString.length() > arrayLength) {
            return bitsString.reverse().substring(0, arrayLength);
        }
        while (bitsString.length() < arrayLength) {
            bitsString.insert(0, "0");
        }
        return bitsString.reverse().toString();
    }

    /**
     * erzeugt einen Iterator, mit dem ueber die Menge iteriert werden kann:
     *
     * for (IntSet.Iterator it = menge.iterator(); it.hasNext(); )
     * { ... it.next() ... }
     * @return ein Iterator auf diese Menge
     */
    public Iterator iterator() {
        return new Iterator(this);
    }

    public class Iterator {
        int position;
        int capacity;
        int intValue;

        /**
         * erzeugt einen Iterator ueber s
         * @param s die Menge, ueber die iteriert werden soll
         */
        public Iterator(IntSet s) {
            this.capacity = s.capacity();
            this.intValue = s.intValue;
        }

        /**
         * @return sind noch weitere Elemente ueber den Iterator in der Menge erreichbar?
         */
        public boolean hasNext() {
            return position < this.capacity;
        }

        /**
         * gibt das naechste Element (wenn noch ein weiteres existiert) zurueck, setzt den Iterator weiter
         * @return das naechste Element
         * @throws SetError wenn bereits das Ende der Menge erreicht ist
         */
        public int next() throws SetError {
            if (this.hasNext()) {
                try {
                    IntSet mask = new IntSet(this.capacity);
                    mask.include(position);
                    ++this.position;
                    return Integer.parseInt(intersection(new IntSet(this.capacity, this.intValue), mask).toString().replace("{", "").replace("}", "").strip());
                } catch (NumberFormatException e) {
                    return this.next();
                }
            } else throw new SetError("End of set");
        }
    }
}
