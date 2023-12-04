package game.Quiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CorrectAnswerListenerB extends CorrectAnswerListener implements ActionListener {

    public CorrectAnswerListenerB(MCQ mcq) {
        super(mcq);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        super.setAnswer("B");
        super.actionPerformed(e);
        super.getMcq().aButton.setEnabled(false);
        super.getMcq().cButton.setEnabled(false);
        super.getMcq().dButton.setEnabled(false);
    }
}
