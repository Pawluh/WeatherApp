package paczwa.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class JSONFileReader {

    public static JSONObject readJsonFromUrl(String url) throws JSONException {

        String jsonText;
        try {
            InputStream is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            is.close();
            return json;
        } catch (IOException e){
            jsonText = "{\"cod\":\"404\",\"message\":\"city not found\"}";
            JSONObject json = new JSONObject(jsonText);
            return json;
        }
    }

    //Build a String from the read Json file
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
