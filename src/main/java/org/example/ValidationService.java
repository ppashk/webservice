package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Service
public class ValidationService {
    Logger logger = LoggerFactory.getLogger(ValidationService.class);
    public List<String> perform(String url) {
        Words words = parseJson(url);
        return validate(words);
    }

    private Words parseJson(String url) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.fromJson(readUrl(url), Words.class);
    }
    private List<String> validate(Words words) {
        List<String> result = new ArrayList<>();
        try {
        if (words.words.get(0).equals(""))
            return null;
        else result.add(words.words.get(0));
        for (int i = 0; i < words.words.size() - 1; i++) {
            if (words.words.get(i).endsWith(words.words.get(i + 1).substring(0, 1)))
                result.add(words.words.get(i + 1));
            else
                return result;
        }
        } catch (Exception e) {
            logger.error("Error: " + e);
        }
        return result;
    }

    private String readUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            logger.error("Error: " + e);
            return null;
        }
    }
}
