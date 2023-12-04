package game.Quiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CorrectAnswerListenerC extends CorrectAnswerListener implements ActionListener {

    public CorrectAnswerListenerC(MCQ mcq) {
        super(mcq);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        super.setAnswer("C");
        super.actionPerformed(e);
        super.getMcq().bButton.setEnabled(false);
        super.getMcq().aButton.setEnabled(false);
        super.getMcq().dButton.setEnabled(false);
    }
}
