class Bigs {
    // addiert die Ziffernfelder a und b
    public static int[] add(int[] a, int[] b) {
        int[] longerArr = (a.length > b.length) ? a : b;
        int[] sum = new int[longerArr.length + 1];
        System.arraycopy(longerArr, 0, sum, 0, longerArr.length);
        for (int i = 0; i < Math.min(a.length, b.length); i++) {
            sum[i] = a[i] + b[i];
        }
        for (int i = 0; i < sum.length - 1; i++) {
            if (sum[i] > 9) {
                sum[i] = sum[i] - 10;
                sum[i + 1]++;
            }
        }
        sum = removeZeros(sum);
        return sum;
    }

    private static int[] removeZeros(int[] sum) {
        while (!ok(sum)) {
            int[] newSum = new int[sum.length - 1];
            System.arraycopy(sum, 0, newSum, 0, sum.length - 1);
            sum = newSum;
        }
        return sum;
    }

    // gibt das Ziffernfeld n in lesbarer dezimaler Form aus
    static void print(int[] n) {
        for (int i = n.length - 1; i >= 0; i--) {
            System.out.print((((n.length - i) % 68 == 0) ? n[i] + "\\\n" : n[i]));
        }
    }

    // konstruiert ein einstelliges Ziffernfeld aus d
    static int[] digit(int d) {
        return new int[]{d};
    }

    // konstruiert das Ziffernfeld, welches den Wert Null repraesentiert
    static int[] Null() {
        return new int[]{0};
    }

    // konstruiert das Ziffernfeld, welches den Wert Eins repraesentiert
    static int[] One() {
        return new int[]{1};
    }

    // Rest des Ziffernfeldes n bei Division durch 10 (eine int-Zahl!)
    static int mod10(int[] n) {
        return n[0];
    }

    // ganzzahliger Quotient bei Division durch 10
    static int[] div10(int[] n) {
        if (n.length == 1) {
            return new int[]{0};
        }
        int[] quotArr = new int[n.length - 1];
        for (int i = n.length - 1; i > 0; i--) {
            quotArr[quotArr.length - i] = n[n.length - i];
        }
        return removeZeros(quotArr);
    }

    // Umwandlung einer int-Zahl in ein Ziffernfeld
    static int[] fromInt(int n) {
        String strNumber = Integer.toString(n);
        int[] nums = new int[strNumber.length()];
        for (int i = strNumber.length() - 1; i >= 0; i--) {
            nums[(strNumber.length() - 1 - i)] = Character.getNumericValue(strNumber.charAt(i));
        }
        return nums;
    }

    // kopiert den Wert von a
    static int[] copy(int[] n) {
        int[] clone = new int[n.length];
        System.arraycopy(n, 0, clone, 0, n.length);
        return clone;
    }

    // multipliziert das Ziffernfeld a mit einer int-Zahl
    static int[] times(int[] n, int d) {
        int[] dArr = digit(d);
        return times(n, dArr);
    }

    // multipliziert das Ziffernfeld n mit 10
    static int[] times10(int[] n) {
        if (n[0] == 0 && n.length == 1) {
            return new int[]{0};
        }
        int[] tenProduct = new int[n.length + 1];
        System.arraycopy(n, 0, tenProduct, 1, n.length);
        tenProduct[0] = 0;
        return removeZeros(tenProduct);
    }

    // multipliziert zwei Ziffernfelder
    static int[] times(int[] a, int[] b) {
        if ((a[0] == 0 && a.length == 1) || (b[0] == 0 && b.length == 1)) {
            return new int[]{0};
        }
        int[] product = new int[a.length + b.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                product[i + j] += a[i] * b[j];
            }
        }
        for (int i = 0; i < product.length - 1; i++) {
            product[i + 1] += product[i] / 10;
            product[i] = product[i] % 10;
        }
        return removeZeros(product);
    }

    // Quadratzahl eines Ziffernfeldes
    static int[] square(int[] a) {
        return times(a, a);
    }

    // Kubikzahl eines Ziffernfeldes
    static int[] cubic(int[] a) {
        return times(times(a, a), a);
    }

    // Test auf kleiner-Relation zweier Ziffernfelder: a < b ?
    static boolean less(int[] a, int[] b) {
        if (a.length != b.length) {
            return b.length > a.length;
        }
        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] != b[i]) {
                return b[i] > a[i];
            }
        }
        return false;
    }

    // Test auf Gleichheit zweier Ziffernfelder
    static boolean equal(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    // Test auf Korrektheit eines Ziffernfeldes: Feld existiert und enthaelt
    // mindestens eine Ziffer, alle Positionen liegen zwischen 0 und 9
    // keine fuehrenden Nullen (ausser bei Null selbst)
    static boolean ok(int[] n) {
        if (n.length == 0) {
            return false;
        }
        for (int num : n) {
            if (num > 9 || num < 0) {
                return false;
            }
        }
        return (n[n.length - 1] >= 1) || (n.length == 1);
    }

    // gibt die (kleinste) Ziffer mit der groessten Haeufigkeit in n aus
    static void maxDigit(int[] n) {
        int[] counter = new int[10];
        for (int j : n) {
            counter[j]++;
        }
        int maxPos = 0;
        int maxVal = 0;
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] > maxVal) {
                maxPos = i;
                maxVal = counter[i];
            }
        }
        System.out.println(maxPos);
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
