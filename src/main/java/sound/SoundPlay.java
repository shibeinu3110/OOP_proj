package sound;

import graphicUserInterface.Setting;

import javax.sound.sampled.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import static imageDictionary.imageList.iconAudioOff;
import static imageDictionary.imageList.iconAudioOn;


public class SoundPlay {


    public static Clip clip;
    public static Clip clipBack;


    public static FloatControl volumeControl;
    public static void playSoundReset(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clipBack = clip;
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            System.err.println("Voice Error.");
        }
    }

    public static void playSoundNonReset(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            System.err.println("Voice Error.");
        }
    }

    public static void setVolume(int value) {
        float minVol = volumeControl.getMinimum();
        float maxVol = volumeControl.getMaximum();
        float volume = minVol + ((maxVol - minVol) * value / 100);
        volumeControl.setValue(volume);
    }

}
