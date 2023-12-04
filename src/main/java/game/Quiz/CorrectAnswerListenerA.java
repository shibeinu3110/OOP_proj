package game.Quiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CorrectAnswerListenerA extends CorrectAnswerListener implements ActionListener {

    public CorrectAnswerListenerA(MCQ mcq) {
        super(mcq);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.setAnswer("A");
        super.actionPerformed(e);
        super.getMcq().bButton.setEnabled(false);
        super.getMcq().cButton.setEnabled(false);
        super.getMcq().dButton.setEnabled(false);
    }

}
