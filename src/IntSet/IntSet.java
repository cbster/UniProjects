package IntSet;

public class IntSet {
    int arrayLength;
    int intValue;  // Use base 10 representation of base 2 number to perform bitwise operations
                   // Representations have to be backwards because 0b notation does not allow leading zeros

    public IntSet(int n) {
        arrayLength = n;
        this.intValue = 0;
    }

    public int capacity() {
        return arrayLength;
    }

    public IntSet enlarge(int n) throws SetError {
        if (n <= this.capacity()) {
            throw new SetError("n smaller than set");
        }
        IntSet newIntSet = new IntSet(n);
        newIntSet.intValue = this.intValue;
        return newIntSet;
    }

    public boolean element(int e) {
        return (((1 << e) & intValue) > 0);  // TODO
    }

    public static IntSet union(IntSet s1, IntSet s2) {
        IntSet newIntSet = new IntSet((Math.max(s1.capacity(), s2.capacity())));
        newIntSet.intValue = s1.intValue | s2.intValue;
        return newIntSet;
    }

    public static IntSet intersection(IntSet s1, IntSet s2) {
        IntSet newIntSet = new IntSet((Math.max(s1.capacity(), s2.capacity())));
        newIntSet.intValue = s1.intValue & s2.intValue;
        return newIntSet;
    }

    public static IntSet difference(IntSet s1, IntSet s2) {
        IntSet newIntSet = new IntSet((Math.max(s1.capacity(), s2.capacity())));
        newIntSet.intValue = s1.intValue ^ s2.intValue;
        return newIntSet;
    }

    public void include(int e) throws SetError {
        if (e >= arrayLength) {
            throw new SetError("Set too small");
        } else {
            intValue = intValue | (1 << e);
        }
    }

    public void include(int[] es) throws SetError {
        for (int e : es) {
            if (e >= arrayLength) {
                throw new SetError("Set too small");
            } else {
                intValue = intValue | (1 << e);
            }
        }
    }

    public IntSet complement() {  // TODO
        IntSet complementSet = new IntSet(arrayLength);
        complementSet.intValue = ~intValue;
        return complementSet;
    }

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

    public Iterator iterator() {
        return new Iterator(this);
    }

    public class Iterator {
        public Iterator(IntSet s) {/* TODO */}

        public boolean hasNext() {return true;/* TODO */}

        public int next() throws SetError {return 0;/* TODO */}
    }
}
