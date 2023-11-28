package game.Quiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CorrectAnswerListenerC implements ActionListener {
    private final MCQ mcq;
    public CorrectAnswerListenerC(MCQ mcq) {
        this.mcq = mcq;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mcq.setPlayersAnswer("C");
        mcq.checkAnswer(mcq.getPlayersAnswer());
        mcq.setResultVisibility();
        mcq.aButton.setEnabled(false);
        mcq.bButton.setEnabled(false);
        mcq.dButton.setEnabled(false);
    }
}
