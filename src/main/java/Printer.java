/**
 * Created by andyl on 4/8/2018.
 */
public class Printer {

    private Hangman hangman;
    private int numPlacesToPrint;

    public Printer(Hangman hangman) {
        this.hangman = hangman;
    }

    public enum PrintOption {
        ALREADY_GUESSED,
        CORRECT_GUESS,
        DIFFICULTY_SET,
        FOUND_LETTER_GUESS_AGAIN,
        GUESS_WORD,
        INVALID_ENTRY_GUESS_AGAIN,
        RESULT_LOSE,
        RESULT_WIN,
        RETRY,
        SELECT_DIFFICULTY,
        NO_FOUND_LETTER_GUESS_AGAIN,
        WELCOME,
        WORD_TO_GUESS,
        GUESSED_WHOLE_WORD
    }

    public void print(PrintOption printOption) {
        switch (printOption) {
            case WELCOME:
                System.out.println("Welcome to Hangman!");
                break;
            case SELECT_DIFFICULTY:
                System.out.println("Please input a difficulty: Easy, Medium, Hard");
                System.out.println("Or a random difficulty will be selected!");
                break;
            case DIFFICULTY_SET:
                System.out.println("Difficulty set: " + hangman.getDifficulty().toString());
                break;
            case GUESS_WORD:
                System.out.println("Enter a letter or word to start guessing");
                break;
            case ALREADY_GUESSED:
                System.out.print("You already guessed this letter. ");
                break;
            case FOUND_LETTER_GUESS_AGAIN:
                System.out.println("Enter another letter to keep guessing.");
                break;
            case NO_FOUND_LETTER_GUESS_AGAIN:
                System.out.println("No matching letter! Try guessing again.");
                break;
            case GUESSED_WHOLE_WORD:
                System.out.println("Wow you guessed the whole word!");
                break;
            case CORRECT_GUESS:
                System.out.print("You found a letter! ");
                break;
            case RESULT_WIN:
                System.out.println("Your IQ must be very high!");
                break;
            case RESULT_LOSE:
                System.out.println("Sorry, you didn't guess the right word.");
                break;
            case WORD_TO_GUESS:
                System.out.println("The word was: " + hangman.getWordToGuess());
                break;
            case INVALID_ENTRY_GUESS_AGAIN:
                System.out.println("Please enter a letter or word for your guesses. Try again.");
                break;
            case RETRY:
                System.out.println("Would you like to play again? Yes (y) / No (n)");
                break;
            default:
                System.err.println("Invalid printOption entered");
                System.exit(0);
                break;
        }
    }

    public void printGameInitialize() {
        numPlacesToPrint = hangman.getWordToGuess().length();

        for (int index = 0; index < numPlacesToPrint; index++) {
            System.out.print("_ ");
        }
        System.out.println(); // prints nextLine
    }

    public void printGame() {
        boolean[] wordToGuessAsBooleanArray = hangman.getWordToGuessAsBooleanArray();
        for (int index = 0; index < numPlacesToPrint; index++) {
            if (wordToGuessAsBooleanArray[index]){
                System.out.print(hangman.getWordToGuess().charAt(index) + " ");
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println();
    }

    public void printGuessesSoFar(String[] userGuesses) {
        System.out.print("You've guessed: ");
        for (String userGuess : userGuesses) {
            if (userGuess != null) {
                System.out.print(userGuess + " ");
            } else {
                System.out.println();
                return;
            }
        }
    }

    public void printLives(int lives) {
        System.out.print("You have " + lives + " lives left. ");
    }
}