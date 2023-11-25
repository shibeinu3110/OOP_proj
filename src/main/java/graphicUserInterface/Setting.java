package graphicUserInterface;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


import static imageDictionary.imageList.*;


public class Setting extends JFrame implements ActionListener {
    JPanel jPanelTop, jPanelBot;

    JButton buttonBack, buttonAudio;

    JLabel labelMusicBar, labelVolumeBar;

    static boolean isPlaying = true;

    String[] option = {"Music Song 1", "Music Song 2", "Music Song 3", "Music Song 4"};
    String[] option1 = {"Music Song 2", "Music Song 1", "Music Song 3", "Music Song 4"};
    String[] option2 = {"Music Song 3", "Music Song 1", "Music Song 2", "Music Song 4"};
    String[] option3 = {"Music Song 4", "Music Song 1", "Music Song 2", "Music Song 3"};

    public static String soundFile = "sound/game_audio1.wav";

    static JComboBox jComboBox;

    static Clip musicClip;
    static FloatControl volumeControl;
    JSlider volumeSlider;

    public static int savedValue = 100;
    private final int WIDTH_WINDOW = 900;
    private final int HEIGHT_WINDOW = 700;

    public Setting() {
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


        jPanelTop = new JPanel();
        jPanelTop.setBounds(0, 0, 900, 150);
        jPanelTop.setLayout(null);
        jPanelTop.setBackground(Color.red);

        this.add(jPanelTop);
        addToTop();

        jPanelBot = new JPanel();
        jPanelBot.setBounds(0, 150, 900, 550);
        jPanelBot.setLayout(null);
        jPanelBot.setBackground(Color.gray);

        this.add(jPanelBot);
        addToBot();


    }

    void addToTop() {

    }

    void addToBot() {

        labelMusicBar = new JLabel("⁂ Music ⁂");
        labelMusicBar.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        labelMusicBar.setForeground(Color.white);
        labelMusicBar.setBounds(400, 55, 180, 20);
        jPanelBot.add(labelMusicBar);

        labelVolumeBar = new JLabel("Volume");
        labelVolumeBar.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        labelVolumeBar.setForeground(Color.white);
        labelVolumeBar.setBounds(420, 150, 180, 20);
        jPanelBot.add(labelVolumeBar);

        buttonBack = new RoundButton("Back", 20, 20);

        buttonBack.setBounds(20, 450, 100, 25);
        /*buttonBack.setBackground(Color.white);
        buttonBack.setOpaque(true);*/
        buttonBack.addActionListener(this);
        buttonBack.setIcon(iconBack);
        buttonBack.setToolTipText("Thoát");
        jPanelBot.add(buttonBack);

        buttonAudio = new RoundButton("",25,25);
        buttonAudio.setBounds(750, 50, 25, 25);
        buttonAudio.addActionListener(this);
        if (isPlaying) {
            buttonAudio.setIcon(iconAudioOff);
            buttonAudio.setToolTipText("Nhấn vào đây để bật nhạc");
        } else {
            buttonAudio.setIcon(iconAudioOn);
            buttonAudio.setToolTipText("Nhấn vào đây để tắt nhạc");
        }
        jPanelBot.add(buttonAudio);

        if (soundFile.equals("sound/game_audio1.wav")) {
            jComboBox = new JComboBox(option);
        } else if (soundFile.equals("sound/game_audio2.wav")) {
            jComboBox = new JComboBox(option1);
        } else if (soundFile.equals("sound/game_audio3.wav")) {
            jComboBox = new JComboBox(option2);
        } else {
            jComboBox = new JComboBox(option3);
        }
        jComboBox.setBounds(520, 50, 200, 30);
        jPanelBot.add(jComboBox);

        volumeSlider = new JSlider();
        volumeSlider.setBounds(520, 150, 200, 30);
        volumeSlider.setMinimum(0);
        volumeSlider.setMaximum(100);
        volumeSlider.setOrientation(JSlider.HORIZONTAL);
        volumeSlider.setValue(savedValue);
        volumeSlider.addChangeListener(e -> {
            int value = volumeSlider.getValue();
            sound.SoundPlay.setVolume(value);
            savedValue = value;
        });
        jPanelBot.add(volumeSlider);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(buttonBack)) {
            sound.SoundPlay.playSoundNonReset("sound/click.wav");
            this.dispose();
            DictFinish dictFinish = new DictFinish();
        }
        if (e.getSource().equals(buttonAudio)) {
            if (isPlaying) {
                sound.SoundPlay.playSoundNonReset("sound/click.wav");
                chooseMusic();
                sound.SoundPlay.playSoundReset(soundFile);
                sound.SoundPlay.setVolume(savedValue);
                buttonAudio.setIcon(iconAudioOn);
                buttonAudio.setToolTipText("Nhấn vào đây để tắt nhạc");
            } else {
                sound.SoundPlay.clip.stop();
                sound.SoundPlay.clipBack.stop();
                sound.SoundPlay.playSoundNonReset("sound/click.wav");
                buttonAudio.setIcon(iconAudioOff);
                buttonAudio.setToolTipText("Nhấn vào đây để bật nhạc");
            }
            isPlaying = !isPlaying;
        }
    }

    public static void chooseMusic() {
        if (jComboBox != null) {
            if (jComboBox.getSelectedItem().equals("Music Song 1")) {
                soundFile = "sound/game_audio1.wav";
            } else if (jComboBox.getSelectedItem().equals("Music Song 2")) {
                soundFile = "sound/game_audio2.wav";
            } else if (jComboBox.getSelectedItem().equals("Music Song 3")) {
                soundFile = "sound/game_audio3.wav";
            } else if (jComboBox.getSelectedItem().equals("Music Song 4")) {
                soundFile = "sound/game_audio4.wav";
            }
        }
    }

}