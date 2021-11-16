public class Looping {
    static long reverseNumber(String stringNum) {
        String reversedStringNum = new StringBuilder(stringNum).reverse().toString();
        long reversedLong = Long.parseLong(reversedStringNum);
        return reversedLong;
    }

    static void palCheck(long testNumber, long attemptNo) {
        if (attemptNo < 100) {
            long reversedNumber = reverseNumber(Long.toString(testNumber));
            long result = testNumber + reversedNumber;
            long reversedResult = reverseNumber(Long.toString(result));
            if (result == reversedResult) {
                return;
            } else {
                palCheck(result, ++attemptNo);
            }
        } else {
            return;
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
                }catch (NumberFormatException e){
                    System.out.println(testNumber);
                }
            }
        }
        if (args.length == 2) {

        }
    }
}
