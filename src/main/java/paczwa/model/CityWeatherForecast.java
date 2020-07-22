package paczwa.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import paczwa.config.OWMConfig;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CityWeatherForecast {

    OWMConfig config;

    private String cityName;
    private String lat;
    private String lon;

    JSONObject jsonWithDataAboutCity;
    JSONObject jsonWithCurrentMainWeatherData;
    JSONArray jsonWithDailyWeatherData;

    private Integer feelsLikeTemp;
    private StringProperty maxTemp;
    private StringProperty minTemp;
    private String pressure;
    private String humidity;
    private String wind;
    private String clouds;

    private StringProperty description;
    private StringProperty date;
    private boolean jsonDataCorrect = true;

    public CityWeatherForecast(String cityName){
        this.cityName = cityName;
        config = new OWMConfig();
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

    public void getWeather(int daysFromToday) throws IOException {
        jsonWithDataAboutCity = readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q="+this.cityName+"&appid="+config.getApiKey()+"&lang=pl&units=metric");
        if(isDataCorrect()){
            jsonDataCorrect = true;
            jsonWithCurrentMainWeatherData = jsonWithDataAboutCity.getJSONObject("main");

            setLat();
            setLon();

            JSONObject jsonWithWeatherData = readJsonFromUrl("https://api.openweathermap.org/data/2.5/onecall?lat="+this.lat+"&lon="+this.lon+"& exclude=daily&appid="+config.getApiKey()+"&lang=pl&units=metric");
            jsonWithDailyWeatherData = jsonWithWeatherData.getJSONArray("daily");
            setWeather(daysFromToday);
        }
        else{
            jsonDataCorrect = false;
        }
    }

    private boolean isDataCorrect() {
        if(jsonWithDataAboutCity.getInt("cod") == 404){
            return false;
        }
        return true;
    }

    public boolean getJsonDataCorrect(){
        return  this.jsonDataCorrect;
    }

    //Reads and returns the JsonObject
    public JSONObject readJsonFromUrl(String url) throws JSONException {

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
    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public void setDescription(int daysFromToday){
        this.description = new SimpleStringProperty(jsonWithDailyWeatherData.getJSONObject(daysFromToday).getJSONArray("weather").getJSONObject(0).getString("description"));
    }

    public StringProperty getDescription(){
        return this.description;
    }

    public void setDate(int daysFromToday){
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, daysFromToday);

            date = calendar.getTime();
            this.date = new SimpleStringProperty(formatter.format(date));
    }

    public StringProperty getDate(){
        return this.date;
    }

    private void setWeather(int daysFromToday){
        setFeelsLikeTemp();
        setMaxTemp(daysFromToday);
        setMinTemp(daysFromToday);
        setPressure(daysFromToday);
        setHumidity(daysFromToday);
        setWind(daysFromToday);
        setClouds(daysFromToday);
        setDescription(daysFromToday);
        setDate(daysFromToday);
    }

    public void setFeelsLikeTemp() {
        this.feelsLikeTemp = jsonWithCurrentMainWeatherData.getInt("feels_like");
    }

    public void setMaxTemp(int daysFromToday) {
        String maxTemp = String.valueOf(jsonWithDailyWeatherData.getJSONObject(daysFromToday).getJSONObject("temp").getInt("max")) +"\u2103";
        this.maxTemp = new SimpleStringProperty (maxTemp);
    }

    public void setMinTemp(int daysFromToday) {
        String minTemp = String.valueOf(jsonWithDailyWeatherData.getJSONObject(daysFromToday).getJSONObject("temp").getInt("min"))+"\u2103";
        this.minTemp = new SimpleStringProperty (minTemp);
    }

    public void setPressure(int daysFromToday) {
        this.pressure = jsonWithDailyWeatherData.getJSONObject(daysFromToday).get("pressure").toString();
    }

    public void setHumidity(int daysFromToday) {
        this.humidity = jsonWithDailyWeatherData.getJSONObject(daysFromToday).get("humidity").toString();
    }

    public void setWind(int daysFromToday) {
        this.wind = jsonWithDailyWeatherData.getJSONObject(daysFromToday).get("wind_speed").toString();
    }

    public void setClouds(int daysFromToday) {
        this.clouds = jsonWithDailyWeatherData.getJSONObject(daysFromToday).get("clouds").toString();
    }

    public Integer getCurrentFeelsLikeTemp(){
        return this.feelsLikeTemp;
    }

    public StringProperty getMaxTempForSpecificDay(){
        return this.maxTemp;
    }

    public StringProperty getMinTempForSpecificDay(){
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
