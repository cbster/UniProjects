public class Deduplikator2 {
    public static void main(String[] args) {
        double[] nums = new double[args.length];
        for (int i = 0; i < args.length; i++) {
            nums[i] = (Double.parseDouble(args[i]));
        }
        for (int i = 0; i < nums.length; i++) {
            boolean found = false;
            for (int j = 0; j < i; j++) {
                if (nums[i] == nums[j]) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println(nums[i]);
            }
        }
    }
}
