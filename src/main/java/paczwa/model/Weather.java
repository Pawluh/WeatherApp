package paczwa.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Weather {

    private Integer feelsLikeTemp;
    private StringProperty maxTemp;
    private StringProperty minTemp;
    private String pressure;
    private String humidity;
    private String wind;
    private String clouds;
    private StringProperty description;
    private StringProperty date;

    public Weather() {
        this.maxTemp = new SimpleStringProperty();
        this.minTemp = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.date = new SimpleStringProperty();
    }

    public void setFeelsLikeTemp(Integer feelsLikeTemp) {
        this.feelsLikeTemp = feelsLikeTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp.set(maxTemp);
    }

    public void setMinTemp(String minTemp) {
        this.minTemp.set(minTemp);
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public Integer getFeelsLikeTemp() {
        return feelsLikeTemp;
    }

    public String getMaxTemp() {
        return maxTemp.get();
    }

    public StringProperty maxTempProperty() {
        return maxTemp;
    }

    public String getMinTemp() {
        return minTemp.get();
    }

    public StringProperty minTempProperty() {
        return minTemp;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWind() {
        return wind;
    }

    public String getClouds() {
        return clouds;
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }
}
