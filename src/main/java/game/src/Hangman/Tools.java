package game.src.Hangman;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Tools {
    // create a JLabel with an image
    public static JLabel loadImage(String resource){
        BufferedImage image;
        try{
            InputStream inputStream = Tools.class.getResourceAsStream(resource);
            image = ImageIO.read(inputStream);
            return new JLabel(new ImageIcon(image));
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        return null;
    }

    public static void updateImage(JLabel imageContainer, String resource){
        BufferedImage image;
        try{
            InputStream inputStream = Tools.class.getResourceAsStream(resource);
            image = ImageIO.read(inputStream);
            imageContainer.setIcon(new ImageIcon(image));
        }catch(IOException e){
            System.out.println("Error: " + e);
        }
    }

    public static Font createFont(String resource){
        // get font file path
        String filePath = Tools.class.getClassLoader().getResource(resource).getPath();

        // check for empty spaces in path (bug)
        if(filePath.contains("%20")){
            filePath = filePath.replaceAll("%20", " ");
        }

        // create font
        try{
            File customFontFile = new File(filePath);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, customFontFile);
            return customFont;
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        return null;
    }

    public static String hideWords(String word){
        String hiddenWord = "";
        for (int i = 0; i < word.length() * 2; i++) {
            if (i % 2 == 0) {
                hiddenWord += "_";
            } else {
                hiddenWord += " ";
            }
        }
        return hiddenWord;
    }
}