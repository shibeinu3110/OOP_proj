package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import connectData.WordDAO;
import graphicUserInterface.DictFinish;
import graphicUserInterface.Setting;
import model.Word;

import javax.swing.*;
import java.util.ArrayList;


public class Main
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {

                                      @Override
                                      public void run() {
                                          DictFinish dictFinish = new DictFinish();
                                      }
                                  }
        );
        //System.out.println(WordDAO.getInstance().getData());
        /*ArrayList<Word> arrayList = WordDAO.getInstance().getData();
        for(int i = 0 ; i < arrayList.size() ; i++)
        {
            System.out.println(arrayList.get(i).getVieString());
        }*/

    }
}