import java.util.Random;

/**
 * Created by andyl on 4/15/2018.
 */
public class MediumWord {

    private enum MediumWordLibrary {
        HOUSE("House"), BOOK("Book");

        String word;

        private MediumWordLibrary(String word) {
            this.word = word;
        }

        public String toString() {
            return word;
        }
    }

    public String getRandomWord() {
        int numWords = MediumWordLibrary.values().length;
        int randomInt = new Random().nextInt(numWords);

        return MediumWordLibrary.values()[randomInt].toString();
    }
}