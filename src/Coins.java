public class Coins {

    private static long pay(int toPay, int n) {

        return 0;
    }

    public static long payCached(int toPay) {
        int[] coins = {1, 2, 5, 10, 20, 50, 100, 200};
        long[] combos = new long[toPay + 1];
        combos[0] = 1;
        for (int coin : coins) {
            for (int i = 0; i < combos.length; i++) {
                if (coin <= i) {
                    combos[i] += combos[i - coin];
                }
            }
        }
        return combos[toPay];
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
            // TODO: non-caching solution with pay()
        } else if (args.length == 2) {
            int sum = Integer.parseInt(args[1]);
            System.out.print(euro(sum));
            System.out.print(" kann auf ");
            System.out.print(payCached(sum));
            System.out.println(" verschiedene Arten passend bezahlt werden");
        }
    }
}
