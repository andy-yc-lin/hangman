import java.util.Random;

/**
 * Created by andyl on 4/14/2018.
 */
public class EasyWord {

    private enum EasyWordLibrary {
        DOG("Dog");

        String word;

        private EasyWordLibrary(String word) {
            this.word = word;
        }

        public String toString() {
            return word;
        }
    }

    public String getRandomWord() {
        int numWords = EasyWordLibrary.values().length;
        int randomInt = new Random().nextInt(numWords);

        return EasyWordLibrary.values()[randomInt].toString();
    }
}