package graphicUserInterface;

import connectData.WordDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import static imageDictionary.imageList.*;

public class AddWordPanel extends JFrame implements BaseAbstractClass, ActionListener {
    JButton buttonBack, buttonAudio , buttonAdd;

    JPanel jPanelRight, jPanelLeft;
    String[] type = {"Danh từ" , "Tính từ" ,"Động từ" , "Trạng từ" , "Giới từ" , "Liên từ" , "Đại từ" , "Thán từ" };
    private final int WIDTH_WINDOW = 900;
    private final int HEIGHT_WINDOW = 700;
    String word, meaning;
    JComboBox jComboBoxType;
    JTextField wordField,ipaField,meaningField;
    public AddWordPanel() {
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
        addToBot();
    }

    @Override
    public void addToTop() {
        JLabel label1 = new JLabel();
        ImageIcon imageIcon1 = new ImageIcon(menuP.getImage());
        label1.setIcon(imageIcon1);
        label1.setBounds(0,0,900,150);
        jPanelLeft.add(label1);
    }

    @Override
    public void addToBot() {
        /*JTextField wordField = new JTextField(15);
        JTextField detailField = new JTextField(15);

        JPanel myPanel = new JPanel(new GridLayout(2, 2));
        myPanel.add(new JLabel("Từ mới:"));
        myPanel.add(wordField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Nghĩa mới:"));
        myPanel.add(detailField);

        if (JOptionPane.showConfirmDialog(null, myPanel, "Thêm từ",
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            WordDAO.getInstance().addWord(wordField.getText(), detailField.getText());
            // JDBCStatement.sortData()WordDAO.getInstance().addWord();
        }*/

        /**word field*/
        wordField = new JTextField("Nhập từ muốn thêm ở đây");
        wordField.setBounds(50,50,250,30);
        wordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Xóa nội dung khi trường văn bản nhận focus
                wordField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Kiểm tra nếu trường văn bản trống rỗng, thì đặt lại văn bản mặc định
                if (wordField.getText().isEmpty()) {
                    wordField.setText("Nhập vào đây");
                }
            }
        });
        jPanelRight.add(wordField);

        /**ipa field*/
        ipaField = new JTextField("Nhập IPA của từ");
        ipaField.setBounds(50,90,250,30);
        ipaField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Xóa nội dung khi trường văn bản nhận focus
                ipaField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Kiểm tra nếu trường văn bản trống rỗng, thì đặt lại văn bản mặc định
                if (ipaField.getText().isEmpty()) {
                    ipaField.setText("Nhập vào đây");
                }
            }
        });
        jPanelRight.add(ipaField);

        /**type word selection*/
        jComboBoxType = new JComboBox<>(type);
        jComboBoxType.setBounds(50,140,250,30);
        jPanelRight.add(jComboBoxType);

        /**meaning field*/
        meaningField = new JTextField("Nhập nghĩa của từ muốn thêm");
        meaningField.setBounds(50,180,250,30);
        meaningField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Xóa nội dung khi trường văn bản nhận focus
                meaningField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Kiểm tra nếu trường văn bản trống rỗng, thì đặt lại văn bản mặc định
                if (meaningField.getText().isEmpty()) {
                    meaningField.setText("Nhập vào đây");
                }
            }
        });
        jPanelRight.add(meaningField);


        buttonAdd = new RoundButton("Thêm từ",20,20);
        buttonAdd.setBounds(500, 300, 100, 30);
        buttonAdd.addActionListener(this);
        jPanelRight.add(buttonAdd);

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

        JLabel label1 = new JLabel();
        ImageIcon imageIcon1 = new ImageIcon(bg5.getImage());
        label1.setIcon(imageIcon1);
        label1.setBounds(0,0,900,550);
        jPanelRight.add(label1);

        meaning = "<C><F><I><N><Q>@" + word + " " + ipaField.getText() +
                "<br />*" + jComboBoxType.getSelectedItem() + "<br />-" +  meaningField.getText() + "</Q></N></I></F></C>";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(buttonBack)) {
            sound.SoundPlay.playSoundNonReset("sound/click.wav");
            this.dispose();
            new DictPanel();
        }
        if(e.getSource().equals(buttonAdd)) {
            word = wordField.getText();
            meaning = "<C><F><I><N><Q>@" + word + " " + ipaField.getText() +
                    "<br />*" + jComboBoxType.getSelectedItem() + "<br />-" +  meaningField.getText() + "</Q></N></I></F></C>";
            WordDAO.getInstance().addWord(word, meaning);
            JOptionPane.showMessageDialog(this, "Từ đã được thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
