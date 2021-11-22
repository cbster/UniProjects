import java.math.BigInteger;

public class Looping {

    static BigInteger reverseBig(String stringNum) { // As above, returning a BigInteger
        String reversedStringNum = new StringBuilder(stringNum).reverse().toString();
        return new BigInteger(reversedStringNum);
    }

    static void palCheck(BigInteger testNumber, long attemptNo, long initNum, Boolean x) { // Checks long for palindrome
        try {
            long testLong = Long.parseLong(testNumber.toString());
            long reversedNumber = Long.parseLong(reverseBig(Long.toString(testLong)).toString());
            long result = testLong + reversedNumber;
            long reversedResult = Long.parseLong(reverseBig(Long.toString(result)).toString());
            if (result != reversedResult) {
                palCheck(new BigInteger(String.valueOf(result)), ++attemptNo, initNum, x); // Will run until NumberFormatException or until palindrome is found
            }
        } catch (NumberFormatException e) {
            if (!x) {
                System.out.println(initNum);
            } else {
                if (attemptNo <= 100) { // Limit attempts to 100 per exercise specification
                    BigInteger reversedNumber = reverseBig(testNumber.toString());
                    BigInteger result = testNumber.add(reversedNumber);
                    BigInteger reversedResult = reverseBig(result.toString());
                    if (result.equals(reversedResult)) {
                        System.out.println(initNum + " braucht " + attemptNo + " Iterationen bis zum Palindrom " + result);
                        System.exit(0);
                    } else {
                        palCheck(result, ++attemptNo, initNum, true);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Bitte geben Sie die Obergrenze als Parameter an.");
        }
        Boolean x = args.length == 2;
        for (long testNumber = 1; testNumber < Integer.parseInt(args[0]); testNumber++) {
            palCheck(new BigInteger(String.valueOf(testNumber)), 1, testNumber, x);
        }
        if (x) {
            System.out.println("alle Zahlen werden auch durch Abbruch per Ueberlauf gefunden");
        }
    }
}
