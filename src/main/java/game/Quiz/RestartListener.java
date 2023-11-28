package game.Quiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestartListener implements ActionListener {

    private MCQ mcq;

    RestartListener(MCQ mcq) {
        this.mcq  = mcq;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        mcq.dispose();

        try {
            new Quiz();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
