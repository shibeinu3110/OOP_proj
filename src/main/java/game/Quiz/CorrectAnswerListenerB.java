package game.Quiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CorrectAnswerListenerB implements ActionListener {
    private final MCQ mcq;
    public CorrectAnswerListenerB(MCQ mcq) {
        this.mcq = mcq;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mcq.setPlayersAnswer("B");
        mcq.checkAnswer(mcq.getPlayersAnswer());
        mcq.setResultVisibility();
        mcq.aButton.setEnabled(false);
        mcq.cButton.setEnabled(false);
        mcq.dButton.setEnabled(false);
    }
}
