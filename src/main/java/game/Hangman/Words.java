package game.Hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Words {

    private final ArrayList<String> words;
    private final ArrayList<String> hints;

    public Words() throws FileNotFoundException {
        words = new ArrayList<>();
        hints = new ArrayList<>();
        File file = new File("src/main/java/game/hangmanwords.txt");
        Scanner scanner = new Scanner(file);
        String blankLine = scanner.nextLine();
        while (scanner.hasNextLine()) {
            String word = scanner.nextLine();
            String hint = scanner.nextLine();
            words.add(word);
            hints.add(hint);
        }
    }

    public int getRandomIndex() {
        return (int) (Math.random() * words.size());
    }
    public String getWord(int randomIndex) {
        return words.get(randomIndex).toUpperCase();
    }

    public String getHint(int randomIndex) {
        return hints.get(randomIndex);
    }

}
