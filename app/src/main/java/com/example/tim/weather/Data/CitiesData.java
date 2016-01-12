package com.example.tim.weather.Data;

public class CitiesData {
    private String cityName;
    private String temperature;
    private int idPhoto;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public int getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
    }

    public CitiesData(String cityName, String temperature, int idPhoto) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.idPhoto = idPhoto;
    }

}
