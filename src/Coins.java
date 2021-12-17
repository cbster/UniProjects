public class Coins {
    private static long pay(int toPay, int n) {
        long[] coins = {1, 2, 5, 10, 20, 50, 100, 200};

        if (coins[n] == 1 || toPay == 0) {  // If there are 0 cents to pay, there is a way to break that coin into smaller parts
            return 1;
        }
        if ((toPay < 0)) {  // If toPay < 0, there is no solution, or if we have reached the final coin, there is no solution.
            return 0;
        }
        if (coins[n] == 2) {
            return (toPay/2) + 1;
        }
        if (coins[n] == 5) {
            return (long) (1 + (0.4 * toPay) + (0.05*(Math.pow(toPay, 2))));
        }
        if (coins[n] == 10) {
            return (long) Math.ceil(1 + ((23 * toPay)/60) + (0.045*(Math.pow(toPay, 2))) + (Math.pow(toPay, 3)/600));
        }
        if (coins[n] > toPay) {
            return pay(toPay, n - 1);
        }
        return pay((int) (toPay - coins[n]), n) + pay(toPay, n - 1);  // LHS recurs every value below toPay, RHS recurs toPay itself
    }

    public static long payCached(int toPay) {
        int[] coins = {1, 2, 5, 10, 20, 50, 100, 200};
        long[] combos = new long[toPay + 1];  // Possible numbers of combinations for each amount stored in this array
        combos[0] = 1;  // There is only one solution for zero cents
        for (int coin : coins) {
            for (int i = 0; i < combos.length; i++) {
                if (coin <= i) {  // If the coin's value is less than or equal to the amount
                    combos[i] += combos[i - coin];  // Take coin value from i, then add the number of ways you can make the change
                }                                   // to the number of ways you can make i.
            }
        }
        return combos[toPay];  // Returns the total number of ways to make a certain amount
    }

    public static String million() {
        for (int i = 0; i <= 1000000; i++) {
            long numWays = payCached(i);
            if (numWays >= 1000000) {
                return "" + i;
            }
        }
        return null;
    }

    public static String euro(int cent) {
        String centVal = (String.valueOf(cent % 100).length() == 1) ? "0" + cent % 100 : String.valueOf(cent % 100);
        return (cent > 99) ? cent / 100 + "," + centVal + " Euro" :
                "00," + centVal + " Euro";
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Aufruf mit Geldbetrag (in Cent) als Parameter");
        } else if (args.length == 1) {
            if (args[0].equals("-c")) {
                System.out.println("Aufruf mit Geldbetrag (in Cent) als Parameter");
            }
            int sum = Integer.parseInt(args[0]);
            System.out.println(euro(sum) + " kann auf " + (pay(sum, 7)) + " verschiedene Arten passend bezahlt werden");
        } else if (args.length == 2) {
            int sum = Integer.parseInt(args[1]);
            System.out.println(euro(sum) + " kann auf " + (payCached(sum)) + " verschiedene Arten passend bezahlt werden");
        }
    }
}

// JS Implementation for recursive tree drawing

/*
if (v == 0) return 1;
if (n == 0) return 1;
if (n == 1) return  Math.floor(v/2) + 1;
if (n == 2) return (1 + (0.4*v) + (0.05*(Math.pow(v, 2))));
if (n == 3) return Math.ceil((1 + (23 * v)/60 + (0.045*(Math.pow(v, 2))) + (Math.pow(v, 3)/600)));
if (v < 0) return 0;
if (a1[n] > v) return f(v, n-1);
return f(v-a1[n], n) + f(v, n-1);
*/
