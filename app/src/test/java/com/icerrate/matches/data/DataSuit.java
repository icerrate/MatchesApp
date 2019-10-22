package com.icerrate.matches.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.icerrate.matches.data.model.Match;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author icerrate
 */
public class DataSuit {

    private static final String FIXTURES_JSON = "fixtures.json";

    private static final String RESULTS_JSON = "results.json";

    public static List<Match> getFixturesData(Class myClass) {
        String jsonText = readFile(myClass, FIXTURES_JSON);
        return parseMatchData(jsonText);
    }

    public static List<Match> getResultsData(Class myClass) {
        String jsonText = readFile(myClass, RESULTS_JSON);
        return parseMatchData(jsonText);
    }

    private static String readFile(Class myClass, String name) {
        String json = "";
        try {
            InputStream inputStream = myClass.getClassLoader().getResourceAsStream(name);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    private static List<Match> parseMatchData(String jsonText) {
        Type listType = new TypeToken<ArrayList<Match>>(){}.getType();
        return new Gson().fromJson(jsonText, listType);
    }
}
