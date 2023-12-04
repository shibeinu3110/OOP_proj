package game.Quiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CorrectAnswerListener implements ActionListener {
    private final MCQ mcq;
    public CorrectAnswerListener(MCQ mcq) {
        this.mcq = mcq;;
    }

    public void setAnswer(String s) {
        mcq.setPlayersAnswer(s);
    }

    public void disableButtons() {

    }
    public MCQ getMcq() {
        return mcq;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mcq.checkAnswer(mcq.getPlayersAnswer());
        mcq.setResultVisibility();
    }
}
