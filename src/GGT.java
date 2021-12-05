public class GGT {
    static int GGT(int a, int b) {
        int init_a = a;
        int init_b = b;
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        System.out.println("The greatest common divisor of " + init_a + " and " + init_b + " is " + a);
        return a;
    }

    public static void main(String[] args) {
        try {
            if (args.length == 2) {
                int a = Integer.parseInt(args[0]);
                int b = Integer.parseInt(args[1]);
                if (a > 0 && b > 0) {
                    GGT(a, b);
                }
                else {
                    System.out.println("Please only provide positive arguments.");
                    System.exit(-1);
                }
            }
            else if (args.length != 2) {
                System.out.println("Please specify two arguments.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please provide only two integers as arguments.");
        }
    }
}
