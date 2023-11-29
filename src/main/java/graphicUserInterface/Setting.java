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
import javax.swing.JOptionPane;

import static imageDictionary.imageList.*;


public class Setting extends JFrame implements ActionListener {
    JPanel jPanelTop, jPanelBot;

    JButton buttonBack, buttonAudio;

    JLabel labelMusicBar, labelVolumeBar;

    public static boolean isPlaying = false;

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
        JLabel label1 = new JLabel();
        ImageIcon imageIcon1 = new ImageIcon(bg5.getImage());
        label1.setIcon(imageIcon1);
        label1.setBounds(0,0,900,550);

        labelMusicBar = new JLabel("⁂ Music ⁂");
        labelMusicBar.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        labelMusicBar.setForeground(Color.white);
        labelMusicBar.setBounds(250, 155, 180, 20);
        jPanelBot.add(labelMusicBar);

        labelVolumeBar = new JLabel("Volume");
        labelVolumeBar.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        labelVolumeBar.setForeground(Color.white);
        labelVolumeBar.setBounds(270, 250, 180, 20);
        jPanelBot.add(labelVolumeBar);

        buttonBack = new RoundButton("Back", 20, 20);

        buttonBack.setBounds(30, 440, 100, 30);
        buttonBack.addActionListener(this);
        buttonBack.setIcon(iconBack);
        buttonBack.setToolTipText("Thoát");
        jPanelBot.add(buttonBack);

        buttonAudio = new RoundButton("",25,25);
        buttonAudio.setBounds(750, 55, 23, 23);
        buttonAudio.addActionListener(this);
        buttonAudio.setIcon(iconChoose);
        buttonAudio.setToolTipText("Chọn");
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
        jComboBox.setBounds(370, 150, 200, 30);
        jPanelBot.add(jComboBox);

        volumeSlider = new JSlider();
        volumeSlider.setBounds(370, 250, 200, 30);
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
        jPanelBot.add(label1);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(buttonBack)) {
            sound.SoundPlay.playSoundNonReset("sound/click.wav");
            this.dispose();
            DictFinish dictFinish = new DictFinish();
        }
        if (e.getSource().equals(buttonAudio)) {
            sound.SoundPlay.playSoundNonReset("sound/click.wav");
            String temp = soundFile;
            Setting.chooseMusic();
            if(soundFile.equals(temp)) {
                int choice = JOptionPane.showConfirmDialog(null,
                        "Bạn đang sử dụng nhạc này", "Xác nhận", JOptionPane.DEFAULT_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    sound.SoundPlay.playSoundNonReset("sound/click.wav");
                } else {
                    sound.SoundPlay.playSoundNonReset("sound/click.wav");
                }
            } else {
                int choice = JOptionPane.showConfirmDialog(null,
                        "Bạn có muốn đổi nhạc?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    if (!isPlaying) {
                        sound.SoundPlay.playSoundNonReset("sound/click.wav");
                        sound.SoundPlay.clip.stop();
                        sound.SoundPlay.clipBack.stop();
                        sound.SoundPlay.playSoundReset(soundFile);
                        sound.SoundPlay.setVolume(Setting.savedValue);
                    } else {
                        sound.SoundPlay.playSoundNonReset("sound/click.wav");
                    }
                }
                else {
                    sound.SoundPlay.playSoundNonReset("sound/click.wav");
                    soundFile = temp;
                }
            }
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