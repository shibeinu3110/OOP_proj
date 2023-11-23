package graphicUserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static imageDictionary.imageList.*;

public class Menu extends JFrame implements ActionListener {
    private final int WIDTH_WINDOW = 900;
    private final int HEIGHT_WINDOW = 700;

    JButton buttonDictPanel, buttonNW, buttonGame, buttonAudioMenu;

    JPanel jPanelRight, jPanelLeft;

    public Menu() {
        super("Dictionary EV ");            //set title
        //Container container = getContentPane(); //create container

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

        buttonDictPanel.setBounds(20, 450, 100, 25);
        /*buttonBack.setBackground(Color.white);
        buttonBack.setOpaque(true);*/
        buttonDictPanel.addActionListener(this);
        //buttonDictPanel.setIcon();
        buttonDictPanel.setToolTipText("Tra từ");
        jPanelRight.add(buttonDictPanel);


        buttonNW = new RoundButton("Dịch câu",20,20);
        //buttonNW.setIcon(iconTrans);
        buttonNW.setBounds(406, 177, 90, 36);
        buttonNW.addActionListener(this);
        buttonNW.setToolTipText("Dịch câu");
        jPanelRight.add(buttonNW);


        buttonAudioMenu = new RoundButton("",25,25);
        buttonAudioMenu.setBounds(10, 5, 25, 25);
        buttonAudioMenu.addActionListener(this);
        if (sound.SoundPlay.isPlaying) {
            buttonAudioMenu.setIcon(iconAudioOff);
            buttonAudioMenu.setToolTipText("Nhấn vào đây để bật nhạc");
        } else {
            buttonAudioMenu.setIcon(iconAudioOn);
            buttonAudioMenu.setToolTipText("Nhấn vào đây để tắt nhạc");
        }
        jPanelRight.add(buttonAudioMenu);


    }

    public void actionPerformed(ActionEvent e) {

    }


}
