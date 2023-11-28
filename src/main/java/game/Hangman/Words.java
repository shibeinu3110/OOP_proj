package game.Hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Words {

    private final ArrayList<String> words;


    public Words() throws FileNotFoundException {
        words = new ArrayList<>();
        File file = new File("src/main/java/game/hangmanwords.txt");
        Scanner scanner = new Scanner(file);
        String blankLine = scanner.nextLine();
        while (scanner.hasNextLine()) {
            String word = scanner.nextLine();
            words.add(word);
        }
    }

    public String getRandomWord() {
        int randomIndex = (int) (Math.random() * words.size());
        return words.get(randomIndex).toUpperCase();
    }

}
