class Bigs {
    // addiert die Ziffernfelder a und b
    public static int[] add(int[] a, int[] b) {
        int[] sum = new int[Math.max(a.length, b.length)];

        for (int i = 0; i < sum.length; i++) {
            sum[i] = a[i] + b[i]; // TODO Array out of bounds exception? Two numbers of different lengths?
        }
        for (int i = 0; i < sum.length - 1; i++) {
            if (sum[i] > 9) {
                sum[i] = sum[i] - 10;
                sum[i + 1]++;
            }
        }
        if (sum[sum.length - 1] > 9) {
            int[] finalSum = new int[sum.length + 1];
            sum[sum.length - 1] = sum[sum.length - 1] - 10;
            finalSum[sum.length] = 1;
            for (int i = 0; i < sum.length; i++) {
                finalSum[i] = sum[i];
            }
            return finalSum;
        } else {
            return sum;
        }
    }

    // gibt das Ziffernfeld n in lesbarer dezimaler Form aus
    static void print(int[] n) {
        for (int i = n.length - 1; i >= 0; i--) {
            System.out.print(n[i]);
        }
    }

    // konstruiert ein einstelliges Ziffernfeld aus d
    static int[] digit(int d) {
        int[] a = {d};
        return a;
    }

    // konstruiert das Ziffernfeld, welches den Wert Null repraesentiert
    static int[] Null() {
        int[] a = {0};
        return a;
    }

    // konstruiert das Ziffernfeld, welches den Wert Eins repraesentiert
    static int[] One() {
        int[] a = {1};
        return a;
    }

    // Rest des Ziffernfeldes n bei Division durch 10 (eine int-Zahl!)
    static int mod10(int[] n) {
        return n[0];
    }

    // ganzzahliger Quotient bei Division durch 10
    static int[] div10(int[] n) {
        int[] quotArr = new int[n.length - 1];
        for (int i = 0; i < n.length - 1; i++) {
            quotArr[i] = n[i];
        }
        return quotArr;
    }

    // Umwandlung einer int-Zahl in ein Ziffernfeld
    static int[] fromInt(int n) {
        String stringInt = Integer.toString(n);
        char[] strArray = stringInt.toCharArray();
        int[] nums = new int[strArray.length];
        for (int i = strArray.length - 1; i >= 0; i--) {
            nums[(strArray.length - 1 - i)] = Integer.parseInt(Character.toString(strArray[i]));
        }
        return nums;
    }

    // kopiert den Wert von a
    static int[] copy(int[] n) {
        int[] clone = new int[n.length];
        for (int i = 0; i < n.length; i++) {
            clone[i] = n[i];
        }
        return clone;
    }

    // multipliziert das Ziffernfeld a mit einer int-Zahl
    static int[] times(int[] n, int d) {
        int[] product = new int[n.length + (String.valueOf(d).length())];
        for (int i = 0; i < n.length; i++) {
            product[i] = n[i] * d;
        }
        for (int i = 0; i < product.length - 1; i++) {
            while (product[i] > 9) {
                product[i] = product[i] - 10;
                product[i + 1]++;
            }
        }
        if (ok(product)) {
            return product;
        } else {
            int[] newProduct = new int[product.length - 1];
            for (int i = 0; i < product.length - 1; i++) {
                newProduct[i] = product[i];
            } return newProduct;
        }
    }

    // multipliziert das Ziffernfeld n mit 10
    static int[] times10(int[] n) {
        int[] tenProduct = new int[n.length + 1];
        for (int i = 0; i < n.length; i++) {
            tenProduct[i + 1] = n[i];
        }
        tenProduct [0] = 0;
        return tenProduct;
    }

    // multipliziert zwei Ziffernfelder
    static int[] times(int[] a, int[] b) {
        int[] product = new int[a.length + b.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                product[i + j] += a[i] * b[j];
            }
        }
        for (int i = 0; i < product.length - 1; i++) {
            product[i+1] += product[i] / 10;
            product[i] = product[i] % 10;
        }
        if (ok(product)) {
            return product;
        } else {
            int[] newProduct = new int[product.length - 1];
            for (int i = 0; i < product.length - 1; i++) {
                newProduct[i] = product[i];
            } return newProduct;
        }
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
        if (a.length < b.length) {
            return true;
        }
        for (int i = 1; i <= a.length; i++) {
            if (a[a.length - i] < b[b.length - i]) {
                return true;
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
        for (int num: n) {
            if (num > 9) {
                return false;
            }
        }
        if ((n[n.length-1] < 1) && (n.length != 1)) {
            return false;
        }
        return true;
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
