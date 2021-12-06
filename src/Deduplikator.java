public class Deduplikator {
    public static void main(String[] args) {
        double[] nums = new double[args.length];
        for (int i = 0; i < args.length; i++) {
            nums[i] = (Double.parseDouble(args[i])); // Parses all arguments to Double
        }
        for (int i = 0; i < nums.length; i++) { // Checking each number in nums
            boolean found = false; // By default, number is not found as duplicate
            for (int j = 0; j < i; j++) { // Check each number up until i
                if (nums[i] == nums[j]) { // If the numbers at i and j are the same, duplicate found
                    found = true;
                    break;
                }
            }
            if (!found) { // if no duplicate was found, print number
                System.out.println(nums[i]);
            }
        }
    }
}
