import java.util.Random;

/**
 * Created by andyl on 4/15/2018.
 */
public class HardWord {

    private enum HardWordLibrary {
        ALLIGATOR("Alligator"), CROCODILE("Crocodile");

        String word;

        private HardWordLibrary(String word) {
            this.word = word;
        }

        public String toString() {
            return word;
        }
    }

    public String getRandomWord() {
        int numWords = HardWordLibrary.values().length;
        int randomInt = new Random().nextInt(numWords);

        return HardWordLibrary.values()[randomInt].toString();
    }
}