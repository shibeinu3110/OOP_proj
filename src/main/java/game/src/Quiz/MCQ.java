package game.src.Quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class MCQ extends JFrame {
    private JPanel panel1;
    public JButton bButton;
    public JButton cButton;
    public JButton dButton;
    public JButton aButton;
    private JLabel questionLabel;
    public JLabel resultText;
    public JButton NEXTButton;
    private JPanel resultPanel;
    private JLabel Score;
    private JLabel finalScoreText;
    private JButton RESTARTButton;

    private String playersAnswer;

    private String correctAnswer;

    private int score;
    public void setPlayersAnswer(String s) {
        playersAnswer = s;
    }

    public void setQuestion(String question) {
        questionLabel.setText(question);
    }

    public void setAnswers(String a, String b, String c, String d) {
        aButton.setText(a);
        bButton.setText(b);
        cButton.setText(c);
        dButton.setText(d);
    }

    public String getPlayersAnswer() {
        return playersAnswer;
    }

    public void setResult(String result) {
        resultText.setText(result);
    }

    public void checkAnswer(String playersAnswer) {
        if (Objects.equals(playersAnswer, correctAnswer)) {
            setResult("  Correct");
            sound.SoundPlay.playSoundNonReset("sound/correct.wav");
            resultPanel.setBackground(new Color(204, 239, 27));
            score += 10;
            Score.setText("Score: " + score + "  ");

        } else {
            setResult("  Game over! The correct answer is " + correctAnswer + ".");
            sound.SoundPlay.playSoundNonReset("sound/nani.wav");
            resultPanel.setBackground(Color.RED);
            NEXTButton.setVisible(false);
            displayGameOver();

        }
    }

    public void displayGameOver() {
        RESTARTButton.setVisible(true);
        ActionListener actionListener = new RestartListener(this);
        RESTARTButton.addActionListener(actionListener);
        finalScoreText.setText("  Your score: " + score);
        finalScoreText.setVisible(true);
    }

    public void setResultVisibility() {
        resultPanel.setVisible(true);
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void hideResultPanel() {
        resultPanel.setVisible(false);
    }


    public MCQ() {
        score = 0;
        Score.setText("Score: 0  ");
        setContentPane(panel1);
        setTitle("Quiz");
        setSize(900, 700);
        setLocationRelativeTo(null);
        resultPanel.setVisible(false);
        RESTARTButton.setVisible(false);
        finalScoreText.setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

}
