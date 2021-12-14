public class Coins {


    public static long pay (int x) {
        // TODO: implementiert die Funktion | M(x)|, s.o.
        return 0;
    }

    public static String million() {
        // TODO: ermittelt den kleinsten Betrag, der auf mindestens 1.000.000 verschiedene Weisen bezahlt werden kann
        return null;
    }

    public static String euro(int cent) {
        return cent/100 + "," + (((cent%10)/10 == 0) ? "0" + cent%10 : cent%10) + " Euro"; // TODO fix for smaller amounts (10 cents)
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Aufruf mit Geldbetrag (in Cent) als Parameter");
        } else {
            int sum = Integer.parseInt(args[0]);
            System.out.print(euro(sum));
            System.out.print(" kann auf ");
            System.out.print(pay(sum));
            System.out.println(" verschiedene Arten passend bezahlt werden");
        }
    }
}
