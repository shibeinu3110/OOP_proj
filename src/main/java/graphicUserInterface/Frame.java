package graphicUserInterface;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private final int WIDTH_WINDOW = 900;
    private final int HEIGHT_WINDOW = 700;
    private DictPanel dictionaryPanel;
    //public long end = Calendar.getInstance().getTimeInMillis(); //Timer

    protected JSplitPane split;

    public Frame() {
        super("Dictionary EV ");            //set title
        Container container = getContentPane(); //create container

        setSize(WIDTH_WINDOW, HEIGHT_WINDOW);   //set size for app
        setLocationRelativeTo(null);            //set location is center
        container.setLayout(new BorderLayout());

        //add panel to frame
        dictionaryPanel = new DictPanel();
        container.add(dictionaryPanel, BorderLayout.CENTER);

        //System.out.println("Time: " + (end - Main.begin)); //Timer
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
