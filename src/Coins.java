public class Coins {
    public static long pay (int toPay, int n) {
        // TODO: implementiert die Funktion | M(x)|, s.o.
        long possibleCombinations = 0;
        int[] comboArray = {};
        return 0;
    }

    public static String million() {
        // TODO: ermittelt den kleinsten Betrag, der auf mindestens 1.000.000 verschiedene Weisen bezahlt werden kann
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
        } else {
            int sum = Integer.parseInt(args[0]);
            System.out.print(euro(sum));
            System.out.print(" kann auf ");
            System.out.print(pay(sum, 1));
            System.out.println(" verschiedene Arten passend bezahlt werden");
        }
    }
}
