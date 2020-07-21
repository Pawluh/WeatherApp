package paczwa.model;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import paczwa.config.OWMConfig;

public class CityWeatherForecast {

    private String cityName;
    OWMConfig config;
    OWM openWeatherMap;


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
        }catch (APIException e){
            System.out.println(e.getMessage());
        }
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
