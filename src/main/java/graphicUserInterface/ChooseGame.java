package graphicUserInterface;

import game.Hangman.Hangman;
import game.Quiz.Quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static imageDictionary.imageList.*;

public class ChooseGame extends JFrame implements BaseAbstractClass, ActionListener {
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
        addToTop();

        jPanelRight = new JPanel();
        jPanelRight.setBounds(0,150,900,550);
        jPanelRight.setLayout(null);
        jPanelRight.setBackground(Color.blue);

        this.add(jPanelRight);
        addToBot();
    }
    @Override
    public void addToTop() {
        JLabel label1 = new JLabel();
        ImageIcon imageIcon1 = new ImageIcon(gameP.getImage());
        label1.setIcon(imageIcon1);
        label1.setBounds(0,0,900,150);
        jPanelLeft.add(label1);
    }

    @Override
    public void addToBot() {
        JLabel label1 = new JLabel();
        ImageIcon imageIcon1 = new ImageIcon(bg5.getImage());
        label1.setIcon(imageIcon1);
        label1.setBounds(0,0,900,550);

        buttonQuiz = new RoundButton(null,20,20);
        buttonQuiz.setBounds(80, 80, 300, 300);
        buttonQuiz.addActionListener(this);
        buttonQuiz.setToolTipText("Câu hỏi ôn tập");
        ImageIcon iconQuiz = new ImageIcon(quizIcon.getImage());
        buttonQuiz.setIcon(iconQuiz);
        buttonQuiz.setHorizontalAlignment(SwingConstants.CENTER);
        buttonQuiz.setVerticalAlignment(SwingConstants.CENTER);
        jPanelRight.add(buttonQuiz);


        buttonGame = new RoundButton(null,20,20);
        buttonGame.setBounds(490, 80, 300, 300);
        buttonGame.addActionListener(this);
        ImageIcon iconHang = new ImageIcon(hangIcon.getImage());
        buttonGame.setIcon(iconHang);
        buttonGame.setHorizontalAlignment(SwingConstants.CENTER);
        buttonGame.setVerticalAlignment(SwingConstants.CENTER);
        jPanelRight.add(buttonGame);

        buttonAudio = new RoundButton("",100,100);
        buttonAudio.setBounds(15, 15, 35, 35);
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
        buttonBack.setBounds(30, 440, 100, 30);
        buttonBack.addActionListener(this);
        buttonBack.setIcon(iconBack);
        buttonBack.setToolTipText("Thoát");
        jPanelRight.add(buttonBack);
        jPanelRight.add(label1);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(buttonQuiz)) {
            this.dispose();
            try {
                new Quiz();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getSource().equals(buttonGame)) {
            this.dispose();
            try {
                new Hangman();
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
            new DictFinish();
        }
    }
}
