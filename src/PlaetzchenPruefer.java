public class PlaetzchenPruefer {

    public static int pruefePlaetzchen(int anzahlPlaetzchen) {
        return (anzahlPlaetzchen == 0 || anzahlPlaetzchen == 1) ?
                anzahlPlaetzchen :
                (anzahlPlaetzchen % 2 == 0) ?
                        2 + pruefePlaetzchen((anzahlPlaetzchen - 2) / 2) :
                        1 + pruefePlaetzchen(anzahlPlaetzchen - 1);
    }

    public static void main(String[] args) {
        System.out.println(pruefePlaetzchen(Integer.parseInt(args[0])));
    }
}
