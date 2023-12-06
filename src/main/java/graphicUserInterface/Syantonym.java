package graphicUserInterface;

import API.SpeechText;
import API.SynonymAPI;
import model.Word;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static imageDictionary.imageList.*;


public class Syantonym extends JFrame implements BaseAbstractClass, ActionListener {
    private final int WIDTH_WINDOW = 900;
    private final int HEIGHT_WINDOW = 700;
    private DictPanel dictionaryPanel;
    //public long end = Calendar.getInstance().getTimeInMillis(); //Timer

    //protected JSplitPane split;
    JButton buttonBack , buttonTrans, buttonAudio;

    JTextArea textFieldQues;
    JTextArea textFieldAns;
    JPanel jPanelTop, jPanelBot;
    JLabel labelSearchBar;

    String[] option = {"Từ đồng nghĩa", "Từ trái nghĩa"};
    JComboBox jComboBox1;




    public Syantonym() {
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
        jPanelTop.setBounds(0,0,900,150);
        jPanelTop.setLayout(null);
        jPanelTop.setBackground(Color.gray);

        this.add(jPanelTop);
        addToTop();

        jPanelBot = new JPanel();
        jPanelBot.setBounds(0,150,900,550);
        jPanelBot.setLayout(null);
        jPanelBot.setBackground(Color.blue);

        this.add(jPanelBot);
        addToBot();
    }
    @Override
    public void addToTop()
    {
        JLabel label1 = new JLabel();
        ImageIcon imageIcon1 = new ImageIcon(simpP.getImage());
        label1.setIcon(imageIcon1);
        label1.setBounds(0,0,900,150);
        jPanelTop.add(label1);
    }
    @Override
    public void addToBot()
    {

        labelSearchBar = new JLabel("⁂ Nhập từ cần tìm ⁂");
        labelSearchBar.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        labelSearchBar.setForeground(Color.white);
        labelSearchBar.setBounds(100, 40, 240, 25);
        jPanelBot.add(labelSearchBar);

        JLabel label1 = new JLabel();
        ImageIcon imageIcon1 = new ImageIcon(bg5.getImage());
        label1.setIcon(imageIcon1);
        label1.setBounds(0,0,900,550);

        //button
        buttonBack = new RoundButton("Back",20,20);

        buttonBack = new RoundButton("Back",20,20);
        buttonBack.setBounds(30, 440, 100, 30);
        buttonBack.addActionListener(this);
        buttonBack.setIcon(iconBack);
        buttonBack.setToolTipText("Thoát");
        jPanelBot.add(buttonBack);

        //text 1
        textFieldQues = new JTextArea();
        textFieldQues.setFont(new Font("SANS_SERIF", Font.BOLD, 18));
        textFieldQues.setLineWrap(true);
        textFieldQues.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textFieldQues);
        scrollPane.setBounds(30, 80, 350, 350);
        jPanelBot.add(scrollPane, BorderLayout.CENTER);


        //text2
        textFieldAns = new JTextArea();
        textFieldAns.setFont(new Font("SANS_SERIF", Font.BOLD, 18));
        textFieldAns.setLineWrap(true);
        textFieldAns.setWrapStyleWord(true);

        JScrollPane scrollPane1 = new JScrollPane(textFieldAns);
        scrollPane1.setBounds(520, 80, 350, 350);

        jPanelBot.add(scrollPane1, BorderLayout.CENTER);

        //button translate
        buttonTrans = new RoundButton("Tìm",20,20);
        buttonTrans.setIcon(iconTrans);
        buttonTrans.setBounds(406, 177, 90, 36);
        buttonTrans.addActionListener(this);
        buttonTrans.setToolTipText("Tìm từ");
        jPanelBot.add(buttonTrans);

        buttonAudio = new RoundButton("",100,100);
        buttonAudio.setBounds(15, 15, 35, 35);
        buttonAudio.addActionListener(this);
        if (Setting.isPlaying) {
            buttonAudio.setIcon(iconAudioOff);
            buttonAudio.setToolTipText("Nhấn vào đây để bật nhạc");
        } else {
            buttonAudio.setIcon(iconAudioOn);
            buttonAudio.setToolTipText("Nhấn vào đây để tắt nhạc");
        }
        jPanelBot.add(buttonAudio);

        jComboBox1 = new JComboBox(option);
        jComboBox1.setBounds(585,40,200,30);


        jPanelBot.add(jComboBox1);
        jPanelBot.add(label1);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(buttonBack))
        {
            sound.SoundPlay.playSoundNonReset("sound/click.wav");
            this.dispose();
            new DictFinish();
        }
        if(e.getSource().equals(buttonTrans))
        {
            sound.SoundPlay.playSoundNonReset("sound/click.wav");
            ArrayList<String> ans = new ArrayList<>();
            try {
                if (jComboBox1.getSelectedItem().equals("Từ đồng nghĩa")) {
                    ans = SynonymAPI.getSynonym(this.textFieldQues.getText());
                } else {
                    ans = SynonymAPI.getAntonym(this.textFieldQues.getText());
                }
                String s = SynonymAPI.convert(ans);
                this.textFieldAns.setText(s);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

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

