public class Caesar {
    public static void main(String[] args) {
        int shift_by = (Integer.parseInt(args[0]) % 26);
        for (int i = 1; i < (args.length); i++) { // For each argument, starting at 1 (0 is occupied by shift_by), do...
            String result = "";
            for (char ch : args[i].toCharArray()) {
                result = (Character.isLetter(ch)) ?
                        result + Character.toString(ch + shift_by) : result.concat(Character.toString(ch));
            }
            System.out.println(result); // Print concatenated sentence
        }
    }
}
