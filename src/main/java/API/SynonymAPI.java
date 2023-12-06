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


    public static ArrayList<String> getSynonym(String text) throws IOException {
        ArrayList<String> response = new ArrayList<>();
        URL url = new URL("https://languagetools.p.rapidapi.com/synonyms/" + URLEncoder.encode(text, StandardCharsets.UTF_8).replace("+", "%20"));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("x-rapidapi-host", "languagetools.p.rapidapi.com");
        con.setRequestProperty("x-rapidapi-key", "990d610252mshcfb719d49d5c783p1a23e1jsnd33c1f591dac");
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.add(inputLine);
        }
        in.close();
        return response;
    }

    public static ArrayList<String> getAntonym(String text) throws IOException {
        ArrayList<String> response = new ArrayList<>();
        URL url = new URL("https://languagetools.p.rapidapi.com/antonyms/" + URLEncoder.encode(text, StandardCharsets.UTF_8).replace("+", "%20"));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("x-rapidapi-host", "languagetools.p.rapidapi.com");
        con.setRequestProperty("x-rapidapi-key", "990d610252mshcfb719d49d5c783p1a23e1jsnd33c1f591dac");
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.add(inputLine);
        }
        in.close();
        return response;
    }

    public static String convert(ArrayList<String> arrayList) {
        StringBuilder ans = new StringBuilder();
        for (String item : arrayList) {
            ans.append(item).append(", ");
        }
        String result = ans.toString();
        result = result.replaceAll("[\"{}\\[\\]]", "");

        result = result.replaceAll("(hypernyms|hyponyms|antonyms)", "\n\n$1");
        result = result.replaceAll("synonyms" , " ❋ Synonyms\n");
        result = result.replaceAll("antonyms" , " ❋ Antonyms\n");
        result = result.replaceAll("hypernyms" , " ❋ Hypernyms\n");
        result = result.replaceAll("hyponyms" , " ❋ Hyponyms\n");
        result = result.replaceAll(",","\n    ⇨");
        result = result.replaceAll(":","\n    ⇨");
        return result.trim();
    }
}

