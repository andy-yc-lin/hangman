import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by andyl on 4/9/2018.
 */
public class Word {

    private static final String EASY = Difficulty.EASY.toString();
    private static final String MEDIUM = Difficulty.MEDIUM.toString();
    private static final String HARD = Difficulty.HARD.toString();

    private EasyWord easyWord = new EasyWord();
    private MediumWord mediumWord = new MediumWord();
    private HardWord hardWord = new HardWord();
    private Hangman hangman;

    public Word(Hangman hangman) {
        this.hangman = hangman;
    }

    public boolean doesWordContainLetter(String userInput) {
        return isSingleLetter(userInput) && matchesWord(userInput);
    }

    public boolean didUserGuessWholeWord(String userInput) {
        return hangman.getWordToGuess().equals(userInput);
    }

    public String getWordBasedOnDifficulty(Difficulty difficulty) {
        switch(difficulty) {
            case EASY:
                return easyWord.getRandomWord();
            case MEDIUM:
                return mediumWord.getRandomWord();
            case HARD:
                return hardWord.getRandomWord();
            default:
                System.err.println("Shouldn't have reached default, exiting game");
                System.exit(0);
                return "";// for compiler, should never reach
        }
    }

    public boolean isDifficultyMatched(String userInput) {
        String lowercaseInput = userInput.toLowerCase();

        return lowercaseInput.equals("easy")
                || lowercaseInput.equals("medium")
                || lowercaseInput.equals("hard");
    }

    public boolean isSingleLetter(String userInput) {
        return userInput.matches("[a-z]");
    }

    public boolean isWholeWordGuessed(boolean[] booleanArray) {
        for(boolean bool: booleanArray) {
            if (!bool) {
                return false;
            }
        }
        return true;
    }

    public boolean matchesWord(String userInput) {
        return hangman.getWordToGuess().contains(userInput);
    }

    public Difficulty toDifficulty(String userInput) {
        String lowercaseInput = userInput.toLowerCase();

        if (lowercaseInput.equals(EASY)) {
            return Difficulty.EASY;
        } else if (lowercaseInput.equals(MEDIUM)) {
            return Difficulty.MEDIUM;
        } else {
            return Difficulty.HARD;
        }
    }

    public boolean validate(String inputToValidate) {
        return inputToValidate.matches("[a-z]+");
    }
}