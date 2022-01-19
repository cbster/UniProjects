package IntSet;

/**
 * IntSet Fehlerklasse
 *
 */
  
public class SetError extends Exception {

        String reason;

        /**
         * erzeugt eine Ausnahme SetError
         *
         * @param msg eine genauere Fehlerbeschreibung
         */
        public SetError(String msg) {
                super(msg);
        }

        /**
         * gibt die Beschreibung der Ausnahmeursache aus
         */
        public void print() {
                System.out.println("SetError: " + reason);
        }

}
