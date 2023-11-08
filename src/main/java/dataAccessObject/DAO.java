package dataAccessObject;

import model.Word;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO<Word> {
    /*int insertWord(T t) throws SQLException;
    public int updateWord(T t);
    public int deleteWord(String t);
    public ArrayList<T> selectAll();
    public T selectById(T t);
    public ArrayList<T> selectByCondition(String condition);*/
    public ArrayList<Word> getData();
    public ArrayList<String> getRecent();
    public ArrayList<String> getStarred();
    public void addWord(String word, String detail);
    public void addWordRecent(String word);
    public void addWordMark(String word);
    public void updateWord(String word, String detail);
    public void deleteWord(String word);
    public void deleteWordMark(String word);
    public void deleteWordRecent(String word);

}
