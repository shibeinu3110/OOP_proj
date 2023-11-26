package game.src.Quiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CorrectAnswerListenerD implements ActionListener {
    private final MCQ mcq;
    public CorrectAnswerListenerD(MCQ mcq) {
        this.mcq = mcq;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mcq.setPlayersAnswer("D");
        mcq.checkAnswer(mcq.getPlayersAnswer());
        mcq.setResultVisibility();
        mcq.aButton.setEnabled(false);
        mcq.bButton.setEnabled(false);
        mcq.cButton.setEnabled(false);
    }
}
