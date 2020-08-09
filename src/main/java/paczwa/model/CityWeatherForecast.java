package paczwa.model;

import org.json.JSONArray;
import org.json.JSONObject;
import paczwa.config.OWMConfig;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CityWeatherForecast {

    private String cityName;
    private String lat;
    private String lon;
    private Weather weather;

    private JSONObject jsonWithDataAboutCity;
    private JSONObject jsonWithCurrentMainWeatherData;
    private JSONArray jsonWithDailyWeatherData;

    public CityWeatherForecast(String cityName)
    {
        this.cityName = cityName;
        weather = new Weather();
    }

    public boolean setWeather(int daysFromToday) {
        try {
            jsonWithDataAboutCity = JSONFileReader.readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q="
                    +this.cityName+"&appid="+OWMConfig.API_KEY+"&lang=pl&units=metric");

            jsonWithCurrentMainWeatherData = jsonWithDataAboutCity.getJSONObject("main");
            setLocation();

            JSONObject jsonWithWeatherData = JSONFileReader.readJsonFromUrl("https://api.openweathermap.org/data/2.5/onecall?lat="
                    +this.lat+"&lon="+this.lon+"& exclude=daily&appid="+OWMConfig.API_KEY+"&lang=pl&units=metric");
            jsonWithDailyWeatherData = jsonWithWeatherData.getJSONArray("daily");

            setWeatherData(daysFromToday);
            return true;
        } catch (IOException e) {
            return false;
        }


    }

    private void setLocation() {
        setLat();
        setLon();
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    private void setLat(){
        this.lat = jsonWithDataAboutCity.getJSONObject("coord").get("lat").toString();
    }
    private void setLon(){
        this.lon = jsonWithDataAboutCity.getJSONObject("coord").get("lon").toString();
    }

    private void setWeatherData(int daysFromToday){
        setDate(daysFromToday);
        setDescription(daysFromToday);
        setFeelsLikeTemp();
        setMaxTemp(daysFromToday);
        setMinTemp(daysFromToday);
        setPressure(daysFromToday);
        setHumidity(daysFromToday);
        setWind(daysFromToday);
        setClouds(daysFromToday);
    }

    private void setDate(int daysFromToday){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, daysFromToday);

        date = calendar.getTime();
        weather.setDate(formatter.format(date));
    }

    private void setDescription(int daysFromToday){
        weather.setDescription(jsonWithDailyWeatherData.getJSONObject(daysFromToday)
                .getJSONArray("weather")
                .getJSONObject(0)
                .getString("description"));
    }

    private void setFeelsLikeTemp() {
        weather.setFeelsLikeTemp(jsonWithCurrentMainWeatherData.getInt("feels_like"));
    }
    private void setMaxTemp(int daysFromToday) {
        weather.setMaxTemp(jsonWithDailyWeatherData.getJSONObject(daysFromToday).getJSONObject("temp").getInt("max") +"\u2103");
    }
    private void setMinTemp(int daysFromToday) {
        weather.setMinTemp(jsonWithDailyWeatherData.getJSONObject(daysFromToday).getJSONObject("temp").getInt("min")+"\u2103");
    }
    private void setPressure(int daysFromToday) {
        weather.setPressure(jsonWithDailyWeatherData.getJSONObject(daysFromToday).get("pressure").toString());
    }
    private void setHumidity(int daysFromToday) {
        weather.setHumidity(jsonWithDailyWeatherData.getJSONObject(daysFromToday).get("humidity").toString());
    }
    private void setWind(int daysFromToday) {
        weather.setWind(jsonWithDailyWeatherData.getJSONObject(daysFromToday).get("wind_speed").toString());
    }
    private void setClouds(int daysFromToday) {
        weather.setClouds(jsonWithDailyWeatherData.getJSONObject(daysFromToday).get("clouds").toString());
    }

    public Weather getWeather() {
        return weather;
    }
}
