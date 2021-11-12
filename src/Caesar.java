import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Caesar {  // Please don't kick me out for making this function, it returns a key from a hashmap given the value associated with that key. I know.
    public static <T, E> T key_to_val(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    static void cipher(String[] args) {
        int shift_by = Integer.parseInt(args[0]);
        int i;
        String word;
        Map<String, Integer> hm_lower
                = new HashMap<String, Integer>();
        Map<String, Integer> hm_upper
                = new HashMap<String, Integer>();
        String alphabet_lower[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        for (i = 0; i < (alphabet_lower.length); i++) { // Makes a hashmap linking a-z to 0-25
            hm_lower.put(alphabet_lower[i], new Integer(i));
        }
        for (i = 0; i < (alphabet_lower.length); i++) { // As above, A-Z to 0-25
            hm_upper.put(alphabet_lower[i].toUpperCase(), new Integer(i));
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
                        String key = key_to_val(hm_lower, ((pos % 26) + (shift_by % 26)) % 26); // Modular addition to avoid array out of bounds exception
                        result = result.concat(key);
                    } else if (Character.isUpperCase(ch)) { // Upper case letters get mapped to hm_upper
                        String str_char = "" + ch;
                        int pos = hm_upper.get(str_char);
                        String key = key_to_val(hm_upper, ((pos % 26) + (shift_by % 26)) % 26); // Modular addition to avoid array out of bounds exception
                        result = result.concat(key);
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
