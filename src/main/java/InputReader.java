import java.util.Scanner;

/**
 * Created by andyl on 4/8/2018.
 */
public class InputReader {

    private Scanner scanner;

    public InputReader() {
        scanner = new Scanner(System.in);
    }

    public String getInput(){
        return scanner.next().toLowerCase();
    }
}
