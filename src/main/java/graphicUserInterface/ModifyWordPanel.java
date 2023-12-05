package graphicUserInterface;

import connectData.WordDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static imageDictionary.imageList.*;

public class ModifyWordPanel extends JFrame implements  ActionListener {
    JPanel jPanelRight, jPanelLeft;
    JButton buttonBack, buttonAudio , buttonModify;
    private final int WIDTH_WINDOW = 900;
    private final int HEIGHT_WINDOW = 700;
    JTextArea wordField , modifyField;

    public ModifyWordPanel(String selectedWord,String vieString)  {
        super("Dictionary EV ");            //set title

        this.setSize(WIDTH_WINDOW, HEIGHT_WINDOW);   //set size for app
        this.setLocationRelativeTo(null);            //set location is center
        this.setLayout(null);

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
        addToBot(selectedWord,vieString );
    }


    public void addToTop() {
        JLabel label1 = new JLabel();
        ImageIcon imageIcon1 = new ImageIcon(modifyP.getImage());
        label1.setIcon(imageIcon1);
        label1.setBounds(0,0,900,150);
        jPanelLeft.add(label1);
    }




    public void addToBot(String select ,String modify) {

        wordField = new JTextArea();
        modifyField = new JTextArea();
        wordField.setBounds(315,85,300,30);
        //modifyField.setBounds(300,170,400,80);

        wordField.setLineWrap(true);
        wordField.setWrapStyleWord(true);


        modifyField.setLineWrap(true);
        modifyField.setWrapStyleWord(true);

        wordField.setFont(new Font("SANS_SERIF", Font.BOLD, 14));
        modifyField.setFont(new Font("SANS_SERIF", Font.BOLD, 14));


        modify = modify.replace("<C><F><I><N><Q>", "");
        modify = modify.replace("</Q></N></I></F></C>", "");
        modify = modify.replace("<br />-", "\n\t-");
        modify = modify.replace("<br />=", "\n\t\t=");
        modify = modify.replace("<br />*", "\n ✽");
        modify = modify.replace("<br />", "\n");
        modify = modify.replace("@", "✨  ");


        wordField.setText(select);
        modifyField.setText(modify);

        JScrollPane scrollPane = new JScrollPane(modifyField);
        scrollPane.setBounds(315, 170, 500, 250);

        jPanelRight.add(wordField);
        jPanelRight.add(scrollPane);

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

        buttonModify = new RoundButton("Sửa từ",20,20);
        buttonModify.setBounds(500, 440, 100, 30);
        buttonModify.addActionListener(this);
        jPanelRight.add(buttonModify);

        JLabel label1 = new JLabel();
        ImageIcon imageIcon1 = new ImageIcon(modifyP2.getImage());
        label1.setIcon(imageIcon1);
        label1.setBounds(0,0,900,550);
        jPanelRight.add(label1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(buttonBack)) {
            sound.SoundPlay.playSoundNonReset("sound/click.wav");
            this.dispose();
            new DictPanel();
        }
        if(e.getSource().equals(buttonModify)) {
            //WordDAO.getInstance().updateWord(wordField.getText(), modifyField.getText());
            int option = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc chắn muốn sửa từ?",
                    "Xác nhận sửa từ",
                    JOptionPane.YES_NO_OPTION);


            if (option == JOptionPane.YES_OPTION) {
                WordDAO.getInstance().updateWord(wordField.getText(), modifyField.getText());

                JOptionPane.showMessageDialog(
                        null,
                        "Đã cập nhật từ thành công!",
                        "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);

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
    }
}
