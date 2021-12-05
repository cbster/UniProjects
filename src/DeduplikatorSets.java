import java.util.HashSet;
import java.util.Set;

public class DeduplikatorSets {
    public static void main(String[] args) {
        Set<Double> nums = new HashSet<>();
        for (String arg : args) {
            nums.add(Double.parseDouble(arg));
        }
        for (Double num : nums) {
            System.out.println(num);
        }
    }
}
