package game.src.Quiz;

import graphicUserInterface.ChooseGame;
import graphicUserInterface.RoundButton;
import graphicUserInterface.Setting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static imageDictionary.imageList.*;

public class MCQ extends JFrame implements ActionListener {
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
    private JPanel buttonPanel;
    private JButton buttonBack;
    private JButton buttonAudio;

    private String playersAnswer;


    private String correctAnswer;

    private final int WIDTH_WINDOW = 900;
    private final int HEIGHT_WINDOW = 700;

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
        setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
        setLocationRelativeTo(null);

        buttonBack.setBounds(-1, -1, 100, 25);
        /*buttonBack.setBackground(Color.white);
        buttonBack.setOpaque(true);*/
        buttonBack.addActionListener(this);
        buttonBack.setIcon(iconBack);
        buttonBack.setToolTipText("Thoát");

        buttonAudio.setBounds(-1, -1 , 25, 25);
        buttonAudio.addActionListener(this);
        if (Setting.isPlaying) {
            buttonAudio.setIcon(iconAudioOff);
            buttonAudio.setToolTipText("Nhấn vào đây để bật nhạc");
        }
        else {
            buttonAudio.setIcon(iconAudioOn);
            buttonAudio.setToolTipText("Nhấn vào đây để tắt nhạc");
        }

        resultPanel.setVisible(false);
        RESTARTButton.setVisible(false);
        finalScoreText.setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(buttonBack)) {
            sound.SoundPlay.playSoundNonReset("sound/click.wav");
            this.dispose();
            new ChooseGame();
        }
        if (e.getSource().equals(buttonAudio)) {
            sound.SoundPlay.playSoundNonReset("sound/click.wav");
            if (Setting.isPlaying) {
                sound.SoundPlay.playSoundNonReset("sound/click.wav");
                Setting.chooseMusic();
                sound.SoundPlay.playSoundReset(Setting.soundFile);
                sound.SoundPlay.setVolume(Setting.savedValue);
                buttonAudio.setIcon(iconAudioOn);
                buttonAudio.setToolTipText("Nhấn vào đây để tắt nhạc");
            } else {
                sound.SoundPlay.clip.stop();
                sound.SoundPlay.clipBack.stop(); // de phong viec back lai trang cu se van phat nhac
                sound.SoundPlay.playSoundNonReset("sound/click.wav");
                buttonAudio.setIcon(iconAudioOff);
                buttonAudio.setToolTipText("Nhấn vào đây để bật nhạc");
            }
            Setting.isPlaying = !Setting.isPlaying;
        }
    }
}
