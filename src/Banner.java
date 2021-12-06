
public class Banner {

    //Definition des Zeichensatzes für 0123456789-
    static String[][] fontChars = {{
            "  ###   ",
            " #   #  ",
            "#     # ",
            "#     # ",
            "#     # ",
            " #   #  ",
            "  ###   "}, {
            "   #    ",
            "  ##    ",
            " # #    ",
            "   #    ",
            "   #    ",
            "   #    ",
            " #####  "}, {
            " #####  ",
            "#     # ",
            "      # ",
            " #####  ",
            "#       ",
            "#       ",
            "####### "}, {
            " #####  ",
            "#     # ",
            "      # ",
            " #####  ",
            "      # ",
            "#     # ",
            " #####  "}, {
            "#       ",
            "#    #  ",
            "#    #  ",
            "#    #  ",
            "####### ",
            "     #  ",
            "     #  "}, {
            "####### ",
            "#       ",
            "#       ",
            "######  ",
            "      # ",
            "#     # ",
            " #####  "}, {
            " #####  ",
            "#     # ",
            "#       ",
            "######  ",
            "#     # ",
            "#     # ",
            " #####  "}, {
            "####### ",
            "#    #  ",
            "    #   ",
            "   #    ",
            "  #     ",
            "  #     ",
            "  #     "}, {
            " #####  ",
            "#     # ",
            "#     # ",
            " #####  ",
            "#     # ",
            "#     # ",
            " #####  "}, {
            " #####  ",
            "#     # ",
            "#     # ",
            " ###### ",
            "      # ",
            "#     # ",
            " #####  "}, {
            "        ",
            "        ",
            "        ",
            " #####  ",
            "        ",
            "        ",
            "        "}};

    public static void main(String[] args) {
        String[] lines = {"","","","","","",""}; // Initialise 7 empty lines
        for (char ch : args[0].toCharArray()) { // For each digit/character in the number
            int mapTo = (ch == 45)? ch - 35 : ch - 48; // Ternary check maps integer or "-" to matching sign in fontChars
            for (int i = 0; i<7; i++) { // For all 7 lines
                lines[i] = lines[i] + fontChars[mapTo][i]; // Append the matching fontChars line to each line
            }
        }
        for (int i = 0; i<7; i++) {
            System.out.println(lines[i]);
        }
    }
}
