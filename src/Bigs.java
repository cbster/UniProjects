class Bigs {
    // addiert die Ziffernfelder a und b
    public static int[] add(int[] a, int[] b) {
        int[] longerArr = (a.length > b.length) ? a : b;
        int[] sum = new int[longerArr.length + 1];
        System.arraycopy(longerArr, 0, sum, 0, longerArr.length);  // Copy longer number into sum
        for (int i = 0; i < Math.min(a.length, b.length); i++) {  // Add the components of the shorter number to the longer
            sum[i] = a[i] + b[i];
        }
        for (int i = 0; i < sum.length - 1; i++) {  // Carry over 10s etc. e.g [19, 1] -> [9, 2]
            if (sum[i] > 9) {
                sum[i] = sum[i] - 10;
                sum[i + 1]++;
            }
        }
        sum = removeZeros(sum);
        return sum;
    }

    private static int[] removeZeros(int[] inputArr) {  // Removes leading zeros using ok check
        while (!ok(inputArr)) {
            int[] newArr = new int[inputArr.length - 1];
            System.arraycopy(inputArr, 0, newArr, 0, inputArr.length - 1);
            inputArr = newArr;
        }
        return inputArr;
    }

    // gibt das Ziffernfeld n in lesbarer dezimaler Form aus
    static void print(int[] n) {
        for (int i = n.length - 1; i >= 0; i--) {  // Print in reverse to form traditional decimal form
            System.out.print((((n.length - i) % 68 == 0) ? n[i] + "\\\n" : n[i]));  // If 68th line, print backslash and newline
        }
    }

    // konstruiert ein einstelliges Ziffernfeld aus d
    static int[] digit(int d) {  // Self-explanatory
        return new int[]{d};
    }

    // konstruiert das Ziffernfeld, welches den Wert Null repraesentiert
    static int[] Null() { // Self-explanatory
        return new int[]{0};
    }

    // konstruiert das Ziffernfeld, welches den Wert Eins repraesentiert
    static int[] One() { // Self-explanatory
        return new int[]{1};
    }

    // Rest des Ziffernfeldes n bei Division durch 10 (eine int-Zahl!)
    static int mod10(int[] n) { // Self-explanatory
        return n[0];
    }

    // ganzzahliger Quotient bei Division durch 10
    static int[] div10(int[] n) {
        if (n.length == 1) {  // Any number < 10 will return 0
            return new int[]{0};
        }
        int[] quotArr = new int[n.length - 1];
        for (int i = n.length - 1; i > 0; i--) {  // Copies all but first index of n to quotArr
            quotArr[quotArr.length - i] = n[n.length - i];
        }
        return removeZeros(quotArr);
    }

    // Umwandlung einer int-Zahl in ein Ziffernfeld
    static int[] fromInt(int n) {
        String strNumber = Integer.toString(n);
        int[] nums = new int[strNumber.length()];
        for (int i = strNumber.length() - 1; i >= 0; i--) {  // Read in characters in reverse to get correct array form
            nums[(strNumber.length() - 1 - i)] = Character.getNumericValue(strNumber.charAt(i));
        }
        return nums;
    }

    // kopiert den Wert von a
    static int[] copy(int[] n) {  // Self-explanatory
        int[] clone = new int[n.length];
        System.arraycopy(n, 0, clone, 0, n.length);
        return clone;
    }

    // multipliziert das Ziffernfeld a mit einer int-Zahl
    static int[] times(int[] n, int d) {  // Self-explanatory
        int[] dArr = digit(d);
        return times(n, dArr);
    }

    // multipliziert das Ziffernfeld n mit 10
    static int[] times10(int[] n) {  // Self-explanatory
        return times(n, fromInt(10));
    }

    // multipliziert zwei Ziffernfelder
    static int[] times(int[] a, int[] b) {  // This was the fun one!
        if ((a[0] == 0 && a.length == 1) || (b[0] == 0 && b.length == 1)) {
            return new int[]{0};  // If a or b are [0], return [0]
        }
        int[] product = new int[a.length + b.length];
        for (int i = 0; i < a.length; i++) {  // For every digit in a
            for (int j = 0; j < b.length; j++) {  // For every digit in b
                product[i + j] += a[i] * b[j];  // The product at a certain index is each digit of a multiplied with each digit of b
            }
        }
        for (int i = 0; i < product.length - 1; i++) {  // Carry over array components with value greater than 9
            product[i + 1] += product[i] / 10;
            product[i] = product[i] % 10;
        }
        return removeZeros(product);  // Remove leading zeros and return
    }

    // Quadratzahl eines Ziffernfeldes
    static int[] square(int[] a) {  // Self-explanatory
        return times(a, a);
    }

    // Kubikzahl eines Ziffernfeldes
    static int[] cubic(int[] a) {  // Self-explanatory
        return times(times(a, a), a);
    }

    // Test auf kleiner-Relation zweier Ziffernfelder: a < b ?
    static boolean less(int[] a, int[] b) {
        if (a.length != b.length) {
            return b.length > a.length;  // If lengths not equal, return true if b longer, false if a longer
        }
        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] != b[i]) {  // If lengths are equal, check components in reverse for equality
                return b[i] > a[i];  // First inequality found will determine whether a < b
            }
        }
        return false;  // Returns false if neither condition is satisfied, as then a = b
    }

    // Test auf Gleichheit zweier Ziffernfelder
    static boolean equal(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {  // Checks all components of array for equality
                return false;
            }
        }
        return true;
    }

    // Test auf Korrektheit eines Ziffernfeldes: Feld existiert und enthaelt
    // mindestens eine Ziffer, alle Positionen liegen zwischen 0 und 9
    // keine fuehrenden Nullen (ausser bei Null selbst)
    static boolean ok(int[] n) {
        if (n.length == 0) {  // Cannot be empty
            return false;
        }
        for (int num : n) {  // Must contain numbers between 0 and 9
            if (num > 9 || num < 0) {
                return false;
            }
        }
        return (n[n.length - 1] >= 1) || (n.length == 1);  // Checks for leading zeros, allowing one if 0 is the only digit
    }

    // gibt die (kleinste) Ziffer mit der groessten Haeufigkeit in n aus
    static void maxDigit(int[] n) {
        int[] counter = new int[10];  // Counter where index position correlates to integer being counted
        for (int j : n) {
            counter[j]++;  // For each number in n, increment its corresponding counter
        }
        int maxPos = 0;
        int maxVal = 0;
        for (int i = 0; i < counter.length; i++) {  // Scans through counter to determine max digit
            if (counter[i] > maxVal) {  // If counter[i] larger than last found max count (not equal to! this gives the smallest, most common number)
                maxPos = i;  // Remember that position correlates to integer counted
                maxVal = counter[i];  // Reassign the highest count and move on to the next
            }
        }
        System.out.println(maxPos + ": " + maxVal);
    }

    public static void main(String[] s) {
        int[] a = One();
        fromInt(123);
        for (int i = 0; i < 33222; ++i) {
            a = times(a, 2);
        }

        System.out.println("2^33222 hat " + a.length + " Stellen");
        print(a);
        System.out.println();

        int[] b = fromInt(13);
        int[] c = copy(b);

        for (int i = 1; i < 8978; ++i) {
            c = times(c, b);
        }

        System.out.println("13^8978 hat " + c.length + " Stellen");
        print(c);
        System.out.println();

        System.out.println(less(a, c)); // beantwortet die Frage aus der Aufgabenueberschrift
        maxDigit(a);
        maxDigit(c);
    }
}
