import java.math.BigInteger;

public class Looping {
    static long reverseNumber(String stringNum) {  // This function reverses a Long by converting it to a string and back
        String reversedStringNum = new StringBuilder(stringNum).reverse().toString();
        return Long.parseLong(reversedStringNum);
    }

    static BigInteger reverseBig(String stringNum) { // As above, returning a BigInteger
        String reversedStringNum = new StringBuilder(stringNum).reverse().toString();
        return new BigInteger(reversedStringNum);
    }

    static void palCheck(long testNumber, long attemptNo) { // Checks long for palindrome
        long reversedNumber = reverseNumber(Long.toString(testNumber));
        long result = testNumber + reversedNumber;
        long reversedResult = reverseNumber(Long.toString(result));
        if (result != reversedResult) {
            palCheck(result, ++attemptNo); // Will run until NumberFormatException or until palindrome is found
        }
    }

    static void palCheckBig(BigInteger testNumber, long attemptNo, long initNum) { // Checks bigIntegers for palindromes
        if (attemptNo <= 100) { // Limit attempts to 100 per exercise specification
            BigInteger reversedNumber = reverseBig(testNumber.toString());
            BigInteger result = testNumber.add(reversedNumber);
            BigInteger reversedResult = reverseBig(result.toString());
            if (result.equals(reversedResult)) {
                System.out.println(initNum + " braucht " + attemptNo + " Iterationen bis zum Palindrom " + result);
                System.exit(0);
            } else {
                palCheckBig(result, ++attemptNo, initNum);
            }
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Bitte geben Sie die Obergrenze als Parameter an.");
        }
        if (args.length == 1) {
            for (long testNumber = 1; testNumber < Integer.parseInt(args[0]); testNumber++) {
                try {
                    palCheck(testNumber, 1);
                } catch (NumberFormatException e) {
                    System.out.println(testNumber);
                }
            }
        }
        if (args.length == 2) {
            for (long testNumber = 1; testNumber < Integer.parseInt(args[0]); testNumber++) {
                try {
                    palCheck(testNumber, 1);
                } catch (NumberFormatException e) {
                    palCheckBig(new BigInteger(String.valueOf(testNumber)), 1, testNumber);
                    }
                }
            System.out.println("alle Zahlen werden auch durch Abbruch per Ueberlauf gefunden");
            }
        }
    }
