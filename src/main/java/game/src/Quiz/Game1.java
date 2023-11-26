package game.src.Quiz;

import java.awt.event.ActionListener;

public class Game1 {

    public Game1() throws Exception {
        MCQ mcq = new MCQ();
        Questions questions = new Questions();
        Question question = questions.getRandomQuestion();
        mcq.setQuestion(question.getQuestion());
        mcq.setAnswers(question.getAnswers()[0], question.getAnswers()[1], question.getAnswers()[2], question.getAnswers()[3]);
        mcq.setCorrectAnswer(question.getCorrectAnswer());
        ActionListener correctAnswerListenerA = new CorrectAnswerListenerA(mcq);
        ActionListener correctAnswerListenerB = new CorrectAnswerListenerB(mcq);
        ActionListener correctAnswerListenerC = new CorrectAnswerListenerC(mcq);
        ActionListener correctAnswerListenerD = new CorrectAnswerListenerD(mcq);
        mcq.aButton.addActionListener(correctAnswerListenerA);
        mcq.bButton.addActionListener(correctAnswerListenerB);
        mcq.cButton.addActionListener(correctAnswerListenerC);
        mcq.dButton.addActionListener(correctAnswerListenerD);
        ActionListener next = new NextListener(questions, mcq);
        mcq.NEXTButton.addActionListener(next);

    }
}

