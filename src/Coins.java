public class Coins {

    public static long pay(int toPay) {
        return pay(toPay, 7);  // I personally think this function is redundant but include it as I believe it is how the exercise specifies the function footprint
    }

    private static long pay(int toPay, int n) {
        short[] coins = {1, 2, 5, 10, 20, 50, 100, 200};

        if (coins[n] == 1 || toPay == 0) {  // If there are 0 cents to pay, there is a way to break that coin into smaller parts
            return 1;
        }
        if ((toPay < 0)) {  // If toPay < 0, there is no solution, or if we have reached the final coin, there is no solution.
            return 0;
        }
        if (coins[n] == 2) {  // The following if statements optimise the code by removing unnecessary recursive branches and replacing them with polynomial functions.
            return (toPay / 2) + 1; // We noticed that some coins return integers that could be found with this method while drawing our recursion tree.
        }
        if (coins[n] == 5) {
            return (long) (1 + (0.4 * toPay) + (0.05 * (Math.pow(toPay, 2))));
        }
        if (coins[n] == 10) {  // Any polynomial higher than this degree is no longer accurate for calculation, and thus we keep the recursive branches past this point.
            return (long) Math.ceil(1 + ((23.0 * toPay) / 60) + (0.045 * (Math.pow(toPay, 2))) + (Math.pow(toPay, 3) / 600));
        }
        if (coins[n] > toPay) {  // This statement avoids unnecessary calls where the coin is larger than the amount to be paid.
            return pay(toPay, n - 1);
        }
        return pay(toPay - coins[n], n) + pay(toPay, n - 1);  // LHS recurs every value below toPay (i.e. change after using a coin), RHS recurs toPay itself
    }

    public static long[][] cache = new long[2001][8];

    private static long payCache(int toPay, int n) {
        short[] coins = {1, 2, 5, 10, 20, 50, 100, 200};

        if (toPay == 0) return 1;
        if (toPay < 0) return 0;
        if (n == 0) return 1;
        if(toPay<n) {
            cache[toPay][n]= payCache(toPay,n-1);
            return payCache(toPay,n-1);
        }
        if (cache[toPay][n]!=0)
            return cache[toPay][n];

        return cache[toPay][n] = payCache(toPay,n-1)+payCache(toPay-coins[n],n); // Returns the total number of ways to make a certain amount
    }

    public static String million() {
        for (int i = 0; i <= 1000000; i++) {
            long numWays = payCache(i, 7);
            if (numWays >= 1000000) {
                return euro(i);
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
            } else {
                int sum = Integer.parseInt(args[0]);
                System.out.println(euro(sum) + " kann auf " + (pay(sum)) + " verschiedene Arten passend bezahlt werden");
            }
        } else if (args.length == 2) {
            int sum = Integer.parseInt(args[1]);
            System.out.println(euro(sum) + " kann auf " + (payCache(sum, 7)) + " verschiedene Arten passend bezahlt werden");
        }
    }
}
