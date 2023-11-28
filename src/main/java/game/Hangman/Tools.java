package game.Hangman;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Tools {
    public static JLabel loadImage(String filePath) {
        BufferedImage image;
        try {
            File file = new File(filePath);
            InputStream inputStream = new FileInputStream(file);
            image = ImageIO.read(inputStream);
            return new JLabel(new ImageIcon(image));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public static void updateImage(JLabel imageContainer, String filePath){
        BufferedImage image;
        try{
            File file = new File(filePath);
            InputStream inputStream = new FileInputStream(file);
            image = ImageIO.read(inputStream);
            imageContainer.setIcon(new ImageIcon(image));
        }catch(IOException e){
            System.out.println("Error: " + e);
        }
    }

    public static Font createFont(String fontPath) {
        // Check if the font path is empty
        if (fontPath.isEmpty()) {
            System.out.println("Error: Font path is empty.");
            return null;
        }

        // Get the absolute path of the font file
        File fontFile = new File(fontPath);
        String filePath = fontFile.getAbsolutePath();

        // Check for empty spaces in the path (bug fix)
        if (filePath.contains("%20")) {
            filePath = filePath.replaceAll("%20", " ");
        }

        // Create the custom font
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(filePath));
            return customFont;
        } catch (FontFormatException | IOException e) {
            System.out.println("Error creating font: " + e.getMessage());
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