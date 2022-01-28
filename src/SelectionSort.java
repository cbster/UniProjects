public class SelectionSort {

    public static String[] sort(char sortKey, String[] lineArray) {
        for (int i = 0; i < lineArray.length; i++)
        {
            int count = 0;
            for (char character:
                    lineArray[i].toCharArray()) {
                if (character == sortKey) {
                    count++;
                }
            }
            lineArray[i] = lineArray[i].concat(Integer.toString(count));
        }

        int sortedLines = 0;
        for (int i = 200; i >= 0; i--) { // occurrence counter // TODO change i?
            for (int j = sortedLines; j < lineArray.length; j++) { // line counter
                if (Integer.parseInt(lineArray[j].substring(lineArray[j].length()-1)) == i) {
                    String temp = lineArray[sortedLines];
                    lineArray[sortedLines] = i + ": " + lineArray[j].substring(0, lineArray[j].length()-1);
                    lineArray[j] = (lineArray[j].substring(3).equals(temp.substring(0, temp.length()-1))) ? lineArray[j] : temp;
                    sortedLines++;
                }
            }
        }
        return lineArray;
    }

    public static void main(String[] args) {
        String[] lines = StdIn.readAllLines();
        for (String line :
                sort(args[0].charAt(0), lines)) {
            System.out.println(line);
        }
    }
}
