package com.here.datamodel;

public class CityInfo {
    private String OutputFormat = "City: %s, Country: %s, Temp(C): %s";

    private String cityName;
    private String countryName;
    private String Temp;

    public CityInfo(String cityName, String countryName, String temp) {
        this.cityName = cityName;
        this.countryName = countryName;
        Temp = temp;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getTemp() {
        return Temp;
    }

    @Override
    public String toString() {
        return String.format(OutputFormat, this.cityName, this.countryName, this.getTemp());
    }
}
