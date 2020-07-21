package paczwa.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import net.aksingh.owmjapis.core.OWM;
import paczwa.config.OWMConfig;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;


public class CityWeatherForecast {

    private String cityName;
    private String lat;
    private String lon;
    OWMConfig config;
    OWM openWeatherMap;
    JSONObject jsonWithDataAboutCity;
    JSONObject jsonWithCurrentMainWeatherData;

    JSONArray jsonWithDailyWeatherData;

    public CityWeatherForecast(String cityName){
        this.cityName = cityName;
        config = new OWMConfig();
        System.out.println(config.getApiKey());

    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setLat(){
        this.lat = jsonWithDataAboutCity.getJSONObject("coord").get("lat").toString();
    }

    public void setLon(){
        this.lon = jsonWithDataAboutCity.getJSONObject("coord").get("lon").toString();
    }

    public void getWeather() throws IOException {
        jsonWithDataAboutCity = readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q="+this.cityName+"&appid="+config.getApiKey()+"&lang=pl&units=metric");
        jsonWithCurrentMainWeatherData = jsonWithDataAboutCity.getJSONObject("main");

        setLat();
        setLon();
        JSONObject jsonWithWeatherData = readJsonFromUrl("https://api.openweathermap.org/data/2.5/onecall?lat="+this.lat+"&lon="+this.lon+"& exclude=daily&appid="+config.getApiKey()+"&lang=pl&units=metric");
        jsonWithDailyWeatherData = jsonWithWeatherData.getJSONArray("daily");

        System.out.println(jsonWithDailyWeatherData);
    }

    //Reads and returns the JsonObject
    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    //Build a String from the read Json file
    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public Integer getCurrentFeelsLikeTemp(){
        return jsonWithCurrentMainWeatherData.getInt("feels_like");
    }

    public Integer getMaxTempForSpecificDay(Integer daysFromToday){
        return jsonWithDailyWeatherData.getJSONObject(daysFromToday).getJSONObject("temp").getInt("max");
    }

    public Integer getMinTempForSpecificDay(Integer daysFromToday){
        return jsonWithDailyWeatherData.getJSONObject(daysFromToday).getJSONObject("temp").getInt("min");
    }

    public String getPressureForSpecificDay(Integer daysFromToday){
        return jsonWithDailyWeatherData.getJSONObject(daysFromToday).get("pressure").toString();
    }

    public String getHumidityForSpecificDay(Integer daysFromToday){
        return jsonWithDailyWeatherData.getJSONObject(daysFromToday).get("humidity").toString();
    }
    public String getWindForSpecificDay(Integer daysFromToday){
        return jsonWithDailyWeatherData.getJSONObject(daysFromToday).get("wind_speed").toString();
    }
    public String getCloudsForSpecificDay(Integer daysFromToday){
        return jsonWithDailyWeatherData.getJSONObject(daysFromToday).get("clouds").toString();
    }
}
