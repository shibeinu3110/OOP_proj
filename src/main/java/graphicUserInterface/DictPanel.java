package graphicUserInterface;

import connectData.JBDCUtil;
import connectData.WordDAO;
import model.DictionaryManagement;
import model.Word;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static imageDictionary.imageList.*;

public class DictPanel extends JPanel implements ListSelectionListener,
        DocumentListener,
        ActionListener,
        KeyListener,
        MouseListener {
    /**
     * variable
     */
    DefaultListModel<String> listModelWord, listModelRecent, listModelMark, resultList;
    JPanel panelLeftTop, panelLeftBot, panelRightTop, panelRightBottom;
    JLabel labelLogo, labelSearchBar, labelDescription;
    JFormattedTextField searchBar;
    JButton buttonSearch, buttonClear, buttonUp, buttonDown, buttonAdd, buttonDelete, buttonEdit, buttonStar;

    JList<String> listWord, listRecent, listMark;
    JScrollPane scrollPaneWord, scrollPaneRecent, scrollPaneMark;
    JTabbedPane tabbedPane;

    JTextPane textPane;
    JScrollPane textScrollPane;
    private ArrayList<Word> listDict;
    int countMouse = 0;


    /**
     * end
     */
    private DictionaryManagement dictionaryManagement;

    /**
     * a
     */
    //create base frame
    public DictPanel() {
        dictionaryManagement = new DictionaryManagement();

        //load list of word
        /**list of vie string*/
        listModelWord = new DefaultListModel<String>();
        /**list of recent*/
        listModelRecent = new DefaultListModel<String>();
        /**list of mark*/
        listModelMark = new DefaultListModel<String>();

        resultList = new DefaultListModel<String>();
        loadData();

        //add method and set base
        addKeyListener(this);
        setLayout(null);
        setBackground(Color.CYAN);

        //create panel left top to contain logo , title ...
        panelLeftTop = new JPanel();
        panelLeftTop.setBounds(0, 0, 290, 110);
        panelLeftTop.setLayout(null);
        panelLeftTop.setBackground(Color.gray);
        //add logo
        this.add(panelLeftTop);
        addPanelLeftTop();

        panelLeftBot = new JPanel();
        panelLeftBot.setBounds(0, 110, 290, 590);
        panelLeftBot.setLayout(null);
        panelLeftBot.setBackground(Color.gray);

        this.add(panelLeftBot);
        addPanelLeftBot();

        panelRightTop = new JPanel();
        panelRightTop.setBounds(290, 0, 610, 110);
        panelRightTop.setLayout(null);
        panelRightTop.setBackground(Color.gray);
        panelRightTop.setOpaque(true);
        panelRightTop.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLUE)
                , BorderFactory.createBevelBorder(BevelBorder.LOWERED)));
        this.add(panelRightTop);
        addPanelRightTop();

        panelRightBottom = new JPanel();
        panelRightBottom.setBounds(290, 110, 610, 590);
        panelRightBottom.setLayout(null);
        panelRightBottom.setBackground(Color.orange);
        this.add(panelRightBottom);
        addPanelRightBottom();
    }

    public void loadData()
    {
        listDict = dictionaryManagement.getDictionary().getData();
        for (int i = 0; i < listDict.size(); i++) {
            listModelWord.addElement(listDict.get(i).getEngString());
        }
        for (int i = 0; i < dictionaryManagement.getDictionary().getRecent().size(); i++) {
            listModelRecent.addElement(dictionaryManagement.getDictionary().getRecent().get(i));
        }
        for (int i = 0; i < dictionaryManagement.getDictionary().getRecent().size(); i++) {
            listModelMark.addElement(dictionaryManagement.getDictionary().getMark().get(i));
        }
    }

    /**
     * add logo
     */
    void addPanelLeftTop() {
        labelLogo = new JLabel();
        ImageIcon imageDictLogo = new ImageIcon(iconLogo.getImage());
        labelLogo.setIcon(imageDictLogo);

        labelLogo.setBounds(0, 0, 290, 110);
        labelLogo.setForeground(Color.LIGHT_GRAY);
        panelLeftTop.add(labelLogo);
    }

    void addPanelLeftBot() {
        //chữ tìm kiếm
        labelSearchBar = new JLabel("-Search-");
        labelSearchBar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        labelSearchBar.setBounds(10, 2, 180, 20);
        panelLeftBot.add(labelSearchBar);

        //search bar
        searchBar = new JFormattedTextField("Nhập từ bạn muốn tìm");
        searchBar.setColumns(10); //hiện tối đa 10 từ
        searchBar.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT | Font.BOLD, 14));
        searchBar.getDocument().addDocumentListener(this);
        searchBar.setBounds(40, 25, 190, 25);
        searchBar.setToolTipText("Nhập từ cần biết");
        searchBar.addKeyListener(this);
        searchBar.addMouseListener(this);
        panelLeftBot.add(searchBar);

        //button search
        buttonSearch = new JButton();
        buttonSearch.setBounds(240, 25, 40, 25);
        buttonSearch.addActionListener(this);
        buttonSearch.setIcon(iconSearch);
        buttonSearch.setToolTipText("Tra từ");
        panelLeftBot.add(buttonSearch);

        //button clear
        buttonClear = new JButton();
        buttonClear.setBounds(10, 25, 25, 25);
        buttonClear.addActionListener(this);
        buttonClear.setIcon(iconClear);
        buttonClear.setToolTipText("Xoá hết");
        panelLeftBot.add(buttonClear);

        //button up down
        buttonUp = new JButton("up");
        //buttonUp.setIcon(iconUp);
        /*buttonUp.setToolTipText("Lên trên");
        buttonUp.setBounds(10, 170, 25, 25);
        buttonUp.setContentAreaFilled(false);
        buttonUp.setBorderPainted(false);
        buttonUp.addActionListener(this);
        panelLeftBot.add(buttonUp);

        // Button Down
        buttonDown = new JButton("down");
        buttonDown.setBounds(10, 200, 25, 25);
        //buttonDown.setIcon(iconDown);
        buttonDown.setToolTipText("Xuống dưới");
        buttonDown.setContentAreaFilled(false);
        buttonDown.setBorderPainted(false);
        buttonDown.addActionListener(this);
        panelLeftBot.add(buttonDown);*/


        /**create prepared list*/

        //new jlist
        listWord = new JList<String>();
        //save all word in listModelWord
        listWord.setModel(listModelWord);
        //set font
        listWord.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
        //only select 1
        listWord.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listWord.addListSelectionListener(this);
        //add scroll pane
        scrollPaneWord = new JScrollPane();
        scrollPaneWord.setViewportView(listWord);
        scrollPaneWord.setHorizontalScrollBar(null); //k hiện scroll bar

        listRecent = new JList<String>(listModelRecent);
        listRecent.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
        listRecent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // single selection
        listRecent.addListSelectionListener(this);
        scrollPaneRecent = new JScrollPane();
        scrollPaneRecent.setViewportView(listRecent);
        scrollPaneRecent.setHorizontalScrollBar(null);

        listMark = new JList<String>(listModelMark);
        listMark.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 14));
        listMark.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // single selection
        listMark.addListSelectionListener(this);
        scrollPaneMark = new JScrollPane();
        scrollPaneMark.setViewportView(listMark);
        scrollPaneMark.setHorizontalScrollBar(null);

        tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(25, 60, 240, 390);
        tabbedPane.setBackground(Color.white);
        tabbedPane.setVisible(true);

        //add tab word
        tabbedPane.addTab("", null, scrollPaneWord, "Toàn bộ");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        //add tab recent
        tabbedPane.addTab("", /*IconItem.iconClock*/null, scrollPaneRecent, "Tìm gần đây");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        //add tab starred
        tabbedPane.addTab("", /*IconItem.iconStarred*/null, scrollPaneMark, "Những từ được yêu thích");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        String[] tabMenu = {"", "Từ điển", "Gần đây", "Yêu thích"};

        //set title
        tabbedPane.setSelectedIndex(0);
        //link tab with title
        tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), tabMenu[tabbedPane.getSelectedIndex() + 1]);

        // Show title for a selected tab
        tabbedPane.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent arg0) {
                tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), tabMenu[tabbedPane.getSelectedIndex() + 1]);
                for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                    if (i != tabbedPane.getSelectedIndex()) {
                        tabbedPane.setTitleAt(i, tabMenu[0]);
                    }
                }
            }
        });

        panelLeftBot.add(tabbedPane);
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);


    }

    void addPanelRightTop() {
        buttonAdd = new JButton("Thêm");
        buttonAdd.setIcon(iconAdd);

        buttonAdd.setBounds(50, 40, 135, 30);
        buttonAdd.setToolTipText("Thêm từ vào từ điển này");
        buttonAdd.setContentAreaFilled(false);
        buttonAdd.addActionListener(this);

        buttonAdd.setBackground(Color.white);
        buttonAdd.setOpaque(true);

        panelRightTop.add(buttonAdd);

        // Button Modify
        buttonEdit = new JButton("Sửa");
        buttonEdit.setIcon(iconFix);

        buttonEdit.setBounds(235, 40, 135, 30);
        buttonEdit.setToolTipText("Sửa từ");
        buttonEdit.setContentAreaFilled(false);
        buttonEdit.addActionListener(this);
        buttonEdit.setEnabled(false);

        buttonEdit.setBackground(Color.white);
        buttonEdit.setOpaque(true);

        panelRightTop.add(buttonEdit);

        // Button Delete
        buttonDelete = new JButton("Xóa");
        buttonDelete.setIcon(iconDelete);

        buttonDelete.setBounds(420, 40, 135, 30);
        buttonDelete.setToolTipText("Xóa từ");
        buttonDelete.setContentAreaFilled(false);
        buttonDelete.addActionListener(this);
        buttonDelete.setEnabled(false);

        buttonDelete.setBackground(Color.white);
        buttonDelete.setOpaque(true);

        panelRightTop.add(buttonDelete);

        // Button Starred
        buttonStar = new JButton();
        //buttonStar.setIcon(IconItem.iconStar);

        buttonStar.setBounds(0, 0, 40, 40);
        buttonStar.setToolTipText("Đánh dấu từ này");
        buttonStar.setContentAreaFilled(false);
        buttonStar.setBorderPainted(false);
        buttonStar.setEnabled(false);
        buttonStar.addActionListener(this);
        panelRightTop.add(buttonStar);
    }

    void addPanelRightBottom() {
        labelDescription = new JLabel("Nghĩa của từ : ");
        labelDescription.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        labelDescription.setBounds(15, 20, 180, 20);
        panelRightBottom.add(labelDescription);

        textPane = new JTextPane();
        Border border = BorderFactory.createLineBorder(Color.BLUE);
        textPane.setBorder(
                BorderFactory.createCompoundBorder(border, BorderFactory.createBevelBorder(BevelBorder.LOWERED)));
        textPane.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));

        textScrollPane = new JScrollPane();
        textScrollPane.setViewportView(textPane);
        textScrollPane.setBounds(10, 50, 570, 480);
        textScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelRightBottom.add(textScrollPane);
    }


    /**
     * find string want to translate
     */
    public void showMeaning(ArrayList<Word> list, String str) {

        for (Word word : list) {
            if (word.getEngString().equals(str)) {
                String vieString = word.getVieString();

                vieString = vieString.replace("<C><F><I><N><Q>", "");
                vieString = vieString.replace("</Q></N></I></F></C>", "");
                vieString = vieString.replace("<br />-", "\n\t-");
                vieString = vieString.replace("<br />=", "\n\t\t=");
                vieString = vieString.replace("<br />*", "\n*");
                vieString = vieString.replace("<br />", "\n");
                textPane.setText(vieString);

            }
        }
    }


    public void deleteWordPanel(String str) {
        if (JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa từ : " + str + " ???",
                "Xóa từ",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            listModelWord.removeElement(str);
            listModelMark.removeElement(str);
            listModelRecent.removeElement(str);
            this.resultList.removeElement(str);
            WordDAO.getInstance().deleteWord(str);
        }
    }


    private void addToListRecent(String str) {
        if (listModelRecent.contains(str)) {
            listModelRecent.removeElement(str);
            WordDAO.getInstance().deleteWordRecent(str);
        } else {
            listModelRecent.addElement(str);
            WordDAO.getInstance().addWordRecent(str);
        }
    }

    private void checkStar(String s) {
        if (listModelMark.contains(s)) {
            buttonStar.setIcon(null);
        } else {
            buttonStar.setIcon(null);
        }
    }


    /**
     * a
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(buttonClear)) {
            this.searchBar.setText(null);
        } else if (e.getSource().equals(buttonAdd)) {
            insertWord();
        } else if (e.getSource().equals(buttonEdit)) {
            updateWord(getSelectedWord());
        } else if (e.getSource().equals(buttonDelete)) {
            deleteWordPanel(getSelectedWord());
        } /*else if (e.getSource().equals(btnUp)) {
            selectUpWord();
        } else if (e.getSource().equals(btnDown)) {
            selectDownWord();
        } else if (e.getSource().equals(btnPronounce)) {
            System.out.println("PA");
            VoiceManager voiceMan;
            Voice voice;

            String speakString = getActiveList().getSelectedValue(); // String word
//			 System.setProperty("mbrola.base", "mbrola");
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            voiceMan = VoiceManager.getInstance();
            voice = voiceMan.getVoice("kevin16");
            voice.allocate();

            // try {
            voice.speak(speakString);
            // } catch (Exception exc) {
            // exc.printStackTrace();
            // }
        }*/ else if (e.getSource().equals(buttonSearch)) {
            submitSearch();
        } else if (e.getSource().equals(buttonStar)) {
            String str = getSelectedWord();
            /*if (btnMark.getIcon().equals(IconItem.iconStarred)) {
                btnMark.setIcon(IconItem.iconStar);
                removeFromListMark(str);
            } else {
                btnMark.setIcon(IconItem.iconStarred);
                addToListMark(str);
            }*/
        }
    }

    private void insertWord() {
        JTextField wordField = new JTextField(15);
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
            // JDBCStatement.sortData();
            this.loadData();
        }
    }

    private void updateWord(String selectedWord) {
        JTextField wordField = new JTextField(15);
        wordField.setText(selectedWord);
        wordField.setEditable(false);
        JTextField detailField = new JTextField(15);
        detailField.setText("Sửa nghĩa của từ tại đây");

        JPanel myPanel = new JPanel(new GridLayout(2, 2));
        myPanel.add(new JLabel("Sửa từ:"));
        myPanel.add(wordField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Sửa nghĩa của từ:"));
        myPanel.add(detailField);

        if (JOptionPane.showConfirmDialog(null, myPanel, "Sửa từ",
                JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            WordDAO.getInstance().updateWord(wordField.getText(), detailField.getText());
            textPane.setText(detailField.getText());
            this.loadData();
        }
    }

    private void submitSearch() {
        System.out.println("DictionaryPanel.submitSearch()");
        getActiveList().setSelectedIndex(0);
        String str = getActiveList().getSelectedValue();
        addToListRecent(str);
        searchBar.setText(str);
        showMeaning(listDict, str);
    }

    private void removeFromListMark(String s) {
        listModelMark.removeElement(s);

        WordDAO.getInstance().deleteWordMark(s);
    }

    private void addToListMark(String s) {
        listModelMark.addElement(s);
        WordDAO.getInstance().addWordMark(s);
    }

    private void searchKeyword(DocumentEvent e) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Open list word when searching
                if (tabbedPane.getSelectedIndex() != 0)
                    tabbedPane.setSelectedIndex(0);
                    resultList = resultSearch(searchBar.getText(), listModelWord);
                if (resultList.size() > 0) {
                    listWord.setModel(resultList);
                    listWord.setSelectedIndex(0);
                    showMeaning(listDict, listWord.getSelectedValue());
                } else {
                    listWord.setModel(resultList);
                    textPane.setText("Not Found");
                }

            }
        });
    }

    public DefaultListModel<String> resultSearch(String keyword, DefaultListModel<String> obj) {
        DefaultListModel<String> rsList = new DefaultListModel<String>();
        for (int i = 0; i < obj.size(); i++) {
            if (obj.get(i).indexOf(keyword) == 0) {
                rsList.addElement(obj.get(i));
            }
        }
        return rsList;
    }

    public void keyEvt(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            submitSearch();
        }
    }






    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        countMouse++;
        if (countMouse == 1) {
            searchBar.setText("");
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        searchKeyword(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        searchKeyword(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        searchKeyword(e);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (getActiveList().getSelectedIndex() == -1) {
                buttonEdit.setEnabled(false);
                buttonDelete.setEnabled(false);
                //btnPronounce.setEnabled(false);
                buttonStar.setEnabled(false);
            } else {
                showMeaning(listDict, getSelectedWord());
                //textPane.setText(getSelectedWord());
                buttonEdit.setEnabled(true);
                buttonDelete.setEnabled(true);
                //btnPronounce.setEnabled(true);
                buttonStar.setEnabled(true);
                //checkMark(getSelectedWord());
            }
        }
    }

    public JList<String> getActiveList() {
        switch (tabbedPane.getSelectedIndex()) {
            case 0:
                return listWord;
            case 1:
                return listRecent;
            case 2:
                return listMark;
            default:
                break;
        }
        return listWord;
    }

    /**
     * get selected value in a list
     */
    public String getSelectedWord() {
        return getActiveList().getSelectedValue();
    }

}
