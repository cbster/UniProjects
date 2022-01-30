package IntSet;

/**
 * Mengen nicht negativer ganzer Zahlen in kompakter Speicherrepraesentation: ob eine Zahl in der Menge enthalten ist, wir durch EIN BIT im Speicher erfasst!
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
    private final int arrayLength;
    private int[] set;

    /**
     * konstruiert eine leere Zahlenmenge der Kapazitaet n: eine Menge, die (nichtnegative ganze) Zahlen im Bereich 0 bis n-1 als Elemente enthalten kann
     * @param n die Kapazitaet der Menge
     */
    public IntSet(int n) {
        arrayLength = n;
        this.set = new int[(int) Math.ceil((float)n/32)];  // Math.ceil prevents initialisation of a 0-array
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
        if (n <= this.arrayLength) {
            throw new SetError("n smaller than or equal to existing set capacity");
        }
        IntSet newIntSet = new IntSet(n);
        newIntSet.set = new int[(int) Math.ceil((float)n/32)];  //Increase set size accordingly
        System.arraycopy(this.set, 0, newIntSet.set, 0, this.set.length);  // Copy existing data
        return newIntSet;
    }

    /**
     * @param e eine nichtnegative ganze Zahl
     * @return ist e in dieser Menge enthalten?
     */
    public boolean element(int e) {
        return e <= this.capacity() && ((1 << e % 32 & set[e / 32]) > 0);  // Bit-masking to check for activated bit at element's position
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
        System.arraycopy(((s1.capacity() > s2.capacity()) ? s1.set : s2.set), 0, newIntSet.set, 0, (s1.capacity() > s2.capacity()) ? s1.set.length : s2.set.length); // Copy the larger sets contents in to the new set
        for (int i = 0; i < Math.min(s1.set.length, s2.set.length); i++) {
            newIntSet.set[i] = s1.set[i] | s2.set[i]; // Include elements from the smaller set in the larger set
        }
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
        IntSet newIntSet = new IntSet((Math.max(s1.capacity(), s2.capacity()))); // No arraycopy as the smaller set cannot intersect with the larger set's out of range numbers
        for (int i = 0; i < Math.min(s1.set.length, s2.set.length); i++) {
            newIntSet.set[i] = s1.set[i] & s2.set[i];
        }
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
    public static IntSet difference(IntSet s1, IntSet s2) throws SetError {
        IntSet newIntSet = new IntSet(s1.arrayLength);
        for (int i = 0; i < Math.max(s1.arrayLength, s2.arrayLength); i++) {
            if (s1.element(i) && !s2.element(i)) {
                newIntSet.include(i);
            }
        }
        return newIntSet;
    }

    /**
     * nimmt die Zahl e in diese Menge auf (durch Setzen des entsprechenden Bits)
     * @param e eine nichtnegative ganze Zahl
     * @throws SetError falls die Kapazitaet der Menge fuer e nicht ausreicht
     */
    public void include(int e) throws SetError {
        if (e >= arrayLength) {
            throw new SetError("Set too small");
        } else {
            this.set[e/32] = this.set[e/32] | (1 << e); // Bit-shift 1 bit into the set's corresponding integer
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
        for (int i = 0; i < complementSet.set.length; i++) {
            complementSet.set[i] = ~this.set[i]; // Invert bits for each integer in the set
        }
        return complementSet;
    }

    /**
     * Stringrepraesentation dieser Menge z.B. "{ 1 3 4 5 }"
     * @return die Menge als String
     */
    public String toString() {
        int counter = 0;
        StringBuilder result = new StringBuilder("{ "); // StringBuilder helps format the string with the parentheses on either end
        for (char bit : bits().toCharArray()) {  // Iterate across each bit in the array
            if (counter >= arrayLength) {  // Do not print further than the capacity of the IntSet, even if there may be bits there
                break;
            }
            if (bit == 49) { // If bit is set (1)
                result.append(counter).append(" ");
            }
            counter++;
        }
        return result.append("}").toString();
    }

    /**
     * Stringrepraesentation der Bits dieser Menge beginnend mit Bit 0 z.B. "01011100"
     * @return die Bits der Menge als String
     */
    public String bits() {
        StringBuilder bitsString = new StringBuilder();
        for (int part : this.set) { // Iterates over each integer in this.set
            StringBuilder partString = new StringBuilder();  // The line below fills leading zeros for printing
            partString.append(part == 0 ? "00000000000000000000000000000000" : String.format("%32s", Integer.toBinaryString(part)).replace(" ", "0"));
            bitsString.append(partString.reverse());
        }
        if (bitsString.length() > arrayLength) {
            return bitsString.substring(0, arrayLength); // Only print as many bits as the capacity of the IntSet
        }
        while (bitsString.length() < arrayLength) {
            bitsString.insert(0, "0"); // Insert leading zeros to match IntSet capacity
        }
        return bitsString.toString();
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
        private final IntSet origin; // IntSet from which the Iterator is created
        private int position;
        private final int capacity;

        /**
         * erzeugt einen Iterator ueber s
         * @param s die Menge, ueber die iteriert werden soll
         */
        public Iterator(IntSet s) {
            this.origin = s;
            this.capacity = s.capacity();
        }

        /**
         * @return sind noch weitere Elemente ueber den Iterator in der Menge erreichbar?
         */
        public boolean hasNext() {  // todo fix - produces true even if no further numbers exist in this.set
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
                    return Integer.parseInt(intersection(origin, mask).toString().replace("{", "").replace("}", "").strip());
                } catch (NumberFormatException e) {
                    return this.next();
                }
            } else throw new SetError("End of set");
        }
    }
}
