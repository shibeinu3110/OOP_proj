package connectData;

import dataAccessObject.DAO;
import model.Word;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class WordDAO implements DAO<Word> {
    public static WordDAO getInstance() {
        return new WordDAO();
    }

    @Override
    public ArrayList<Word> getData() {
        ArrayList<Word> listWord = new ArrayList();
        try {
            Connection c = JBDCUtil.getConnection();
            Statement statement = c.createStatement();

            String sql = "SELECT * FROM DictionaryEV";

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String engString = rs.getString(2);
                String vieString = rs.getString(3);
                Word tempWord = new Word(engString, vieString);
                listWord.add(tempWord);
            }

            statement.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listWord;

    }

    @Override
    public ArrayList<String> getRecent() {
        ArrayList<String> listRecent = new ArrayList();
        try {
            Connection c = JBDCUtil.getConnection();
            Statement statement = c.createStatement();

            String sql = "SELECT * FROM Recent";

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String string = rs.getString(1);

                listRecent.add(string);
            }

            statement.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listRecent;

    }

    @Override
    public ArrayList<String> getStarred() {
        ArrayList<String> listStarred = new ArrayList();
        try {
            Connection c = JBDCUtil.getConnection();
            Statement statement = c.createStatement();

            String sql = "SELECT * FROM STAR";

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String string = rs.getString(2);

                listStarred.add(string);
            }

            statement.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listStarred;
    }

    @Override
    public void addWord(String engString, String vieString) {
        String sql = "INSERT INTO DictionaryEV(word,detail) VALUES(?,?)";
        try {
            Connection c = JBDCUtil.getConnection();

            PreparedStatement statement = c.prepareStatement(sql);

            statement.setString(1, engString);
            statement.setString(2, vieString);

            //System.out.println("da them thanh cong ");

            statement.executeUpdate();

            JBDCUtil.closeConnection(c);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addWordRecent(String word) {
        String sql = "INSERT INTO Recent(word) VALUES(?)";
        try {
            Connection c = JBDCUtil.getConnection();

            PreparedStatement statement = c.prepareStatement(sql);

            statement.setString(1, word);


            //System.out.println("da them thanh cong ");

            statement.executeUpdate();

            JBDCUtil.closeConnection(c);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addWordMark(String word) {
        String sql = "INSERT INTO STAR(word) VALUES(?)";
        try {
            Connection c = JBDCUtil.getConnection();

            PreparedStatement statement = c.prepareStatement(sql);

            statement.setString(1, word);


            //System.out.println("da them thanh cong ");

            statement.executeUpdate();

            JBDCUtil.closeConnection(c);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateWord(String word, String detail) {
        Connection con;
        String sql = "UPDATE DictionaryEV SET detail='" + detail + "' WHERE word='" + word + "'";
        try {
            con = JBDCUtil.getConnection();
            java.sql.Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void deleteWord(String word) {
        Connection con;
        String sql = "DELETE FROM DictionaryEV WHERE word='" + word + "'";
        try {
            con = JBDCUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public void deleteWordMark(String word) {
        Connection con;
        String sql = "DELETE FROM STAR WHERE word='" + word +"'";
//		System.out.println(sql);
        try {
            con = JBDCUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteWordRecent(String word) {
        Connection con;
        String sql = "DELETE FROM Recent WHERE word='" + word +"'";
//		System.out.println(sql);
        try {
            con = JBDCUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
