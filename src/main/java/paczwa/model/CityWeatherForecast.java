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

    OWMConfig config;

    private String cityName;
    private String lat;
    private String lon;

    JSONObject jsonWithDataAboutCity;
    JSONObject jsonWithCurrentMainWeatherData;
    JSONArray jsonWithDailyWeatherData;

    private Integer feelsLikeTemp;
    private Integer maxTemp;
    private Integer minTemp;
    private String pressure;
    private String humidity;
    private String wind;
    private String clouds;

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

    public void getWeather(Integer daysFromToday) throws IOException {
        jsonWithDataAboutCity = readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q="+this.cityName+"&appid="+config.getApiKey()+"&lang=pl&units=metric");
        jsonWithCurrentMainWeatherData = jsonWithDataAboutCity.getJSONObject("main");

        setLat();
        setLon();

        JSONObject jsonWithWeatherData = readJsonFromUrl("https://api.openweathermap.org/data/2.5/onecall?lat="+this.lat+"&lon="+this.lon+"& exclude=daily&appid="+config.getApiKey()+"&lang=pl&units=metric");
        jsonWithDailyWeatherData = jsonWithWeatherData.getJSONArray("daily");
        setWeather(daysFromToday);
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

    private void setWeather(Integer daysFromToday){
        setFeelsLikeTemp();
        setMaxTemp(daysFromToday);
        setMinTemp(daysFromToday);
        setPressure(daysFromToday);
        setHumidity(daysFromToday);
        setWind(daysFromToday);
        setClouds(daysFromToday);
    }

    public void setFeelsLikeTemp() {
        this.feelsLikeTemp = jsonWithCurrentMainWeatherData.getInt("feels_like");
    }

    public void setMaxTemp(Integer daysFromToday) {
        this.maxTemp = jsonWithDailyWeatherData.getJSONObject(daysFromToday).getJSONObject("temp").getInt("max");
    }

    public void setMinTemp(Integer daysFromToday) {
        this.minTemp = jsonWithDailyWeatherData.getJSONObject(daysFromToday).getJSONObject("temp").getInt("min");;
    }

    public void setPressure(Integer daysFromToday) {
        this.pressure = jsonWithDailyWeatherData.getJSONObject(daysFromToday).get("pressure").toString();
    }

    public void setHumidity(Integer daysFromToday) {
        this.humidity = jsonWithDailyWeatherData.getJSONObject(daysFromToday).get("humidity").toString();
    }

    public void setWind(Integer daysFromToday) {
        this.wind = jsonWithDailyWeatherData.getJSONObject(daysFromToday).get("wind_speed").toString();
    }

    public void setClouds(Integer daysFromToday) {
        this.clouds = jsonWithDailyWeatherData.getJSONObject(daysFromToday).get("clouds").toString();
    }

    public Integer getCurrentFeelsLikeTemp(){
        return this.feelsLikeTemp;
    }

    public Integer getMaxTempForSpecificDay(){
        return this.maxTemp;
    }

    public Integer getMinTempForSpecificDay(){
        return this.minTemp;
    }

    public String getPressureForSpecificDay(){
        return this.pressure;
    }

    public String getHumidityForSpecificDay(){
        return this.humidity;
    }
    public String getWindForSpecificDay(){
        return this.wind;
    }
    public String getCloudsForSpecificDay(){
        return this.clouds;
    }
}
