package model;

import connectData.WordDAO;

import java.util.ArrayList;

public class Dictionary {
    public ArrayList<Word> getData()
    {
        return WordDAO.getInstance().getData();
    }

    public ArrayList<String> getRecent()
    {
        return WordDAO.getInstance().getRecent();
    }
    public ArrayList<String> getMark()
    {
        return WordDAO.getInstance().getStarred();
    }
}