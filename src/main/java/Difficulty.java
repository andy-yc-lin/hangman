/**
 * Created by andyl on 4/8/2018.
 */
public enum Difficulty {
    EASY("easy"), MEDIUM("medium"), HARD("hard");

    private String difficultyString;
    private String wordToGuess;

    private Difficulty (String difficulty) {
        difficultyString = difficulty;
    }

    public String toString() {
        return difficultyString;
    }
}