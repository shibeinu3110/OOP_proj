package graphicUserInterface;

import connectData.Trie;
import connectData.WordDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static imageDictionary.imageList.*;

public class AddWordPanel extends JFrame implements BaseAbstractClass, ActionListener {
    JButton buttonBack, buttonAudio , buttonAdd;

    JPanel jPanelRight, jPanelLeft;
    String[] type = {"Danh từ" , "Tính từ" ,"Động từ" , "Trạng từ" , "Giới từ" , "Liên từ" , "Đại từ" , "Thán từ" };
    private final int WIDTH_WINDOW = 900;
    private final int HEIGHT_WINDOW = 700;
    String word, meaning;
    JComboBox jComboBoxType;
    JTextField wordField,ipaField;
    JTextArea meaningField;
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
        ImageIcon imageIcon1 = new ImageIcon(addP.getImage());
        label1.setIcon(imageIcon1);
        label1.setBounds(0,0,900,150);
        jPanelLeft.add(label1);
    }

    @Override
    public void addToBot() {


        /**word field*/
        wordField = new JTextField("Nhập từ muốn thêm ở đây");
        wordField.setBounds(320,140,250,40);
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
                    wordField.setText("Chưa nhập từ");
                }
            }
        });
        jPanelRight.add(wordField);

        /**ipa field*/
        ipaField = new JTextField("Nhập IPA của từ");
        ipaField.setBounds(320,210,250,40);
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
                    ipaField.setText("Từ hiện tại chưa có IPA");
                }
            }
        });
        jPanelRight.add(ipaField);

        /**type word selection*/
        jComboBoxType = new JComboBox<>(type);
        jComboBoxType.setBounds(320,275,250,40);
        jPanelRight.add(jComboBoxType);

        /**meaning field*/
        meaningField = new JTextArea("Nhập nghĩa của từ muốn thêm");
        meaningField.setBounds(320,340,450,60);
        //meaningField.setHorizontalAlignment(JTextField.LEFT);
        //meaningField.setVerticalAlignment(JTextField.TOP);
        meaningField.setLineWrap(true);
        meaningField.setWrapStyleWord(true);
        meaningField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Xóa nội dung khi trường văn bản nhận focus
                meaningField.setText(" ");
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Kiểm tra nếu trường văn bản trống rỗng, thì đặt lại văn bản mặc định
                if (meaningField.getText().isEmpty()) {
                    meaningField.setText(" Từ hiện tại chưa có nghĩa ");
                }
            }
        });

        meaningField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Do nothing
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Insert a tab character when Enter key is pressed
                    meaningField.append("\n\t- ");
                    // Consume the event to prevent a newline from being inserted
                    e.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Do nothing
            }
        });
        meaningField.setLineWrap(true);
        meaningField.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(meaningField);
        scrollPane.setBounds(320, 340, 450, 60);


        wordField.setFont(new Font("Arial", Font.BOLD, 14));
        ipaField.setFont(new Font("Arial", Font.BOLD, 14));
        jComboBoxType.setFont(new Font("Arial", Font.BOLD, 14));
        meaningField.setFont(new Font("Arial", Font.BOLD, 14));


        //jPanelRight.add(meaningField);
        jPanelRight.add(scrollPane, BorderLayout.CENTER);

        buttonAdd = new RoundButton("Thêm từ",20,20);
        buttonAdd.setBounds(500, 440, 100, 30);
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
        ImageIcon imageIcon1 = new ImageIcon(addP2.getImage());
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
            //Trie.insert(word);
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
