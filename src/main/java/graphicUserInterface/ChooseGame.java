package graphicUserInterface;

import game.src.Hangman.Hangman;
import game.src.Quiz.Game1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static imageDictionary.imageList.*;
import static imageDictionary.imageList.iconGame;

public class ChooseGame extends JFrame implements ActionListener {
    JButton buttonGame, buttonQuiz, buttonBack, buttonAudio;

    JPanel jPanelRight, jPanelLeft;

    private final int WIDTH_WINDOW = 900;
    private final int HEIGHT_WINDOW = 700;

    public ChooseGame() {
        super("Dictionary EV ");            //set title
        //Container container = getContentPane(); //create container

        this.setSize(WIDTH_WINDOW, HEIGHT_WINDOW);   //set size for app
        this.setLocationRelativeTo(null);            //set location is center
        this.setLayout(null);
        //container.setLayout(new BorderLayout());

        //add panel to frame
        /*dictionaryPanel = new DictPanel();
        container.add(dictionaryPanel, BorderLayout.CENTER);*/

        //System.out.println("Time: " + (end - Main.begin)); //Timer
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        jPanelLeft = new JPanel();
        jPanelLeft.setBounds(0,0,900,150);
        jPanelLeft.setLayout(null);
        jPanelLeft.setBackground(Color.gray);

        this.add(jPanelLeft);
        addToLeft();

        jPanelRight = new JPanel();
        jPanelRight.setBounds(0,150,900,550);
        jPanelRight.setLayout(null);
        jPanelRight.setBackground(Color.blue);

        this.add(jPanelRight);
        addToRight();
    }

    void addToLeft() {

    }

    void addToRight() {

        buttonQuiz = new RoundButton("Quiz",20,20);
        buttonQuiz.setBounds(600, 200, 200, 36);
        buttonQuiz.addActionListener(this);
        buttonQuiz.setToolTipText("Câu hỏi ôn tập");
        buttonQuiz.setIcon(iconQuiz);
        jPanelRight.add(buttonQuiz);


        buttonGame = new RoundButton("Hangman",20,20);
        buttonGame.setBounds(600, 250, 200, 36);
        buttonGame.addActionListener(this);
        buttonGame.setToolTipText("Game Hangman");
        buttonGame.setIcon(iconHangman);
        jPanelRight.add(buttonGame);

        buttonAudio = new RoundButton("",45,45);
        buttonAudio.setBounds(10, 0, 25, 25);
        buttonAudio.addActionListener(this);
        if (Setting.isPlaying) {
            buttonAudio.setIcon(iconAudioOff);
            buttonAudio.setToolTipText("Nhấn vào đây để bật nhạc");
        }
        else {
            buttonAudio.setIcon(iconAudioOn);
            buttonAudio.setToolTipText("Nhấn vào đây để tắt nhạc");
        }
        jPanelRight.add(buttonAudio);

        buttonBack = new RoundButton("Back",20,20);

        buttonBack.setBounds(20, 450, 100, 25);
        /*buttonBack.setBackground(Color.white);
        buttonBack.setOpaque(true);*/
        buttonBack.addActionListener(this);
        buttonBack.setIcon(iconBack);
        buttonBack.setToolTipText("Thoát");
        jPanelRight.add(buttonBack);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(buttonQuiz)) {
            this.dispose();
            try {
                Game1 game1 = new Game1();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource().equals(buttonGame)) {
            this.dispose();
            try {
                Hangman hangMan = new Hangman();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource().equals(buttonAudio)) {
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
        if(e.getSource().equals(buttonBack)) {
            sound.SoundPlay.playSoundNonReset("sound/click.wav");
            this.dispose();
            DictFinish dictFinish = new DictFinish();
        }
    }
}
