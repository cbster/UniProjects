import java.math.BigInteger;

public class Looping {
    static BigInteger reverseBig(String stringNum) { // Reverses BigInteger via String
        String reversedStringNum = new StringBuilder(stringNum).reverse().toString();
        return new BigInteger(reversedStringNum);
    }

    static void palCheck(BigInteger testNumber, long attemptNo, long initNum, Boolean x) { // Checks for palindrome
        try {
            long testLong = Long.parseLong(testNumber.toString());
            long result = testLong + Long.parseLong(reverseBig(Long.toString(testLong)).toString());
            if (result != Long.parseLong(reverseBig(Long.toString(result)).toString())) {
                palCheck(new BigInteger(String.valueOf(result)), ++attemptNo, initNum, x); // Will run until NumberFormatException or until palindrome is found
            }
        } catch (NumberFormatException e) { // This Exception occurs when String.valueOf() is no longer able to
            if (!x) {
                System.out.println(initNum); // If x is not given as second parameter, simply print the numbers that caused the error
            } else {
                if (attemptNo <= 100) { // Limit attempts to 100 per exercise specification
                    BigInteger result = testNumber.add(reverseBig(testNumber.toString()));
                    if (result.equals(reverseBig(result.toString()))) {
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
        Boolean x = (args.length == 2);
        for (long testNumber = 1; testNumber <= Integer.parseInt(args[0]); testNumber++) {
            palCheck(new BigInteger(String.valueOf(testNumber)), 1, testNumber, x);
        }
        if (x) {
            System.out.println("alle Zahlen werden auch durch Abbruch per Ueberlauf gefunden");
        }
    }
}
