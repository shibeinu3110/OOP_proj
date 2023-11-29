package graphicUserInterface;

import API.Translator;
import API.SpeechText;
import sound.SoundPlay;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static imageDictionary.imageList.*;


public class NewWindow extends JFrame implements ActionListener {
    private final int WIDTH_WINDOW = 900;
    private final int HEIGHT_WINDOW = 700;
    private DictPanel dictionaryPanel;
    //public long end = Calendar.getInstance().getTimeInMillis(); //Timer

    //protected JSplitPane split;
    JButton buttonBack , buttonTrans, buttonSoundEng, buttonSoundVie, buttonAudio;

    JTextArea textFieldQues;
    JTextArea textFieldAns;
    JPanel jPanelTop, jPanelBot;

    boolean engToVie;
    String[] option = {"Tiếng Anh -- English", "Tiếng Việt -- Vietnamese"};
    String[] option1 = {"Tiếng Việt -- Vietnamese" , "Tiếng Anh -- English"};

    String[] option3 = {"UK", "US", "VIE"};
    String[] option4 = {"VIE", "UK", "US"};
    JComboBox jComboBox1 , jComboBox2, jComboBox3, jComboBox4;

    public NewWindow() {
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

    void addToTop()
    {
        JLabel label1 = new JLabel();
        ImageIcon imageIcon1 = new ImageIcon(transP.getImage());
        label1.setIcon(imageIcon1);
        label1.setBounds(0,0,900,150);
        jPanelTop.add(label1);
    }

    void addToBot()
    {
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
        scrollPane.setBounds(30, 110, 350, 300);
        jPanelBot.add(scrollPane, BorderLayout.CENTER);


        //text2
        textFieldAns = new JTextArea();
        textFieldAns.setFont(new Font("SANS_SERIF", Font.BOLD, 18));
        textFieldAns.setLineWrap(true);
        textFieldAns.setWrapStyleWord(true);

        JScrollPane scrollPane1 = new JScrollPane(textFieldAns);
        scrollPane1.setBounds(520, 110, 350, 300);

        jPanelBot.add(scrollPane1, BorderLayout.CENTER);

        //button translate
        buttonTrans = new RoundButton("Dịch",20,20);
        buttonTrans.setIcon(iconTrans);
        buttonTrans.setBounds(406, 177, 90, 36);
        buttonTrans.addActionListener(this);
        buttonTrans.setToolTipText("Tra từ");
        jPanelBot.add(buttonTrans);

        buttonSoundEng = new RoundButton("",30, 30);
        buttonSoundEng.setBounds(270, 60, 30, 30);
        buttonSoundEng.addActionListener(this);
        buttonSoundEng.setIcon(iconSound);
        buttonSoundEng.setToolTipText("Âm thanh của đoạn");
        jPanelBot.add(buttonSoundEng);

        buttonSoundVie = new RoundButton("",30, 30);
        buttonSoundVie.setBounds(760, 60, 30, 30);
        buttonSoundVie.addActionListener(this);
        buttonSoundVie.setIcon(iconSound);
        buttonSoundVie.setToolTipText("Âm thanh của đoạn");
        jPanelBot.add(buttonSoundVie);

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
        jComboBox2 = new JComboBox(option1);
        jComboBox3 = new JComboBox(option3);
        jComboBox4 = new JComboBox(option4);

        jComboBox1.setRenderer(new IconComboBoxRenderer(engIcon, vieIcon));
        jComboBox2.setRenderer(new IconComboBoxRenderer(engIcon, vieIcon));

        jComboBox1.setBounds(30,60,200,30);
        jComboBox2.setBounds(520,60,200,30);
        jComboBox3.setBounds(330,60,45,30);
        jComboBox4.setBounds(820,60,45,30);

        jPanelBot.add(jComboBox1);
        jPanelBot.add(jComboBox2);
        jPanelBot.add(jComboBox3);
        jPanelBot.add(jComboBox4);
        jPanelBot.add(label1);
    }

    void checkEngToVie()
    {
        if(jComboBox1.getSelectedItem().equals("Tiếng Anh -- English") && jComboBox2.getSelectedItem().equals("Tiếng Việt -- Vietnamese"))
        {
            engToVie  = true;
        }

        if(jComboBox2.getSelectedItem().equals("Tiếng Anh -- English") && jComboBox1.getSelectedItem().equals("Tiếng Việt -- Vietnamese"))
        {
            engToVie = false;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(buttonBack))
        {
            sound.SoundPlay.playSoundNonReset("sound/click.wav");
            this.dispose();
            DictFinish dictFinish = new DictFinish();
        }
        if(e.getSource().equals(buttonTrans))
        {
            sound.SoundPlay.playSoundNonReset("sound/click.wav");
            checkEngToVie();
            if(engToVie)
            {
                this.textFieldAns.setText(Translator.translateEnToVi(this.textFieldQues.getText()));
            }
            else
            {
                this.textFieldAns.setText(Translator.translateViToEn(this.textFieldQues.getText()));
            }
        }
        if (e.getSource().equals(buttonSoundEng)) {
            sound.SoundPlay.playSoundNonReset("sound/click.wav");
            if(jComboBox3.getSelectedItem().equals("UK")) {
                SpeechText.playSoundGoogleTranslateEnUKToVi(this.textFieldQues.getText());
            } else if(jComboBox3.getSelectedItem().equals("US")) {
                SpeechText.playSoundGoogleTranslateEnUSToVi(this.textFieldQues.getText());
            } else {
                SpeechText.playSoundGoogleTranslateViToEn(this.textFieldQues.getText());
            }
        }
        if (e.getSource().equals(buttonSoundVie)) {
            sound.SoundPlay.playSoundNonReset("sound/click.wav");
            if(jComboBox4.getSelectedItem().equals("UK")) {
                SpeechText.playSoundGoogleTranslateEnUKToVi(this.textFieldAns.getText());
            } else if(jComboBox4.getSelectedItem().equals("US")) {
                SpeechText.playSoundGoogleTranslateEnUSToVi(this.textFieldAns.getText());
            } else {
                SpeechText.playSoundGoogleTranslateViToEn(this.textFieldAns.getText());
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

    static class IconComboBoxRenderer extends DefaultListCellRenderer {
        private final ImageIcon iconEnglish;
        private final ImageIcon iconVietnamese;

        public IconComboBoxRenderer(ImageIcon iconEnglish, ImageIcon iconVietnamese) {
            this.iconEnglish = iconEnglish;
            this.iconVietnamese = iconVietnamese;
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value != null) {
                String text = value.toString();
                if (text.equals("Tiếng Anh -- English")) {
                    setIcon(iconEnglish);
                } else if (text.equals("Tiếng Việt -- Vietnamese")) {
                    setIcon(iconVietnamese);
                } else {
                    setIcon(null);
                }
            }

            return this;
        }
    }

}



