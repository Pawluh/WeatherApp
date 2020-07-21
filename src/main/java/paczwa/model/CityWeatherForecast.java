package paczwa.model;

import org.json.JSONException;
import org.json.JSONObject;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.DailyWeatherForecast;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import net.aksingh.owmjapis.api.DailyWeatherForecastAPI;
import paczwa.config.OWMConfig;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;


public class CityWeatherForecast {

    private String cityName;
    OWMConfig config;
    OWM openWeatherMap;
    JSONObject jsonWithWeatherData;

    public CityWeatherForecast(String cityName){
        this.cityName = cityName;
        config = new OWMConfig();
        openWeatherMap = new OWM(config.getApiKey());
        openWeatherMap.setUnit(OWM.Unit.METRIC);
        System.out.println(config.getApiKey());

    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
// zastanowaić się jak pozbyć sie try catch w kazdej funkcji zwracającej info o pogodzie
    public void setCurrentWeather(){
        try {
            CurrentWeather currentWeather = openWeatherMap.currentWeatherByCityName(this.cityName);
            System.out.println("Miasto: " + currentWeather.getCityName());
            getWeather();
        }catch (APIException | IOException e){
            System.out.println(e.getMessage());
        }
    }



    public void getWeather() throws IOException {
        jsonWithWeatherData =readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q="+this.cityName+"&appid="+config.getApiKey()+"&lang=eng&units=metric");

        jsonWithWeatherData = jsonWithWeatherData.getJSONObject("main");
        System.out.println("Cisnienie: " + jsonWithWeatherData.get("feels_like").toString() );

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

    public Double getCurrentMaxTemp(){
        try {
            CurrentWeather currentWeather = openWeatherMap.currentWeatherByCityName(this.cityName);
            System.out.println("Temperatura: " + currentWeather.getMainData().getTempMax());
            return currentWeather.getMainData().getTempMax();
        }catch (APIException e){
            System.out.println(e.getMessage());
        }
        return 0.0;
    }

}
