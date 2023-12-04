package game.Quiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CorrectAnswerListenerD extends CorrectAnswerListener implements ActionListener {

    public CorrectAnswerListenerD(MCQ mcq) {
        super(mcq);
        mcq.setPlayersAnswer("D");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        super.setAnswer("D");
        super.actionPerformed(e);
        super.getMcq().bButton.setEnabled(false);
        super.getMcq().cButton.setEnabled(false);
        super.getMcq().aButton.setEnabled(false);
    }
}
