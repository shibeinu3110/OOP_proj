package API;
import model.Word;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SynonymAPI {
        public static ArrayList<String> getSynonym(Word word) throws IOException {
            ArrayList<String> response = new ArrayList<>();
            URL url = new URL("https://languagetools.p.rapidapi.com/all/" + URLEncoder.encode(word.getEngString(), StandardCharsets.UTF_8).replace("+", "%20"));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("x-rapidapi-host", "languagetools.p.rapidapi.com");
            con.setRequestProperty("x-rapidapi-key", "aca2c0c9a3mshdae9b0fd091fb0dp1923ffjsn84863605816e");
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.add(inputLine);
            }
            word.setSynonyms(response);
            in.close();
            return word.getSynonyms();
        }
}

