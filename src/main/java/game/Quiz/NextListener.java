package game.Quiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextListener implements ActionListener {
    private final Questions questions;
    private final MCQ mcq;

    public NextListener(Questions questions, MCQ mcq) {
        this.questions = questions;
        this.mcq = mcq;
    }

    public void actionPerformed(ActionEvent e) {

        mcq.hideResultPanel();
        mcq.aButton.setEnabled(true);
        mcq.bButton.setEnabled(true);
        mcq.cButton.setEnabled(true);
        mcq.dButton.setEnabled(true);
        Question question = questions.getRandomQuestion();
        mcq.setQuestion(question.getQuestion());
        mcq.setAnswers(question.getAnswers()[0], question.getAnswers()[1], question.getAnswers()[2], question.getAnswers()[3]);
        mcq.setCorrectAnswer(question.getCorrectAnswer());

    }
}
