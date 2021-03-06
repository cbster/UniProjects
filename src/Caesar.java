public class Caesar {
    public static void main(String[] args) {
        for (int i = 1; i < (args.length); i++) { // For each argument, starting at 1 (0 is occupied by the number to shift letters by), do...
            String result = ""; // Reset result string for each argument
            for (char ch : args[i].toCharArray()) { // For each character in the argument
                if (Character.isLetter(ch)) { // If char is a letter, shift and add to result
                    result = (Character.isLowerCase(ch)) ? // Ternary check
                            result + Character.toString((((int)(ch) + Integer.parseInt(args[0]) - 97) % 26) + 97) // If letter is lower-case
                            : result + Character.toString((((int)(ch) + Integer.parseInt(args[0]) - 65) % 26) + 65); // If letter is upper-case
                } else {
                    result = result + ch; // If char is not a letter, leave it be and add to result
                }

            }
            System.out.println(result); // Print result, then loop to next arg or terminate
        }
    }
}
