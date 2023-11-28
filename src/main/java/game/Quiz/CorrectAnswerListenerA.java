package game.Quiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CorrectAnswerListenerA implements ActionListener {
    private final MCQ mcq;
    public CorrectAnswerListenerA(MCQ mcq) {
        this.mcq = mcq;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mcq.setPlayersAnswer("A");
        mcq.checkAnswer(mcq.getPlayersAnswer());
        mcq.setResultVisibility();
        mcq.bButton.setEnabled(false);
        mcq.cButton.setEnabled(false);
        mcq.dButton.setEnabled(false);
    }
}
