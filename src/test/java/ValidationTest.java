import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


/**
 * Created by andyl on 4/8/2018.
 */
public class ValidationTest {

    private Hangman hangman;
    private Word word;

    @Before
    public void setup() {
        hangman = new HangmanDriver();
        hangman.runGame();
        word = new Word(hangman);
    }

    @Test
    public void verifyEmptyStringValidation() {
        assertFalse("Empty string should return false", word.validate(""));
    }

    @Test
    public void verifyNumberValidation() {
        assertFalse("0 should return false", word.validate("0"));
        assertFalse("Positive number should return false", word.validate("1"));
        assertFalse("Negative number should return false", word.validate("-1"));
        assertFalse("Decimals should return false", word.validate("0.9"));
        assertFalse("Number should return false", word.validate("999999"));
    }

    @Test
    public void verifyPunctuationValidation() {
        assertFalse("Punctuation . should return false", word.validate("."));
        assertFalse("Punctuation , should return false", word.validate(","));
        assertFalse("Punctuation \" should return false", word.validate("\""));
        assertFalse("Punctuation : should return false", word.validate(":"));
        assertFalse("Punctuation @ should return false", word.validate("@"));
        assertFalse("Punctuation # should return false", word.validate("#"));
        assertFalse("Punctuation $ should return false", word.validate("$"));
        assertFalse("Punctuation % should return false", word.validate("%"));
        assertFalse("Space should return false", word.validate(" "));
    }

    @Test
    public void verifyStringValidation() {
        assertTrue("Lowercase string should return true", word.validate("abc"));
        assertTrue("Uppercase string should return true", word.validate("ABC"));
        assertTrue("Combined case strings should return true", word.validate("aBc"));
    }

    @Test
    public void verify() {

    }

}
