package game.src.Hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class Hangman extends JFrame implements ActionListener {
    private JLabel hangingTree;
    private JLabel background;
    private JLabel hiddenWordLabel;

    private JLabel resultLabel;
    private JLabel wordLabel;
    private String word;

    private JButton[] letterButtons;

    private JDialog resultDialog;
    private int incorrectGuesses;
    private final Font NeueHaas;
    private final Font TanNimbus;


    public Hangman() throws IOException {
        setTitle("Hangman");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        word = new Words().getRandomWord();
        letterButtons = new JButton[26];
        NeueHaas = Tools.createFont("NeueHaasDisplayBold.ttf");
        TanNimbus = Tools.createFont("TANNIMBUS.ttf");
        createResultDialog();
        createUIComponents();
    }


    private void createUIComponents() {
        //background
        background = Tools.loadImage("gameBackground.png");
        background.setBounds(0, 0, 900, 700);

        //hanging tree
        hangingTree = Tools.loadImage("hangman0.png");
        hangingTree.setBounds(0, 0, hangingTree.getPreferredSize().width, hangingTree.getPreferredSize().height);



        //hidden word
        hiddenWordLabel = new JLabel(Tools.hideWords(word));
        hiddenWordLabel.setFont(NeueHaas.deriveFont(34f));
        setBoundsHiddenWord();
        hiddenWordLabel.setForeground(new Color(10, 32, 62));


        //letter buttons
        GridLayout gridLayout = new GridLayout(4, 7);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(450, 164, 420, 240 );
        buttonPanel.setLayout(gridLayout);

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
                Tools.updateImage(hangingTree, "hangman" + incorrectGuesses + ".png");
                hangingTree.setBounds(0, 0, hangingTree.getPreferredSize().width, hangingTree.getPreferredSize().height);
                //lose
                if(incorrectGuesses >= 6) {
                    resultLabel.setText("Oh no! You couldn't save Pikachu :(");
                    resultDialog.setVisible(true);
                }
            }
            wordLabel.setText("The word is " + word);
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
        word = new Words().getRandomWord();
        incorrectGuesses = 0;
        Tools.updateImage(hangingTree, "hangman0.png");
        hangingTree.setBounds(0, 0, hangingTree.getPreferredSize().width, hangingTree.getPreferredSize().height);
        String hiddenWord = Tools.hideWords(word);
        hiddenWordLabel.setText(hiddenWord);
        setBoundsHiddenWord();

        for (JButton letterButton : letterButtons) {
            letterButton.setEnabled(true);
        }
    }
}
