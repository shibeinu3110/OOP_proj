package game.Hangman;

import graphicUserInterface.ChooseGame;
import graphicUserInterface.RoundButton;
import graphicUserInterface.Setting;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

import static imageDictionary.imageList.*;

public class Hangman extends JFrame implements ActionListener {
    private JLabel hangingTree;
    private JLabel hiddenWordLabel;

    private JLabel resultLabel;
    private JLabel wordLabel;
    private String word;

    private final JButton[] letterButtons;

    private JDialog resultDialog;
    private int incorrectGuesses;
    private final Font NeueHaas;
    private final Font TanNimbus;

    private JButton buttonAudio;
    private JButton buttonBack;

    private JLabel hintLabel;
    private String hint;




    public Hangman() throws IOException {
        setTitle("Hangman");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        Words words = new Words();
        int randomIndex = words.getRandomIndex();
        word = words.getWord(randomIndex);
        hint = words.getHint(randomIndex);
        letterButtons = new JButton[26];
        NeueHaas = Tools.createFont("src/main/java/game/Hangman/NeueHaasDisplayBlack.ttf");
        TanNimbus = Tools.createFont("src/main/java/game/Hangman/TANNIMBUS.ttf");
        createResultDialog();
        createUIComponents();
        setVisible(true);
    }


    private void createUIComponents() {
        //background
        JLabel background = Tools.loadImage("src/main/java/game/Hangman/gameBackground.png");
        background.setBounds(0, 0, 900, 700);

        //hanging tree
        hangingTree = Tools.loadImage("src/main/java/game/Hangman/hangman0.png");
        hangingTree.setBounds(0, 0, hangingTree.getPreferredSize().width, hangingTree.getPreferredSize().height);

        JPanel jPanel = new JPanel();
        jPanel.setBounds(800, 0, 100, 60);
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel.setBackground(Color.white);
        jPanel.setOpaque(true);



        //hidden word
        hiddenWordLabel = new JLabel(Tools.hideWords(word));
        hiddenWordLabel.setFont(NeueHaas.deriveFont(34f));
        setBoundsHiddenWord();
        hiddenWordLabel.setForeground(new Color(10, 32, 62));

        //hint
        hintLabel = new JLabel(hint);
        hintLabel.setFont(NeueHaas.deriveFont(11f));
        hintLabel.setBounds(382, 50, 127, hintLabel.getPreferredSize().height);
        hintLabel.setForeground(Color.BLACK);
        hintLabel.setVisible(false);


        //letter buttons
        GridLayout gridLayout = new GridLayout(4, 7);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(450, 120, 420, 240 );
        buttonPanel.setLayout(gridLayout);
        buttonPanel.setBackground(Color.WHITE);


        for(char c = 'A'; c <= 'Z'; c++) {
            JButton button = new JButton(Character.toString(c));
            button.setForeground(new Color(10, 32, 62));
            button.addActionListener(this);

            int currentIndex = c - 'A';
            letterButtons[currentIndex] = button;
            buttonPanel.add(letterButtons[currentIndex]);
        }

        //restart button
        JButton restartButton = new JButton("restart");
        restartButton.setForeground(Color.RED);
        restartButton.addActionListener(this);
        buttonPanel.add(restartButton);


        buttonBack = new JButton("quit");
        buttonBack.setForeground(Color.RED);
        buttonBack.addActionListener(this);
        buttonPanel.add(buttonBack);

        buttonAudio = new RoundButton("",20,20);
        buttonAudio.addActionListener(this);
        if (Setting.isPlaying) {
            buttonAudio.setIcon(iconAudioOff);
            buttonAudio.setToolTipText("Nhấn vào đây để bật nhạc");
        } else {
            buttonAudio.setIcon(iconAudioOn);
            buttonAudio.setToolTipText("Nhấn vào đây để tắt nhạc");
        }
        jPanel.add(buttonAudio);

        getContentPane().add(jPanel);
        getContentPane().add(hintLabel);
        getContentPane().add(hangingTree);
        getContentPane().add(buttonPanel);
        getContentPane().add(hiddenWordLabel);
        getContentPane().add(background);


    }

    private void setBoundsHiddenWord() {
        hiddenWordLabel.setBounds(450 - (hiddenWordLabel.getPreferredSize().width)/2, 570,hiddenWordLabel.getPreferredSize().width, hiddenWordLabel.getPreferredSize().height);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
        else if(e.getSource().equals(buttonBack))
        {
            sound.SoundPlay.playSoundNonReset("sound/click.wav");
            this.dispose();
            new ChooseGame();
        }
        else {
            String command = e.getActionCommand();
            if (Objects.equals(command, "restart") || Objects.equals(command, "RESTART")) {
                try {
                    restartHangman();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                resultDialog.setVisible(false);
            } else {
                JButton button = (JButton) e.getSource();
                button.setEnabled(false);

                //check
                if (word.contains(command)) {
                    int i = word.indexOf(command);
                    while (i != -1) {
                        String tmp = hiddenWordLabel.getText().substring(0, i * 2) + command + hiddenWordLabel.getText().substring(i * 2 + 1);
                        hiddenWordLabel.setText(tmp);
                        i = word.indexOf(command, i + 1);

                        setBoundsHiddenWord();
                        if (!hiddenWordLabel.getText().contains("_")) {
                            //win
                            sound.SoundPlay.playSoundNonReset("sound/happy-happy-happy-song.wav");
                            resultLabel.setText("Hooray! You saved Pikachu");
                            resultDialog.setVisible(true);
                        }
                    }
                } else {
                    incorrectGuesses++;
                    Tools.updateImage(hangingTree, "src/main/java/game/Hangman/hangman" + incorrectGuesses + ".png");
                    if(incorrectGuesses >= 2) {
                        hintLabel.setVisible(true);
                    }
                    hangingTree.setBounds(0, 0, hangingTree.getPreferredSize().width, hangingTree.getPreferredSize().height);
                    //lose
                    if (incorrectGuesses >= 6) {
                        resultLabel.setText("Oh no! You couldn't save Pikachu :(");
                        resultDialog.setVisible(true);
                    }
                }
                wordLabel.setText("The word is " + word);
            }
        }

    }

    private void createResultDialog() {
        resultDialog = new JDialog();
        resultDialog.setSize(450, 200);
        resultDialog.getContentPane().setBackground(new Color(10, 32, 62));
        resultDialog.setResizable(false);
        resultDialog.setLocationRelativeTo(this);
        resultDialog.setModal(true);
        resultDialog.setLayout(new GridLayout(3, 1));
        resultDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    restartHangman();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        resultLabel = new JLabel();
        resultLabel.setForeground(Color.white);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        wordLabel = new JLabel();
        wordLabel.setForeground(Color.WHITE);
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton restartButton = new JButton("RESTART");
        restartButton.setFont(TanNimbus.deriveFont(13f));
        restartButton.setForeground(Color.BLACK);
        restartButton.addActionListener(this);



        resultDialog.add(resultLabel);
        resultDialog.add(wordLabel);
        resultDialog.add(restartButton);

    }

    private void restartHangman() throws FileNotFoundException {
        Words words = new Words();
        int randomIndex = words.getRandomIndex();
        word = words.getWord(randomIndex);
        hint = words.getHint(randomIndex);
        hintLabel.setText(hint);
        incorrectGuesses = 0;
        Tools.updateImage(hangingTree, "src/main/java/game/Hangman/hangman0.png");
        hangingTree.setBounds(0, 0, hangingTree.getPreferredSize().width, hangingTree.getPreferredSize().height);
        String hiddenWord = Tools.hideWords(word);
        hiddenWordLabel.setText(hiddenWord);
        setBoundsHiddenWord();
        hintLabel.setVisible(false);

        for (JButton letterButton : letterButtons) {
            letterButton.setEnabled(true);
        }
    }
}
