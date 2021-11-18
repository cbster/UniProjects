import java.util.HashMap;
import java.util.Map;

public class Caesar {
    static void cipher(String[] args) {
        int shift_by = Integer.parseInt(args[0]);
        int i;
        String word;
        Map<String, Integer> hm_lower
                = new HashMap<String, Integer>();
        String alphabet_lower[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        for (i = 0; i < (alphabet_lower.length); i++) { // Makes a hashmap linking a-z to 0-25
            hm_lower.put(alphabet_lower[i], new Integer(i));
        }
        for (i = 1; i < (args.length); i++) { // For each argument, starting at 1 (0 is occupied by shift_by), do...
            String result = "";
            word = args[i];
            char[] split_word = word.toCharArray(); // Splits word into chars
            for (char ch : split_word) {
                if (Character.isLetter(ch)) { // If it's a letter, act upon it
                    if (Character.isLowerCase(ch)) { // Lower case letters get mapped to hm_lower
                        String str_char = "" + ch;
                        int pos = hm_lower.get(str_char);
                        result = result.concat(alphabet_lower[((pos % 26) + (shift_by % 26)) % 26]);
                    } else if (Character.isUpperCase(ch)) { // Upper case letters get mapped to hm_upper
                        String str_char = "" + ch;
                        int pos = hm_lower.get(str_char.toLowerCase());
                        result = result.concat(alphabet_lower[((pos % 26) + (shift_by % 26)) % 26].toUpperCase());
                    }
                } else {
                    result = result.concat(String.valueOf(ch)); // If not a letter, leave it be
                }
            }
            System.out.println(result); // Print concatenated sentence
        }
    }

    public static void main(String[] args) {
    cipher(args); // Does the thing
    }
}
