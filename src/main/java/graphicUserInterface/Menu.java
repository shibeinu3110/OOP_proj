package graphicUserInterface;

import sound.SoundPlay;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;


import static imageDictionary.imageList.*;


public class Menu extends JFrame implements ActionListener {

    JButton buttonDictPanel, buttonNW, buttonGame, buttonQuiz, buttonSetting, buttonExit, buttonAudio;

    JPanel jPanelRight, jPanelLeft;

    private final int WIDTH_WINDOW = 900;
    private final int HEIGHT_WINDOW = 700;

    public Menu() {
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

        buttonDictPanel = new RoundButton("Tra từ",20,20);

        buttonDictPanel.setBounds(600, 150, 200, 36);
        /*buttonBack.setBackground(Color.white);
        buttonBack.setOpaque(true);*/
        buttonDictPanel.addActionListener(this);
        buttonDictPanel.setToolTipText("Tra từ");
        buttonDictPanel.setIcon(iconSearch);
        jPanelRight.add(buttonDictPanel);


        buttonNW = new RoundButton("Dịch văn bản",20,20);
        buttonNW.setBounds(600, 200, 200, 36);
        buttonNW.addActionListener(this);
        buttonNW.setToolTipText("Dịch văn bản");
        buttonNW.setIcon(iconTrans);
        jPanelRight.add(buttonNW);


        buttonGame = new RoundButton("Trò chơi",20,20);
        buttonGame.setBounds(600, 250, 200, 36);
        buttonGame.addActionListener(this);
        buttonGame.setToolTipText("Trò chơi");
        buttonGame.setIcon(iconGame);
        jPanelRight.add(buttonGame);

        buttonSetting = new RoundButton("Cài đặt",20,20);
        buttonSetting.setBounds(600, 300, 200, 36);
        buttonSetting.addActionListener(this);
        buttonSetting.setToolTipText("Cài đặt");
        buttonSetting.setIcon(iconSetting);
        jPanelRight.add(buttonSetting);

        buttonExit = new RoundButton("Exit",20,20);

        buttonExit.setBounds(20, 450, 100, 25);
        /*buttonBack.setBackground(Color.white);
        buttonBack.setOpaque(true);*/
        buttonExit.addActionListener(this);
        buttonExit.setIcon(iconBack);
        buttonExit.setToolTipText("Thoát");
        jPanelRight.add(buttonExit);

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


    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(buttonDictPanel)) {
            sound.SoundPlay.playSoundNonReset("sound/click.wav");
            this.dispose();
            DictPanel dictionaryPanel = new DictPanel();
        }
        if(e.getSource().equals(buttonNW)) {
            sound.SoundPlay.playSoundNonReset("sound/click.wav");
            this.dispose();
            NewWindow newWindow = new NewWindow();
        }
        if(e.getSource().equals(buttonSetting)) {
            sound.SoundPlay.playSoundNonReset("sound/click.wav");
            this.dispose();
            Setting setting = new Setting();
        }
        if(e.getSource().equals(buttonExit)) {
            sound.SoundPlay.playSoundNonReset("sound/click.wav");
            this.dispose();
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
    }

}

