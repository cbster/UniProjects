public class PlaetzchenPruefer {

    static void tester(int testNum, int samples) {
        if (testNum > 0) {
            samples += ((testNum % 2) == 0) ? 2 : 1;
            testNum = ((testNum % 2) == 0) ? ((testNum - 2) / 2) : testNum - 1;
            tester(testNum, samples);
        } else {
            System.out.println(samples);
        }
    }

    public static void main(String[] args) {
        tester(Integer.parseInt(args[0]), 0);
    }
}
