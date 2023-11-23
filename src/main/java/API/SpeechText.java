package API;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class SpeechText {
    public static void playSoundGoogleTranslateEnToVi(String text) {
        try {
            String apiText =
                    "https://translate.google.com/translate_tts?ie=UTF-8&tl="
                            + "en"
                            + "&client=tw-ob&q="
                            + URLEncoder.encode(text, StandardCharsets.UTF_8);
            URL url = new URL(apiText);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            InputStream voice = connect.getInputStream();
            new Player(voice).play();
            connect.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Voice Error");
        }
    }

    public static void playSoundGoogleTranslateViToEn(String text) {
        try {
            String apiText =
                    "https://translate.google.com/translate_tts?ie=UTF-8&tl="
                            + "vi"
                            + "&client=tw-ob&q="
                            + URLEncoder.encode(text, StandardCharsets.UTF_8);
            URL url = new URL(apiText);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            InputStream voice = connect.getInputStream();
            new Player(voice).play();
            connect.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Voice Error");
        }
    }
}
