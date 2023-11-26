package game.src.Quiz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Questions {

    private final ArrayList<Question> questions;

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public Question getRandomQuestion() {
        // Lấy một số ngẫu nhiên trong khoảng từ 0 đến số lượng câu hỏi
        int randomIndex = (int) (Math.random() * questions.size());

        // Trả về câu hỏi ở vị trí ngẫu nhiên đó
        return questions.get(randomIndex);
    }

    public Questions() throws FileNotFoundException {
        questions = new ArrayList<>() ;
        File file = new File("C:\\Users\\DELL\\Documents\\GitHub\\OOP_proj\\src\\main\\java\\game\\questions.txt");
        Scanner scanner = new Scanner(file);

        // Đọc các câu hỏi từ file
        while (scanner.hasNextLine()) {
            String question = scanner.nextLine();
            String[] answers = new String[4];
            answers[0] = scanner.nextLine();
            answers[1] = scanner.nextLine();
            answers[2] = scanner.nextLine();
            answers[3] = scanner.nextLine();
            String correctAnswer = scanner.nextLine();
            Question q = new Question(question, answers, correctAnswer);
            addQuestion(q);
        }
    }
}