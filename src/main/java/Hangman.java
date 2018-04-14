import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by andyl on 4/8/2018.
 */
public class Hangman {

    private static final AtomicBoolean isWordGuessed = new AtomicBoolean(false);
    private static final int MAX_LIVES = 5;
    private boolean[] wordToGuessAsBooleanArray;
    private String[] userGuesses;

    private String wordToGuess;

    private Printer printer;
    private InputReader inputReader;
    private Word word;
    private Difficulty difficulty;

    public Hangman() {
        printer = new Printer(this);
        inputReader = new InputReader();
        word = new Word(this);
    }

    public void runGame() {
        // Initialize game
        printer.print(Printer.PrintOption.WELCOME);
        printer.print(Printer.PrintOption.SELECT_DIFFICULTY);

        // Set difficulty
        selectDifficulty(inputReader.getInput());
        setWordToGuess(difficulty);
        printer.print(Printer.PrintOption.DIFFICULTY_SET);

        // Hangman begins
        printer.print(Printer.PrintOption.GUESS_WORD);
        printer.printGameInitialize();

        int lives = MAX_LIVES;
        while(lives > 0 && !isWordGuessed.get()) {
            String userInput = inputReader.getInput();

            if (word.validate(userInput)) {
                if (word.didUserGuessWholeWord(userInput)) {
                    isWordGuessed.set(true);
                    printer.print(Printer.PrintOption.GUESSED_WHOLE_WORD);
                } else if (word.doesWordContainLetter(userInput)) {
                    addUserGuess(userInput);
                    updateGameInfo(userInput);
                    printer.print(Printer.PrintOption.CORRECT_GUESS);
                    if (word.isWholeWordGuessed(wordToGuessAsBooleanArray)){
                        isWordGuessed.set(true);
                        printer.print(Printer.PrintOption.GUESSED_WHOLE_WORD);
                    } else {
                        printer.print(Printer.PrintOption.FOUND_LETTER_GUESS_AGAIN);
                        printer.printGuessesSoFar(userGuesses);
                        printer.printGame();
                    }
                } else {
                    addUserGuess(userInput);
                    printer.print(Printer.PrintOption.NO_FOUND_LETTER_GUESS_AGAIN);
                    printer.printGuessesSoFar(userGuesses);
                    printer.printGame();
                    lives--;
                }
            } else {
                printer.print(Printer.PrintOption.INVALID_ENTRY_GUESS_AGAIN);
            }
        }

        // Game ends
        displayResult();
        printer.print(Printer.PrintOption.RETRY);
        retryOrQuit();
    }

    private void addUserGuess(String userInput) {
        for(int index = 0; index < userGuesses.length; index++){
            if(userGuesses[index] == null){
                userGuesses[index] = userInput;
                return;
            }
        }
    }

    private void displayResult() {
        if (isWordGuessed.get()){
            printer.print(Printer.PrintOption.RESULT_WIN);
        } else {
            printer.print(Printer.PrintOption.RESULT_LOSE);
        }
        printer.print(Printer.PrintOption.WORD_TO_GUESS);
    }

    public boolean[] getWordToGuessAsBooleanArray() {
        return wordToGuessAsBooleanArray;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    private void retryOrQuit() {
        String userInput = inputReader.getInput();

        if (userInput.matches("y")){
            Hangman hangman = new Hangman();
            hangman.runGame();
        } else {
            System.exit(0);
        }
    }

    private void selectDifficulty(String userInput) {
        if (word.validate(userInput) && word.isDifficultyMatched(userInput)) {
            setDifficulty(word.toDifficulty(userInput));
        } else {
            difficulty = setRandomDifficulty();
        }
    }

    private void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    private Difficulty setRandomDifficulty() {
        int max = 3; // only 3 difficulties
        int min = 1;

        // chooses a number between 1 to 3
        int randomInt = new Random().nextInt(max - min + 1) + min;

        switch(randomInt) {
            case 1:
                return Difficulty.EASY;
            case 2:
                return Difficulty.MEDIUM;
            case 3:
                return Difficulty.HARD;
            default:
                System.err.println("Shouldn't have reached default, exiting game");
                System.exit(0);
                return Difficulty.EASY; // for compiler, should never reach
        }
    }

    private void setWordToGuess(Difficulty difficulty){
        wordToGuess = word.getWordBasedOnDifficulty(difficulty).toLowerCase();

        // initialize guess checking mechanic boolean[] and String[]
        wordToGuessAsBooleanArray = new boolean[wordToGuess.length()];
        userGuesses = new String[MAX_LIVES + wordToGuess.length()];
    }

    private void updateGameInfo(String userInput) {
        for(int index = 0; index < wordToGuess.length(); index++) {
            if (wordToGuess.charAt(index) == userInput.charAt(0)){
                wordToGuessAsBooleanArray[index] = true;
            }
        }
    }
}